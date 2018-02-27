import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class LinkedInSearchTest {
    WebDriver driver;

    @BeforeMethod
    public void beforeTest() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.linkedin.com/");
    }

    @AfterMethod
    public void afterTest() {
        driver.close();
    }

    @Test
    public void basicSearchTest() throws InterruptedException {
        //login
        LinkedInLoginPage loginPage = new LinkedInLoginPage(driver);
        loginPage.loginAs("testmedia@ukr.net", "qwertyQ1");
        sleep(5000);
        WebElement settingsControl = driver.findElement(By.xpath("//*[@id='nav-settings__dropdown-trigger']"));
        Assert.assertTrue(settingsControl.isDisplayed(), "Settings drop down is not displayed");

        //search
        String searchTerm = "hr";
        String searchTerm1 = "linkedin member";
        WebElement submitButton = driver.findElement(By.xpath("//*[@type='search-icon']"));
        WebElement searchLine = driver.findElement(By.xpath("//input[@placeholder='Search']"));
        searchLine.sendKeys(searchTerm);
        submitButton.click();

        //to know what classes can be found by selenium
        List<WebElement> results = driver.findElements(By.xpath("//ul[@class='search-results__list list-style-none']/li"));
        for (WebElement ClassInfo : results) {
            System.out.println(ClassInfo.getAttribute("class"));
        }

        //to find 10 our links
        List<WebElement> searchResults = driver.findElements(By.xpath("//li[contains(@class,'search-result__occluded-item')]"));
        System.out.println("Search result includes " + searchResults.size() + " links");
        Assert.assertEquals(searchResults.size(), 10, "There are less than 10 results have been found");

        //Scroll page:
        for (int i = 0; i < searchResults.size(); i++) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", searchResults.get(i));
            String cardTitle = driver.findElement(By.xpath("//li[contains(@class,'search-result__occluded-item')][" + (i+1) + "]//span[contains(@class, 'actor-name')]")).getText().toLowerCase();
            //String cardTitle = driver.findElement(By.xpath("//li[contains(@class,'search-result__occluded-item')][" + (i + 1) + "]//p[contains(@class,'subline-level-1')]")).getText().toLowerCase();
            System.out.println(cardTitle);
            Assert.assertTrue(cardTitle.contains(searchTerm.toLowerCase())|cardTitle.contains(searchTerm1.toLowerCase()), "Search Term " + searchTerm + " not found");
        }
    }
}




