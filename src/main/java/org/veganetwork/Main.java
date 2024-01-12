package org.veganetwork;

import net.minestom.server.MinecraftServer;
import org.veganetwork.configs.ConfigsInit;
import org.veganetwork.server.RunServer;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    static{

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm");
        System.setProperty("current.date.time", dateFormat.format(new Date()));
    }
    public static void main(String[] args) {
        new ConfigsInit();
        MinecraftServer.LOGGER.info("");
        MinecraftServer.LOGGER.info("-- DEBUG INFO  --");
        MinecraftServer.LOGGER.info("Java: " + Runtime.version());
        MinecraftServer.LOGGER.info("Supported protocol: %d (%s)".formatted(MinecraftServer.PROTOCOL_VERSION, MinecraftServer.VERSION_NAME));
        MinecraftServer.LOGGER.info("-- END DEBUG   --");
        MinecraftServer.LOGGER.info("");
        MinecraftServer.LOGGER.info("Initializing VCS");
        new RunServer().ServerStart();
    }
}