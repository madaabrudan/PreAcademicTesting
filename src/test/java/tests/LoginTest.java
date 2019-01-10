package tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;

public class LoginTest extends BaseTest {

    HomePage homePage;

    @BeforeClass
    public void setUpHomePage() {

        homePage = PageFactory.initElements(driver, HomePage.class);
    }

    @Test
    public void succesfulloginAndLogoutTest() throws Exception {
        homePage.login("guran", "guran");
        homePage.logout();
        homePage.submitCredentials();
    }

    @Test
    public void errorMessagesTestBothFields() throws Exception {
        homePage.login("", "");

        Assert.assertEquals(homePage.getUserNameErrorMessage(),"Username field is mandatory", "Testing the Username Error Message");
        Assert.assertEquals(homePage.getPasswordErrorMessage(),"Password field is mandatory", "Testing the Password Error Message");
    }

    @Test
    public void errorMessagesTestForUsernameAndNotPassword() throws Exception {
        homePage.login("", "Mada");

        Assert.assertEquals(homePage.getUserNameErrorMessage(),"Username field is mandatory", "Testing the Username Error Message");
        Assert.assertEquals(homePage.getPasswordErrorMessage(),"", "Testing the Absence Password Error Message");

        homePage.clearPassword();
    }

    @Test
    public void errorMessagesTestForPasswordAndNotUsername() throws Exception {
        homePage.login("guran", "");

        Assert.assertEquals(homePage.getUserNameErrorMessage(),"", "Testing the Absence Username Error Message");
        Assert.assertEquals(homePage.getPasswordErrorMessage(),"Password field is mandatory", "Testing the Password Error Message");

        homePage.clearUsername();
    }

    @Test
    public void errorMessagesForInvalidCredentialsTest() throws Exception {
        homePage.login("mada", "aaa");

        Assert.assertEquals(homePage.getUserNameErrorMessage(),"", "Testing the Username Error Message");
        Assert.assertEquals(homePage.getPasswordErrorMessage(),"", "Testing the Password Error Message");
        Assert.assertEquals(homePage.getInvalidErrorMessage(),"Invalid username or password!", "Testing the Password Error Message");
    }
}