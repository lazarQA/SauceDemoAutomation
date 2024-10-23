package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutPagesTests extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");
    }

    @Test
    public void userCanAccessCheckoutStepOnePage() {
        String[] listOfItems = {"Sauce Labs Bolt T-Shirt", "Sauce Labs Backpack", "Sauce Labs Onesie"};
        homepage.loginValidCredentials();
        cartPage.deleteItemsIfFull();
        Assert.assertTrue(cartPage.assertCartIsEmpty());
        cartPage.goToInventoryPage();
        inventoryPage.addItemToCart(listOfItems);
        Assert.assertTrue(inventoryPage.cartNumberIsCorrect(3));
        Assert.assertTrue(cartPage.assertCorrectItemsAddedToCart(3, listOfItems));
        checkoutStepOnePage.clickCheckoutButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
        Assert.assertTrue(checkoutStepOnePage.allThreeFieldsPresent());

    }

    @Test(priority = 1)
    public void userIsAbleToProceedToCheckoutStepTwopage() {
        String[] listOfItems = {"Sauce Labs Bolt T-Shirt", "Sauce Labs Backpack", "Sauce Labs Onesie"};
        homepage.loginValidCredentials();
        cartPage.deleteItemsIfFull();
        Assert.assertTrue(cartPage.assertCartIsEmpty());
        cartPage.goToInventoryPage();
        inventoryPage.addItemToCart(listOfItems);
        Assert.assertTrue(inventoryPage.cartNumberIsCorrect(3));
        Assert.assertTrue(cartPage.assertCorrectItemsAddedToCart(3, listOfItems));
        checkoutStepOnePage.clickCheckoutButton();
        checkoutStepOnePage.addValidDataToFields();
        checkoutStepOnePage.clickContinueButton();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-two.html");
        Assert.assertTrue(checkoutStepOnePage.checkoutSteptwoFinishButton.isDisplayed());
    }

    @Test(priority = 2)
    public void userIsNotAbleToProceedToStepTwoInvalidFirstName() {
        String[] listOfItems = {"Sauce Labs Bolt T-Shirt", "Sauce Labs Backpack", "Sauce Labs Onesie"};
        homepage.loginValidCredentials();
        cartPage.deleteItemsIfFull();
        Assert.assertTrue(cartPage.assertCartIsEmpty());
        cartPage.goToInventoryPage();
        inventoryPage.addItemToCart(listOfItems);
        Assert.assertTrue(inventoryPage.cartNumberIsCorrect(3));
        Assert.assertTrue(cartPage.assertCorrectItemsAddedToCart(3, listOfItems));
        checkoutStepOnePage.clickCheckoutButton();
        checkoutStepOnePage.addDataInvalidFirstName();
        checkoutStepOnePage.clickContinueButton();
        Assert.assertEquals(checkoutStepOnePage.errorMessage.getText(), checkoutStepOnePage.errorMessageFirstName);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");

    }

    @Test(priority = 3)
    public void userIsNotAbleToProceedToStepTwoInvalidLastName() {
        String[] listOfItems = {"Sauce Labs Bolt T-Shirt", "Sauce Labs Backpack", "Sauce Labs Onesie"};
        homepage.loginValidCredentials();
        cartPage.deleteItemsIfFull();
        Assert.assertTrue(cartPage.assertCartIsEmpty());
        cartPage.goToInventoryPage();
        inventoryPage.addItemToCart(listOfItems);
        Assert.assertTrue(inventoryPage.cartNumberIsCorrect(3));
        Assert.assertTrue(cartPage.assertCorrectItemsAddedToCart(3, listOfItems));
        checkoutStepOnePage.clickCheckoutButton();
        checkoutStepOnePage.addDataInvalidLastName();
        checkoutStepOnePage.clickContinueButton();
        Assert.assertEquals(checkoutStepOnePage.errorMessage.getText(), checkoutStepOnePage.errorMessageLastName);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");


    }
    @Test(priority = 4)
    public void userIsNotAbleToProceedToStepTwoInvalidPostalCode() {
        String[] listOfItems = {"Sauce Labs Bolt T-Shirt", "Sauce Labs Backpack", "Sauce Labs Onesie"};
        homepage.loginValidCredentials();
        cartPage.deleteItemsIfFull();
        Assert.assertTrue(cartPage.assertCartIsEmpty());
        cartPage.goToInventoryPage();
        inventoryPage.addItemToCart(listOfItems);
        Assert.assertTrue(inventoryPage.cartNumberIsCorrect(3));
        Assert.assertTrue(cartPage.assertCorrectItemsAddedToCart(3, listOfItems));
        checkoutStepOnePage.clickCheckoutButton();
        checkoutStepOnePage.addDataInvalidPostalCode();
        checkoutStepOnePage.clickContinueButton();
        Assert.assertEquals(checkoutStepOnePage.errorMessage.getText(), checkoutStepOnePage.errorMessagePostalCode);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
    }
    @Test(priority = 5)
    public void userIsRedirectedToCartCancelButton(){
        String[] listOfItems = {"Sauce Labs Bolt T-Shirt", "Sauce Labs Backpack", "Sauce Labs Onesie"};
        homepage.loginValidCredentials();
        cartPage.deleteItemsIfFull();
        Assert.assertTrue(cartPage.assertCartIsEmpty());
        cartPage.goToInventoryPage();
        inventoryPage.addItemToCart(listOfItems);
        Assert.assertTrue(inventoryPage.cartNumberIsCorrect(3));
        Assert.assertTrue(cartPage.assertCorrectItemsAddedToCart(3, listOfItems));
        checkoutStepOnePage.clickCheckoutButton();
        checkoutStepOnePage.clickCancelButton();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/cart.html");

    }
    @Test(priority = 6)
    public void userIsAbleToFinalizePurchase() {
        String[] listOfItems = {"Sauce Labs Bolt T-Shirt", "Sauce Labs Backpack", "Sauce Labs Onesie"};
        homepage.loginValidCredentials();
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
        inventoryPage.clickCartButton();
        Assert.assertTrue(cartPage.assertCartIsEmpty());
    }
    @Test(priority = 7)
    public void backToHomeButtonFunctionality(){
        String[] listOfItems = {"Sauce Labs Bolt T-Shirt", "Sauce Labs Backpack", "Sauce Labs Onesie"};
        homepage.loginValidCredentials();
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
        checkoutCompletePage.clickBackHomeButton();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");

    }



}
