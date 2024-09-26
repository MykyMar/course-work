package api.utils;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.hamcrest.Matcher;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;

public class ApiHelper {
    private static final String usernameAuth = "jsonrpc";
    private static final String apiToken = "819a91af641e4f0e8098cba4899199f4b2fe1ae0ceed17377727ae3f7e0e";

    public static Response sendPostRequest(Object body, Matcher<?> expectedResult){
        return    given()
                .log().all()
                .body(body)
                .when()
                .post("/jsonrpc.php")
                .then()
                .log().body()
                .statusCode(200)
                .body(not(emptyOrNullString()))
                .body("result", expectedResult)
                .extract().response();
    }
    public static void setup() {
        RestAssured.baseURI = "http://localhost:80";
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setAuth(RestAssured.basic(usernameAuth, apiToken))
                .addHeader("Content-Type", "application/json")
                .build()
                .filter(new AllureRestAssured());
    }
}
