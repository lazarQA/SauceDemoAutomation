package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EndToEndTest extends BaseTest {
    @BeforeMethod
    public void pageSetUp(){
        driver.navigate().to("https://www.saucedemo.com/");
    }
    @Test
    public void endToEndTest(){
        String[] listOfItems = {"Sauce Labs Bolt T-Shirt", "Sauce Labs Backpack", "Sauce Labs Onesie"};
        homepage.loginValidCredentials();
        inventoryPage.addItemToCart(listOfItems);
        cartPage.deleteItemsIfFull();
        Assert.assertTrue(cartPage.assertCartIsEmpty());
        cartPage.goToInventoryPage();
        inventoryPage.addItemToCart(listOfItems);
        Assert.assertTrue(inventoryPage.cartNumberIsCorrect(3));
        Assert.assertTrue(cartPage.assertCorrectItemsAddedToCart(3, listOfItems));
        checkoutStepOnePage.clickCheckoutButton();
        checkoutStepOnePage.addValidDataToFields();
        checkoutStepOnePage.clickContinueButton();
        checkOutStepTwoPage.clickFinishButton();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/checkout-complete.html");
        Assert.assertTrue(checkoutCompletePage.backHomeButton.isDisplayed());
        Assert.assertEquals(checkoutCompletePage.successfullPurchaseNotification.getText(),checkoutCompletePage.successfullPurchaseText);
        checkoutCompletePage.clickBackHomeButton();
        inventoryPage.clickCartButton();
        Assert.assertTrue(cartPage.assertCartIsEmpty());
        cartPage.goToInventoryPage();
        sidemenuPage.clickSideMenuOption("Logout");
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/");
        Assert.assertTrue(homepage.loginButton.isDisplayed());

    }


}
