package framework.utils;

import framework.logger.MyLogger;

import java.util.Random;

public class RandomGeneration {
    private static final MyLogger LOGGER = new MyLogger();

    public static int randomGeneration(int value) {
        final Random random = new Random();
        int randInt = random.nextInt(value);
        LOGGER.info(randInt + " is random value from " + value);
        return randInt;
    }

    public static String randomString(int value) {
        String SALTCHARS = "ABCDEFGHIJKLwerfgMNOPQRtyuiopasdSTUVWXYZqhjklzxcvbnm";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < value; i++) {
            char c = SALTCHARS.charAt(random.nextInt(SALTCHARS.length()));
            sb.append(c);
        }
        return sb.toString();
    }
}
