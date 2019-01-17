package tests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProfessorStudentPage;

import java.util.List;

import static jdk.nashorn.internal.objects.NativeMath.round;

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
        homePage.login(guran, guran);
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

    @Test
    public void justGradesInputAverageTest() throws InterruptedException {
        professorStudentPage.getGroup().click();

        List<WebElement> firstStudentGradeList = driver.findElements(professorStudentPage.getFirstStudentGradeList());

        double actualSum = 0;

        for (int i = 0; i < 8; i++) {
            firstStudentGradeList.get(i).sendKeys("10");
        }

        firstStudentGradeList = driver.findElements(professorStudentPage.getFirstStudentGradeList());

        for (int i = 0; i < 7; i++) {
            String gradeValue = firstStudentGradeList.get(i).getAttribute("value");
            double grade = Double.parseDouble(gradeValue);
            grade = grade * 0.07;
            actualSum = actualSum + grade;
        }

        actualSum = actualSum + Double.parseDouble(firstStudentGradeList.get(7).getAttribute("value")) * 0.09;

        Thread.sleep(1000);
        driver.findElement(professorStudentPage.languageDropDown).click();
        Thread.sleep(1000);

        double expectedSum = Double.parseDouble(professorStudentPage.getFirstAverageCell().getText());

        actualSum = Math.round(actualSum * 100.00) / 100.00;

        Assert.assertEquals(actualSum, expectedSum);
    }

}
