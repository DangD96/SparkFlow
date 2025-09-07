import framework.Assert;
import framework.SparkDriver;
import framework.SparkTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CatalogPage;
import pages.LoginPage;

import java.io.IOException;
import java.util.HashMap;

public class LoginTests extends SparkTest {
    /* Method below runs n times, where n is the number of objects in the array returned by dataProvider in superclass.
    The Object returned by dataProvider during each iteration gets passed as argument to the input parameter I defined.
    The dataProvider lives in the super class, so all subclasses can access it */
    @Test(description = "Unsuccessful Login", dataProvider = "testInvalidLoginDataProvider")
    protected void testInvalidLogin(HashMap<String, String> input) {
        LoginPage.login(input.get("username"), input.get("password"), "", "Teacher", true);
        Assert.elementIsVisible(LoginPage.loginErrorMsg());
    }

    @Test(description = "Successful Login")
    protected void testValidLogin() {
        LoginPage.clearUsername();
        LoginPage.clearPassword();
        LoginPage.login("rahulshettyacademy", "learning", "", "", false);
        Assert.elementIsVisible(CatalogPage.checkoutBtn());
        Assert.equals(SparkDriver.getURL(), "https://rahulshettyacademy.com/angularpractice/shop");
        Assert.elementIsHidden(LoginPage.loginBtn());
    }

    @Test(description = "fail", enabled = false)
    protected void intentionalFail() {
        Assert.fail();
    }

    @Test(description = "pass", enabled = false)
    protected void quickPass() {
        Assert.isTrue(true);
    }

    @DataProvider(parallel = true)
    public Object[] testInvalidLoginDataProvider() throws IOException {
        return getTestData("TestInvalidLoginData.json");
    }
}
