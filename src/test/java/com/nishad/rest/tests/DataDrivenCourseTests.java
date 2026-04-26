package com.nishad.rest.tests;

import com.nishad.rest.models.Course;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * 📊 DataDrivenCourseTests
 * Demonstrates how to run the same test with multiple datasets using TestNG DataProvider.
 */
public class DataDrivenCourseTests extends BaseTest {

    @DataProvider(name = "courseProvider")
    public Object[][] getCourseData() {
        return new Object[][] {
            { "React JS for Beginners", 2999, "Beginner", "4 Weeks" },
            { "Node.js Backend Mastery", 3999, "Intermediate", "6 Weeks" },
            { "Docker & Kubernetes Pro", 4999, "Advanced", "5 Weeks" }
        };
    }

    @Test(dataProvider = "courseProvider", description = "Run the create course test with multiple data sets")
    public void testCreateMultipleCourses(String title, int price, String level, String duration) {
        // Create POJO from DataProvider values
        Course course = new Course(title, price, level, duration);
        course.setDescription("Course generated via Data-Driven Testing");

        System.out.println("🚀 Running test for course: " + title);

        given()
            .contentType("application/json")
            .body(course)
        .when()
            .post("/courses")
        .then()
            .statusCode(201)
            .body("success", is(true))
            .body("data.title", equalTo(title));
    }
}
