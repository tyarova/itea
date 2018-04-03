package page;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedInRequestPasswordResetPage extends LinkedInBasePage {

    @FindBy(xpath = "//a[@class='link-forgot-password']")
    private WebElement forgotPasswordLink;

    @FindBy(id = "login-email")
    private WebElement emailField;

    @FindBy(xpath = "//input[@name= 'userName']")
    private WebElement userNameField;

    @FindBy(xpath = "//button[@type= 'submit']")
    private WebElement submitBtton;

    /**
     * {@inheritDoc}
     * Constructor for class LinkedInRequestPasswordResetPage
     */
    public LinkedInRequestPasswordResetPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * {@inheritDoc}
     * @return {@code true} if userNameField has been loaded
     * @throws NoSuchElementException if userNameField has not been loaded
     */
    @Override
    public boolean isLoaded(){
        boolean isLoaded = false;
        try {
            isLoaded = userNameField.isDisplayed();

        }catch (NoSuchElementException e){
            isLoaded = false;
        } return isLoaded;
    }

    /**
     * Submit the email to which reset password link is going to be sent
     * @param userEmail - the email address to which reset password link is going to be sent
     * @return new page object
     */
    public LinkedInPasswordResetSubmitPage submitEmail(String userEmail) {
    userNameField.sendKeys(userEmail);
    submitBtton.click();
    return new LinkedInPasswordResetSubmitPage(driver);
    }
}


