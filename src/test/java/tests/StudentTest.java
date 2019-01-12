package tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProfessorStudentPage;

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
}
