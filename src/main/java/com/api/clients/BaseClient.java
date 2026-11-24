package com.api.clients;

import com.api.specs.SpecFactory;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

/**
 * Base HTTP Client containing common REST Assured execution methods.
 */
public abstract class BaseClient {

    public RequestSpecification request() {
        return given().spec(SpecFactory.getAuthenticatedRequestSpec());
    }

    public RequestSpecification unauthenticatedRequest() {
        return given().spec(SpecFactory.getUnauthenticatedRequestSpec());
    }

    public RequestSpecification requestWithCustomToken(String token) {
        return given()
                .spec(SpecFactory.getUnauthenticatedRequestSpec())
                .header("Authorization", "Bearer " + token);
    }
}
