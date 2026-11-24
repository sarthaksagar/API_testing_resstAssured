package com.api.tests.attendance;

import com.api.tests.base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;

/**
 * Test Suite for Attendance Resource (/api/attendances).
 * Covers List, Count, By ID, and Negative Boundary validation.
 */
public class AttendanceTests extends BaseTest {

    @Test(priority = 1, description = "TC_ATT_GET_019: Get all attendances returns 200 OK")
    public void testGetAllAttendances() {
        Response response = attendanceClient.getAllAttendances();
        assertStatusCode(response, 200);
        response.then().body("size()", greaterThanOrEqualTo(0));
    }

    @Test(priority = 2, description = "TC_ATT_COUNT_001: Get total attendance count")
    public void testGetAttendanceCount() {
        Response response = attendanceClient.getAttendanceCount();
        assertStatusCode(response, 200);
        Assert.assertTrue(response.getBody().asString().matches("\\d+"), "Count must be numeric!");
    }

    @Test(priority = 3, description = "TC_ATT_GET_003: Non-existing attendance ID returns 404")
    public void testGetNonExistentAttendance() {
        Response response = attendanceClient.getAttendanceById(999999L);
        assertStatusCode(response, 404);
    }

    @Test(priority = 4, description = "TC_ATT_GET_004: Negative attendance ID boundary check")
    public void testGetAttendanceWithNegativeId() {
        Response response = attendanceClient.getAttendanceById(-1L);
        Assert.assertTrue(response.getStatusCode() == 400 || response.getStatusCode() == 404,
                "Expected 400 or 404 for negative ID, got: " + response.getStatusCode());
    }
}
