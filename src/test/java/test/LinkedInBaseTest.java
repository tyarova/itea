package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import page.LinkedInLandingPage;

public class LinkedInBaseTest {

    WebDriver driver;
    LinkedInLandingPage landingPage;
    String initialPageTitle;
    String initialPageUrl;

    /**
     * Chain of calls to run before each test method:
     * 1.downloads the latest version of the WebDriver binary and exports the proper Java system variable
     * 2.creates the driver object, depending on browser type
     * 3.opens environment link
     * 4. creates new object of the GoogleStartPage class
     * @param browserType - the browser(Firefox, Chrome), used for test run
     * @param envURL - the environment link, used for test run
     */
    @Parameters({"browserType", "envURL"})
    @BeforeMethod
    public void beforeTest(@Optional("chrome") String browserType, @Optional("https://www.linkedin.com/") String envURL) {

        switch (browserType.toLowerCase()){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                System.out.println("Here we can run default browser");
                break;
        }
        driver.get(envURL);
        landingPage = new LinkedInLandingPage(driver);
        initialPageTitle = landingPage.getPageTitle();
        initialPageUrl = landingPage.getPageUrl();
    }

    /**
     * Chain of calls to run after each test method
     */
    @AfterMethod
    public void afterTest() {
        driver.close();
    }

}

