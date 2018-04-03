package page;

import org.openqa.selenium.NoSuchElementException;
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

    /**
     * {@inheritDoc}
     * Constructor for class LinkedInLandingPage
     */
    public LinkedInLandingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * {@inheritDoc}
     * @return {@code true} if loginForm has been loaded
     * @throws NoSuchElementException if loginForm has not been loaded
     */
    @Override
    public boolean isLoaded(){
        boolean isLoaded = false;
        try {
            isLoaded = waitUntilElementIsVisible(loginForm, 5).isDisplayed();
        }catch (NoSuchElementException e){
            isLoaded = false;
        } return isLoaded;
    }

    /**
     * Click on forgot password link
     * @return new page object
     */
    public LinkedInRequestPasswordResetPage forgotPasswordLinkClick(){
        forgotPasswordLink.click();
        return new LinkedInRequestPasswordResetPage(driver);
    }

    /**
     * Login to the site using different credentials
     * @param email - email, that has been used for login
     * @param password - password, that has been used for login
     * @param <T> - type of the used parameters, that returns appropriate page object
     * @return new page object, depending on type of used parameters
     */
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
