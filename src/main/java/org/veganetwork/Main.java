package org.veganetwork;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.veganetwork.configs.ConfigsInit;
import org.veganetwork.server.ServerStarter;

public class Main {
    public static final Logger logger = LoggerFactory.getLogger("main");
    public static void main(String[] args) {
        new ConfigsInit();
        logger.info("Initializing VEGA Core");
        new ServerStarter(logger).ServerStart();
    }
}