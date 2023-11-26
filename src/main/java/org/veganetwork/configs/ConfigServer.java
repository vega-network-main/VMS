package org.veganetwork.configs;

import dev.dejvokep.boostedyaml.YamlDocument;
import dev.dejvokep.boostedyaml.route.Route;

public class ConfigServer {
    public static String server_ip;
    public static int server_port;
    public static int network_compression;
    public static String motd;
    public static int max_players;
    public static String brand_name;
    public ConfigServer(YamlDocument config) {
        InitVar(config);
    }
    private void InitVar(YamlDocument config) {
        server_ip = config.getString(Route.from("server_ip"));
        server_port = config.getInt(Route.from("server_port"));
        network_compression = config.getInt(Route.from("network_compression"));
        motd = config.getString(Route.from("motd"));
        max_players = config.getInt(Route.from("max_players"));
        brand_name = config.getString(Route.from("brand_name"));
    }
}
