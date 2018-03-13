package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedInLoginPage extends LinkedInBasePage {

    @FindBy(xpath = "//div[@id='global-alert-queue']//strong[not(text()='')]")
    private WebElement alertMessage;

    public LinkedInLoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isFailedLogIn() throws InterruptedException {
        waitUntilElementIsClickable(alertMessage);
        return alertMessage.isDisplayed();
    }
}
