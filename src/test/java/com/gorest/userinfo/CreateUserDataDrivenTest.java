package com.gorest.userinfo;

import com.gorest.testbase.TestBase;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;

@Concurrent(threads = "4x")
@UseTestDataFrom("src/test/java/resources/testdata/userinfo.csv")
//@RunWith(SerenityParameterizedRunner.class)
public class CreateUserDataDrivenTest extends TestBase {

    private String name;
    private String gender;
    private String email;
    private String status;

    @Steps
    UserSteps userSteps;

    @Title("Data driven test for adding multiple students to the application")
    @Test
    public void createMultipleUser() {
        userSteps.createANewUser(name,gender,email,status).statusCode(201);
    }


}
