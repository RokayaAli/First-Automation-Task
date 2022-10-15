package HappyScenario;

import Util.ReadDataExcel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ShoppinhScenraio {
    WebDriver rdriver;
    String Refrence;
    WebDriverWait wait;
    @Test(dataProvider = "Login",priority = 1)
    public void Login_TCs(String Email_Address, String Password, String Customer_firstname ,String Customer_lastname) {
        rdriver.findElement(By.xpath("//*[@id=\'header\']/div[2]/div/div/nav/div[1]/a")).click();
        rdriver.findElement(By.id("email")).sendKeys(Email_Address);
        rdriver.findElement(By.id("passwd")).sendKeys(Password);
        rdriver.findElement(By.id("SubmitLogin")).click();
        String welcome_msg = rdriver.findElement(By.xpath("//*[@id=\'center_column\']/h1")).getText();
        System.out.println(welcome_msg);
        Assert.assertEquals(welcome_msg, "MY ACCOUNT");
        String Account_name = rdriver.findElement(By.xpath("//*[@id=\'header\']/div[2]/div/div/nav/div[1]/a/span")).getText();
        Assert.assertEquals(Account_name, Customer_firstname + " " + Customer_lastname);
    }

    @Test(priority = 2)

    public void Shop_TCs()
    {   Actions action = new Actions(rdriver);
        WebElement hover = rdriver.findElement(By.xpath("//*[@id=\'block_top_menu\']/ul/li[1]/a"));
        action.moveToElement(hover).moveToElement(rdriver.findElement(By.xpath("//*[@id=\'block_top_menu\']/ul/li[1]/ul/li[1]/ul/li[2]/a"))).click().build().perform();
        String Blouses = rdriver.findElement(By.xpath("//*[@id=\'center_column\']/h1/span[1]")).getText();
        System.out.println(Blouses);
        Assert.assertEquals(Blouses, "BLOUSES ");
    }
    @Test(priority = 3)
    public void SelectResultedProduct_TCs()
    {
        Actions action = new Actions(rdriver);
        WebElement hover1 = rdriver.findElement(By.xpath("//*[@id=\'center_column\']/ul/li/div"));
        action.moveToElement(hover1).moveToElement(rdriver.findElement(By.xpath("//*[@id=\'center_column\']/ul/li/div/div[2]/div[2]/a[1]/span"))).click().build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'layer_cart\']/div[1]/div[1]/h2")));
        String AddToCartMsg= rdriver.findElement(By.xpath("//*[@id=\'layer_cart\']/div[1]/div[1]/h2")).getText();
        System.out.println(AddToCartMsg);
        Assert.assertEquals(AddToCartMsg, "Product successfully added to your shopping cart");

    }
    @Test(priority = 4)
    public void AddToCart_TCs() {
        rdriver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a/span")).click();
        String ShoppingCartSummary = rdriver.findElement(By.id("cart_title")).getText();
        System.out.println(ShoppingCartSummary);
        Assert.assertEquals(ShoppingCartSummary, "SHOPPING-CART SUMMARY\n" +
                "Your shopping cart contains: 1 Product");
    }
    @Test(priority = 5)
    public void CartSummary()
    {
        rdriver.findElement(By.xpath("//*[@id=\'center_column\']/p[2]/a[1]/span")).click();
        String AddressesMsg = rdriver.findElement(By.xpath("//*[@id=\'center_column\']/h1")).getText();
        System.out.println(AddressesMsg);
        Assert.assertEquals(AddressesMsg, "ADDRESSES");
    }
    @Test(priority = 6)
    public void ShippingSummary()
    {
        rdriver.findElement(By.xpath("//*[@id=\'center_column\']/form/p/button/span")).click();
        String ShippingMsg = rdriver.findElement(By.xpath("//*[@id=\'carrier_area\']/h1")).getText();
        System.out.println(ShippingMsg);
        Assert.assertEquals(ShippingMsg, "SHIPPING");
    }
    @Test(priority = 7)
    public void SelectPaymentMethod()
    {
        rdriver.findElement(By.id("uniform-cgv")).click();
        rdriver.findElement(By.xpath("//*[@id=\'form\']/p/button/span")).click();
        String PaymentMethodMsg = rdriver.findElement(By.xpath("//*[@id=\'center_column\']/h1")).getText();
        System.out.println(PaymentMethodMsg);
        Assert.assertEquals(PaymentMethodMsg, "PLEASE CHOOSE YOUR PAYMENT METHOD");
    }
    @Test(priority = 8)
    public void OrderSummary()
    {
       // rdriver.findElement(By.id("uniform-cgv")).click();
        rdriver.findElement(By.xpath("//*[@id=\'HOOK_PAYMENT\']/div[1]/div/p/a")).click();
        String OrderSummaryMsg = rdriver.findElement(By.xpath("//*[@id=\'center_column\']/h1")).getText();
        System.out.println(OrderSummaryMsg);
        Assert.assertEquals(OrderSummaryMsg, "ORDER SUMMARY");
    }
    @Test(priority = 9)
    public void ConfirmOrder()
    {
        rdriver.findElement(By.xpath("//*[@id=\'cart_navigation\']/button/span")).click();
        String OrderConfirmationMsg= rdriver.findElement(By.xpath("//*[@id=\'center_column\']/h1")).getText();
        System.out.println(OrderConfirmationMsg);
        Assert.assertEquals(OrderConfirmationMsg, "ORDER CONFIRMATION");
        String SymRef=  rdriver.findElement(By.xpath("//*[@id=\'center_column\']/div")).getText();
        Refrence= SymRef.substring(SymRef.indexOf("reference")+10,SymRef.lastIndexOf("in the subject of your bank wire")-1);
        System.out.println(Refrence);

    }

    @Test(priority = 10)
    public void ValidateOrder()
    {
        rdriver.findElement(By.xpath("//*[@id=\'center_column\']/p/a")).click();
        String OrderRef = rdriver.findElement(By.xpath("//*[@id=\'order-list\']/tbody/tr[1]/td[1]/a")).getText();
        Assert.assertEquals(OrderRef, Refrence);

    }

    @BeforeClass
    public void open_browser(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Veronia\\IdeaProjects\\Automation_Task\\Broweser_Driver\\chromedriver.exe");
        rdriver = new ChromeDriver();
        rdriver.get("http://automationpractice.com/");
        rdriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        wait = new WebDriverWait(rdriver, Duration.ofSeconds(20));

        /*  this is for resolution (1024x768)
        //    // MutableCapabilities capabilities = new MutableCapabilities();
        //    // HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
        //    //browserstackOptions.put("resolution", "1024x768");
        //    //// Set the selenium version to 4.0.0.
        //    //browserstackOptions.put("seleniumVersion", "4.0.0");
        //    //capabilities.setCapability("bstack:options", browserstackOptions);*/
    }

    @AfterClass
    public void close_browser() {
        rdriver.close();
    }

    @DataProvider(name = "Login")
    public String[][] gdata() throws IOException {
        String[][] obj = null;
        obj = ReadDataExcel.getData("Login");
        return obj;
    }
}