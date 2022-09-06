package com.automation.utils;

import com.automation.pojos.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DbResponse {

    public static Response post(String body, String endPoint){
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .headers("Authorization", ConfigReader.getProperty("gr.apiToken"))
                .when()
                .accept(ContentType.JSON)
                .body(body)
                .post(endPoint);
    }

    public static Response put(String body, String endPoint){
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .headers("Authorization", ConfigReader.getProperty("gr.apiToken"))
                .when()
                .accept(ContentType.JSON)
                .body(body)
                .put(endPoint);
    }

    public static Response getUser(String id){
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .headers("Authorization",ConfigReader.getProperty("gr.apiToken"))
                .accept(ContentType.JSON)
                .when()
                .pathParam("userId", id)
                .get("/users/{userId}");
    }

    public static Response getEndpoint(String endPoint){
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .headers("Authorization", ConfigReader.getProperty("gr.apiToken"))
                .when()
                .get(endPoint);
    }

    public static Response deleteUser(String userId){
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .headers("Authorization", ConfigReader.getProperty("gr.apiToken"))
                .when()
                .pathParam("userId", userId)
                .delete("/users/{userId}");
    }

    public static Response patch(String endPoint, String body){
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .headers("Authorization", ConfigReader.getProperty("gr.apiToken"))
                .when()
                .accept(ContentType.JSON)
                .body(body)
                .patch(endPoint);
    }
}
