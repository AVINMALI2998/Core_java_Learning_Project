package com.hydra.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;

/**
 * JSON Utility - For handling JSON files and data
 */
public class JsonUtils {
    private static final Logger logger = LogManager.getLogger(JsonUtils.class);
    private static final Gson gson = new Gson();

    /**
     * Read JSON file and convert to JsonObject
     */
    public static JsonObject readJsonFile(String filePath) {
        try {
            FileReader reader = new FileReader(filePath);
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            reader.close();
            logger.info("JSON file read successfully: " + filePath);
            return jsonObject;
        } catch (Exception e) {
            logger.error("Failed to read JSON file: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Write JSON object to file
     */
    public static void writeJsonFile(String filePath, JsonObject jsonObject) {
        try {
            FileWriter writer = new FileWriter(filePath);
            gson.toJson(jsonObject, writer);
            writer.close();
            logger.info("JSON file written successfully: " + filePath);
        } catch (Exception e) {
            logger.error("Failed to write JSON file: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Convert object to JSON string
     */
    public static String toJsonString(Object object) {
        try {
            String jsonString = gson.toJson(object);
            logger.info("Object converted to JSON string");
            return jsonString;
        } catch (Exception e) {
            logger.error("Failed to convert object to JSON: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Convert JSON string to object
     */
    public static <T> T fromJsonString(String jsonString, Class<T> classType) {
        try {
            T object = gson.fromJson(jsonString, classType);
            logger.info("JSON string converted to object");
            return object;
        } catch (Exception e) {
            logger.error("Failed to convert JSON string to object: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Get value from JSON object by key
     */
    public static String getJsonValue(JsonObject jsonObject, String key) {
        try {
            String value = jsonObject.get(key).getAsString();
            logger.info("Retrieved value from JSON: " + key + " = " + value);
            return value;
        } catch (Exception e) {
            logger.warn("Failed to get JSON value for key: " + key);
            return null;
        }
    }
}
