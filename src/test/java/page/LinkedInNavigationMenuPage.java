package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedInNavigationMenuPage extends LinkedInBasePage {
    @FindBy(xpath = "//input[@placeholder='Search']")
    private WebElement searchField;

    @FindBy(xpath = "//*[@type='search-icon']")
    private WebElement submiteSearchIcon;

    String searchTerm;

    public LinkedInNavigationMenuPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public SearchResultsPage searchFor (String searchTerm){
        searchField.sendKeys(searchTerm);
        submiteSearchIcon.click();
        return new SearchResultsPage(driver);
    }
}
