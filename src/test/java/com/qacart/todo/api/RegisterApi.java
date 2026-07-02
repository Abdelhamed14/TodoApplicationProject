package com.qacart.todo.api;

import com.qacart.todo.config.EndPoint;
import com.qacart.todo.objects.User;
import com.qacart.todo.utils.UserUtils;
import io.restassured.http.Cookie;

import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class RegisterApi {
    public List<Cookie> getRestAssuredCookies() {
        return this.restAssuredCookies;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    private List<Cookie> restAssuredCookies;

    public String getFirstName() {
        return this.firstName;
    }

    private String firstName;
    private String accessToken;

    public String getUserId() {
        return this.userId;
    }

    private String userId;

    public void register(){
        User user =  UserUtils.generateRandomUser();
        Response response =
                given()
                        .baseUri("https://todo.qacart.com")
                        .header("Content-Type","application/json")
                        .body(user)
                        .log().all()
                .when()
                        .post(EndPoint.API_REGISTER_ENDPOINT)
                .then()
                        .log().all()
                        .extract().response();

        if(response.getStatusCode() != 201){
            throw  new RuntimeException("some thing went wrong");

        }


        restAssuredCookies = response.detailedCookies().asList();
        accessToken = response.path("access_token");
        userId = response.path("userId");
        firstName = response.path("firstName");



    }

}
