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

    @FindBy(how = How.CSS, using = "a[href=\"/logout\"]")
    private WebElement logout;

    @FindBy(how = How.CSS, using = "div#err_username")
    private WebElement userNameErrorMessage;

    @FindBy(how = How.CSS, using = "div#err_password")
    private WebElement passwordErrorMessage;

    @FindBy(how = How.CSS, using = "span.err")
    private WebElement invalidCredentialsErrorMessage;

    private void enterUserName(String userName){
        inputUserName.sendKeys(userName);
    }

    private void enterPassword(String password){
        inputPassword.sendKeys(password);
    }

    public void submitCredentials( ){
        submitCredentials.click();
    }

    public void login(String userName, String password){
        enterUserName(userName);
        enterPassword(password);
        submitCredentials();
    }

    public void logout(){
        logout.click();
    }

    public String getUserNameErrorMessage(){
        return userNameErrorMessage.getText();
    }

    public String getPasswordErrorMessage(){
        return passwordErrorMessage.getText();
    }

    public String getInvalidErrorMessage(){
        return invalidCredentialsErrorMessage.getText();
    }
}