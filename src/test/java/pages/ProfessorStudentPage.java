package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProfessorStudentPage {


    @FindBy(how = How.CSS, using = "div a[href='/professor/timeline?course=MLR5023&group=231']")
    private WebElement group;

    @FindBy(how = How.CSS, using = "td[id='lessonCell-1'] input[class='grade-field']")
    private WebElement grade;

    @FindBy(how = How.CSS, using = "td[id='lessonCell-1'] input[class='attendance-field']")
    private WebElement presence;

    @FindBy(how = How.CSS, using = "tr[id='enrollmentRow-1'] td[class*='attendances-cell']")
    private WebElement presencePercentage;

    @FindBy(how = How.CSS, using = "tr[id='enrollmentRow-1'] td[class='average-cell']")
    private WebElement averageGrade;

    @FindBy(how = How.CSS, using = "input[type='button']")
    private WebElement btn;

    @FindBy(how = How.CSS, using = "input[class*='modal-close']")
    private WebElement modal;

    @FindBy(how = How.CSS, using = ".svg-inline--fa.fa-cog.fa-w-16")
    private WebElement settings;

    @FindBy(how = How.CSS, using = "input[id='spinner_nrOfSeminarPartialExams']")
    private WebElement seminarPartial;

    @FindBy(how = How.CSS, using = "input[id='lessonWeight-29'")
    private WebElement seminarPartialProcent;

    @FindBy(how = How.CSS, using = "input[id='lessonWeight-1'")
    private WebElement seminar2PartialProcent;

    @FindBy(how = How.CSS, using = ".professor-course-save-btn.modal-close")
    private WebElement profesorSave;

    By noGroups = By.xpath("//div/a[@class='group']");

    public By getNoGroups() {
        return noGroups;
    }

    public WebElement getGroup() {
        return group;
    }

    public void enterGrade(String value){
        grade.clear();
        grade.sendKeys(value);
    }

    public void markPresence() {
        if(presence.isSelected())
            return;
        presence.click();
    }

    public WebElement getPresence() {
        return presence;
    }

    public String getPresencePercentage() {
        return presencePercentage.getText();
    }

    public String getAverageGrade() {
        return averageGrade.getText();
    }

    public void submit() {
        btn.click();
    }

    public void modalClick() {
        modal.click();
    }

    public void getSettings() { settings.click(); }

    public WebElement getSeminarPartial() { return seminarPartial; }

    public WebElement getSeminarPartialProcent() { return seminarPartialProcent; }

    public WebElement getSeminar2PartialProcent() { return seminar2PartialProcent; }

    public void getProfesorSave() { profesorSave.click(); }
}
