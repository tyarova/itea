package page;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedInChooseNewPasswordPage extends LinkedInBasePage{

    @FindBy(xpath = "//input[@name='newPassword']")
    private WebElement newPasswordField;

    @FindBy(xpath = "//input[@name='confirmPassword']")
    private WebElement newPasswordRetypeField;

    @FindBy(xpath = "//button[@class='form__submit']")
    private WebElement submitButton;

    /**
     * {@inheritDoc}
     * Constructor for class LinkedInChooseNewPasswordPage
     */
    public LinkedInChooseNewPasswordPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * {@inheritDoc}
     * @return {@code true} if newPasswordField has been loaded
     * @throws NoSuchElementException if newPasswordField has not been loaded
     */
    @Override
    public boolean isLoaded(){
        boolean isLoaded;
        try {
            isLoaded = newPasswordField.isDisplayed();
        }catch (NoSuchElementException e){
            isLoaded = false;
        }return isLoaded;
    }

    /**
     * Submit new password, that has been entered into the field
     * @param password - value of the new password
     * @return new page object
     */
    public LinkedInResetPasswordSuccessPage submitNewPassword(String password) {
        newPasswordField.sendKeys(password);
        newPasswordRetypeField.sendKeys(password);
        waitUntilElementIsClickable(submitButton).click();
        return new LinkedInResetPasswordSuccessPage(driver);
    }


}
