package com.api.specs;

import com.api.auth.TokenManager;
import com.api.config.ConfigurationManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

/**
 * Reusable Request and Response Specification factory.
 * Enforces security, logging, content type, and base URI across all client calls.
 */
public class SpecFactory {

    public static RequestSpecification getAuthenticatedRequestSpec() {
        RequestSpecBuilder builder = new RequestSpecBuilder()
                .setBaseUri(ConfigurationManager.getBaseUri())
                .setRelaxedHTTPSValidation()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addHeader("Authorization", TokenManager.getAuthorizationHeader());

        if (ConfigurationManager.isLoggingEnabled()) {
            builder.log(LogDetail.ALL);
        }

        return builder.build();
    }

    public static RequestSpecification getUnauthenticatedRequestSpec() {
        RequestSpecBuilder builder = new RequestSpecBuilder()
                .setBaseUri(ConfigurationManager.getBaseUri())
                .setRelaxedHTTPSValidation()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON);

        if (ConfigurationManager.isLoggingEnabled()) {
            builder.log(LogDetail.ALL);
        }

        return builder.build();
    }

    public static ResponseSpecification getResponseSpec(int statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .expectContentType(ContentType.JSON)
                .build();
    }
}
