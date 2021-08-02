package net.edhum.bukkit.api.player;

import com.google.inject.Inject;
import net.edhum.bukkit.api.group.Group;
import net.edhum.bukkit.api.group.filter.GroupFilterFactory;
import net.edhum.bukkit.api.permission.PermissionService;
import net.edhum.bukkit.api.player.newcomer.PlayerNewcomerConfiguration;
import net.edhum.bukkit.api.player.persistence.PlayerBean;
import net.edhum.bukkit.api.player.persistence.PlayerDAO;
import net.edhum.bukkit.api.player.repository.PlayerRepository;
import net.edhum.bukkit.api.player.repository.filter.PlayerFilterFactory;
import net.edhum.common.i18n.Language;
import net.edhum.common.i18n.filter.LanguageFilterFactory;

import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public class PlayerServiceImpl implements PlayerService {

    private final PlayerDAO playerDAO;
    private final PlayerRepository playerRepository;
    private final PlayerFilterFactory playerFilterFactory;
    private final PlayerNewcomerConfiguration playerNewcomerConfiguration;
    private final GroupFilterFactory groupFilterFactory;
    private final LanguageFilterFactory languageFilterFactory;
    private final PermissionService permissionService;

    @Inject
    public PlayerServiceImpl(PlayerDAO playerDAO,
                             PlayerRepository playerRepository,
                             PlayerFilterFactory playerFilterFactory,
                             PlayerNewcomerConfiguration playerNewcomerConfiguration,
                             GroupFilterFactory groupFilterFactory,
                             LanguageFilterFactory languageFilterFactory,
                             PermissionService permissionService) {
        this.playerDAO = playerDAO;
        this.playerRepository = playerRepository;
        this.playerFilterFactory = playerFilterFactory;
        this.playerNewcomerConfiguration = playerNewcomerConfiguration;
        this.groupFilterFactory = groupFilterFactory;
        this.languageFilterFactory = languageFilterFactory;
        this.permissionService = permissionService;
    }

    @Override
    public boolean playerExists(UUID uuid) throws SQLException {
        return this.playerDAO.playerExists(uuid.toString());
    }

    @Override
    public void createPlayer(UUID uuid) throws SQLException {
        if (this.playerExists(uuid)) {
            throw new IllegalArgumentException(String.format("A player with uuid %s exists already", uuid));
        }

        this.playerDAO.insertPlayer(new PlayerBean(
                uuid.toString(),
                null,
                Group.find(this.groupFilterFactory.name(this.playerNewcomerConfiguration.getGroup())).orElseThrow().getId(),
                Language.find(this.languageFilterFactory.tag(this.playerNewcomerConfiguration.getLanguage())).orElseThrow().getId(),
                this.playerNewcomerConfiguration.getMoney()
        ));
    }

    @Override
    public Player loadPlayer(UUID uuid) throws SQLException {
        if (!this.playerExists(uuid)) {
            throw new IllegalArgumentException(String.format("Player with uuid %s doesn't exists", uuid));
        }

        PlayerBean bean = this.playerDAO.selectPlayer(uuid.toString());

        Group group = Group.find(this.groupFilterFactory.id(bean.groupId())).orElseThrow();
        Language language = Language.find(this.languageFilterFactory.id(bean.languageId())).orElseThrow();

        Player player = new PlayerImpl(
                uuid,
                bean.name(),
                group,
                language,
                bean.money(),
                false
        );

        this.permissionService.updatePermissions(player);

        this.playerRepository.add(player);

        return player;
    }

    @Override
    public void unloadPlayer(UUID uuid) throws SQLException {
        Optional<Player> optionalPlayer = this.playerRepository.find(this.playerFilterFactory.uuid(uuid));

        if (optionalPlayer.isEmpty()) {
            throw new IllegalArgumentException(String.format("No player with uuid %s has been found", uuid));
        }

        Player player = optionalPlayer.get();

        this.playerDAO.updatePlayer(new PlayerBean(
                uuid.toString(),
                player.getName(),
                player.getGroup().getId(),
                player.getLanguage().getId(),
                player.getMoney()
        ));

        this.playerRepository.remove(uuid);
    }
}
