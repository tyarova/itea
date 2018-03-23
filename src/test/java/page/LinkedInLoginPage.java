package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedInLoginPage extends LinkedInBasePage {

    @FindBy(xpath = "//div[@id='global-alert-queue']//strong[not(text()='')]")
    private WebElement alertMessage;

    @FindBy(id = "session_key-login")
    private WebElement emailField;

    @FindBy(id = "session_password-login")
    private WebElement passwordField;

    @FindBy(id = "btn-primary")
    private WebElement signInButton;

    @FindBy(id = "session_key-login-error")
    private WebElement emailValidationMessage;

    @FindBy(id = "session_password-login-error")
    private WebElement passwordValidationMessage;

    public LinkedInLoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isFailedLogIn() {
        waitUntilElementIsClickable(alertMessage);
        return alertMessage.isDisplayed();
    }

    public String getEmailMessage() {
        try {
            waitUntilElementIsClickable(emailValidationMessage, 5);
                    } catch (Exception e){
            System.out.println("There is no email error message");
        }
        return emailValidationMessage.getText();
    }

    public String getPasswordMessage() {
        try {
            waitUntilElementIsClickable(passwordValidationMessage, 5);
        }catch (Exception e){
            System.out.println("There is no password error message");
        }
        return passwordValidationMessage.getText();
    }
}
