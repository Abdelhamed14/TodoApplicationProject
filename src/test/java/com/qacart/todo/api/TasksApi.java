package com.qacart.todo.api;

import com.qacart.todo.config.EndPoint;
import com.qacart.todo.objects.Task;
import com.qacart.todo.utils.ConfigUtils;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class TasksApi {
    public void addTask(String token){
       Task newTask = new Task("learn Selenium",false);
       Response response =given()
               .baseUri(ConfigUtils.getInstance().getBaseUrl())
               .header("Content-Type","application/json")
               .body(newTask)
               .auth().oauth2(token)
       .when()
               .post(EndPoint.API_TASK_ENDPOINT)
       .then()
               .log().all().extract().response();

       if(response.statusCode()!=201){
           throw  new RuntimeException("Failed : HTTP error code : "+ response.statusCode());
       }




    }
}
