package API;

import API.data.ApiHelper;
import API.data.CreateRequest;
import API.data.CreateUserParams;
import lombok.Getter;
import org.testng.annotations.BeforeMethod;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;


public class UserApiProceduresTests {

    @Getter
    private static int userId;

    private final String username = "NewUser";
    private final String usernameAuth = "jsonrpc";
    private final String apiToken = "819a91af641e4f0e8098cba4899199f4b2fe1ae0ceed17377727ae3f7e0e";

    @BeforeMethod(groups = "preconditions")
    public void setup() {
        ApiHelper.setup();
    }

    @Test(groups = "create")
    public void createUser() {
        CreateRequest<CreateUserParams> body = CreateRequest.<CreateUserParams>builder()
                .jsonrpc("2.0")
                .method("createUser")
                .id(1518863034)
                .params(CreateUserParams.builder()
                        .username(username)
                        .password("NewUser")
                        .role("app-manager")
                        .build())
                .build();

        Response response = ApiHelper.sendPostRequest(body, notNullValue());

        userId = response.jsonPath().getInt("result");
        System.out.println("User ID: " + userId);
    }

    @Test(groups = "postconditions", dependsOnMethods = "createUser", priority = 2)
    public void removeUser() {
        Map<String, Object> params = new HashMap<>();
        params.put("user_id", userId);
        CreateRequest<Map<String, Object>> body = CreateRequest.<Map<String, Object>>builder()
                .jsonrpc("2.0")
                .method("removeUser")
                .id(2094191872)
                .params(params)
                .build();

        Response response = ApiHelper.sendPostRequest(body, equalTo(true));

        System.out.println("User " + userId + " was deleted!");
    }

    @Test(testName = "Negative: createUserWithExistingUsername", dependsOnMethods = "createUser", priority = 1)
    public void createUserWithExistingUsername() {
        CreateRequest<CreateUserParams> body = CreateRequest.<CreateUserParams>builder()
                .jsonrpc("2.0")
                .method("createUser")
                .id(1518863034)
                .params(CreateUserParams.builder()
                        .username(username)
                        .password("NewUser")
                        .role("app-manager")
                        .build())
                .build();

        Response response = ApiHelper.sendPostRequest(body, equalTo(false));
    }
}
