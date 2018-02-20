import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class LinkedInSearchTest {
    WebDriver driver;
    @BeforeMethod
    public void beforeTest() {
        driver = new FirefoxDriver();
        driver.get("https://www.linkedin.com/");
    }

    @AfterMethod
    public void afterTest() {
        driver.close();
    }

    @Test
    public void basicSearchTest() throws InterruptedException {

        LinkedInLoginPage loginPage = new LinkedInLoginPage(driver);
        loginPage.loginAs("testmedia@ukr.net", "qwertyQ1");
        sleep(5000);
        WebElement settingsControl = driver.findElement(By.xpath("//*[@id='nav-settings__dropdown-trigger']"));
        Assert.assertTrue(settingsControl.isDisplayed(), "Settings drop down is not displayed");

        //search
        //WebElement submitButton = driver.findElement(By.xpath("//*[@type='search-icon']"));
        //WebElement searchLine = driver.findElement(By.xpath("//input[@placeholder='Search']"));
        //div[contains(@class, 'search-result--person')]
        //searchLine.sendKeys("hr");
        //submitButton.click();

    }

}

