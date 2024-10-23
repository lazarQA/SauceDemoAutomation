package Pages;

import Base.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CartPage extends BaseTest {
    public CartPage(){
        PageFactory.initElements(driver,this);
    }
    public InventoryPage inventoryPage=new InventoryPage();
    public JavascriptExecutor js = ((JavascriptExecutor) driver);
    public WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    @FindBy(css = ".btn.btn_secondary.btn_small.cart_button")
    public List<WebElement> removeItemButtons;
    @FindBy(className = "cart_item_label")
    public List<WebElement> itemsInsideCart;
    @FindBy(id = "continue-shopping")
    public WebElement continueShoppingButton;

    public void goToInventoryPage(){
        driver.navigate().to("https://www.saucedemo.com/inventory.html");
    }
    //-------Methods for Clicking Buttons

    public void clickContinueShopping(){
        js.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", continueShoppingButton);
        continueShoppingButton.click();
    }

    public void emptyCart(){
        inventoryPage.clickCartButton();
        for (int i =removeItemButtons.size()-1;i>=0;i--){
            removeItemButtons.get(i).click();
        }
    }
    //-------Methods for Assertations
    public boolean assertCartIsEmpty(){
        boolean isEmpty=false;
        inventoryPage.clickCartButton();
        if (removeItemButtons.isEmpty()){
            isEmpty=true;
        }
        return isEmpty;
    }
    public void deleteItemsIfFull(){
        inventoryPage.clickCartButton();
        if (!assertCartIsEmpty()){
            emptyCart();
        }
    }

    public boolean assertCorrectItemsAddedToCart(int expectedNumberOfItems, String[] namesList){
        int counter=0;
        boolean isTrue=false;
        js.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", inventoryPage.cartButton);
        inventoryPage.cartButton.click();
        wait.until(ExpectedConditions.visibilityOf(itemsInsideCart.getFirst()));
        for (WebElement oneItem:itemsInsideCart){
            for (String itemName:namesList){
                if (oneItem.getText().contains(itemName)){
                    js.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", oneItem);
                    counter++;
                }
            }
        }
        if (counter==expectedNumberOfItems){
            isTrue=true;
        }
        return isTrue;
    }

    private static List<String> getStrings() {
        List<String> listOfNames=new ArrayList<>();
        String name1="Sauce Labs Backpack";
        String name2="Sauce Labs Bike Light";
        String name3="Sauce Labs Bolt T-Shirt";
        String name4="Sauce Labs Fleece Jacket";
        String name5="Sauce Labs Onesie";
        String name6="Test.allTheThings() T-Shirt (Red)";
        listOfNames.add(name1);
        listOfNames.add(name2);
        listOfNames.add(name3);
        listOfNames.add(name4);
        listOfNames.add(name5);
        listOfNames.add(name6);
        return listOfNames;
    }


}
