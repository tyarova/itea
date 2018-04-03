package page;

import org.openqa.selenium.NoSuchElementException;
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

    /**
     * {@inheritDoc}
     * Constructor for class LinkedInLoginPage
     */
    public LinkedInLoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * {@inheritDoc}
     * @return {@code true} if alertMessage has been loaded
     * @throws NoSuchElementException if alertMessage has not been loaded
     */
    @Override
    public boolean isLoaded(){
        boolean isLoaded = false;
        try {
            isLoaded = waitUntilElementIsClickable(alertMessage).isDisplayed();

        }catch (NoSuchElementException e){
            isLoaded = false;
        } return isLoaded;
    }

    /**
     * Get the error message from login form in case of using invalid email
     * @return text of the error message
     * @throws Exception if there is no error message
     */
    public String getEmailMessage() {
        try {
            waitUntilElementIsClickable(emailValidationMessage, 5);
                    } catch (Exception e){
            System.out.println("There is no email error message");
        }
        return emailValidationMessage.getText();
    }

    /**
     * Get the error message from login form in case of using invalid password
     * @return text of the error message
     * @throws Exception if there is no error message
     */
    public String getPasswordMessage() {
        try {
            waitUntilElementIsClickable(passwordValidationMessage, 5);
        }catch (Exception e){
            System.out.println("There is no password error message");
        }
        return passwordValidationMessage.getText();
    }
}
