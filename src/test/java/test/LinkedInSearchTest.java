package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.LinkedInHomePage;
import page.LinkedInLandingPage;
import page.LinkedInSearchPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class LinkedInSearchTest {
    WebDriver driver;
    LinkedInLandingPage landingPage;
    String searchRequest;

    @BeforeMethod
    public void beforeTest() {
        driver = new FirefoxDriver();
        driver.get("https://www.linkedin.com/");
        searchRequest = "hr";
        landingPage = new LinkedInLandingPage(driver);
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
        LinkedInSearchPage searchResultsPage = homePage.searchFor(searchRequest);
        Assert.assertTrue(searchResultsPage.resultsAreShown(),
                "Search Result Block is not shown");
        searchResultsPage.scrollSearchResults();
        Assert.assertEquals(searchResultsPage.countSearchResults(), 10,
                "There are less than 10 results have been found");
        Assert.assertTrue(searchResultsPage.searchResultsContainSearchTerm(searchRequest),
                "Some of the results do not contain search request");
    }
    @Test
    public void basicSearchTestTeacherVersion(){
        LinkedInHomePage homePage = landingPage.loginAs("testmedia@ukr.net", "qwertyQ1");
        LinkedInSearchPage searchResultsPage = homePage.searchFor(searchRequest);
        List<String> results = searchResultsPage.getResults();

        Assert.assertEquals(results.size(), 10,"There are less than 10 results have been found");

        for (String result: results){
            Assert.assertTrue(result.toLowerCase().contains(searchRequest),
                    "Searchterm "+searchRequest+ "not found in title");
        }
    }

}




