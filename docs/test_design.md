## General

**Browsers**:
- Chrome
- Chrome headless
- Firefox.

**Pages**
 - Login
 - Dashboard
 - Board
 - Users


## Scenarios

### Preconditions: 
**Type**: api
- Create a user
- Create a project
- Add a user to a project

### Postconditions:
**Type**: api
- Delete the project.
- Delete the user.


## api tests (Positive)
1. Create a user 
2. Create a project 
3. Add a user to a project
4. Create a task
5. Delete the task
6. Delete the project
7. Delete the user

## api tests (Negative)
1. Create a user with an existing username.
2. Create a project without a name.

## ui tests
1. Login Positive/Negative // without Preconditions and Postconditions
   - Positive: Login with valid credentials.
   - Negative: Login with incorrect password.
   - Negative: Login with incorrect name.
2. Create project Positive/Negative // without Preconditions and Postconditions
   - Positive: Create a project with valid data.
   - Negative: Try to create a project with existing Identifier.
3. Create a task
4. Move a task between different phases(e.g., from "To Do" to "In Progress")
5. Create a subtask 
6. Add a comment to a task 
7. Close a task 
 


