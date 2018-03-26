package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedInResetPasswordSuccessPage extends LinkedInBasePage {
    @FindBy(xpath = "//div[@class= 'password-reset password-reset-success flow-login no-container  phone-collection']")
    private WebElement passwordResetSuccessForm;

    @FindBy(xpath = "//div[@class= '//a[@class= 'btn-secondary-transparent']")
    private WebElement goToHomePageButton;

    public LinkedInResetPasswordSuccessPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public LinkedInHomePage loginWithNewPassword(String password) {
        goToHomePageButton.click();
        return new LinkedInHomePage(driver);
    }
}
