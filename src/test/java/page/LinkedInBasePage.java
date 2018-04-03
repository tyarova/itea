package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class LinkedInBasePage {
    WebDriver driver;


    /**
     * Constructor of LinkedInBasePage class which takes Webdriver instance initialized in @BeforeMethod
     * for reuse in LinkedInBasePage class methods
     * @param driver - Webdriver instance
     */
    public LinkedInBasePage(WebDriver driver) {
        this.driver = driver;
       }

    /**
     * Gets the title of the needed page
     * @return page's title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Gets the URL of the needed page
     * @return page's URL
     */
    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Wait until WebElement is Clickable on Web page
     * @param webElement - WebElement to Wait for
     * @return WebElement after
     */
    public WebElement waitUntilElementIsClickable(WebElement webElement) {
        waitUntilElementIsClickable(webElement, 10);
        return webElement;
    }

    /**
     * Wait until WebElement is Clickable on Web page
     * @param webElement - WebElement to Wait for
     * @param timeOutInSeconds - the time we can set in seconds to wait for needed element
     * @return WebElement after
     */
    public void waitUntilElementIsClickable(WebElement webElement, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    /**
     * Wait until WebElement is Visible on Web page
     * @param webElement - WebElement to Wait for
     * @param timeOutInSeconds - the time we can set in seconds to wait for needed element
     * @return WebElement after
     */
    public WebElement waitUntilElementIsVisible(WebElement webElement, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(webElement));
        return webElement;
    }

    /**
     * Wait until WebElement is Clickable on Web page
     * @param webElement - WebElement to Wait for
     * @return WebElement after
     */
    public WebElement waitUntilElementIsVisible(WebElement webElement) {
        waitUntilElementIsVisible(webElement, 10);
        return webElement;
    }


    /**
     * Returns {@code true} if needed element on Web page has been loaded
     * @return {@code true} if needed element on Web page has been loaded
     */
    public abstract boolean isLoaded();
}

