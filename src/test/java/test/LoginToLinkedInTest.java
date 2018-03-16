package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import page.LinkedInHomePage;
import page.LinkedInLandingPage;
import page.LinkedInLoginPage;

public class LoginToLinkedInTest {
    WebDriver driver;
    LinkedInLandingPage landingPage;
    String initialPageTitle;
    String initialPageUrl;

    @BeforeMethod
    public void beforeTest() {
        driver = new FirefoxDriver();
        driver.get("https://www.linkedin.com/");
        landingPage = new LinkedInLandingPage(driver);
        initialPageTitle = landingPage.getPageTitle();
        initialPageUrl = landingPage.getPageUrl();
    }

    @AfterMethod
    public void afterTest() {
        driver.close();
    }

    @Test
    public void successfulLoginTest() throws InterruptedException {
        Assert.assertEquals(initialPageTitle, "LinkedIn: Log In or Sign Up",
                "Login page title is wrong");
        LinkedInHomePage homePage = landingPage.loginAs("testmedia@ukr.net", "qwertyQ1");
        Assert.assertTrue(homePage.isSignedIn(),
                "User is not signed in");
        Assert.assertNotEquals(homePage.getPageTitle(), initialPageTitle,
                "Page title has NOT been changed after Login");
        Assert.assertNotEquals(homePage.getPageUrl(), initialPageUrl,
                "Page URL has NOT been changed after Login");
    }

    @Test
    public void negativeLoginTest()  {
        Assert.assertEquals(initialPageTitle, "LinkedIn: Log In or Sign Up",
                "Login page title is wrong");
        LinkedInLoginPage loginPage = landingPage.loginAs("testmedia", "qwertyQ1");
        Assert.assertTrue(loginPage.isFailedLogIn(),
                "User has signed in");
        Assert.assertNotEquals(loginPage.getPageTitle(), initialPageTitle,
                "Page title has NOT been changed after Login");
        Assert.assertNotEquals(loginPage.getPageUrl(), initialPageUrl,
                "Page URL has NOT been changed after Login");
    }

    @Test
    public void negativeLoginTestWithEmptyFields() {
        Assert.assertEquals(initialPageTitle, "LinkedIn: Log In or Sign Up",
                "Login page title is wrong");
        landingPage.loginAs("", "testmedia");
        Assert.assertEquals(landingPage.getPageTitle(), initialPageTitle,
                "Page title has been changed after Login");
        Assert.assertEquals(landingPage.getPageUrl(), initialPageUrl,
                "Page URL has been changed after Login");
    }
}