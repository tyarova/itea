package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class LinkedInSearchPage extends LinkedInBasePage {
    @FindBy(xpath = "//li[contains(@class,'search-result__occluded-item')]")
    private List<WebElement> searchResults;

    @FindBy(xpath = "//div[@class= 'search-results top-page ember-view']")
    private WebElement serchResultsContainer;

    public LinkedInSearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean resultsAreShown(){
        waitUntilElementIsVisible(serchResultsContainer, 5);
        return serchResultsContainer.isDisplayed();
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

    public List<String> getResults() {
        waitUntilElementIsVisible(serchResultsContainer, 5);
        List<String> resultsStringList = new ArrayList();
        for(WebElement x: searchResults) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", x);
            String elementTitle = x.getText().toLowerCase();
            resultsStringList.add(elementTitle);
            //System.out.println();
            //System.out.println(elementTitle);
        }
        return resultsStringList;
    }
}
