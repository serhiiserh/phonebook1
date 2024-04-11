package com.phonebook.tests;

import com.phonebook.data.UserData;
import com.phonebook.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    @BeforeMethod

    public void ensurePrecondition(){
        if(!app.getUser().isLoginLinkPresent()){
            app.getUser().clickOnSignOutButton();
        }
    }

    @Test

    public void loginPositiveTest() {
        app.getUser().clickOnLoginLink();
        // enter email
        app.getUser().feelLoginRegisterForm(new User().setEmail(UserData.EMAIL).setPassword(UserData.PASSWORD));
        // click on Login button
        app.getUser().clickOnLoginButton();
        //assert Sigh out button is present
        Assert.assertTrue(app.getUser().isSignOutButtonPresent());

    }

    @Test
    public void loginNegativeTestWithoutEmail() {
        app.getUser().clickOnLoginLink();
        // enter email
        app.getUser().feelLoginRegisterForm(new User().setPassword("HewlettPackard1234$"));
        // click on Login button
        app.getUser().clickOnLoginButton();
        //assert Sign out button is present
        Assert.assertTrue(app.getUser().isAlertPresent());

    }



}
