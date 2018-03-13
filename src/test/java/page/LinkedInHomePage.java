package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedInHomePage extends LinkedInBasePage {

    @FindBy(xpath = "//*[@id='nav-settings__dropdown-trigger']")
    WebElement settingsControl;

    @FindBy(xpath = "//input[@placeholder='Search']")
    private WebElement searchField;

    @FindBy(xpath = "//*[@type='search-icon']")
    private WebElement submiteSearchIcon;

    public LinkedInHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public boolean isSignedIn(){
        waitUntilElementIsClickable(settingsControl);
        return settingsControl.isDisplayed();
    }
    public SearchResultsPage searchFor (String searchTerm){
        searchField.sendKeys(searchTerm);
        submiteSearchIcon.click();
        return new SearchResultsPage(driver);
    }
}
