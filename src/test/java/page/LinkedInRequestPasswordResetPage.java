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

    @FindBy(id = "userName-requestPasswordReset")
    private WebElement requestPasswordResetForm;

    @FindBy(xpath = "//input[@name= 'userName']")
    private WebElement userNameField;

    @FindBy(xpath = "//input[@name= 'request']")
    private WebElement submitBtton;


    public LinkedInRequestPasswordResetPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public boolean IsLoaded(){
        boolean isLoaded = false;
        try {
            isLoaded = requestPasswordResetForm.isDisplayed();

        }catch (NoSuchElementException e){
            isLoaded = false;
        } return isLoaded;
    }

    public LinkedInPasswordResetSubmitPage submitEmail(String userEmail) {
    userNameField.sendKeys(userEmail);
    submitBtton.click();
    return new LinkedInPasswordResetSubmitPage(driver);
    }
}


