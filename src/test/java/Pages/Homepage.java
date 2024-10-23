package Pages;

import Base.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Homepage extends BaseTest {
    public Homepage(){
        PageFactory.initElements(driver,this);
    }
    public JavascriptExecutor js=((JavascriptExecutor) driver);
    public WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
    public String expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service";
    public InventoryPage inventoryPage=new InventoryPage();
    @FindBy(id="user-name")
    public WebElement usernameField;
    @FindBy(id="password")
    public WebElement passwordField;
    @FindBy(id="login-button")
    public WebElement loginButton;
    @FindBy(css = ".error-message-container.error")
    public WebElement errorMessage;

    public String errorMesageText(){
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }


    public void loginValidCredentials(){
        String validUsername = "standard_user";
        String validPassword="secret_sauce";
        usernameField.clear();
        passwordField.clear();
        usernameField.sendKeys(validUsername);
        passwordField.sendKeys(validPassword);
        loginButton.click();
    }
    public void loginInvalidUsername(){
        String invalidUsername = "standarduser";
        String validPassword="secret_sauce";
        usernameField.clear();
        passwordField.clear();
        usernameField.sendKeys(invalidUsername);
        passwordField.sendKeys(validPassword);
        loginButton.click();
    }

    public void loginInvalidPassword(){
        String validUsername = "standard_user";
        String invalidPassword="secret_sauce123";
        usernameField.clear();
        passwordField.clear();
        usernameField.sendKeys(validUsername);
        passwordField.sendKeys(invalidPassword);
        loginButton.click();
    }
    public void loginInvalidUsernameInvalidPassword(String invalidUsername, String invalidPassword){
        usernameField.clear();
        passwordField.clear();
        usernameField.sendKeys(invalidUsername);
        passwordField.sendKeys(invalidPassword);
        loginButton.click();

    }
    public Boolean user_unable_to_access_pages_if_not_logged_in(){
        boolean isAble=false;
        //Program checks if user IS loged in, if he is loged in, then program logs out, before trying different URLS
        Boolean isLogedIn=false;
        try {
            isLogedIn=inventoryPage.cartButton.isDisplayed();
        }catch (Exception e){

        }
        if (isLogedIn){
            inventoryPage.menuButton.click();
            wait.until(ExpectedConditions.visibilityOf(inventoryPage.logoutButton));
            inventoryPage.logoutButton.click();
            wait.until(ExpectedConditions.visibilityOf(loginButton));
        }

            driver.navigate().to("https://www.saucedemo.com/inventory.html");
            if (!errorMesageText().equals("Epic sadface: You can only access '/inventory.html' when you are logged in.")){
                isAble=true;
            }
            driver.navigate().to("https://www.saucedemo.com/cart.html");
            if (!errorMesageText().equals("Epic sadface: You can only access '/cart.html' when you are logged in.")){
                isAble=true;
            }
            driver.navigate().to("https://www.saucedemo.com/checkout-step-one.html");
            if (!errorMesageText().equals("Epic sadface: You can only access '/checkout-step-one.html' when you are logged in.")){
                isAble=true;
            }
            driver.navigate().to("https://www.saucedemo.com/checkout-step-two.html");
            if (!errorMesageText().equals("Epic sadface: You can only access '/checkout-step-one.html' when you are logged in.")){
                isAble=true;
            }
            driver.navigate().to(" https://www.saucedemo.com/checkout-complete.html");
            if (!errorMesageText().equals("Epic sadface: You can only access '/checkout-complete.html' when you are logged in.")){
                isAble=true;
            }

        return isAble;
    }

}
