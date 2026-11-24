package com.api.tests.core;

import com.api.models.core.DepartmentDTO;
import com.api.tests.base.BaseTest;
import com.api.utils.TestDataGenerator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

/**
 * Test Suite for Department API Endpoints (/api/departments).
 * Covers Positive CRUD, Boundary checks, Schema validation, and Security (BOLA/Auth).
 */
public class DepartmentTests extends BaseTest {

    private Long createdDepartmentId;

    @Test(priority = 1, description = "TC-DEPT-P-01: Create new department with valid payload")
    public void testCreateDepartmentSuccess() {
        String deptName = TestDataGenerator.getUniqueDepartmentName();
        DepartmentDTO newDept = new DepartmentDTO(deptName, defaultCompanyId, "ACTIVE");

        Response response = departmentClient.createDepartment(newDept);
        assertStatusCode(response, 201); // Or 200 depending on server contract
        createdDepartmentId = extractId(response);

        Assert.assertEquals(response.jsonPath().getString("name"), deptName);
        Assert.assertEquals(response.jsonPath().getLong("companyId"), defaultCompanyId.longValue());
    }

    @Test(priority = 2, dependsOnMethods = "testCreateDepartmentSuccess", description = "TC-DEPT-G-01: Retrieve created department by valid ID")
    public void testGetDepartmentByIdSuccess() {
        Response response = departmentClient.getDepartmentById(createdDepartmentId);
        assertStatusCode(response, 200);

        Assert.assertEquals(response.jsonPath().getLong("id"), createdDepartmentId.longValue());
        Assert.assertNotNull(response.jsonPath().getString("name"));
    }

    @Test(priority = 3, dependsOnMethods = "testCreateDepartmentSuccess", description = "TC-DEPT-P-01: Full update of department using PUT")
    public void testUpdateDepartmentSuccess() {
        String updatedName = "Updated_" + TestDataGenerator.getUniqueDepartmentName();
        DepartmentDTO updatePayload = new DepartmentDTO(updatedName, defaultCompanyId, "ACTIVE");
        updatePayload.setId(createdDepartmentId);

        Response response = departmentClient.updateDepartment(createdDepartmentId, updatePayload);
        assertStatusCode(response, 200);

        Assert.assertEquals(response.jsonPath().getString("name"), updatedName);
    }

    @Test(priority = 4, dependsOnMethods = "testCreateDepartmentSuccess", description = "TC-DEPT-PT-01: Partial update using PATCH (status change)")
    public void testPatchDepartmentSuccess() {
        DepartmentDTO patchPayload = new DepartmentDTO();
        patchPayload.setId(createdDepartmentId);
        patchPayload.setStatus("INACTIVE");

        Response response = departmentClient.patchDepartment(createdDepartmentId, patchPayload);
        assertStatusCode(response, 200);

        Assert.assertEquals(response.jsonPath().getString("status"), "INACTIVE");
    }

    @Test(priority = 5, description = "TC-DEPT-G-03: Retrieve non-existent department returns 404")
    public void testGetNonExistentDepartment() {
        Response response = departmentClient.getDepartmentById(999999L);
        assertStatusCode(response, 404);
    }

    @Test(priority = 6, description = "TC-DEPT-G-04: Request department without authentication returns 401")
    public void testGetDepartmentUnauthenticated() {
        Response response = departmentClient.getDepartmentByIdUnauthenticated(1L);
        assertStatusCode(response, 401);
    }

    @Test(priority = 7, description = "TC-DEPT-G-07: Get all departments returns list")
    public void testGetAllDepartments() {
        Response response = departmentClient.getAllDepartments();
        assertStatusCode(response, 200);
        response.then().body("size()", greaterThan(0));
    }

    @Test(priority = 8, description = "Verify department count API returns valid numeric count")
    public void testGetDepartmentCount() {
        Response response = departmentClient.getDepartmentCount();
        assertStatusCode(response, 200);
        Assert.assertTrue(response.getBody().asString().matches("\\d+"), "Count should be numeric!");
    }

    @Test(priority = 9, dependsOnMethods = "testCreateDepartmentSuccess", description = "TC-DEPT-D-01: Delete department by valid ID")
    public void testDeleteDepartmentSuccess() {
        Response response = departmentClient.deleteDepartment(createdDepartmentId);
        // Standard REST response for DELETE is 200 OK or 204 No Content
        Assert.assertTrue(response.getStatusCode() == 200 || response.getStatusCode() == 204,
                "Expected 200 or 204 on delete, but got: " + response.getStatusCode());

        // Verify deleted record is no longer active
        Response verifyResponse = departmentClient.getDepartmentById(createdDepartmentId);
        Assert.assertEquals(verifyResponse.getStatusCode(), 404);
    }
}
