package api;

import api.utils.*;
import lombok.Getter;
import org.testng.annotations.BeforeMethod;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;


public class ProjectApiProceduresTests {

    @Getter
    private static int projectId;

    @BeforeMethod(groups = {"preconditions","postconditions"})
    public void setup() {
        ApiHelper.setup();
    }

    @Test(groups = "create")
    public void createProject() {
        CreateRequest<CreateProjectParams> body = CreateRequest.<CreateProjectParams>builder()
                .jsonrpc("2.0")
                .method("createProject")
                .id(1797076613)
                .params(CreateProjectParams.builder()
                        .name("NewTestProject")
                        .identifier("newtestproject")
                        .build())
                .build();

        Response response = ApiHelper.sendPostRequest(body, notNullValue());
        projectId = response.jsonPath().getInt("result");
        System.out.println("Project id: " + projectId);
    }

    @Test(dependsOnGroups = "create", groups = "preconditions")
    public void addUserToProject() {
        int userId = UserApiProceduresTests.getUserId();
        CreateRequest<AddProjectUserParams> body = CreateRequest.<AddProjectUserParams>builder()
                .jsonrpc("2.0")
                .method("addProjectUser")
                .id(1294688355)
                .params(AddProjectUserParams.builder()
                        .project_id(projectId)
                        .user_id(userId)
                        .role("project-manager")
                        .build())
                .build();

        Response response = ApiHelper.sendPostRequest(body, equalTo(true));
    }

    @Test(groups = "postconditions", dependsOnMethods = "createProject", priority = 2)
    public void removeProject() {
        Map<String, Object> params = new HashMap<>();
        params.put("project_id", projectId);
        CreateRequest<Map<String, Object>> body = CreateRequest.<Map<String, Object>>builder()
                .jsonrpc("2.0")
                .method("removeProject")
                .id(46285125)
                .params(params)
                .build();

        Response response = ApiHelper.sendPostRequest(body, equalTo(true));

        System.out.println("Project " + projectId + " was deleted!");
    }

    @Test(testName = "Negative: createProjectWithoutName")
    public void createProjectWithoutName() {
        CreateRequest<CreateProjectParams> body = CreateRequest.<CreateProjectParams>builder()
                .jsonrpc("2.0")
                .method("createProject")
                .id(1797076613)
                .params(CreateProjectParams.builder()
                        .identifier("newtestproject")
                        .build())
                .build();

        Response response = ApiHelper.sendPostRequest(body, equalTo(false));
    }

    @Test(dependsOnGroups = "create", testName = "Negative: addUserToProjectToNonExistentProject")
    private void addUserToProjectToNonExistentProject() {
        int userId = UserApiProceduresTests.getUserId();
        CreateRequest<AddProjectUserParams> body = CreateRequest.<AddProjectUserParams>builder()
                .jsonrpc("2.0")
                .method("addProjectUser")
                .id(1294688355)
                .params(AddProjectUserParams.builder()
                        .project_id(0)
                        .user_id(userId)
                        .role("project-manager")
                        .build())
                .build();
        Response response = ApiHelper.sendPostRequest(body, equalTo(false));
    }


}




