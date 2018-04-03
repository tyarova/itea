package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class LinkedInSearchPage extends LinkedInBasePage {

    @FindBy(xpath = "//li[contains(@class,'search-result__occluded-item')]")
    private List<WebElement> searchResults;

    @FindBy(xpath = "//div[@class='search-results top-page ember-view']")
    private WebElement searchResultsContainer;

    /**
     * {@inheritDoc}
     * Constructor for class LinkedInSearchPage
     */
    public LinkedInSearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * {@inheritDoc}
     * @return {@code true} if searchResultsContainer has been loaded
     * @throws NoSuchElementException if searchResultsContainer has not been loaded
     */
    @Override
    public boolean isLoaded(){
        boolean isLoaded = false;
        try {
            isLoaded = waitUntilElementIsVisible(searchResultsContainer).isDisplayed();
        }catch (NoSuchElementException e){
            isLoaded = false;
        } return isLoaded;
    }

    /**
     * Scroll the page with search results
     */
    public void scrollSearchResults() {
        for(WebElement x: searchResults){
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", x);
        }
    }

    /**
     * Count search results
     * @return quantity of search results
     */
    public int countSearchResults(){
        return searchResults.size();
    }

    /**
     * Return {@code true} if each search result contains search term
     * @param searchTerm - the term, used for search
     * @return {@code true} if each search result contains search term
     */
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

    /**
     * Get the list of results, triggered by search
     * @return a list of results, triggered by search
     */
    public List<String> getResults() {
        waitUntilElementIsVisible(searchResultsContainer, 5);
        List<String> resultsStringList = new ArrayList();
        for(WebElement x: searchResults) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", x);
            String elementTitle = x.getText().toLowerCase();
            resultsStringList.add(elementTitle);
        }
        return resultsStringList;
    }
}
