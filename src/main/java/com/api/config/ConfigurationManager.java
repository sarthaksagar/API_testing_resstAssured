package com.api.config;

import java.io.InputStream;
import java.util.Properties;

/**
 * Thread-safe configuration manager to read test environment properties.
 * Supports JVM argument overrides (-Dbase.uri=...).
 */
public class ConfigurationManager {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigurationManager.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null) {
                properties.load(input);
            } else {
                System.err.println("Warning: config.properties not found in classpath.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key, String defaultValue) {
        String sysProp = System.getProperty(key);
        if (sysProp != null && !sysProp.trim().isEmpty()) {
            return sysProp;
        }
        return properties.getProperty(key, defaultValue);
    }

    public static String getBaseUri() {
        return getProperty("base.uri", "https://qa.techvgi.com/smarthr-qa");
    }

    public static String getAuthUsername() {
        return getProperty("auth.username", "omkark11@gmail.com");
    }

    public static String getAuthPassword() {
        return getProperty("auth.password", "admin");
    }

    public static Long getDefaultCompanyId() {
        return Long.parseLong(getProperty("default.company.id", "33"));
    }

    public static Long getDefaultEmployeeId() {
        return Long.parseLong(getProperty("default.employee.id", "1321"));
    }

    public static Long getCrossTenantCompanyId() {
        return Long.parseLong(getProperty("cross.tenant.company.id", "999999"));
    }

    public static boolean isLoggingEnabled() {
        return Boolean.parseBoolean(getProperty("log.all.requests", "true"));
    }
}
