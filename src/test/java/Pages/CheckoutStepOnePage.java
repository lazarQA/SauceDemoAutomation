package Pages;

import Base.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CheckoutStepOnePage extends BaseTest {
    public CheckoutStepOnePage(){
        PageFactory.initElements(driver,this);
    }
    public InventoryPage inventoryPage=new InventoryPage();
    public JavascriptExecutor js = ((JavascriptExecutor) driver);
    public WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    public String errorMessageFirstName="Error: First Name is required";
    public String errorMessageLastName="Error: Last Name is required";
    public String errorMessagePostalCode="Error: Postal Code is required";

    @FindBy(id = "checkout")
    public WebElement checkoutButton;
    @FindBy(id = "first-name")
    public WebElement firstNameField;
    @FindBy(id = "last-name")
    public WebElement lastNameField;
    @FindBy(id="postal-code")
    public WebElement postalCodeField;
    @FindBy(id = "continue")
    public WebElement continueButton;
    @FindBy(id = "finish")
    public WebElement checkoutSteptwoFinishButton;
    @FindBy(css = ".error-message-container.error")
    public WebElement errorMessage;
    @FindBy(id = "cancel")
    public WebElement stepOneCancelButton;


    public void clickCheckoutButton(){
        js.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", checkoutButton);
        checkoutButton.click();
    }
    public void clickContinueButton(){
        js.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", continueButton);
        continueButton.click();

    }
    public void clickCancelButton(){
        js.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", stepOneCancelButton);
        stepOneCancelButton.click();

    }
    public boolean allThreeFieldsPresent(){
        boolean isTrue=true;
        for (WebElement oneField:listOfFields()){
            if (!oneField.isDisplayed()){
                isTrue=false;
                break;
            }
        }
        return isTrue;
    }
    public void addValidDataToFields(){
        String validFirstName="Lazar";
        String validLastName="Arsic";
        String validPostalCode="123456789";
        for (WebElement oneField:listOfFields()){
            oneField.clear();
        }
        firstNameField.sendKeys(validFirstName);
        lastNameField.sendKeys(validLastName);
        postalCodeField.sendKeys(validPostalCode);
    }
    public void addDataInvalidFirstName(){
        String validLastName="Arsic";
        String validPostalCode="123456789";
        for (WebElement oneField:listOfFields()){
            oneField.clear();
        }
        lastNameField.sendKeys(validLastName);
        postalCodeField.sendKeys(validPostalCode);
    }
    public void addDataInvalidLastName(){
        String firstName="Lazar";
        String validPostalCode="123456789";
        for (WebElement oneField:listOfFields()){
            oneField.clear();
        }
        firstNameField.sendKeys(firstName);
        postalCodeField.sendKeys(validPostalCode);
    }
    public void addDataInvalidPostalCode(){
        String firstName="Lazar";
        String lastName="Arsic";
        for (WebElement oneField:listOfFields()){
            oneField.clear();
        }
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
    }





    public List<WebElement> listOfFields(){
        List<WebElement> listOfFields = new ArrayList<>();
        listOfFields.add(firstNameField);
        listOfFields.add(lastNameField);
        listOfFields.add(postalCodeField);
        return listOfFields;
    }

}

