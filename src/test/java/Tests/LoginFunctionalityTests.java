package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginFunctionalityTests extends BaseTest {
    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");
    }
    @Test
    public void userLoginValidCredentials(){
        homepage.loginValidCredentials();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
        Assert.assertTrue(inventoryPage.cartButton.isDisplayed());
        Assert.assertTrue(inventoryPage.logoutButtonIsDisplayed());
    }
    @Test(priority = 1)
    public void userCannotLogInInvalidUsername(){
        homepage.loginInvalidUsername();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/");
        Assert.assertTrue(homepage.loginButton.isDisplayed());
        Assert.assertTrue(homepage.errorMessage.isDisplayed());
        Assert.assertEquals(homepage.errorMesageText(),homepage.expectedErrorMessage);
    }
    @Test(priority = 2)
    public void userCannotLogInInvalidPassword(){
        homepage.loginInvalidPassword();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/");
        Assert.assertTrue(homepage.loginButton.isDisplayed());
        Assert.assertTrue(homepage.errorMessage.isDisplayed());
        Assert.assertEquals(homepage.errorMesageText(),homepage.expectedErrorMessage);

    }
    @Test(priority = 3)
    public void userCannotLogInInvalidUsernameInvalidPassword(){
        homepage.loginInvalidUsernameInvalidPassword("standardd_User","12345");
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/");
        Assert.assertTrue(homepage.loginButton.isDisplayed());
        Assert.assertTrue(homepage.errorMessage.isDisplayed());
        Assert.assertEquals(homepage.errorMesageText(),homepage.expectedErrorMessage);

    }
    @Test(priority = 4)
    public void userCannotGainAccessPagesIfNotLoggedIn(){
        Assert.assertTrue(homepage.user_unable_to_access_pages_if_not_logged_in());
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/");
        Assert.assertTrue(homepage.loginButton.isDisplayed());


    }

}
