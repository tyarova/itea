package page;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedInResetPasswordSuccessPage extends LinkedInBasePage {

    @FindBy(xpath = "//div[@class='form-actions']/a[@href]")
    private WebElement goToHomepageButton;

    /**
     * {@inheritDoc}
     * Constructor for class LinkedInResetPasswordSuccessPage
     */
    public LinkedInResetPasswordSuccessPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * {@inheritDoc}
     * @return {@code true} if goToHomepageButton has been loaded
     * @throws NoSuchElementException if goToHomepageButton has not been loaded
     */
    @Override
    public boolean isLoaded(){
        boolean isLoaded = false;
        try {
            isLoaded = goToHomepageButton.isDisplayed();

        }catch (NoSuchElementException e){
            isLoaded = false;
        } return isLoaded;
    }

    /**
     * Redirect user to Home Page
     * @return new page object
     */
    public LinkedInHomePage navigateToHomePage() {
        goToHomepageButton.click();
        return new LinkedInHomePage(driver);
    }
}
