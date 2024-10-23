package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ItemSortingFunctionalityTests extends BaseTest {
    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");
    }
    @Test
    public void userCanSelectSortingZtoA(){
        homepage.loginValidCredentials();
        inventoryPage.sortingButton.click();
        inventoryPage.selectSortingOption("Name (Z to A)");
        Assert.assertTrue(inventoryPage.assertItemsAreSortedZtoA());
    }
    @Test(priority = 1)
    public void userCanSelectSortingAtoZ(){
        homepage.loginValidCredentials();
        inventoryPage.sortingButton.click();
        inventoryPage.selectSortingOption("Name (Z to A)");
        Assert.assertTrue(inventoryPage.assertItemsAreSortedZtoA());
        inventoryPage.selectSortingOption("Name (A to Z)");
        Assert.assertTrue(inventoryPage.assertItemsAreSortedAtoZ());
    }
    @Test(priority = 2)
    public void userCanSelectSortingPriceLowToHigh(){
        homepage.loginValidCredentials();
        inventoryPage.sortingButton.click();
        inventoryPage.selectSortingOption("Price (low to high)");
        Assert.assertTrue(inventoryPage.assertPriceTagsLowToHigh());
    }
    @Test(priority = 3)
    public void userCanSelectSortingPriceHighToLow(){
        homepage.loginValidCredentials();
        inventoryPage.sortingButton.click();
        inventoryPage.selectSortingOption("Price (high to low)");
        Assert.assertTrue(inventoryPage.assertPriceTagsHighToLow());
    }






}
