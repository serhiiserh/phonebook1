package com.phonebook.tests;

import com.phonebook.data.UserData;
import com.phonebook.models.Contact;
import com.phonebook.models.User;
import com.phonebook.utils.DataProviders;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AddContactTests extends TestBase {

    @BeforeMethod

    public void ensurePrecondition() {
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }
        app.getUser().clickOnLoginLink();
        // enter email
        app.getUser().feelLoginRegisterForm(new User().setEmail(UserData.EMAIL).setPassword(UserData.PASSWORD));
        // click on Login button
        app.getUser().click(By.cssSelector("[name='login']"));
    }

    @Test

    public void addContactPositiveTest() {
        // click on Add link
        app.getContact().clickOnAddLink();
        // enter name
        app.getContact().fillAddContactForm(new Contact()
                .setName("Vitja")
                .setLastName("Vitkov")
                .setPhone("123456987789")
                .setEmail("vitkov_vitja1221@gmail.com")
                .setAddress("La-Valletta")
                .setDescription("Brodjaga"));
        // click on Save button
        app.getContact().clickOnSaveButton();
        // assert Contact is added by text
        Assert.assertTrue(app.getContact().isContactCreated("Vitja"));
    }

    @AfterMethod
    public void postCondition() {
        app.getContact().removeContact();
    }



    @Test(dataProvider = "addNewContact", dataProviderClass = DataProviders.class)
    public void addContactPositiveTestFromDataProvider(String name, String lastName, String phone, String email, String address, String description) {
        app.getContact().clickOnAddLink();
        app.getContact().fillAddContactForm(new Contact()
                .setName(name)
                .setLastName(lastName)
                .setPhone(phone)
                .setEmail(email)
                .setAddress(address)
                .setDescription(description));
        app.getContact().clickOnSaveButton();
        Assert.assertTrue(app.getContact().isContactCreated(name));
    }



    @Test(dataProvider = "addNewContactFromCsvFile", dataProviderClass = DataProviders.class)
    public void addContactPositiveTestFromDataProviderWithCsvFile(Contact contact) {
        app.getContact().clickOnAddLink();
        app.getContact().fillAddContactForm(contact);
        app.getContact().clickOnSaveButton();
        Assert.assertTrue(app.getContact().isContactCreated(contact.getName()));
    }
}
