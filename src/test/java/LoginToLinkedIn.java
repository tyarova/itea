import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;


public class LoginToLinkedIn {
    @Test
    public void successfulLoginTest() throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.linkedin.com/");

        WebElement emailField = driver.findElement(By.id("login-email"));
        //WebElement emailField = driver.findElement(By.xpath("//*[@id='login-email']"));
        WebElement passwordField = driver.findElement(By.id("login-password"));
        WebElement signInButton = driver.findElement(By.id("login-submit"));
        String expectedUrl = "https://www.linkedin.com/feed/";

        emailField.sendKeys("testmedia@ukr.net");
        passwordField.sendKeys("qwertyQ1");
        signInButton.click();
        sleep(5000);

        WebElement settingsControl = driver.findElement(By.xpath("//*[@id='nav-settings__dropdown-trigger']"));
        
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
        Assert.assertTrue(settingsControl.isDisplayed(), "Settings drop down is not displayed");
        driver.close();
    }

    @Test
    public void negativeLoginTest() {
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.linkedin.com/");

        WebElement emailField = driver.findElement(By.id("login-email"));
        //WebElement emailField = driver.findElement(By.xpath("//*[@id='login-email']"));
        WebElement passwordField = driver.findElement(By.id("login-password"));
        WebElement signInButton = driver.findElement(By.id("login-submit"));
        emailField.sendKeys("test@ukr.net");
        passwordField.sendKeys("12345");
        signInButton.click();

        WebElement alertMessage = driver.findElement(By.xpath("//div[@id='global-alert-queue']//strong[not(text()='')]"));
        Assert.assertTrue(alertMessage.isDisplayed(), "Alert message is not displayed");
        driver.close();
    }
}