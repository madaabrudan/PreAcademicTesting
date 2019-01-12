package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProfessorStudentPage;

public class ProfesorTest extends BaseTest {

    HomePage homePage;
    ProfessorStudentPage professorStudentPage;
    private String guran = "guran";
    private String mihis = "mihis";
    private String motogna = "motogna";
    private String camelia = "camelia";
    private String forest = "forest";
    private String ticle = "ticle";
    private String suciu = "suciu";
    private String password = "password";

    @BeforeClass
    public void setUpHomePage() {

        professorStudentPage = PageFactory.initElements(driver, ProfessorStudentPage.class);
        homePage = PageFactory.initElements(driver, HomePage.class);
    }

    public void profesorGroups(String prof, int nrGroup) {
        homePage.login(prof, prof);
        int noGroups = driver.findElements(professorStudentPage.getNoGroups()).size();
        Assert.assertEquals(noGroups, nrGroup);
        homePage.logout();
    }

    @Test
    public void guranGroups() {
        profesorGroups(guran, 4);
    }

    @Test
    public void mihisGroups() {
        profesorGroups(mihis, 2);
    }

    @Test
    public void motognaGroups() {
        profesorGroups(motogna, 1);
    }

    @Test
    public void cameliaGroups() {
        profesorGroups(camelia, 3);
    }

    @Test
    public void ticleGroups() {
        profesorGroups(ticle, 2);
    }

    @Test
    public void forestGroups() {
        profesorGroups(forest, 6);
    }

    @Test
    public void suciuGroups() {
        profesorGroups(suciu, 3);
    }

    @Test(enabled = false)
    public void profesorSettings() throws InterruptedException {
        homePage.login(guran, guran);
        professorStudentPage.getSettings();
        professorStudentPage.getSeminarPartial().click();
        //professorStudentPage.getSeminarPartial().sendKeys("value", "2");
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("document.getElementById('spinner_nrOfSeminarPartialExams').value='2';");
        professorStudentPage.getSeminarPartial().sendKeys(Keys.RETURN);
        //professorStudentPage.getSeminarPartialProcent().click();
        jse.executeScript("document.getElementById('lessonWeight-29').value='7';");
        Thread.sleep(1000);
        //professorStudentPage.getSeminar2PartialProcent().click();
        jse.executeScript("document.getElementById('lessonWeight--1').value='7';");
        professorStudentPage.getProfesorSave();
        homePage.logout();
    }

    @Test
    public void studentView() {
        homePage.login(guran, guran);
        professorStudentPage.getGroup().click();
        professorStudentPage.getStudent();
        Assert.assertEquals(professorStudentPage.getModalStudent().isDisplayed(), true);
        professorStudentPage.modalClose();
        homePage.logout();
    }

    @Test
    public void profesorProfile() {
        homePage.login(guran, guran);
        professorStudentPage.getProfile();
        professorStudentPage.changePassword();
        professorStudentPage.getCurrentPassword().sendKeys(guran);
        professorStudentPage.getNewPassword().sendKeys(password);
        professorStudentPage.getConfirmPassword().sendKeys(password);
        professorStudentPage.saveButton();
        professorStudentPage.modalClose2();
        homePage.logout();
        homePage.login(guran, password);
        homePage.logout();
    }
}
