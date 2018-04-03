package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.LinkedInHomePage;
import page.LinkedInSearchPage;
import java.util.List;

public class LinkedInSearchTest extends LinkedInBaseTest {
    String searchRequest;

    /**
     * Test that verifies basic Search by specific Search term
     */
    @Test
    public void basicSearchTest(){
        searchRequest = "hr";
        LinkedInHomePage homePage = landingPage.loginAs("testmedia@ukr.net", "passworD1");
        Assert.assertTrue(homePage.isLoaded(),
                "User is not signed in");
        LinkedInSearchPage searchResultsPage = homePage.searchFor(searchRequest);
        Assert.assertTrue(searchResultsPage.isLoaded(), "Search Result Block is not shown");
        searchResultsPage.scrollSearchResults();
        Assert.assertEquals(searchResultsPage.countSearchResults(), 10,
                "There are less than 10 results have been found");
        Assert.assertTrue(searchResultsPage.searchResultsContainSearchTerm(searchRequest),
                "Some of the results do not contain search request");
    }

    /**
     * Test that verifies basic Search by specific Search term
     */
    @Test
    public void basicSearchTestTeacherVersion(){
        searchRequest = "hr";
        LinkedInHomePage homePage = landingPage.loginAs("testmedia@ukr.net", "qwertYQ1y");
        LinkedInSearchPage searchResultsPage = homePage.searchFor(searchRequest);
        List<String> results = searchResultsPage.getResults();

        Assert.assertEquals(results.size(), 10,"There are less than 10 results have been found");

        for (String result: results){
            Assert.assertTrue(result.toLowerCase().contains(searchRequest),
                    "Searchterm "+searchRequest+ "not found in title");
        }
    }
}




