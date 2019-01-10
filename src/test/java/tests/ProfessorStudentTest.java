package tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProfessorStudentPage;

public class ProfessorStudentTest extends BaseTest {

    HomePage homePage;
    ProfessorStudentPage professorStudentPage;
    private String guran = "guran";
    private String grade = "8.00";
    private String presence = "0.00% / 7.14%";

    @BeforeClass
    public void setUpHomePage() {

        professorStudentPage = PageFactory.initElements(driver, ProfessorStudentPage.class);
        homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.login("guran", "guran");
    }

    @Test
    public void giveNoteAndThenVerrify() {
        //professor do
        professorStudentPage.getGroup().click();
        professorStudentPage.enterGrade(grade);
        professorStudentPage.markPresence();
        professorStudentPage.submit();
        professorStudentPage.modalClick();
        Assert.assertEquals(professorStudentPage.getPresencePercentage(), presence);
        Assert.assertEquals(professorStudentPage.getAverageGrade(), grade);
        homePage.logout();

        //student verify
        homePage.login("aiir2030", "mada");
        Assert.assertEquals(professorStudentPage.getPresence().getAttribute("checked"), "true");
        Assert.assertEquals(professorStudentPage.getPresencePercentage(), presence);
        Assert.assertEquals(professorStudentPage.getAverageGrade(), grade);
        homePage.logout();
    }
}
