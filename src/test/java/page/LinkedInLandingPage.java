package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedInLandingPage extends LinkedInBasePage {
    @FindBy(id = "login-email")
    private WebElement emailField;

    @FindBy(id = "login-password")
    private WebElement passwordField;

    @FindBy(id = "login-submit")
    private WebElement signInButton;

    @FindBy(xpath = "//form[@class= 'login-form']")
    private WebElement loginForm;

    @FindBy(xpath = "//a[@class='link-forgot-password']")
    private WebElement forgotPasswordLink;

    public LinkedInLandingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean stayOnLandingPage(){
        waitUntilElementIsVisible(loginForm, 5);
        return loginForm.isDisplayed();
    }

    public LinkedInRequestPasswordResetPage forgotPasswordLinkClick(){
        forgotPasswordLink.click();
        return new LinkedInRequestPasswordResetPage(driver);
    }

    public <T> T loginAs(String email, String password) {
        waitUntilElementIsClickable(emailField, 5);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        signInButton.click();
        if (getPageUrl().contains("/feed")) {
            return (T) new LinkedInHomePage(driver);
        }
        else if(getPageUrl().contains("/login-submit")){
            return (T) new LinkedInLoginPage(driver);
        }
        else {
            return (T) this;
        }
    }

}
