package Pages;

import Base.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckOutStepTwoPage extends BaseTest {
    public CheckOutStepTwoPage(){
        PageFactory.initElements(driver,this);

    }
    public InventoryPage inventoryPage=new InventoryPage();
    public JavascriptExecutor js = ((JavascriptExecutor) driver);
    public WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    @FindBy(id = "finish")
    public WebElement finishButton;

    public void clickFinishButton(){
        js.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", finishButton);
        finishButton.click();
    }



}
