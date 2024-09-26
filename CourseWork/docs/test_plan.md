# Test Plan for Kanban Application

## 1. Introduction
This document describes the testing strategy and scenarios for the Kanban application.
Based on the study of the functionality of the ui and api documentation, a set of tests and the infrastructure for running them will be developed to test the application.


## 2. Objectives
- To ensure the core functionality of the Kanban application through automated testing.
- To cover both ui and api functionality using comprehensive test scenarios.
- To integrate testing into the CI/CD pipeline for regular test execution.

## 3. Scope
### 3.1. In-Scope
- **ui Testing**: Testing core functionalities of the user interface, including user login, project management, and task management across different browsers (Chrome, Chrome headless, Firefox).
- **api Testing**: Verifying CRUD operations for users, projects, and tasks through REST api.
- **CI/CD**: Daily execution of tests through Jenkins.

### 3.2. Out-of-Scope
- Performance testing, security testing, localization testing, and mobile testing.

## 4. Testing Strategy
### 4.1. Types of Testing
- **ui Testing**: Using Selenide to automate ui tests.
- **api Testing**: Using Rest Assured for automated api testing.
- **Automation Framework**: The project will be executed as a Maven project with integration of TestNG for test execution, Allure for test reporting, and Cucumber for BDD.

### 4.2. Test Environments
- **Browsers**: Chrome, Chrome headless, Firefox.
- **CI/CD**: Jenkins will be used for daily scheduled test execution.

### 4.3. Tools and Frameworks
- **TestNG** for test execution.
- **Selenide** for ui tests.
- **Rest Assured** for api tests.
- **Allure** for reporting.
- **Cucumber** for behavior-driven development (BDD). Only for 4 tests.
- **Git** for version control and code storage.

## 5. Test Execution Plan
### 5.1. Test Cycles
- ui and api tests will be executed daily through Jenkins.
- Separate test cycles for different functionalities, with the ability to selectively run specific test groups via a configuration file.

### 5.2. Test Data
- Test data for users, projects, and tasks will be created and cleaned up via api.

## 6. Test Scenarios

### 6.1. ui Test Scenarios

1. **Login Testing**:
   - Positive case: Login with valid credentials.
   - Negative case: Login with incorrect password.
   - Negative case: Login with incorrect username.
   
2. **Project Management**:
   - Positive case: Create a project with valid data.
   - Negative case: Try to create a project with existing Identifier.

3. **Task Management**:
    - Positive case: Create a new task within a project.
    - Positive case: Move a task between different phases (e.g., from "To Do" to "In Progress").
    - Positive case: Create a subtask under an existing task.
    - Positive case: Add a comment to a task and verify the comment appears correctly.
    - Positive case: Close an existing task and verify it is no longer active.


### 6.2. api Test Scenarios

1. **User Management**:
   - Positive case: Create a new user.
   - Positive case: Delete a user.
   - Negative case: Create a user with an existing username.
   
2. **Project Management**:
   - Positive case: Create a new project.
   - Positive case: Add a user to a project.
   - Negative case: Create a project without a name.

3. **Task Management**:
   - Positive case: Create a new task.
   - Positive case: Delete a task.


## 7. Entry and Exit Criteria
### 7.1. Entry Criteria
- Application is deployed and functional.
- Test environments are configured.

### 7.2. Exit Criteria
- All tests have passed or failed with defects logged.

## 8. Reporting and Metrics
- Allure reports will be generated after each test run.
- Test results will be stored in Jenkins and Git.

## 9. Success Criteria
- All critical ui and api functionalities pass automated testing.
- Tests successfully run on a daily schedule without interruptions.
