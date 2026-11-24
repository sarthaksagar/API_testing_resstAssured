package com.api.tests.security;

import com.api.config.ConfigurationManager;
import com.api.models.core.DepartmentDTO;
import com.api.tests.base.BaseTest;
import com.api.utils.TestDataGenerator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Security Suite: Multi-Tenant Data Isolation (BOLA / Broken Object Level Authorization).
 * Verifies that User/Company A cannot view, modify, or delete Company B resources.
 */
public class TenantIsolationTests extends BaseTest {

    private final Long foreignCompanyId = ConfigurationManager.getCrossTenantCompanyId();

    @Test(description = "Verify department creation rejects cross-tenant company ID or enforces caller company")
    public void testCrossCompanyDepartmentCreation() {
        DepartmentDTO spoofedDept = new DepartmentDTO(
                TestDataGenerator.getUniqueDepartmentName(),
                foreignCompanyId,
                "ACTIVE"
        );

        Response response = departmentClient.createDepartment(spoofedDept);
        
        // Either the server rejects foreign company ID (400/403), or auto-overrides with caller's companyId
        if (response.getStatusCode() == 200 || response.getStatusCode() == 201) {
            Long savedCompanyId = response.jsonPath().getLong("companyId");
            Assert.assertEquals(savedCompanyId, defaultCompanyId,
                    "Security Violation! Server allowed assigning foreign companyId: " + foreignCompanyId);
            // Clean up
            departmentClient.deleteDepartment(extractId(response));
        } else {
            Assert.assertTrue(response.getStatusCode() == 400 || response.getStatusCode() == 403,
                    "Expected 400 or 403 when creating cross-company resource, got: " + response.getStatusCode());
        }
    }

    @Test(description = "Verify unauthenticated request cannot access any company data")
    public void testUnauthenticatedAccessRejected() {
        Response response = departmentClient.getDepartmentByIdUnauthenticated(1L);
        Assert.assertEquals(response.getStatusCode(), 401,
                "Security Failure: Endpoint should return 401 Unauthorized!");
    }

    @Test(description = "Verify request with completely invalid token returns 401")
    public void testInvalidTokenRejected() {
        Response response = departmentClient.requestWithCustomToken("invalid_tampered_jwt_token_12345")
                .when()
                .get("/api/departments");

        Assert.assertEquals(response.getStatusCode(), 401,
                "Security Failure: Tampered token was not rejected with 401!");
    }
}
