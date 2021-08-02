package net.edhum.bukkit.api.permission.group;

import com.google.inject.AbstractModule;
import com.google.inject.name.Named;
import com.google.inject.throwingproviders.CheckedProvides;
import com.google.inject.throwingproviders.ThrowingProviderBinder;
import net.edhum.bukkit.api.group.Group;
import net.edhum.bukkit.api.group.filter.GroupFilterFactory;
import net.edhum.bukkit.api.permission.group.map.GroupPermissionMap;
import net.edhum.bukkit.api.permission.group.map.GroupPermissionMapProvider;
import net.edhum.common.plugin.annotations.PluginDataFolder;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GroupPermissionModule extends AbstractModule {

    private static final String GROUP_PERMISSIONS_FILE_NAME = "group.yml";

    @Override
    protected void configure() {
        install(ThrowingProviderBinder.forModule(this));

        bind(GroupPermissionService.class).to(GroupPermissionServiceImpl.class);
    }

    @SuppressWarnings("BindingAnnotationWithoutInject")
    @CheckedProvides(GroupPermissionMapProvider.class)
    public GroupPermissionMap provideGroupPermissionMap(@PluginDataFolder Path dataFolder, @Named("PermissionFolder") String permissionFolder, GroupFilterFactory groupFilterFactory) throws IOException {
        Path filePath = dataFolder.resolve(permissionFolder).resolve(GROUP_PERMISSIONS_FILE_NAME);

        try (InputStream in = Files.newInputStream(filePath)) {
            Yaml yaml = new Yaml();
            Map<String, Collection<String>> content = yaml.load(in);

            Map<Group, Collection<String>> permissionMap = new HashMap<>();

            if (content != null) {
                for (Map.Entry<String, Collection<String>> entry : content.entrySet()) {
                    String groupName = entry.getKey();
                    Collection<String> permissions = entry.getValue();

                    permissionMap.put(Group.find(groupFilterFactory.name(groupName)).orElseThrow(), permissions);
                }
            }

            return new GroupPermissionMap(permissionMap);
        }
    }
}
