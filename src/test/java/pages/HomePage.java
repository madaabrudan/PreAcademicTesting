package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage {

    @FindBy(how = How.CSS, using = "input#lbl_username")
    private WebElement inputUserName;

    @FindBy(how = How.CSS, using = "input#lbl_password")
    private WebElement inputPassword;

    @FindBy(how = How.CSS, using = "input#btn_submitLoginForm")
    private WebElement submitCredentials;

    public void enterUserName(String userName){
        inputUserName.sendKeys(userName);
    }

    public void enterPassword(String password){
        inputPassword.sendKeys(password);
    }

    public void submitCredentials( ){
        submitCredentials.click();
    }
}