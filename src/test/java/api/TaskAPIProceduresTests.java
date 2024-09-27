package api;

import api.utils.*;
import io.restassured.response.Response;
import lombok.Getter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


@Getter
public class TaskAPIProceduresTests {
    private int taskId;

    @BeforeMethod()
    public void setup() {
        ApiHelper.setup();
    }

    @Test(dependsOnGroups = "preconditions")
    public void createTask() {
        int projectId = ProjectApiProceduresTests.getProjectId();
        int userId = UserApiProceduresTests.getUserId();

        CreateRequest<CreateTaskParams> body = CreateRequest.<CreateTaskParams>builder()
                .jsonrpc("2.0")
                .method("createTask")
                .id(1176509098)
                .params(CreateTaskParams.builder()
                        .title("NewProjectTask")
                        .project_id(projectId)
                        .owner_id(userId)
                        .color_id("green")
                        .build())
                .build();

        Response response = ApiHelper.sendPostRequest(body, notNullValue());
        taskId = response.jsonPath().getInt("result");
        System.out.println("Task id: " + taskId);
    }

    @Test(dependsOnMethods = "createTask")
    public void removeTask() {
        Map<String, Object> params = new HashMap<>();
        params.put("task_id", taskId);
        CreateRequest<Map<String, Object>> body = CreateRequest.<Map<String, Object>>builder()
                .jsonrpc("2.0")
                .method("removeTask")
                .id(1423501287)
                .params(params)
                .build();

        Response response = ApiHelper.sendPostRequest(body, equalTo(true));

        System.out.println("Task " + taskId + " was deleted!");
    }

    @Test(testName = "Negative: removeTaskNonExistentTask")
    public void removeTaskNonExistentTask() {
        Map<String, Object> params = new HashMap<>();
        params.put("task_id", 12345);
        CreateRequest<Map<String, Object>> body = CreateRequest.<Map<String, Object>>builder()
                .jsonrpc("2.0")
                .method("removeTask")
                .id(1423501287)
                .params(params)
                .build();

        Response response = ApiHelper.sendPostRequest(body, equalTo(false));

    }

}
