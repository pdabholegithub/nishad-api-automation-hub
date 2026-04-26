Feature: Course Management API Tests
  As a student of Nishad IT Solutions
  I want to be able to fetch and create courses
  So that I can manage my learning journey

  Background:
    * url baseUrl
    * header Accept = 'application/json'

  Scenario: Get all courses and verify success
    Given path 'courses'
    When method get
    Then status 200
    And match response.success == true
    And match response.data[*].title contains 'Core Java & Advanced OOP'

  Scenario: Create a new course and verify creation
    Given path 'courses'
    And request 
    """
    {
      "title": "Karate Framework Mastery",
      "price": 4999,
      "level": "Intermediate",
      "duration": "4 Weeks",
      "description": "Learn API Automation without Java boilerplate"
    }
    """
    When method post
    Then status 201
    And match response.data.title == 'Karate Framework Mastery'
    And match response.message == 'Course successfully created via API'
