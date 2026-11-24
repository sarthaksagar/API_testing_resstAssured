package com.api.tests.base;

import com.api.auth.TokenManager;
import com.api.clients.*;
import com.api.config.ConfigurationManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

/**
 * Base Test Class for all REST Assured Test Suites.
 * Initializes authentication, default context, and service clients.
 */
public abstract class BaseTest {

    protected DepartmentClient departmentClient;
    protected DesignationClient designationClient;
    protected EmployeeClient employeeClient;
    protected CompanyClient companyClient;
    protected LeaveClient leaveClient;
    protected AttendanceClient attendanceClient;
    protected PayrollClient payrollClient;
    protected RecruitmentClient recruitmentClient;
    protected AssetClient assetClient;

    protected static Long defaultCompanyId;

    @BeforeSuite(alwaysRun = true)
    public void globalSetup() {
        System.out.println("==================================================");
        System.out.println(">>> Initializing SmartHR QA REST Assured Automation");
        System.out.println(">>> Target Environment: " + ConfigurationManager.getBaseUri());
        System.out.println(">>> Pre-warming Authentication Token...");
        
        String token = TokenManager.getToken();
        Assert.assertNotNull(token, "Global setup failed: Could not retrieve authentication token!");
        System.out.println(">>> Authentication Successful. Ready to run tests.");
        System.out.println("==================================================");

        defaultCompanyId = ConfigurationManager.getDefaultCompanyId();
    }

    public BaseTest() {
        departmentClient = new DepartmentClient();
        designationClient = new DesignationClient();
        employeeClient = new EmployeeClient();
        companyClient = new CompanyClient();
        leaveClient = new LeaveClient();
        attendanceClient = new AttendanceClient();
        payrollClient = new PayrollClient();
        recruitmentClient = new RecruitmentClient();
        assetClient = new AssetClient();
    }

    protected void assertStatusCode(Response response, int expectedCode) {
        Assert.assertEquals(response.getStatusCode(), expectedCode,
                "Status code mismatch! Response: " + response.getBody().asString());
    }

    protected Long extractId(Response response) {
        Number id = response.jsonPath().get("id");
        Assert.assertNotNull(id, "Response did not contain an 'id' attribute!");
        return id.longValue();
    }
}
