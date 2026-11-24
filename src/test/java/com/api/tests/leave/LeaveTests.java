package com.api.tests.leave;

import com.api.models.leave.LeaveTypeDTO;
import com.api.tests.base.BaseTest;
import com.api.utils.TestDataGenerator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;

/**
 * Test Suite for Leave Management (/api/leave-types).
 * Covers CRUD, Count, and Boundary validation.
 */
public class LeaveTests extends BaseTest {

    private Long createdLeaveTypeId;

    @Test(priority = 1, description = "TC_LT-POST-001: Create new leave type with valid data")
    public void testCreateLeaveTypeSuccess() {
        String leaveTypeName = TestDataGenerator.getUniqueLeaveTypeName();
        LeaveTypeDTO dto = new LeaveTypeDTO(leaveTypeName, 12, defaultCompanyId, "ACTIVE");
        dto.setLeaveAbbreviation("LT");
        dto.setHasCarryForward(true);

        Response response = leaveClient.createLeaveType(dto);
        Assert.assertTrue(response.getStatusCode() == 201 || response.getStatusCode() == 200,
                "Expected 201 or 200, got: " + response.getStatusCode());

        createdLeaveTypeId = extractId(response);
        Assert.assertEquals(response.jsonPath().getString("leaveType"), leaveTypeName);
    }

    @Test(priority = 2, dependsOnMethods = "testCreateLeaveTypeSuccess", description = "TC_LT-GET-001: Get leave type by valid ID")
    public void testGetLeaveTypeByIdSuccess() {
        Response response = leaveClient.getLeaveTypeById(createdLeaveTypeId);
        assertStatusCode(response, 200);
        Assert.assertEquals(response.jsonPath().getLong("id"), createdLeaveTypeId.longValue());
    }

    @Test(priority = 3, description = "TC_LT-LIST-001: Get all leave types")
    public void testGetAllLeaveTypes() {
        Response response = leaveClient.getAllLeaveTypes();
        assertStatusCode(response, 200);
        response.then().body("size()", greaterThanOrEqualTo(0));
    }

    @Test(priority = 4, description = "TC_LT-COUNT-001: Get total leave type count")
    public void testGetLeaveTypeCount() {
        Response response = leaveClient.getLeaveTypeCount();
        assertStatusCode(response, 200);
        Assert.assertTrue(response.getBody().asString().matches("\\d+"));
    }

    @Test(priority = 5, description = "TC_LT-GET-002: Non-existing leave type returns 404")
    public void testGetNonExistentLeaveType() {
        Response response = leaveClient.getLeaveTypeById(999999L);
        assertStatusCode(response, 404);
    }

    @Test(priority = 6, dependsOnMethods = "testCreateLeaveTypeSuccess", description = "TC_LT-DELETE-001: Delete leave type by valid ID")
    public void testDeleteLeaveTypeSuccess() {
        Response response = leaveClient.deleteLeaveType(createdLeaveTypeId);
        Assert.assertTrue(response.getStatusCode() == 200 || response.getStatusCode() == 204);

        Response verify = leaveClient.getLeaveTypeById(createdLeaveTypeId);
        Assert.assertEquals(verify.getStatusCode(), 404);
    }
}
