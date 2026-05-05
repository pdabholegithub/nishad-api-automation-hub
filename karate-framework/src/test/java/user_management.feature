Feature: User Management API Production Suite
  # Description: Comprehensive API tests for User CRUD operations
  # Author: Antigravity Expert QA
  # Project: Nishad IT Solutions - Automation Hub

  Background:
    # 1. Base URL and Endpoint configurations
    * url 'https://reqres.in/api'
    * val userPath = '/users'
    
    # 2. Common headers for all requests
    * header Content-Type = 'application/json'
    * header Accept = 'application/json'
    
    # 3. Dynamic data generation for uniqueness
    * def timestamp = function(){ return java.lang.System.currentTimeMillis() }
    * def randomEmail = 'automation_' + timestamp() + '@nishad-it.com'

    # 4. Expected User Schema for validation
    * def userSchema = { id: '#number', name: '#string', job: '#string', createdAt: '#regex ^\\d{4}-\\d{2}-\\d{2}T.*' }

  # --- POSITIVE TEST CASES ---

  Scenario: [Success] Create a new user with valid data
    Given path userPath
    And request 
    """
    {
      "name": "Nishad Expert",
      "job": "Lead QA Engineer",
      "email": "#(randomEmail)"
    }
    """
    When method post
    Then status 201
    # Validate specific fields
    And match response.name == 'Nishad Expert'
    And match response.job == 'Lead QA Engineer'
    # Schema validation
    And match response == userSchema
    # Performance assertion: Response time should be under 2 seconds
    And assert responseTime < 2000
    * print 'User created with ID:', response.id

  Scenario: [Success] Fetch an existing user by ID
    Given path userPath, '2'
    When method get
    Then status 200
    And match response.data.id == 2
    And match response.data.email == 'janet.weaver@reqres.in'
    # Fuzzy matching for email format
    And match response.data.email == '#regex ^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$'

  # --- DATA-DRIVEN TESTING ---

  Scenario Outline: [DDT] Create multiple users with different roles
    Given path userPath
    And request { "name": "<name>", "job": "<role>" }
    When method post
    Then status 201
    And match response.name == "<name>"
    And match response.job == "<role>"

    Examples:
      | name    | role             |
      | Amit    | SDET             |
      | Priya   | DevOps Architect |
      | Rahul   | Backend Dev      |

  # --- NEGATIVE TEST CASES ---

  Scenario: [Error] Attempt to fetch a non-existent user
    Given path userPath, '9999'
    When method get
    Then status 404
    # Optional: Validate error body if the API returns one
    # And match response == {}

  Scenario: [Error] Login with missing password
    Given path '/login'
    And request { "email": "sydney@fife" }
    When method post
    Then status 400
    And match response.error == 'Missing password'

  # --- REUSABLE LOGIC (Advanced) ---

  Scenario: [Workflow] Create user and immediately update details
    # Step 1: Create
    Given path userPath
    And request { "name": "Initial Name", "job": "Intern" }
    When method post
    Then status 201
    def createdId = response.id

    # Step 2: Update using dynamic ID from Step 1
    Given path userPath, createdId
    And request { "name": "Updated Name", "job": "Manager" }
    When method put
    Then status 200
    And match response.name == "Updated Name"
    And match response.job == "Manager"
