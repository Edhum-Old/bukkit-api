package net.edhum.bukkit.api.player.persistence;

import com.google.inject.Inject;
import net.edhum.common.persistence.sql.SQLAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerDAO {

    private final SQLAccess sqlAccess;

    @Inject
    public PlayerDAO(SQLAccess sqlAccess) {
        this.sqlAccess = sqlAccess;
    }

    public boolean playerExists(String uuid) throws SQLException {
        try (Connection connection = this.sqlAccess.getResource()) {
            String sql = "SELECT 1 FROM players WHERE uuid = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, uuid);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public void insertPlayer(PlayerBean bean) throws SQLException {
        try (Connection connection = this.sqlAccess.getResource()) {
            String sql = "INSERT INTO players(uuid, name, group_id, language_id, money) VALUE (?, ?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, bean.uuid());
            ps.setString(2, bean.name());
            ps.setInt(3, bean.groupId());
            ps.setInt(4, bean.languageId());
            ps.setLong(5, bean.money());

            ps.executeUpdate();
        }
    }

    public PlayerBean selectPlayer(String uuid) throws SQLException {
        try (Connection connection = this.sqlAccess.getResource()) {
            String sql = "SELECT * FROM players WHERE uuid = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, uuid);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    throw new SQLException(); // TODO
                }

                String name = rs.getString("name");
                int groupId = rs.getInt("group_id");
                int languageId = rs.getInt("language_id");
                int money = rs.getInt("money");

                return new PlayerBean(
                        uuid,
                        name,
                        groupId,
                        languageId,
                        money
                );
            }
        }
    }

    public void updatePlayer(PlayerBean bean) throws SQLException {
        try (Connection connection = this.sqlAccess.getResource()) {
            String sql = "UPDATE players SET name = ?, group_id = ?, language_id = ?, money = ? WHERE uuid = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, bean.name());
            ps.setInt(2, bean.groupId());
            ps.setInt(3, bean.languageId());
            ps.setLong(4, bean.money());
            ps.setString(5, bean.uuid());

            ps.executeUpdate();
        }
    }
}
