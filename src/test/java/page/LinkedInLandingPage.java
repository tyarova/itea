package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

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

    public LinkedInHomePage loginAs(String email, String password) throws InterruptedException {
        sleep(5000);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        signInButton.click();
        return new LinkedInHomePage(driver);
    }

//    public <T> T loginAs(String email, String password) {
//        waitUntilElementIsClickable(emailField, 5);
//        emailField.sendKeys(email);
//        passwordField.sendKeys(password);
//        signInButton.click();
//        if (signInButton.isDisplayed()){
//            return (T) LinkedInLoginPage.class;
//        }
//        else {
//            return (T) PageFactory.initElements(driver, LinkedInLandingPage.class);
//        }
//           }

}
