package com.api.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;

/**
 * Utility for generating unique, dynamic, and realistic test data.
 * Prevents database collisions on unique constraints (emails, codes, names).
 */
public class TestDataGenerator {

    private static final Random random = new Random();
    private static final DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String getUniqueId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public static String getUniqueEmail() {
        return "qa_test_" + System.currentTimeMillis() + "_" + random.nextInt(1000) + "@testcompany.com";
    }

    public static String getUniqueDepartmentName() {
        return "QA_Dept_" + getUniqueId();
    }

    public static String getUniqueDesignationName() {
        return "QA_Role_" + getUniqueId();
    }

    public static String getUniqueCompanyName() {
        return "QA_Company_" + getUniqueId() + " Ltd";
    }

    public static String getUniqueAssetTypeName() {
        return "Asset_" + getUniqueId();
    }

    public static String getUniqueLeaveTypeName() {
        return "Leave_" + getUniqueId();
    }

    public static String getMobileNumber() {
        return "9" + (100000000 + random.nextInt(900000000));
    }

    public static String getCurrentDate() {
        return LocalDate.now().format(ISO_FORMATTER);
    }

    public static String getPastDate(int daysAgo) {
        return LocalDate.now().minusDays(daysAgo).format(ISO_FORMATTER);
    }

    public static String getFutureDate(int daysAhead) {
        return LocalDate.now().plusDays(daysAhead).format(ISO_FORMATTER);
    }
}
