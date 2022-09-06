package com.automation.endpoints;

import com.automation.pojos.User;
import com.automation.utils.DbResponse;
import com.automation.utils.Serializers;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;

public class UserEndpoint extends Base{
    Serializers<User> serializer = new Serializers<>();
    public void createUser() {
        //create User object
        user.setEmail(faker.name().firstName().replace(" ", "") + "@gmail.com");
        user.setGender("male");
        user.setName(faker.funnyName().name());
        user.setStatus("active");
    }


    public void postUser() {
        //Serialize pojo into json
        String jsonBody = serializer.serialize(user);
        //Post user to api
        Response response = DbResponse.post(jsonBody, "/users");
        //deserialize response back to pojo
        user = serializer.deserialize(user, response);
        Assertions.assertEquals(201, response.getStatusCode());
    }

    public void verifyUserExists() {
        //verify User
        Response response = DbResponse.getUser(user.getId());
        Assertions.assertEquals(200, response.getStatusCode());

    }

    public void updateUserInfo() {
        String changedName = "Changed";
        user.setName(changedName);
        String jsonBody = serializer.serialize(user);
        Response response = DbResponse.put(jsonBody, "/users/" + user.getId());
        Assertions.assertEquals(200, response.getStatusCode());
    }

    public void verifyUserUpdated() {
        User userToVerify = new User();
        Response response = DbResponse.getUser(user.getId());
        userToVerify = serializer.deserialize(userToVerify, response);

        User finalUserToVerify = userToVerify;
        Assertions.assertAll(
                () -> Assertions.assertEquals(200, response.getStatusCode()),
                () -> Assertions.assertEquals(user.getName(), finalUserToVerify.getName())
        );
    }

    public void deleteUser() {
        Response response = DbResponse.deleteUser(user.getId());
        Assertions.assertEquals(204, response.getStatusCode());
    }

    public void verifyUserDeleted() {
        Response response = DbResponse.getUser(user.getId());
        Assertions.assertEquals(404, response.getStatusCode());
    }
}
