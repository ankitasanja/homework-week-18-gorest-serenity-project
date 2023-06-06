package com.gorest.userinfo;

import com.gorest.constants.EndPoints;
import com.gorest.model.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class UserSteps {

    @Step("Creating a new user with name: {0},gender: {1}, email: {2},status: {3}")
    public ValidatableResponse createANewUser(String name,String gender,String email, String status) {
        UserPojo userPojo = UserPojo.getUserPojo(name,gender,email,status);

        return SerenityRest.given()
                .header("Content-Type", "application.json")
                .header("Authorization", "Bearer 61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215")
                .contentType(ContentType.JSON)
                .body(userPojo)
                .when()
                .post(EndPoints.CREATE_USER)
                .then();

    }

    @Step("Getting the user information with userID: {0}")
    public HashMap<String,Object> getUserInfoById(int userId) {
        String token = "61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215";
        HashMap<String, Object> userMap = SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Access", "application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .pathParam("userID", userId)
                .get(EndPoints.GET_SINGLE_USER_BY_ID)
                .then().statusCode(200)
                .extract()
                .path("");

        return userMap;
    }

    @Step("Update the user with name: {0},gender: {1},email: {2}, status: {3},userId: {4}")
    public ValidatableResponse updatingUser(int userId,String name,String gender,String email, String status) {

        UserPojo userPojo = UserPojo.getUserPojo(name,gender,email,status);

        String token = "61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215";
        return SerenityRest.given().log().all()
                .header("Content-Type", "application.json")
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(userPojo)
                .pathParam("userID", userId)
                .when()
                .put(EndPoints.UPDATE_SINGLE_USER_BY_ID)
                .then();

    }

    @Step
    public ValidatableResponse deleteUser(int userId) {
        String token = "61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215";
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Access", "application/json")
                .header("Authorization", "Bearer " + token)
                .pathParam("userID", userId)
                .when()
                .delete(EndPoints.DELETE_SINGLE_USER_BY_ID)
                .then();

    }

    @Step("Getting all the User information")
    public ValidatableResponse getAllUserInfo() {
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_USERS)
                .then();
    }


}
