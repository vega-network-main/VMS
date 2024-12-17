package org.veganetwork.configs;

import com.electronwill.nightconfig.core.file.FileConfig;

public class ConfigServer {
    public static int server_port;
    public static int network_compression;
    public static int max_players;
    public static String motd;
    public static String server_ip;
    public static String brand_name;
    public static String velocity_secret;
    public static String server_mode;
    public ConfigServer(FileConfig config) {
        config.load();
        InitVar(config);
    }
    private void InitVar(FileConfig config) {
        server_ip = config.get("server_ip");
        server_port = config.getInt("server_port");
        network_compression = config.getInt("network_compression");
        motd = config.get("motd");
        max_players = config.getInt("max_players");
        brand_name = config.get("brand_name");
        velocity_secret = config.get("velocity_secret");
        server_mode = config.get("server_mode");
    }
}
