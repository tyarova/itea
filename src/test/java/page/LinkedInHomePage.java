package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import page.LinkedInBasePage;

public class LinkedInHomePage extends LinkedInBasePage {

    @FindBy(xpath = "//*[@id='nav-settings__dropdown-trigger']")
    WebElement settingsControl;

    public LinkedInHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public boolean isSignedIn(){
        waitUntilElementIsClickable(settingsControl);
        return settingsControl.isDisplayed();
    }
}
