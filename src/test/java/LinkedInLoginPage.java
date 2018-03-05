import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LinkedInLoginPage extends LinkedInBasePage {
    WebDriver driver;

    public LinkedInLoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    private WebElement emailField;
    private WebElement passwordField;
    private WebElement signInButton;
    private WebElement alertMessage;

    private void initElements() {
        emailField = driver.findElement(By.id("login-email"));
        passwordField = driver.findElement(By.id("login-password"));
        signInButton = driver.findElement(By.id("login-submit"));
    }

    private void initErrorElements() {
        alertMessage = driver.findElement(By.xpath("//div[@id='global-alert-queue']//strong[not(text()='')]"));
    }

    public boolean isFailedLogIn() throws InterruptedException {
        initErrorElements();
        waitUntilElementIsClickable(alertMessage);
        return alertMessage.isDisplayed();
    }

    public LinkedInBasePage loginAs(String email, String password) {
        initElements();
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        signInButton.click();
        return new LinkedInBasePage(driver);
    }
//    public void loginAs(String password){
//        loginAs("12345", password);
//    }

}
