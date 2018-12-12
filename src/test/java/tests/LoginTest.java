package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;

import java.util.List;

public class LoginTest extends BaseTest {


    @BeforeMethod
    public void setUpHomePage() {

        homePage = PageFactory.initElements(driver, HomePage.class);
    }

    @Test
    public void succesfulloginAndLogoutTest() throws Exception {
        homePage.login("guran", "guran");
        Thread.sleep(500);
        homePage.logout();
        homePage.submitCredentials();
    }

    @Test
    public void errorMessagesTestBothFields() throws Exception {
        homePage.login("", "");
        Thread.sleep(500);

        Assert.assertEquals(homePage.getUserNameErrorMessage(),"Username field is mandatory", "Testing the Username Error Message");
        Assert.assertEquals(homePage.getPasswordErrorMessage(),"Password field is mandatory", "Testing the Password Error Message");

    }

    @Test
    public void errorMessagesTestForUsernameAndNotPassword() throws Exception {
        homePage.login("", "Mada");
        Thread.sleep(500);

        Assert.assertEquals(homePage.getUserNameErrorMessage(),"Username field is mandatory", "Testing the Username Error Message");
        Assert.assertEquals(homePage.getPasswordErrorMessage(),"", "Testing the Absence Password Error Message");

    }

    @Test
    public void errorMessagesTestForPasswordAndNotUsername() throws Exception {
        homePage.login("guran", "");
        Thread.sleep(500);

        Assert.assertEquals(homePage.getUserNameErrorMessage(),"", "Testing the Absence Username Error Message");
        Assert.assertEquals(homePage.getPasswordErrorMessage(),"Password field is mandatory", "Testing the Password Error Message");

    }

    @Test
    public void errorMessagesForInvalidCredentialsTest() throws Exception {
        homePage.login("mada", "aaa");
        Thread.sleep(500);

        Assert.assertEquals(homePage.getUserNameErrorMessage(),"", "Testing the Username Error Message");
        Assert.assertEquals(homePage.getPasswordErrorMessage(),"", "Testing the Password Error Message");
        Assert.assertEquals(homePage.getInvalidErrorMessage(),"Invalid username or password!", "Testing the Password Error Message");

    }

}