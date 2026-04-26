package com.nishad.rest.tests;

import com.nishad.rest.models.Course;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * 🎓 CourseTests Class
 * Contains functional tests for the Course-related APIs of Nishad Institute.
 */
public class CourseTests extends BaseTest {

    @Test(priority = 1, description = "Verify that the Courses API returns a list of courses successfully")
    public void testGetAllCourses() {
        given()
            // We use log().all() here manually to see the output for our first successful run
            .log().all()
        .when()
            .get("/courses")
        .then()
            .statusCode(200)
            .body("success", is(true))
            .body("data", is(notNullValue()))
            .log().body(); // Logs the JSON response for verification
    }

    @Test(priority = 2, description = "Verify that a new course can be created successfully")
    public void testCreateCourse() {
        String newCourseBody = """
            {
                "title": "Playwright Automation Pro",
                "description": "Master end-to-end testing with Playwright and TypeScript.",
                "duration": "6 Weeks",
                "level": "Advanced",
                "price": 5999,
                "technologies": "TypeScript, Playwright, CI/CD",
                "popular": true
            }
            """;

        given()
            .contentType("application/json") // Informing server we are sending JSON
            .body(newCourseBody)
        .when()
            .post("/courses")
        .then()
            .statusCode(201) // 201 means "Created"
            .body("success", is(true))
            .body("data.title", equalTo("Playwright Automation Pro"))
            .log().all();
    }

    @Test(priority = 3, description = "Concept: Request Chaining - Create a course and verify it by ID")
    public void testCreateAndVerifyCourse() {
        String courseName = "Appium Mobile Automation";
        String payload = """
            {
                "title": "%s",
                "price": 4500,
                "level": "Intermediate",
                "duration": "5 Weeks"
            }
            """.formatted(courseName);

        // STEP 1: Create the course and extract the ID
        String courseId = given()
            .contentType("application/json")
            .body(payload)
        .when()
            .post("/courses")
        .then()
            .statusCode(201)
            .extract().path("data.id"); // 🔍 Extraction logic

        System.out.println("✅ Extracted Course ID: " + courseId);

        // STEP 2: Use the ID to fetch the specific course (Request Chaining)
        given()
            .pathParam("id", courseId) // Passing the extracted ID
        .when()
            .get("/courses/{id}")
        .then()
            .statusCode(200)
            .body("success", is(true))
            .body("data.id", equalTo(courseId))
            .body("data.title", equalTo(courseName));
    }

    @Test(priority = 4, description = "Concept: Serialization - Create a course using a POJO (Java Object)")
    public void testCreateCourseWithPojo() {
        // Create Java Object instead of JSON String
        Course course = new Course("Selenium with Python", 3500, "Beginner", "4 Weeks");
        course.setDescription("Master web automation using Selenium and Python.");
        course.setTechnologies("Python, Selenium, PyTest");

        given()
            .contentType("application/json")
            .body(course) // Rest Assured automatically serializes this object to JSON!
        .when()
            .post("/courses")
        .then()
            .statusCode(201)
            .body("success", is(true))
            .body("data.title", equalTo(course.getTitle()));
    }

    @Test(priority = 5, description = "Concept: Deserialization - Convert JSON response back to Java Object")
    public void testCreateAndVerifyWithDeserialization() {
        Course expectedCourse = new Course("API Testing with Karate", 2500, "Intermediate", "3 Weeks");

        // Create the course and extract the response directly into a Course Object
        Course actualCourse = given()
            .contentType("application/json")
            .body(expectedCourse)
        .when()
            .post("/courses")
        .then()
            .statusCode(201)
            .extract()
            .jsonPath().getObject("data", Course.class); // 🔄 The Magic: JSON to Java Object

        // Professional Assertion: Comparing Java Objects
        System.out.println("✅ Verified Course from Response Object: " + actualCourse.getTitle());
        assert(actualCourse.getTitle().equals(expectedCourse.getTitle()));
        assert(actualCourse.getPrice() == expectedCourse.getPrice());
    }

    @Test(priority = 6, description = "Concept: Advanced Assertions - Verify data integrity and list contents")
    public void testCourseDataIntegrity() {
        // 1. Capture the response
        io.restassured.response.Response response = given().when().get("/courses");
        
        // 2. Extract values for reliable comparison
        int actualListSize = response.jsonPath().getList("data").size();
        int reportedCount = response.jsonPath().getInt("count");

        System.out.println("📊 Data Integrity Check - List Size: " + actualListSize + " | Reported Count: " + reportedCount);

        // 3. Perform assertions using TestNG's Assert for more detailed error messages
        org.testng.Assert.assertEquals(actualListSize, reportedCount, "Course list size should match the reported count!");
        
        // 4. Use Hamcrest for content validation
        response.then()
            .body("data.title", hasItem("Core Java & Advanced OOP"))
            .body("data.id", everyItem(notNullValue()));
    }
}
