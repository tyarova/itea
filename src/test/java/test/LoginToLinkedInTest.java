package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.LinkedInHomePage;
import page.LinkedInLoginPage;

public class LoginToLinkedInTest extends LinkedInBaseTest {

    @DataProvider
    public Object[][] successfulLoginCredentials(){
        return new Object[][]{
                {"testmedia@ukr.net", "passworD1"},
                {"TESTMEDIA@UKR.NET", "passworD1"},
                {"testmedia@ukr.net ", "passworD1"},
                {"TestMedia@ukr.net", "passworD1"},
        };
    }

    /**
     * Test, that verifies user login with valid credentials
     * @param email - email address, that will be used for login
     * @param password - password, that will be used for login
     */
    @Test (dataProvider ="successfulLoginCredentials" )
    public void successfulLoginTest(String email, String password) {
        Assert.assertEquals(initialPageTitle, "LinkedIn: Log In or Sign Up",
                "Login page title is wrong");
        LinkedInHomePage homePage = landingPage.loginAs(email, password);
        Assert.assertTrue(homePage.isLoaded(),
                "User is not signed in");
        Assert.assertNotEquals(homePage.getPageTitle(), initialPageTitle,
                "Page title has NOT been changed after Login");
        Assert.assertNotEquals(homePage.getPageUrl(), initialPageUrl,
                "Page URL has NOT been changed after Login");
    }

    @DataProvider
    public Object[][] negativeTestCredentialsIsReturnedToLogin(){
        return new Object[][]{
                //{"testmedia", "qwertyQ1", "Please enter a valid email address.", ""},
                {"testmedia@ukr.net", "password", "", "Hmm, that's not the right password. Please try again or request a new one."},
                //{"testmedia", "password", "Please enter a valid email address.", ""},
                //{"testmed@ukr.net", "qwertyQ1", "Hmm, we don't recognize that email. Please try again.", ""},
                //{"testmedia@ukr.net", "pas", "", "The password you provided must have at least 6 characters."},
                };
    }

    /**
     * Test that verifies, that user cannot access the profile with invalid credentials
     * @param email - email address, that will be used for login
     * @param password - password, that will be used for login
     */
    @Test(dataProvider ="negativeTestCredentialsIsReturnedToLogin" )
    public void negativeLoginTest(String email, String password, String emailMessage, String passwordMessage) {
        Assert.assertEquals(initialPageTitle, "LinkedIn: Log In or Sign Up",
                "Login page title is wrong");
        LinkedInLoginPage loginPage = landingPage.loginAs(email, password);
        Assert.assertTrue(loginPage.isLoaded(),
                "User has signed in");
        Assert.assertNotEquals(loginPage.getPageTitle(), initialPageTitle,
                "Page title has NOT been changed after Login");
        Assert.assertNotEquals(loginPage.getPageUrl(), initialPageUrl,
                "Page URL has NOT been changed after Login");

        String actualEmailMessage = loginPage.getEmailMessage();
        String actualPasswordMessage = loginPage.getPasswordMessage();
        Assert.assertEquals(emailMessage, actualEmailMessage, "Actual and Expected Email messages do not match");
        Assert.assertEquals(passwordMessage, actualPasswordMessage, "Actual and Expected Password messages do not match");
    }

    @DataProvider
    public Object[][] negativeTestCredentialsIsReturnedToLanding(){
        return new Object[][]{
                {"testmedia@ukr.net", ""},
                //{"", "qwertyQ1"},
                //{"", ""},
        };
    }

    /**
     * Test that verifies, that user cannot access the profile with invalid/empty credentials
     * @param email - email address, that will be used for login
     * @param password - password, that will be used for login
     */
    @Test(dataProvider ="negativeTestCredentialsIsReturnedToLanding" )
    public void negativeLoginTestWithEmptyFields(String email, String password) {
        Assert.assertEquals(initialPageTitle, "LinkedIn: Log In or Sign Up",
                "Login page title is different");
        landingPage.loginAs(email, password);
        Assert.assertTrue(landingPage.isLoaded(), "User has been navigated to another page");
        Assert.assertEquals(landingPage.getPageTitle(), initialPageTitle,
                "Page title has been changed after Login");
        Assert.assertEquals(landingPage.getPageUrl(), initialPageUrl,
                "Page URL has been changed after Login");
    }
}