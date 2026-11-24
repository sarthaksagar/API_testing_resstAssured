package com.api.tests.recruitment;

import com.api.tests.base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;

/**
 * Test Suite for Recruitment & Hiring (/api/candidates, /api/vacancies).
 */
public class RecruitmentTests extends BaseTest {

    @Test(priority = 1, description = "TC_CAN-054: Get all candidates")
    public void testGetAllCandidates() {
        Response response = recruitmentClient.getAllCandidates();
        assertStatusCode(response, 200);
        response.then().body("size()", greaterThanOrEqualTo(0));
    }

    @Test(priority = 2, description = "TC_CAN-089: Get candidate count")
    public void testGetCandidateCount() {
        Response response = recruitmentClient.getCandidateCount();
        assertStatusCode(response, 200);
        Assert.assertTrue(response.getBody().asString().matches("\\d+"));
    }

    @Test(priority = 3, description = "TC_ID_VACANCY_067: Get all vacancies")
    public void testGetAllVacancies() {
        Response response = recruitmentClient.getAllVacancies();
        assertStatusCode(response, 200);
        response.then().body("size()", greaterThanOrEqualTo(0));
    }

    @Test(priority = 4, description = "Verify vacancy count")
    public void testGetVacancyCount() {
        Response response = recruitmentClient.getVacancyCount();
        assertStatusCode(response, 200);
        Assert.assertTrue(response.getBody().asString().matches("\\d+"));
    }

    @Test(priority = 5, description = "TC_CAN-025: Get candidate with non-existing ID returns 404")
    public void testGetNonExistentCandidate() {
        Response response = recruitmentClient.getCandidateById(999999L);
        assertStatusCode(response, 404);
    }
}
