package com.api.tests.core;

import com.api.models.core.DepartmentDTO;
import com.api.models.core.DesignationDTO;
import com.api.tests.base.BaseTest;
import com.api.utils.TestDataGenerator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.greaterThan;

/**
 * Test Suite for Designation API Endpoints (/api/designations).
 * Covers Creation linked to Department, Retrieval, Updates, and Deletion.
 */
public class DesignationTests extends BaseTest {

    private Long parentDepartmentId;
    private Long createdDesignationId;

    @BeforeClass
    public void setupParentDepartment() {
        // Create an active department to link designations to
        DepartmentDTO dept = new DepartmentDTO(TestDataGenerator.getUniqueDepartmentName(), defaultCompanyId, "ACTIVE");
        Response response = departmentClient.createDepartment(dept);
        parentDepartmentId = extractId(response);
    }

    @Test(priority = 1, description = "TC_DES-018: Create designation with valid data")
    public void testCreateDesignationSuccess() {
        String roleName = TestDataGenerator.getUniqueDesignationName();
        DesignationDTO designation = new DesignationDTO(roleName, parentDepartmentId, defaultCompanyId, "ACTIVE");

        Response response = designationClient.createDesignation(designation);
        Assert.assertTrue(response.getStatusCode() == 201 || response.getStatusCode() == 200,
                "Expected 201 or 200 on creation, got: " + response.getStatusCode());

        createdDesignationId = extractId(response);
        Assert.assertEquals(response.jsonPath().getString("name"), roleName);
        Assert.assertEquals(response.jsonPath().getLong("departmentId"), parentDepartmentId.longValue());
    }

    @Test(priority = 2, dependsOnMethods = "testCreateDesignationSuccess", description = "TC_DES-001: Get designation by valid ID")
    public void testGetDesignationByIdSuccess() {
        Response response = designationClient.getDesignationById(createdDesignationId);
        assertStatusCode(response, 200);

        Assert.assertEquals(response.jsonPath().getLong("id"), createdDesignationId.longValue());
        Assert.assertNotNull(response.jsonPath().getString("name"));
    }

    @Test(priority = 3, dependsOnMethods = "testCreateDesignationSuccess", description = "TC_DES-041: Update designation details")
    public void testUpdateDesignationSuccess() {
        String updatedName = "Lead_" + TestDataGenerator.getUniqueDesignationName();
        DesignationDTO updateDTO = new DesignationDTO(updatedName, parentDepartmentId, defaultCompanyId, "ACTIVE");
        updateDTO.setId(createdDesignationId);

        Response response = designationClient.updateDesignation(createdDesignationId, updateDTO);
        assertStatusCode(response, 200);

        Assert.assertEquals(response.jsonPath().getString("name"), updatedName);
    }

    @Test(priority = 4, description = "TC_DES-009: Get designation with non-existing ID returns 404")
    public void testGetNonExistentDesignation() {
        Response response = designationClient.getDesignationById(999999L);
        assertStatusCode(response, 404);
    }

    @Test(priority = 5, description = "TC_DES-075: Retrieve all designations")
    public void testGetAllDesignations() {
        Response response = designationClient.getAllDesignations();
        assertStatusCode(response, 200);
        response.then().body("size()", greaterThan(0));
    }

    @Test(priority = 6, dependsOnMethods = "testCreateDesignationSuccess", description = "TC_DES-066: Delete designation by valid ID")
    public void testDeleteDesignationSuccess() {
        Response response = designationClient.deleteDesignation(createdDesignationId);
        Assert.assertTrue(response.getStatusCode() == 200 || response.getStatusCode() == 204);

        Response verify = designationClient.getDesignationById(createdDesignationId);
        Assert.assertEquals(verify.getStatusCode(), 404);
    }

    @AfterClass(alwaysRun = true)
    public void cleanup() {
        if (parentDepartmentId != null) {
            departmentClient.deleteDepartment(parentDepartmentId);
        }
    }
}
