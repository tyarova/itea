import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.interrupted;
import static java.lang.Thread.sleep;

public class BadCodeExample {
    @Test
    public void BadCode() throws InterruptedException {
        //System.out.println("Hello World!");
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.google.com/");
        driver.findElement(By.id("lst-ib")).sendKeys("selenium");
        driver.findElement(By.name("btnK")).click();
        //Assert.assertEquals(driver.getTitle(), "selenium - Пошук Google");
        sleep(5000);

        List<WebElement> links = driver.findElements(By.xpath("//*[@id=\"rso\"]//div/div/div/h3/a"));
        boolean y = true;
        boolean k = false;
        int counter = 0;
        for (WebElement myElement : links) {
            String linkTitle = myElement.getText().toLowerCase();
            System.out.println(linkTitle);
            if (linkTitle.contains("selenium")) {
                counter++;
            }
            System.out.println(counter);
            System.out.println(links.size());
            if (counter == links.size()) {
                k = true;
            }
        }
        Assert.assertEquals(k, y);
        driver.close();

    }
}

