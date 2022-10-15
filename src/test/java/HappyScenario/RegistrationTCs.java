package HappyScenario;

import Util.ReadDataExcel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class RegistrationTCs
{
    WebDriver rdriver;

    @Test(dataProvider = "Registration")
    public void registrationTCs(String Email,String Gender,String Customer_firstname,String Customer_lastname, String Password, String Days,
                                String months, String years, String first_name_address,String last_name_address,String Company,String address1,String address2,
                                String city,String State,String Postal_Code, String Country,  String addtional_info,String Home_phone,String Mobile_number, String Assigned_address){
        rdriver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).click();
        rdriver.findElement(By.id("email_create")).sendKeys(Email);
        rdriver.findElement(By.id("SubmitCreate")).click();
        rdriver.findElement(By.id("id_gender1")).click();
        rdriver.findElement(By.id("customer_firstname")).sendKeys(Customer_firstname);
        rdriver.findElement(By.id("customer_lastname")).sendKeys(Customer_lastname);
        rdriver.findElement(By.id("passwd")).sendKeys(Password);
        Select drpDays= new Select(rdriver.findElement(By.xpath("//*[@id=\'days\']")));
        drpDays.selectByVisibleText(Days);
        Select drMonths= new Select(rdriver.findElement(By.id("months")));
        drMonths.selectByVisibleText(months);
        Select drYears= new Select(rdriver.findElement(By.id("years")));
        drYears.selectByVisibleText(years);
        rdriver.findElement(By.id("firstname")).clear();
        rdriver.findElement(By.id("firstname")).sendKeys(first_name_address);
        rdriver.findElement(By.id("lastname")).clear();
        rdriver.findElement(By.id("lastname")).sendKeys(last_name_address);
        rdriver.findElement(By.id("company")).sendKeys(Company);
        rdriver.findElement(By.id("address1")).sendKeys(address1);
        rdriver.findElement(By.id("address2")).sendKeys(address2);
        rdriver.findElement(By.id("city")).sendKeys(city);
        Select drStates= new Select(rdriver.findElement(By.id("id_state")));
        drStates.selectByVisibleText(State);
        rdriver.findElement(By.id("postcode")).sendKeys(Postal_Code);
        Select drCountries= new Select(rdriver.findElement(By.id("id_country")));
        drCountries.selectByVisibleText(Country);
        rdriver.findElement(By.id("other")).sendKeys(addtional_info);
        rdriver.findElement(By.id("phone")).sendKeys(Home_phone);
        rdriver.findElement(By.id("phone_mobile")).sendKeys(Mobile_number);
        rdriver.findElement(By.id("alias")).sendKeys(Assigned_address);
        rdriver.findElement(By.id("submitAccount")).click();
        String welcome_msg= rdriver.findElement(By.xpath("//*[@id=\'center_column\']/h1")).getText();
        System.out.println(welcome_msg);
        Assert.assertEquals(welcome_msg,"MY ACCOUNT");
        String Account_name=rdriver.findElement(By.xpath("//*[@id=\'header\']/div[2]/div/div/nav/div[1]/a/span")).getText();
        Assert.assertEquals(Account_name ,Customer_firstname +" "+ Customer_lastname);
    }

    @BeforeClass
    public void open_browser(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Veronia\\IdeaProjects\\Automation_Task\\Broweser_Driver\\chromedriver.exe");
        rdriver = new ChromeDriver();
        rdriver.get("http://automationpractice.com/");
        rdriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @AfterClass
    public void close_browser()
    {
        rdriver.close();
    }

    @DataProvider(name="Registration")
    public String[][] gdata() throws IOException {
        String[][] obj = null;
        obj = ReadDataExcel.getData("Sheet1");
        return obj;
    }


}
