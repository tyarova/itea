package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class SearchResultsPage extends LinkedInBasePage {
    @FindBy(xpath = "//li[contains(@class,'search-result__occluded-item')]")
    private List<WebElement> searchResults;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void scrollSearchResults(String searchTerm) {
        for(WebElement x: searchResults){
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", x);
            String elementTitle = x.getText().toLowerCase();
            System.out.println();
            System.out.println(elementTitle);
            Assert.assertTrue(elementTitle.contains(searchTerm.toLowerCase()), "Search Term " + searchTerm + " not found");
        }
    }
}
