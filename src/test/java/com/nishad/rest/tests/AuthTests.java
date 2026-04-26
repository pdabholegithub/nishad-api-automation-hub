package com.nishad.rest.tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

/**
 * 🔐 AuthTests
 * Demonstrates how to handle different authentication types in Rest Assured.
 */
public class AuthTests extends BaseTest {

    @Test(description = "Demonstrate Bearer Token Authentication")
    public void testBearerTokenAuth() {
        String mockToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...";

        // In a real scenario, you would first call a /login API to get this token
        given()
            .header("Authorization", "Bearer " + mockToken)
            .log().headers()
        .when()
            .get("/courses") // Even if this route is public, we are showing how to send the token
        .then()
            .statusCode(200);
    }

    @Test(description = "Demonstrate Basic Authentication")
    public void testBasicAuth() {
        given()
            .auth().basic("admin", "password123")
        .when()
            .get("/courses")
        .then()
            .statusCode(200);
    }
}
