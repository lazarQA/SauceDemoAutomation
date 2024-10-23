package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class InventoryPage extends BaseTest {
    public InventoryPage() {
        PageFactory.initElements(driver, this);
    }

    public JavascriptExecutor js = ((JavascriptExecutor) driver);
    public WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    //--------Button Webelements
    @FindBy(id = "shopping_cart_container")
    public WebElement cartButton;
    @FindBy(id = "react-burger-menu-btn")
    public WebElement menuButton;
    @FindBy(id = "logout_sidebar_link")
    public WebElement logoutButton;
    @FindBy(css = ".btn.btn")
    public List<WebElement> addToCartButtons;
    @FindBy(className = "shopping_cart_badge")
    public WebElement numberOfItemsInCart;
    @FindBy(css = ".btn.btn_secondary.btn_small.btn_inventory ")
    public List<WebElement> removeItemsInventoryPage;
    @FindBy(className = "product_sort_container")
    public WebElement sortingButton;
    @FindBy (className = "inventory_item_price")
    public List<WebElement> priceTags;
    //---------------------------
    @FindBy(css = ".inventory_item_name ")
    public List<WebElement> listOfInventoryItems;

    //--------Methods for Clicking Buttons
    public void clickCartButton() {
        cartButton.click();
    }

    //--------Methods for Inventory items


    public void addItemToCart(String[] listOfItems) {
        for (String oneItem : listOfItems) {
            int counter = 0;
            for (WebElement pageItem : listOfInventoryItems) {
                counter++;
                if (oneItem.equals(pageItem.getText())) {
                    addToCartButtons.get(counter - 1).click();
                    break;
                }
            }
        }


    }
    public void removeItemsInventoryPage() {
        for (int i = removeItemsInventoryPage.size() - 1; i >= 0; i--) {
            js.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", removeItemsInventoryPage.get(i));
            removeItemsInventoryPage.get(i).click();
        }
    }



    //Methods for Sorting Menu
    public void selectSortingOption(String sortingOption) {
        Select sortingMenu = new Select(sortingButton);
        sortingMenu.selectByVisibleText(sortingOption);
    }
    public List<Double> getPrices(){
        List<Double> getPrices=new ArrayList<>();
        for (WebElement onePrice:priceTags){
            getPrices.add(Double.parseDouble(onePrice.getText().replace("$", "")));
        }
        return getPrices;
    }


    public boolean assertItemsAreSortedZtoA() {
        boolean isTrue = true;
        for (int i = 0; i < listOfInventoryItems.size() - 1; i++) {
            js.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", listOfInventoryItems.get(i));
            if (listOfInventoryItems.get(i).getText().compareTo(listOfInventoryItems.get(i + 1).getText()) < 0) {
                isTrue = false;
                break;
            }
        }
        return isTrue;
    }

    public boolean assertItemsAreSortedAtoZ() {
        boolean isTrue = true;
        for (int i = 0; i < listOfInventoryItems.size() - 1; i++) {
            js.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", listOfInventoryItems.get(i));
            if (listOfInventoryItems.get(i).getText().compareTo(listOfInventoryItems.get(i + 1).getText()) > 0) {
                isTrue = false;
                break;
            }
        }
        return isTrue;
    }

    public boolean  assertPriceTagsLowToHigh(){
        boolean isTrue=true;
        for (int i=0;i< getPrices().size()-1;i++){
            if (getPrices().get(i)>getPrices().get(i+1)){
                isTrue=false;
                break;
            }
        }
        return isTrue;
    }
    public boolean  assertPriceTagsHighToLow(){
        boolean isTrue=true;
        for (int i=0;i< getPrices().size()-1;i++){
            if (getPrices().get(i)<getPrices().get(i+1)){
                isTrue=false;
                break;
            }
        }
        return isTrue;
    }



    //------Methods for Assertations--Hamburger Menu
    public boolean logoutButtonIsDisplayed() {
        boolean logoutButtonIsDisplayed = false;
        js.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", menuButton);
        menuButton.click();
        wait.until(ExpectedConditions.visibilityOf(logoutButton));
        if (logoutButton.isDisplayed()) {
            logoutButtonIsDisplayed = true;
        }
        return logoutButtonIsDisplayed;
    }


    public boolean cartNumberIsCorrect(int expectedNumberInsideCart) {
        boolean isCorrect = false;
        String expectedNumber = String.valueOf(expectedNumberInsideCart);
        js.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", menuButton);
        if (numberOfItemsInCart.getText().equals(expectedNumber)) {
            isCorrect = true;
        }
        return isCorrect;
    }

    public boolean cartNumberIsDisplayed() {
        boolean isDisplayed = false;
        try {
            isDisplayed = numberOfItemsInCart.isDisplayed();
        } catch (Exception e) {

        }
        return isDisplayed;

    }

}