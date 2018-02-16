import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

@Test
public class BadCodeExample {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello World!");
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.google.com/");
        driver.findElement(By.id("lst-ib")).sendKeys("selenium");
        driver.findElement(By.name("btnK")).click();
        //Assert.assertEquals(driver.getTitle(), "selenium - Пошук Google");

        List<WebElement> links = driver.findElements(By.xpath("//*[@id=\"rso\"]//div/div/div/h3/a"));
        System.out.println(links.toString());
        boolean x = false;
        boolean y = true;
        for (WebElement myElement : links) {
            String link = myElement.getText().toLowerCase();
            System.out.println(link);
            if (link.contains("selenium")) {
                x = true;
            }

            sleep(5000);
            Assert.assertEquals(x, y);
            driver.close();

        }
    }
}

