package com.api.tests;

import com.api.models.UserPost;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SimpleApiTest {

    @BeforeClass
    public void setup() {
        // Base URI for public testing API
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    /**
     * Test 1: Simple GET request to fetch a post by ID and validate status code & fields
     */
    @Test(description = "Verify GET /posts/{id} returns 200 and expected post details")
    public void testGetSinglePost() {
        System.out.println("\n========== RUNNING TEST 1: GET /posts/1 ==========");
        given()
            .header("Content-Type", "application/json")
            .log().uri() // Prints the requested URL
        .when()
            .get("/posts/1")
        .then()
            .log().body() // Prints the JSON response body
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("id", equalTo(1))
            .body("userId", equalTo(1))
            .body("title", notNullValue());
    }

    /**
     * Test 2: GET request using Query Parameters
     */
    @Test(description = "Verify GET /comments with query param postId returns comments")
    public void testGetCommentsWithQueryParam() {
        System.out.println("\n========== RUNNING TEST 2: GET /comments?postId=1 ==========");
        given()
            .queryParam("postId", 1)
            .log().uri()
        .when()
            .get("/comments")
        .then()
            .log().body()
            .statusCode(200)
            .body("size()", greaterThan(0))
            .body("[0].email", containsString("@"));
    }

    /**
     * Test 3: POST request with POJO payload and response extraction
     */
    @Test(description = "Verify POST /posts creates a new resource and returns 201")
    public void testCreatePostWithPOJO() {
        System.out.println("\n========== RUNNING TEST 3: POST /posts ==========");
        UserPost newPost = new UserPost(101, "API Testing with RestAssured", "Learn how to automate APIs using Java");

        Response response = given()
            .contentType(ContentType.JSON)
            .body(newPost)
            .log().all() // Prints full request details (Headers, Body, Method, URI)
        .when()
            .post("/posts")
        .then()
            .log().all() // Prints full response details (Status code, Headers, Body)
            .statusCode(201)
            .extract()
            .response();

        // Validate response using TestNG assertions
        int statusCode = response.getStatusCode();
        String title = response.jsonPath().getString("title");

        Assert.assertEquals(statusCode, 201, "Status code should be 201 Created");
        Assert.assertEquals(title, "API Testing with RestAssured", "Title should match the sent payload");
    }
}

