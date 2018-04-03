package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;


public class LinkedInPasswordResetTest extends LinkedInBaseTest {

    String userEmail = "testmedia0000@gmail.com";
    String newPassword = "passworD1";

    /**
     * Reset password test
     */
    @Test
    public void successfulPasswordResetTest() {
        LinkedInRequestPasswordResetPage passwordResetPage = landingPage.forgotPasswordLinkClick();
        Assert.assertTrue(passwordResetPage.isLoaded(), "Password Reset Page is not loaded");

        LinkedInPasswordResetSubmitPage passwordResetSubmitPage = passwordResetPage.submitEmail(userEmail);
        String resetPasswordLink = passwordResetSubmitPage.getResetPasswordLinkFromEmail(userEmail);
        //Assert.assertTrue(passwordResetSubmitPage.isLoaded(), "Password Reset Submit Page is not loaded");
        //entering capture manually :)

        LinkedInChooseNewPasswordPage chooseNewPasswordPage = passwordResetSubmitPage.navigateToResetPasswordLink(resetPasswordLink);
        //Assert.assertTrue(chooseNewPasswordPage.isLoaded(), "Choose New Password Page is not loaded");

        LinkedInResetPasswordSuccessPage resetPasswordSuccessPage = chooseNewPasswordPage.submitNewPassword(newPassword);
        Assert.assertTrue(resetPasswordSuccessPage.isLoaded(), "Reset Password Success Page is not loaded");

        LinkedInHomePage homePage = resetPasswordSuccessPage.navigateToHomePage();
        Assert.assertTrue(homePage.isLoaded(), "User has not been redirected to Home page");

    }
}

