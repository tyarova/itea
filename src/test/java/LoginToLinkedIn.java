import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class LoginToLinkedIn {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.get("https://www.linkedin.com/");
    }

    @AfterClass
    public void afterClass() {
        driver.close();
    }

    @Test
    public void successfulLoginTest() throws InterruptedException {
        String initialPageTitle = "LinkedIn: Log In or Sign Up";
        Assert.assertEquals(driver.getTitle(), initialPageTitle, "Login page title is wrong");

        WebElement emailField = driver.findElement(By.id("login-email"));
        //WebElement emailField = driver.findElement(By.xpath("//*[@id='login-email']"));
        WebElement passwordField = driver.findElement(By.id("login-password"));
        WebElement signInButton = driver.findElement(By.id("login-submit"));
        String initiaiPageUrl = driver.getCurrentUrl();

        emailField.sendKeys("testmedia@ukr.net");
        passwordField.sendKeys("qwertyQ1");
        signInButton.click();
        sleep(5000);

        WebElement settingsControl = driver.findElement(By.xpath("//*[@id='nav-settings__dropdown-trigger']"));

        //Assert.assertEquals(driver.getCurrentUrl(), initiaiPageUrl);
        Assert.assertNotEquals(driver.getTitle(), initialPageTitle, "Page title has NOT been changed after Login");
        //Assert.assertFalse(driver.getTitle().equals(initialPageTitle), "Page title has NOT been changed after Login");
        Assert.assertTrue(settingsControl.isDisplayed(), "Settings drop down is not displayed");
        Assert.assertNotEquals(driver.getCurrentUrl(), initiaiPageUrl, "Page URL has NOT been changed after Login");
    }

    @Test
    public void negativeLoginTest() {
        WebElement emailField = driver.findElement(By.id("login-email"));
        //WebElement emailField = driver.findElement(By.xpath("//*[@id='login-email']"));
        WebElement passwordField = driver.findElement(By.id("login-password"));
        WebElement signInButton = driver.findElement(By.id("login-submit"));
        emailField.sendKeys("test@ukr.net");
        passwordField.sendKeys("12345");
        signInButton.click();

        WebElement alertMessage = driver.findElement(By.xpath("//div[@id='global-alert-queue']//strong[not(text()='')]"));
        Assert.assertTrue(alertMessage.isDisplayed(), "Alert message is not displayed");
    }
}