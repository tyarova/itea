package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import page.LinkedInLandingPage;

public class LinkedInBaseTest {
    WebDriver driver;
    LinkedInLandingPage landingPage;

    @BeforeMethod
    public void beforeTest() {
        driver = new FirefoxDriver();
        driver.get("https://www.linkedin.com/");
        landingPage = new LinkedInLandingPage(driver);
    }

    @AfterMethod
    public void afterTest() {
        driver.close();
    }
}
