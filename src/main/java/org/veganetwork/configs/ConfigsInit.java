package org.veganetwork.configs;

import dev.dejvokep.boostedyaml.YamlDocument;
import lombok.Getter;
import net.minestom.server.MinecraftServer;

import java.io.File;
import java.nio.file.Path;
import java.util.Objects;

public class ConfigsInit {
    @Getter
    public static YamlDocument server_config;
    /**
     * ConfigsInit - Initializing configs for further use in server/logger package
     */
    public ConfigsInit() {
        Path dataDir = Path.of(System.getProperty("user.dir") + "/config");
        SConfig(dataDir);
        InitAll();
    }
    private void SConfig(Path dataDirectory) {
        try {
            boolean configExists = new File(dataDirectory.toString(), "server-config.yml").isFile();

            File configFile = new File(dataDirectory.toFile(), "server-config.yml");
            if (!configExists) {
                // Generate the config file if it doesn't exist
                server_config = YamlDocument.create(new File(dataDirectory.toFile(), "server-config.yml"), Objects.requireNonNull(ConfigsInit.class.getResourceAsStream("/server-config.yml")));
                server_config.update();
                server_config.save();
            } else {
                // Load the config file if it exists
                server_config = YamlDocument.create(configFile);
                server_config.update();
            }

        } catch (Exception e) {
            MinecraftServer.LOGGER.error(String.valueOf(e));
        }
    }
    public void InitAll() {
        new ConfigServer(server_config);
    }
}
