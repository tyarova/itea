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


    public LinkedInLandingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
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
//        if (getPageUrl().equals("https://www.linkedin.com/uas/login-submit")) {
//            return (T) PageFactory.initElements(driver, LinkedInLoginPage.class);
//        } else if (getPageTitle().equals("LinkedIn: Log In or Sign Up")) {
//            return (T) PageFactory.initElements(driver, LinkedInLandingPage.class);
//        } else {
//            return (T) PageFactory.initElements(driver, LinkedInHomePage.class);
//        }
    }

}
