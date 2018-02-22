import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

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
        WebElement submitButton = driver.findElement(By.xpath("//*[@type='search-icon']"));
        WebElement searchLine = driver.findElement(By.xpath("//input[@placeholder='Search']"));
        searchLine.sendKeys("hr");
        submitButton.click();
        sleep(10000);

        List<WebElement> links = driver.findElements(By.xpath("//li[@class='search-result search-result__occluded-item ember-view']"));
        boolean y = true;
        boolean k = false;
        int counter = 0;
        for (WebElement myElement : links) {
            String linkTitle = myElement.getText().toLowerCase();
            System.out.println(linkTitle);
            if (linkTitle.contains("hr")) {
                counter++;
            }
            System.out.println(counter);
            System.out.println(links.size());
            if (counter == links.size()) {
                k = true;
            }
        }
        Assert.assertEquals(k, y, "Some of the results do not contain search request");
        Assert.assertEquals(counter, 10, "There are less than 10 results have been found");

    }

}

