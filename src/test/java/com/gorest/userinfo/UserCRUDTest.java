package com.gorest.userinfo;

import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class UserCRUDTest extends TestBase {

    static int userId;
    static String name = "testprime" + TestUtils.getRandomValue();
    static String gender = "male";
    static String email = TestUtils.getRandomValue() + "testprime@gmail.com";
    static String status = "active";

    @Steps
    UserSteps userSteps;
    @Title("This will a create a new user")
    @Test
    public void test001() {
        ValidatableResponse response = userSteps.createANewUser(name,gender,email,status);
        response.log().all().statusCode(201);
        userId = response.log().all().extract().path("id");
        System.out.println(userId);

    }

    @Title("Verify if the user was added to the application")
    @Test
    public void test002() {
        HashMap<String, Object> userMap = userSteps.getUserInfoById(userId);
        Assert.assertThat(userMap,hasValue(userId));
        System.out.println(userMap);
    }

    @Title("Update the user information and verify the updated information")
    @Test
    public void test003() {
        name = name + "_updated";
        userSteps.updatingUser(userId,name,gender,email,status);
        HashMap<String, Object> userMap = userSteps.getUserInfoById(userId);
        Assert.assertThat(userMap,hasValue(userId));

    }

    @Title("Deleting user information and verify if the product is deleted")
    @Test
    public void test004() {
        userSteps.deleteUser(userId).statusCode(204);
        userSteps.getAllUserInfo().statusCode(200);
    }

}
