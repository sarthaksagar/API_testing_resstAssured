package com.api.tests.payroll;

import com.api.tests.base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;

/**
 * Test Suite for Payroll & Compensation (/api/salaries, /api/tax-slabs, /api/pay-slips).
 */
public class PayrollTests extends BaseTest {

    @Test(priority = 1, description = "TC_SAL-020: Get all salaries list returns 200 OK")
    public void testGetAllSalaries() {
        Response response = payrollClient.getAllSalaries();
        assertStatusCode(response, 200);
        response.then().body("size()", greaterThanOrEqualTo(0));
    }

    @Test(priority = 2, description = "TC_SAL-035: Get total salary count")
    public void testGetSalaryCount() {
        Response response = payrollClient.getSalaryCount();
        assertStatusCode(response, 200);
        Assert.assertTrue(response.getBody().asString().matches("\\d+"));
    }

    @Test(priority = 3, description = "TC_TS-011: Get all tax slabs")
    public void testGetAllTaxSlabs() {
        Response response = payrollClient.getAllTaxSlabs();
        assertStatusCode(response, 200);
        response.then().body("size()", greaterThanOrEqualTo(0));
    }

    @Test(priority = 4, description = "TC_TS-075: Get tax slab count")
    public void testGetTaxSlabCount() {
        Response response = payrollClient.getTaxSlabCount();
        assertStatusCode(response, 200);
        Assert.assertTrue(response.getBody().asString().matches("\\d+"));
    }

    @Test(priority = 5, description = "PS-API-007: Get all pay slips")
    public void testGetAllPaySlips() {
        Response response = payrollClient.getAllPaySlips();
        assertStatusCode(response, 200);
        response.then().body("size()", greaterThanOrEqualTo(0));
    }

    @Test(priority = 6, description = "PS-API-037: Get pay slip count")
    public void testGetPaySlipCount() {
        Response response = payrollClient.getPaySlipCount();
        assertStatusCode(response, 200);
        Assert.assertTrue(response.getBody().asString().matches("\\d+"));
    }

    @Test(priority = 7, description = "TC_SAL-SET-043: Get all salary settings")
    public void testGetAllSalarySettings() {
        Response response = payrollClient.getAllSalarySettings();
        assertStatusCode(response, 200);
    }
}
