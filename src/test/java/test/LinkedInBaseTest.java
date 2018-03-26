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

    @Parameters({"browserType", "envURL"})
    @BeforeMethod
    public void beforeTest(@Optional("firefox") String browserType, @Optional("https://www.linkedin.com/") String envURL) {

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

    @AfterMethod
    public void afterTest() {
        driver.close();
    }

    public void openLink(String link) {
        driver.get(link);
    }

}

