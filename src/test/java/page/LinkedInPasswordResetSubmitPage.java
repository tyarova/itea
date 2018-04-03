package page;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.GmailService;


public class LinkedInPasswordResetSubmitPage extends LinkedInBasePage{

    @FindBy(xpath = "//a[@class='status-link btn-resend-link']")
    private WebElement resetButtonLink;

    /**
     * {@inheritDoc}
     * Constructor for class LinkedInPasswordResetSubmitPage
     */
        public LinkedInPasswordResetSubmitPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * {@inheritDoc}
     * @return {@code true} if resetButtonLink has been loaded
     * @throws NoSuchElementException if resetButtonLink has not been loaded
     */
    @Override
    public boolean isLoaded(){
            boolean isLoaded = false;
            try {
                isLoaded = resetButtonLink.isDisplayed();

            }catch (NoSuchElementException e){
                isLoaded = false;
            } return isLoaded;
    }

    /**
     * Open the reset password link
     * @param resetPasswordLink - the link, that has been sent to user's mail box, to reset password
     * @return new page object
     */
    public LinkedInChooseNewPasswordPage navigateToResetPasswordLink(String resetPasswordLink){
        driver.get(resetPasswordLink);
        return new LinkedInChooseNewPasswordPage(driver);
    }

    /**
     * Get the reset password link from user's mail box
     * @param messageToPartial - email address to which the reset password link is going to be sent
     * @return reset password link
     */
    public String getResetPasswordLinkFromEmail(String messageToPartial) {
        String messageSubjectPartial = "here's the link to reset your password";
        String messageFromPartial = "security-noreply@linkedin.com";
        GmailService gmailService = new GmailService();
        String message = gmailService.waitForNewMessage(messageSubjectPartial, messageToPartial, messageFromPartial, 60);
        String resetPasswordLink = StringUtils.substringBetween(message, "browser:", "This link").trim();
        return resetPasswordLink;
    }
}
