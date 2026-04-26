package com.nishad.rest.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.nishad.rest.utils.ConfigReader;
import io.restassured.RestAssured;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * 🏛️ BaseTest Class
 * The parent class for all API tests. Handles global setup and configuration.
 */
public class BaseTest {

    protected static ExtentReports extent;
    protected static ExtentTest test;

    @BeforeSuite
    public void setupSuite() {
        // 1. Initialize the HTML Reporter
        ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        
        System.out.println("📊 Extent Report Initialized at target/ExtentReport.html");

        // 2. Initialize Rest Assured
        String baseUrl = ConfigReader.getProperty("baseUrl");
        String basePath = ConfigReader.getProperty("basePath");
        
        RestAssured.baseURI = baseUrl;
        RestAssured.basePath = basePath;

        // 3. Professional Configuration: Log only if the test fails
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        
        System.out.println("✅ Rest Assured initialized with BaseURI: " + baseUrl + basePath);
    }

    @AfterSuite
    public void tearDownSuite() {
        // 3. Flush the report to write it to the file
        extent.flush();
        System.out.println("✅ Extent Report Generated Successfully.");
    }
}
