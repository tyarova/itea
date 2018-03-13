package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class SearchResultsPage extends LinkedInBasePage {
    @FindBy(xpath = "//li[contains(@class,'search-result__occluded-item')]")
    private List<WebElement> searchResults;

    @FindBy(xpath = "//div[@class= 'search-results top-page ember-view']")
    private WebElement serchResultsBlock;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean resultsAreShown(){
        waitUntilElementIsVisible(serchResultsBlock, 5);
        return serchResultsBlock.isDisplayed();
    }

    public void scrollSearchResults() {
        for(WebElement x: searchResults){
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", x);
        }
    }

    public int countSearchResults(){
        return searchResults.size();
    }

    public boolean searchResultsContainSearchTerm(String searchTerm){
        boolean flag = false;
        for(WebElement x: searchResults) {
            String elementTitle = x.getText().toLowerCase();
            System.out.println();
            System.out.println(elementTitle);
            if(elementTitle.contains(searchTerm.toLowerCase()))
                flag = true;
        }
        return flag;
    }
}
