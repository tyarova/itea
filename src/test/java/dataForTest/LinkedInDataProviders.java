package dataForTest;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class LinkedInDataProviders {

    @DataProvider
    public Object[][] loginCredentials(Method m) {
        if (m.getName().equals("successfulLoginTest")) {
            return new Object[][]{
                    {"testmedia@ukr.net", "qwertyQ1"},
                    {"TESTMEDIA@UKR.NET", "qwertyQ1"},
                    {"testmedia@ukr.net ", "qwertyQ1"},
                    {"TestMedia@ukr.net", "qwertyQ1"},
            };
        } else if (m.getName().equals("negativeLoginTest")) {
            return new Object[][]{
                    {"testmedia", "qwertyQ1", "Please enter a valid email address.", ""},
                    {"testmedia@ukr.net", "password", "", "Hmm, that's not the right password. Please try again or request a new one."},
                    {"testmedia", "password", "Please enter a valid email address.", ""},
                    {"testmed@ukr.net", "qwertyQ1", "Hmm, we don't recognize that email. Please try again.", ""},
                    {"testmedia@ukr.net", "pas", "", "The password you provided must have at least 6 characters."},
            };
        } else if (m.getName().equals("negativeLoginTestWithEmptyFields")) {
            return new Object[][]{
                    {"testmedia@ukr.net", ""},
                    {"", "qwertyQ1"},
                    {"", ""},
            };
        } else {
            return null;
        }
    }
}
