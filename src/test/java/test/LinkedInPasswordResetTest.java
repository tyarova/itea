package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.LinkedInPasswordResetSubmitPage;
import page.LinkedInRequestPasswordResetPage;
import utils.GmailService;

public class LinkedInPasswordResetTest extends LinkedInBaseTest{

    String userEmail = "testmedia0000@gmail.com";

    @Test
    public void successfulPasswordResetTest () {
        LinkedInRequestPasswordResetPage passwordResetPage = landingPage.forgotPasswordLinkClick();
        Assert.assertTrue(passwordResetPage.IsLoaded(),
                "Password Reset Page is not loaded");
        LinkedInPasswordResetSubmitPage passwordResetSubmitPage = passwordResetPage.submitEmail(userEmail);
        //Assert.assertTrue(passwordResetPage.IsLoaded(), "Password Reset Submit Page is not loaded");

        //read email
        String messageSubjectPartial = "here's the link to reset your password";
        String messageToPartial = "testmedia0000@gmail.com";
        String messageFromPartial = "security-noreply@linkedin.com";

        GmailService GmailService = new GmailService();
        String message = GmailService.waitForNewMessage(messageSubjectPartial, messageToPartial, messageFromPartial, 60);
        System.out.println("Content: " + message);
        String letterRows[];
        letterRows = message.split("\\n");

        String link;
        for(String row: letterRows){
            if (row.contains("https://www.linkedin.com/e/rpp/")) {
                link = row;
                System.out.println("The link is " + link);
            }
        }
        //openLink(link);
    }
}
