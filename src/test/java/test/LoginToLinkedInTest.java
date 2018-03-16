package test;

import org.testng.Assert;
import org.testng.annotations.*;
import page.LinkedInHomePage;
import page.LinkedInLoginPage;

import java.awt.image.SampleModel;

public class LoginToLinkedInTest extends LinkedInBaseTest {
String correctEmail = "testmedia@ukr.net";
String correctPAssword = "qwertyQ1";

    @Test
    public void successfulLoginTest(String email, String password) {
        String initialPageTitle = landingPage.getPageTitle();
        String initialPageUrl = landingPage.getPageUrl();
        Assert.assertEquals(initialPageTitle, "LinkedIn: Log In or Sign Up",
                "Login page title is wrong");
        LinkedInHomePage homePage = landingPage.loginAs(correctEmail, correctPAssword);
        Assert.assertTrue(homePage.isSignedIn(),
                "User is not signed in");
        Assert.assertNotEquals(homePage.getPageTitle(), initialPageTitle,
                "Page title has NOT been changed after Login");
        Assert.assertNotEquals(homePage.getPageUrl(), initialPageUrl,
                "Page URL has NOT been changed after Login");
    }

    @DataProvider
    public Object[][] negativeLoginCredentials(){
        return new Object[][]{
                {"testmedia", "qwertyQ1", "Укажите действительный адрес эл. почты.", "Пароль должен содержать не менее 6 символов."},
                {"testmedia@ukr.net", "password"},
                {"testmedia", "password"},
        };
    }

    @Test(dataProvider ="negativeLoginCredentials" )
    public void negativeLoginTest(String email, String password, String emailMessage, String passwordMessage) {
        String initialPageTitle = landingPage.getPageTitle();
        String initialPageUrl = landingPage.getPageUrl();
        Assert.assertEquals(initialPageTitle, "LinkedIn: Log In or Sign Up",
                "Login page title is wrong");
        LinkedInLoginPage loginPage = landingPage.loginAs(email, password);
        Assert.assertTrue(loginPage.isFailedLogIn(),
                "User has signed in");
        Assert.assertNotEquals(loginPage.getPageTitle(), initialPageTitle,
                "Page title has NOT been changed after Login");
        Assert.assertNotEquals(loginPage.getPageUrl(), initialPageUrl,
                "Page URL has NOT been changed after Login");

        String actualEmailMessage = loginPage.getEmailMessage();
        String actualPasswordMessage = loginPage.getPaswordMessage();
        Assert.assertEquals(emailMessage, actualEmailMessage, "Actual and Expected Email messages do not match");
        Assert.assertEquals(passwordMessage, actualPasswordMessage, "Actual and Expected Password messages do not match");
    }

    @DataProvider
    public Object[][] loginCredentialsEmpty(){
        return new Object[][]{
                {"testmedia@ukr.net", ""},
                {"", "qwertyQ1"},
                {"", ""},
        };
    }
    @Test(dataProvider ="loginCredentialsEmpty" )
    public void negativeLoginTestWithEmptyFields(String email, String password) {
        String initialPageTitle = landingPage.getPageTitle();
        String initialPageUrl = landingPage.getPageUrl();
        Assert.assertEquals(initialPageTitle, "LinkedIn: Log In or Sign Up",
                "Login page title is different");
        landingPage.loginAs(email, password);
        Assert.assertTrue(landingPage.stayOnLandingPage(), "User has been navigated to another page");
        Assert.assertEquals(landingPage.getPageTitle(), initialPageTitle,
                "Page title has been changed after Login");
        Assert.assertEquals(landingPage.getPageUrl(), initialPageUrl,
                "Page URL has been changed after Login");
    }
}