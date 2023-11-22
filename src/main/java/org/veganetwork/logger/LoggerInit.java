package org.veganetwork.logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerInit {
    public static final Logger S4FLogger = LogManager.getLogger("VLOG");
    public LoggerInit() {
        // Example usage
        S4FLogger.info("This is a log message.");
    }
}
