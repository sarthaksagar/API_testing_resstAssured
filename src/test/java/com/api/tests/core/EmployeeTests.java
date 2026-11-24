package com.api.tests.core;

import com.api.tests.base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.greaterThan;

/**
 * Test Suite for Employee Management (/api/employees).
 * Covers List retrieval, Count, By ID, and Boundary validation.
 */
public class EmployeeTests extends BaseTest {

    @Test(priority = 1, description = "TC_EMP-GET-001: Get employee list returns 200 OK")
    public void testGetAllEmployees() {
        Response response = employeeClient.getAllEmployees();
        assertStatusCode(response, 200);
        response.then().body("size()", greaterThan(0));
    }

    @Test(priority = 2, description = "TC_EMP-UP-025: Get total employee count returns numeric count")
    public void testGetEmployeeCount() {
        Response response = employeeClient.getEmployeeCount();
        assertStatusCode(response, 200);
        Assert.assertTrue(response.getBody().asString().matches("\\d+"), "Count should be a number!");
    }

    @Test(priority = 3, description = "TC_EMP-GET-001: Verify retrieval of active employee by ID")
    public void testGetEmployeeById() {
        // Use default logged in employee id (1321)
        Response response = employeeClient.getEmployeeById(1321L);
        assertStatusCode(response, 200);
        Assert.assertEquals(response.jsonPath().getLong("id"), 1321L);
        Assert.assertNotNull(response.jsonPath().getString("firstName"));
    }

    @Test(priority = 4, description = "TC_EMP-GET-033: Non-existing employee ID returns 404 Not Found")
    public void testGetNonExistentEmployee() {
        Response response = employeeClient.getEmployeeById(999999L);
        assertStatusCode(response, 404);
    }

    @Test(priority = 5, description = "TC_EMP-GET-034: Employee ID zero boundary validation")
    public void testGetEmployeeWithZeroId() {
        Response response = employeeClient.getEmployeeById(0L);
        Assert.assertTrue(response.getStatusCode() == 400 || response.getStatusCode() == 404,
                "Expected 400 or 404 for ID 0, got: " + response.getStatusCode());
    }
}
