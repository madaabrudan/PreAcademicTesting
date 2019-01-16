package tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProfessorStudentPage;

import java.io.File;

public class StudentTest extends BaseTest {

    HomePage homePage;
    ProfessorStudentPage professorStudentPage;
    private String username = "aiir2030";
    private String password = "mada";
    private String parola = "mada2";

    @BeforeClass
    public void setUpHomePage() {

        professorStudentPage = PageFactory.initElements(driver, ProfessorStudentPage.class);
        homePage = PageFactory.initElements(driver, HomePage.class);
    }

    @Test
    public void profesorProfile() {
        homePage.login(username, password);
        professorStudentPage.getProfile().click();
        professorStudentPage.changePassword();
        professorStudentPage.getCurrentPassword().sendKeys(password);
        professorStudentPage.getNewPassword().sendKeys(parola);
        professorStudentPage.getConfirmPassword().sendKeys(parola);
        professorStudentPage.saveButton();
        professorStudentPage.modalClose2();
        homePage.logout();
        homePage.login(username, parola);
        homePage.logout();
    }

    @Test
    public void changeBiggerThan1MbPhotoTest() {
        homePage.login(username, password);
        professorStudentPage.getProfile().click();

        File file = new File("src/test/resources/testdata/cornulete.png");
        String fullPath = file.getAbsolutePath();

        professorStudentPage.getChangePhoto().sendKeys(fullPath);

        Alert alertDialog = driver.switchTo().alert();
        String alertText = alertDialog.getText();

        Assert.assertEquals(alertText, "The dimension file has to be less than 1 MB.");
        alertDialog.accept();
    }

    @Test
    public void changeLessThan1MbPhotoTest() throws InterruptedException {
        homePage.login(username, password);
        professorStudentPage.getProfile().click();

        File file = new File("src/test/resources/testdata/poza.png");
        String fullPath = file.getAbsolutePath();

        professorStudentPage.getChangePhoto().sendKeys(fullPath);

        Thread.sleep(1000);
        String message = driver.findElement(professorStudentPage.getChangePhotoSuccessMessage()).getText();
        Assert.assertEquals(message, "Operation has succeeded");
        professorStudentPage.modalClose2();
    }

    @Test
    public void removePhotoTest() throws InterruptedException {
        changeLessThan1MbPhotoTest();
        professorStudentPage.clickRemovePhoto();

        professorStudentPage.modalClose2();
        String photoPath = professorStudentPage.getProfilePhotoPath();

        boolean isContained = photoPath.contains("images/profile.jpg");

        Assert.assertTrue(isContained);

    }

}
