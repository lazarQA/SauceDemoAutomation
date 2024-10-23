package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SidemenuPage extends BaseTest {
    public SidemenuPage() {
        PageFactory.initElements(driver, this);
    }

    public JavascriptExecutor js = ((JavascriptExecutor) driver);
    public WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    public InventoryPage inventoryPage = new InventoryPage();
    @FindBy(id = "react-burger-menu-btn")
    public WebElement sidemenuButton;
    @FindBy(id = "react-burger-cross-btn")
    public WebElement closeSidemenuButton;
    @FindBy(css = ".bm-item.menu-item")
    public List<WebElement> sidemenuOptions;

    public void openSidemenu() {
        sidemenuButton.click();
        wait.until(ExpectedConditions.visibilityOf(closeSidemenuButton));

    }
    public void closeSidemenu(){
        closeSidemenuButton.click();
        wait.until(ExpectedConditions.visibilityOf(sidemenuButton));

    }

    public Boolean assertMenuClickableOnAllPages() {
        boolean isTrue = true;
        for (String url : listOfURLS()) {
            driver.navigate().to(url);
            if (!sidemenuButton.isDisplayed()) {
                isTrue = false;
                break;
            }
            sidemenuButton.click();
            if (sidemenuOptions.size() != 4) {
                isTrue = false;
                break;
            }
        }
        return isTrue;
    }
    public Boolean sidemenuContainsValidOptions(){
        boolean isTrue=false;
        int counter=0;
        sidemenuButton.click();
        wait.until(ExpectedConditions.visibilityOf(closeSidemenuButton));
        for (String validOption:listOfMenuOptionsString()){
            for (WebElement oneItem:sidemenuOptions){
                if (oneItem.getText().equalsIgnoreCase(validOption)){
                    counter++;
                }
            }
        }
        if (counter==4){
            isTrue=true;
        }
        return isTrue;
    }
    public void clickSideMenuOption(String chooseOption){
        sidemenuButton.click();
        wait.until(ExpectedConditions.visibilityOf(closeSidemenuButton));
        for (WebElement oneOption:sidemenuOptions){
            if (oneOption.getText().equals(chooseOption)){
                oneOption.click();
                break;
            }
        }
    }
    public boolean closeButtonIsDisplayed(){
        boolean isTrue=false;
        try {
            isTrue=driver.findElement(By.id("react-burger-cross-btn")).isDisplayed();
        }catch (Exception e){

        }
        return isTrue;
    }









    public List<String> listOfURLS() {
        List<String> listOfURLS = new ArrayList<>();
        String url1 = "https://www.saucedemo.com/inventory.html";
        String url2 = "https://www.saucedemo.com/inventory.html";
        String url3 = "https://www.saucedemo.com/cart.html";
        String url4 = "https://www.saucedemo.com/checkout-step-one.html";
        String url5 = "https://www.saucedemo.com/checkout-step-two.html";
        String url6 = "https://www.saucedemo.com/checkout-complete.html";
        listOfURLS.add(url1);
        listOfURLS.add(url2);
        listOfURLS.add(url3);
        listOfURLS.add(url4);
        listOfURLS.add(url5);
        listOfURLS.add(url6);
        return listOfURLS;
    }
    public List<String> listOfMenuOptionsString(){
        List<String> listOfMenuOptionsString=new ArrayList<>();
        String allItems="All Items";
        String about="About";
        String logout="Logout";
        String resetAppState="Reset App State";
        listOfMenuOptionsString.add(allItems);
        listOfMenuOptionsString.add(about);
        listOfMenuOptionsString.add(logout);
        listOfMenuOptionsString.add(resetAppState);
        return listOfMenuOptionsString;

    }
}
