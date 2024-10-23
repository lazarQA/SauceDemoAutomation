package Pages;

import Base.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutCompletePage extends BaseTest {
    public CheckoutCompletePage(){
        PageFactory.initElements(driver,this);
    }
    public JavascriptExecutor js = ((JavascriptExecutor) driver);
    public WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    public String successfullPurchaseText="Your order has been dispatched, and will arrive just as fast as the pony can get there!";



    @FindBy(id = "back-to-products")
    public WebElement backHomeButton;
    @FindBy(className = "complete-text")
    public WebElement successfullPurchaseNotification;

    public void clickBackHomeButton(){
        js.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", backHomeButton);
        backHomeButton.click();

    }
}
