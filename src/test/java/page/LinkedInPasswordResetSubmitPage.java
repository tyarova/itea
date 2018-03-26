package page;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LinkedInPasswordResetSubmitPage extends LinkedInBasePage{

    @FindBy(xpath = "//a[@class='status-link btn-resend-link']")
    private WebElement resetBttonLink;

        public LinkedInPasswordResetSubmitPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean IsLoaded(){
            boolean isLoaded = false;
            try {
                isLoaded = resetBttonLink.isDisplayed();

            }catch (NoSuchElementException e){
                isLoaded = false;
            } return isLoaded;
    }
}
