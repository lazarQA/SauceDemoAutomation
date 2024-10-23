package Base;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class BaseTest {
    public static WebDriver driver;
    public WebDriverWait wait;
    public JavascriptExecutor js;
    public Homepage homepage;
    public InventoryPage inventoryPage;
    public CartPage cartPage;
    public SidemenuPage sidemenuPage;
    public CheckoutStepOnePage checkoutStepOnePage;
    public CheckOutStepTwoPage checkOutStepTwoPage;
    public CheckoutCompletePage checkoutCompletePage;


    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = ((JavascriptExecutor) driver);

        homepage = new Homepage();
        inventoryPage=new InventoryPage();
        cartPage=new CartPage();
        sidemenuPage=new SidemenuPage();
        checkoutStepOnePage=new CheckoutStepOnePage();
        checkOutStepTwoPage=new CheckOutStepTwoPage();
        checkoutCompletePage=new CheckoutCompletePage();
    }

}
