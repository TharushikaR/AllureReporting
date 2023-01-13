package allureReports;

import io.qameta.allure.*;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({AllureListener.class})
public class Tests extends BaseClass {

    WebDriver driver;

    @BeforeClass
    public void setup(){
        /*WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();*/
        BaseClass bs=new BaseClass();
        bs.initialize_driver();
        driver.get("https://demo.nopcommerce.com/");
    }

    @Test(priority = 1,description = "Verify logo availability on Home Page")
    @Description("Verify logo availability on Home Page")
    @Epic("EP001")
    @Feature("Feature 1 : Logo")
    @Story("Story: Logo Presence")
    @Step("Verify Logo Presence")
    @Severity(SeverityLevel.MINOR)
    public void logoPresence(){
        boolean disStatus=driver.findElement(By.xpath("//img[@alt=\"nopCommerce demo store\"]")).isDisplayed();
        Assert.assertTrue(disStatus);
    }

    @Test(priority = 2)
    @Description("Verify login")
    @Epic("EP001")
    @Feature("Feature 2 : Login")
    @Story("Story: Valid Login")
    @Step("Verify Login")
    @Severity(SeverityLevel.BLOCKER)
    public void loginTest(){
        driver.findElement(By.linkText("Log in")).click();
        driver.findElement(By.id("Email")).sendKeys("Test@123gmail.com");
        driver.findElement(By.id("Password")).sendKeys("Test@123");
        driver.findElement(By.xpath("//button[contains(@class, 'login-button')]")).click();

        Assert.assertEquals(driver.getTitle(),"nopCommerce demo store");
    }

    @Test(priority = 3)
    @Description("Verify user registration")
    @Epic("EP001")
    @Feature("Feature 3 : Registration")
    @Story("Story: User Registration")
    @Severity(SeverityLevel.NORMAL)
    public void registrationTest(){
        throw new SkipException("Skipping this test");
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

}
