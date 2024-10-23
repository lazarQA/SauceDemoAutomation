package Tests;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartFunctionalityTests extends BaseTest {
    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");
    }

    @Test
    public void userCanAddOneItemToTheCart() {
        String[] listOfItems = {"Sauce Labs Bolt T-Shirt"};
        homepage.loginValidCredentials();
        cartPage.deleteItemsIfFull();
        Assert.assertTrue(cartPage.assertCartIsEmpty());
        cartPage.goToInventoryPage();
        inventoryPage.addItemToCart(listOfItems);
        Assert.assertTrue(inventoryPage.cartNumberIsCorrect(1));
        Assert.assertTrue(cartPage.assertCorrectItemsAddedToCart(1, listOfItems));
        cartPage.emptyCart();
    }

    @Test(priority = 1)
    public void userCanAddMultipleItemsToTheCart() {
        String[] listOfItems = {"Sauce Labs Bolt T-Shirt", "Sauce Labs Backpack", "Sauce Labs Onesie"};
        homepage.loginValidCredentials();
        cartPage.deleteItemsIfFull();
        Assert.assertTrue(cartPage.assertCartIsEmpty());
        cartPage.goToInventoryPage();
        inventoryPage.addItemToCart(listOfItems);
        Assert.assertTrue(inventoryPage.cartNumberIsCorrect(3));
        Assert.assertTrue(cartPage.assertCorrectItemsAddedToCart(3, listOfItems));
        cartPage.emptyCart();
    }

    @Test(priority = 2)
    public void userCanDeleteCartItemsFromCartPage()  {
        String[] listOfItems = {"Sauce Labs Bolt T-Shirt", "Sauce Labs Onesie", "Sauce Labs Bike Light"};
        homepage.loginValidCredentials();
        inventoryPage.addItemToCart(listOfItems);
        cartPage.deleteItemsIfFull();
        Assert.assertTrue(cartPage.assertCartIsEmpty());
        cartPage.goToInventoryPage();
        inventoryPage.addItemToCart(listOfItems);
        Assert.assertTrue(inventoryPage.cartNumberIsCorrect(3));
        Assert.assertTrue(cartPage.assertCorrectItemsAddedToCart(3, listOfItems));
        cartPage.emptyCart();
        Assert.assertFalse(inventoryPage.cartNumberIsDisplayed());
        Assert.assertTrue(cartPage.assertCartIsEmpty());
    }
    @Test(priority = 3)
    public void userCanDeleteCartItemsFromInventoryPage(){
        String[] listOfItems = {"Sauce Labs Bolt T-Shirt", "Sauce Labs Onesie", "Sauce Labs Bike Light"};
        homepage.loginValidCredentials();
        cartPage.deleteItemsIfFull();
        Assert.assertTrue(cartPage.assertCartIsEmpty());
        cartPage.goToInventoryPage();
        inventoryPage.addItemToCart(listOfItems);
        Assert.assertTrue(inventoryPage.cartNumberIsCorrect(3));
        Assert.assertTrue(cartPage.assertCorrectItemsAddedToCart(3, listOfItems));
        cartPage.goToInventoryPage();
        inventoryPage.removeItemsInventoryPage();
        Assert.assertTrue(cartPage.assertCartIsEmpty());
        Assert.assertFalse(inventoryPage.cartNumberIsDisplayed());

    }
    @Test(priority = 4)
    public void userCanClickContinueShoppingButton(){
        homepage.loginValidCredentials();
        inventoryPage.clickCartButton();
        cartPage.clickContinueShopping();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");

    }


}
