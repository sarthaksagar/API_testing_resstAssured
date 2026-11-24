package com.api.tests;

import com.api.models.auth.LoginRequest;
import com.api.models.auth.LoginResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class SmartHrAuthTest {

    public static String accessToken;
    public static String tokenType;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://qa.techvgi.com/smarthr-qa";
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test(priority = 1, description = "TC_ID_LOGIN_001: User login with valid credentials ('admin') returns 200 OK and JWT access token")
    public void testUserLoginSuccess() {
        System.out.println("\n========== RUNNING VALID LOGIN TEST ==========");

        LoginRequest validCredentials = new LoginRequest("omkark11@gmail.com", "admin");

        Response response = given()
            .contentType(ContentType.JSON)
            .body(validCredentials)
            .log().all()
        .when()
            .post("/api/auth/login")
        .then()
            .log().all()
            .statusCode(200)
            .body("access_token", notNullValue())
            .body("token_type", equalTo("Bearer"))
            .extract()
            .response();

        LoginResponse loginResponse = response.as(LoginResponse.class);
        accessToken = loginResponse.getAccessToken();
        tokenType = loginResponse.getTokenType();

        System.out.println("====================================================");
        System.out.println(">>> LOGIN SUCCESSFUL!");
        System.out.println(">>> User: " + loginResponse.getName());
        System.out.println(">>> Employee ID: " + loginResponse.getEmployeeId());
        System.out.println(">>> Company ID: " + loginResponse.getCompanyId());
        System.out.println(">>> Access Token: " + accessToken.substring(0, 50) + "...");
        System.out.println("====================================================");

        Assert.assertNotNull(accessToken, "Access token should not be null");
    }

    @Test(priority = 2, description = "TC_ID_LOGIN_003: Login with invalid password returns 401 Unauthorized")
    public void testLoginWithInvalidPassword() {
        System.out.println("\n========== RUNNING INVALID LOGIN TEST ==========");

        LoginRequest invalidCredentials = new LoginRequest("omkark11@gmail.com", "wrong_password_xyz");

        given()
            .contentType(ContentType.JSON)
            .body(invalidCredentials)
            .log().all()
        .when()
            .post("/api/auth/login")
        .then()
            .log().all()
            .statusCode(401)
            .body("title", equalTo("Invalid username or password"))
            .body("status", equalTo(401));
    }

    @Test(priority = 3, dependsOnMethods = "testUserLoginSuccess", description = "TC_AUTH_001: Verify authenticated user can access /api/authorities")
    public void testGetAuthoritiesWithToken() {
        System.out.println("\n========== RUNNING GET AUTHORITIES TEST ==========");

        given()
            .header("Authorization", "Bearer " + accessToken)
            .log().uri()
        .when()
            .get("/api/authorities")
        .then()
            .log().body()
            .statusCode(200)
            .body("size()", greaterThan(0));
    }

    @Test(priority = 4, dependsOnMethods = "testUserLoginSuccess", description = "TC_ACC_AUTH_001: Verify authenticate endpoint returns logged in username")
    public void testAuthenticateEndpoint() {
        System.out.println("\n========== RUNNING GET /api/authenticate TEST ==========");

        given()
            .header("Authorization", "Bearer " + accessToken)
            .log().uri()
        .when()
            .get("/api/authenticate")
        .then()
            .log().body()
            .statusCode(200)
            .body(equalTo("omkark11@gmail.com"));
    }
}
