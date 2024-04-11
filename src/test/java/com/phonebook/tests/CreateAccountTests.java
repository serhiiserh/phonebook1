package com.phonebook.tests;

import com.phonebook.data.UserData;
import com.phonebook.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateAccountTests extends TestBase {

    @BeforeMethod

    public void ensurePrecondition(){
        if(!app.getUser().isLoginLinkPresent()){
            app.getUser().clickOnSignOutButton();
        }
    }

    @Test(enabled = false)

    public void createNewAccountPositiveTest() {

        // click on Login link
        app.getUser().clickOnLoginLink();
        // enter email
        app.getUser().feelLoginRegisterForm(new User().setEmail(UserData.EMAIL).setPassword(UserData.PASSWORD));
        // click Registration Button
        app.getUser().clickOnRegistrationButton();
        // assert Sigh Out Button is present
        Assert.assertTrue(app.getUser().isSignOutButtonPresent());


    }

    @Test

    public void createNewAccountWithExistedEmailNegativeTest() {
        // click on Login link
        app.getUser().clickOnLoginLink();
        // enter email and password
        app.getUser().feelLoginRegisterForm(new User().setEmail(UserData.EMAIL).setPassword(UserData.PASSWORD));
        // click Registration Button
        app.getUser().clickOnRegistrationButton();
        // assert Sign Out Button is present
        //Assert.assertTrue(isHomeElementPresent(By.xpath("//button[.='Sign Out']")));
        // assert Alert is present
        Assert.assertTrue(app.getUser().isAlertPresent());


    }


    // 1. id
    // 2. className
    // 3. cssSelector
    // 4. xPath


}
