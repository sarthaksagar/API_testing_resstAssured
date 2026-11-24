package com.api.auth;

import com.api.config.ConfigurationManager;
import com.api.constants.Endpoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * Thread-safe Authentication Token Manager.
 * Handles login, JWT token caching, and automatic token retrieval.
 */
public class TokenManager {

    private static String cachedToken;
    private static final Object lock = new Object();

    public static String getToken() {
        if (cachedToken == null) {
            synchronized (lock) {
                if (cachedToken == null) {
                    cachedToken = authenticate(
                            ConfigurationManager.getAuthUsername(),
                            ConfigurationManager.getAuthPassword()
                    );
                }
            }
        }
        return cachedToken;
    }

    public static String forceRefreshToken() {
        synchronized (lock) {
            cachedToken = authenticate(
                    ConfigurationManager.getAuthUsername(),
                    ConfigurationManager.getAuthPassword()
            );
            return cachedToken;
        }
    }

    public static String getAuthorizationHeader() {
        return "Bearer " + getToken();
    }

    private static String authenticate(String username, String password) {
        Map<String, String> payload = new HashMap<>();
        payload.put("username", username);
        payload.put("password", password);

        Response response = RestAssured.given()
                .baseUri(ConfigurationManager.getBaseUri())
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(Endpoints.AUTH_LOGIN)
                .then()
                .extract()
                .response();

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Authentication failed with status " + response.getStatusCode()
                    + ": " + response.getBody().asString());
        }

        String token = response.jsonPath().getString("access_token");
        if (token == null || token.isEmpty()) {
            token = response.jsonPath().getString("token");
        }
        if (token == null || token.isEmpty()) {
            token = response.jsonPath().getString("id_token");
        }

        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Failed to extract JWT access token from login response!");
        }

        return token;
    }
}
