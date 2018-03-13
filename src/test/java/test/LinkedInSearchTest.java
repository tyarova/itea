package test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.LinkedInHomePage;
import page.LinkedInLandingPage;
import page.LinkedInNavigationMenuPage;
import page.SearchResultsPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class LinkedInSearchTest {
    WebDriver driver;
    LinkedInLandingPage landingPage;
    SearchResultsPage searchResultsPage;
    LinkedInNavigationMenuPage navMenu;

    @BeforeMethod
    public void beforeTest() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.linkedin.com/");
        landingPage = new LinkedInLandingPage(driver);
        searchResultsPage = new SearchResultsPage(driver);
        navMenu = new LinkedInNavigationMenuPage(driver);
    }

    @AfterMethod
    public void afterTest() {
        driver.close();
    }

    @Test
    public void basicSearchTest() throws InterruptedException {
        LinkedInHomePage homePage = landingPage.loginAs("testmedia@ukr.net", "qwertyQ1");
        Assert.assertTrue(homePage.isSignedIn(),
                "User is not signed in");
        //search
        navMenu.searchFor("hr");
        //System.out.println("Search result includes " + searchResults.size() + " links");
        searchResultsPage.scrollSearchResults("HR");


        //to know what classes can be found by selenium
//        List<WebElement> results = driver.findElements(By.xpath("//ul[@class='search-results__list list-style-none']/li"));
//        for (WebElement ClassInfo : results) {
//            System.out.println(ClassInfo.getAttribute("class"));
//        }

        //to find 10 our links
//        List<WebElement> searchResults = driver.findElements(By.xpath("//li[contains(@class,'search-result__occluded-item')]"));
//        System.out.println("Search result includes " + searchResults.size() + " links");
//        Assert.assertEquals(searchResults.size(), 10, "There are less than 10 results have been found");

        //Scroll page:
//        for (int i = 0; i < searchResults.size(); i++) {
//            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", searchResults.get(i));
//            String cardTitle = driver.findElement(By.xpath("//li[contains(@class,'search-result__occluded-item')][" + (i+1) + "]//span[contains(@class, 'actor-name')]")).getText().toLowerCase();
//            //String cardTitle = driver.findElement(By.xpath("//li[contains(@class,'search-result__occluded-item')][" + (i + 1) + "]//p[contains(@class,'subline-level-1')]")).getText().toLowerCase();
//            System.out.println(cardTitle);
//            Assert.assertTrue(cardTitle.contains(searchTerm.toLowerCase())|cardTitle.contains(searchTerm1.toLowerCase()), "Search Term " + searchTerm + " not found");
//        }
//        List <WebElement> tryTwo = driver.findElements(By.xpath("//li[contains(@class,'search-result__occluded-item')]"));
//        for(WebElement x: tryTwo){
//            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", x);
//            String elementTitle = x.getText();
//            System.out.println();
//            System.out.println(elementTitle);
//            //Assert.assertEquals(tryTwo.size(), 10, "There are less than 10 results have been found");
//            Assert.assertTrue(elementTitle.contains(searchTerm.toLowerCase()), "Search Term " + searchTerm + " not found");
//
//        }
    }


}




