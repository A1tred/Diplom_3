package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import config.Config;

public class ApiUtils {

    public static Response createUser(String email, String password, String name) {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .body("{\"email\": \"" + email + "\", \"password\": \"" + password + "\", \"name\": \"" + name + "\"}")
                .when()
                .post(Config.getBaseUrl() + "api/auth/register");
    }

    public static Response loginUser(String email, String password) {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .body("{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}")
                .when()
                .post(Config.getBaseUrl() + "api/auth/login");
    }

    public static Response deleteUser(String accessToken) {
        return RestAssured.given()
                .header("Authorization", accessToken)
                .when()
                .delete(Config.getBaseUrl() + "api/auth/user");
    }
}
