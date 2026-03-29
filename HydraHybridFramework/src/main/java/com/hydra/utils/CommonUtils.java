package com.hydra.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Random;

/**
 * Common Utility Methods
 */
public class CommonUtils {
    private static final Logger logger = LogManager.getLogger(CommonUtils.class);

    /**
     * Wait for specified seconds
     */
    public static void waitForSeconds(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
            logger.info("Waited for " + seconds + " seconds");
        } catch (InterruptedException e) {
            logger.error("Wait interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Wait for milliseconds
     */
    public static void waitForMilliseconds(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
            logger.info("Waited for " + milliseconds + " milliseconds");
        } catch (InterruptedException e) {
            logger.error("Wait interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Generate random number
     */
    public static int generateRandomNumber(int min, int max) {
        Random random = new Random();
        int randomNumber = random.nextInt((max - min) + 1) + min;
        logger.info("Generated random number: " + randomNumber);
        return randomNumber;
    }

    /**
     * Generate random string
     */
    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }

        String randomString = sb.toString();
        logger.info("Generated random string: " + randomString);
        return randomString;
    }

    /**
     * Generate random email
     */
    public static String generateRandomEmail() {
        String randomEmail = generateRandomString(10) + "@testmail.com";
        logger.info("Generated random email: " + randomEmail);
        return randomEmail;
    }

    /**
     * Clear console
     */
    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
            logger.info("Console cleared");
        } catch (Exception e) {
            logger.warn("Failed to clear console: " + e.getMessage());
        }
    }

    /**
     * Print message with border
     */
    public static void printBorder(String message) {
        System.out.println("\n===============================================");
        System.out.println(message);
        System.out.println("===============================================\n");
    }

    /**
     * Encode special characters for HTML
     */
    public static String encodeSpecialCharacters(String text) {
        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }

    /**
     * Verify two strings are equal
     */
    public static boolean compareStrings(String actual, String expected) {
        boolean result = actual.equals(expected);
        if (result) {
            logger.info("String comparison successful. Expected: " + expected + ", Actual: " + actual);
        } else {
            logger.warn("String comparison failed. Expected: " + expected + ", Actual: " + actual);
        }
        return result;
    }

    /**
     * Verify two strings are equal (case-insensitive)
     */
    public static boolean compareStringsIgnoreCase(String actual, String expected) {
        boolean result = actual.equalsIgnoreCase(expected);
        if (result) {
            logger.info("String comparison (ignore case) successful. Expected: " + expected + ", Actual: " + actual);
        } else {
            logger.warn("String comparison (ignore case) failed. Expected: " + expected + ", Actual: " + actual);
        }
        return result;
    }

    /**
     * Verify string contains substring
     */
    public static boolean verifyStringContains(String actual, String substring) {
        boolean result = actual.contains(substring);
        if (result) {
            logger.info("String contains verification successful. Text: " + actual + ", Substring: " + substring);
        } else {
            logger.warn("String contains verification failed. Text: " + actual + ", Substring: " + substring);
        }
        return result;
    }
}
