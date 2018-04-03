package page;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedInHomePage extends LinkedInBasePage {

    @FindBy(xpath = "//*[@id='nav-settings__dropdown-trigger']")
    WebElement settingsControl;

    @FindBy(xpath = "//input[@role='combobox']")
    private WebElement searchField;

    @FindBy(xpath = "//li-icon[@type='search-icon']")
    private WebElement submiteSearchIcon;

    /**
     * Constructor of LinkedInHomePage class that takes instance from LinkedInBasePage class and
     * initialize LinkedInHomePage WebElements via PageFactory
     * @param driver - Webdriver instance
     */
    public LinkedInHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * {@inheritDoc}
     * @return {@code true} if settingsControl has been loaded
     * @throws NoSuchElementException if settingsControl has not been loaded
     */
    @Override
    public boolean isLoaded(){
        boolean isLoaded = false;
        try {
            isLoaded = waitUntilElementIsClickable(settingsControl).isDisplayed();
        }catch (NoSuchElementException e){
            isLoaded = false;
        } return isLoaded;
    }

    /**
     * Perform search
     * @param searchTerm - data we are searching for
     * @return new page object
     */
    public LinkedInSearchPage searchFor (String searchTerm){
        searchField.sendKeys(searchTerm);
        submiteSearchIcon.click();
        return new LinkedInSearchPage(driver);
    }
}
