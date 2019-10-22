package framework.logger;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;

public class MyLogger {

    private static final org.apache.log4j.Logger LOGGER = LogManager.getLogger(MyLogger.class);

    public void info(String message) {
        LOGGER.log(Level.INFO, message);
    }

    public void info(String message, Object o) {
        LOGGER.log(Level.INFO, message + o);
    }

    public void error(String message, Exception e) {
        LOGGER.log(Level.ERROR, message + e);
    }

    public void step(String step, String message) {
        LOGGER.log(Level.INFO, String.format("<----------- Step %s - ", step) + message + " ->");
    }
}
