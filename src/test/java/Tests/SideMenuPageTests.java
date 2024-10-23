package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SideMenuPageTests extends BaseTest {
    @BeforeMethod
    public void pageSetUp(){
        driver.navigate().to("https://www.saucedemo.com/");
    }
    @Test
    public void sideMenuClickableOnAllPages(){
        homepage.loginValidCredentials();
        Assert.assertTrue(sidemenuPage.sidemenuContainsValidOptions());
        Assert.assertTrue(sidemenuPage.assertMenuClickableOnAllPages());
    }
    @Test(priority = 1)
    public void userCanClickAllItemsOption(){
        homepage.loginValidCredentials();
        inventoryPage.clickCartButton();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/cart.html");//Assertation that program is not on inventory page
        sidemenuPage.clickSideMenuOption("All Items");
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
    }
    @Test(priority = 2)
    public void userCanClickAboutButton(){
        homepage.loginValidCredentials();
        sidemenuPage.clickSideMenuOption("About");
        Assert.assertEquals(driver.getCurrentUrl(),"https://saucelabs.com/");



    }
    @Test(priority = 3)
    public void userCanClickResetAppState() {
        homepage.loginValidCredentials();
        String[] listOfItems = {"Sauce Labs Bolt T-Shirt", "Sauce Labs Backpack", "Sauce Labs Onesie"};
        cartPage.emptyCart();
        Assert.assertTrue(cartPage.assertCartIsEmpty());
        cartPage.goToInventoryPage();
        inventoryPage.addItemToCart(listOfItems);
        Assert.assertTrue(inventoryPage.cartNumberIsCorrect(3));
        Assert.assertTrue(cartPage.assertCorrectItemsAddedToCart(3, listOfItems));
        sidemenuPage.clickSideMenuOption("Reset App State");
        sidemenuPage.closeSidemenu();
        inventoryPage.cartButton.click();
        Assert.assertFalse(inventoryPage.cartNumberIsDisplayed());
        Assert.assertTrue(cartPage.assertCartIsEmpty());
    }
    @Test(priority = 4)
    public void userCanLogoutUsingSidemenu(){
        homepage.loginValidCredentials();
        sidemenuPage.clickSideMenuOption("Logout");
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/");
        Assert.assertTrue(homepage.loginButton.isDisplayed());
    }
   @Test(priority = 5)
    public void userCanCloseSidemenu() throws InterruptedException {
       homepage.loginValidCredentials();
       sidemenuPage.openSidemenu();
       sidemenuPage.closeSidemenu();
       Thread.sleep(2000);
       Assert.assertFalse(sidemenuPage.closeButtonIsDisplayed());


   }



}
