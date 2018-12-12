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
    public void succesfulloginTest() throws Exception {

        homePage.enterUserName("guran");
        homePage.enterPassword("guran");
        homePage.submitCredentials();

    }
}