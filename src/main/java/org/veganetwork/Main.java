package org.veganetwork;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.veganetwork.configs.ConfigsInit;
import org.veganetwork.server.ServerStarter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    static{

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm");
        System.setProperty("current.date.time", dateFormat.format(new Date()));
    }
    public static final Logger logger = LoggerFactory.getLogger("main");
    public static void main(String[] args) {
        new ConfigsInit();
        logger.info("Initializing VEGA Core");
        new ServerStarter(logger).ServerStart();
    }
}