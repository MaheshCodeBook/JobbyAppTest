import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.support.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.time.Duration;


public class LoginPageTest {
    public WebDriver driver;
    WebDriverWait wait;
    LoginPage loginPage;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\Downloads\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        driver.get("https://qajobbyapp.ccbp.tech/login");
    }

    @Test(priority = 1)
    public void testLoginPageUI(){
        Assert.assertTrue(loginPage.appLogo().isDisplayed(),"App logo is not displayed");
        Assert.assertEquals(loginPage.userNameLabel(),"USERNAME","UserName label text does not match");
        Assert.assertEquals(loginPage.passwordLabel(),"PASSWORD","Password label text does not match");
    }

    @Test(priority = 2)
    public void testLoginWithEmptyInputs(){
        loginPage.loginIntoApplication("","");
        Assert.assertEquals(loginPage.errorMessage(),"*Username or password is invalid","Error text with empty input fields does not match");
    }

    @Test(priority = 3)
    public  void testLoginWithEmptyUserName(){
        loginPage.loginIntoApplication("","rahul@2021");
        Assert.assertEquals(loginPage.errorMessage(),"*Username or password is invalid","Error text with empty input fields does not match");
    }

    @Test(priority = 4)
    public  void testLoginWithEmptyPassword(){
        loginPage.loginIntoApplication("rahul","");
        Assert.assertEquals(loginPage.errorMessage(),"*Username or password is invalid","Error text with empty input fields does not match");
    }

    @Test(priority = 5)
    public void testLoginWithInvalidCred(){
        loginPage.loginIntoApplication("rahul","rahul2021");
        Assert.assertEquals(loginPage.errorMessage(),"*username and password didn't match","Error text with invalid password do not match");
    }

    @Test(priority = 6)
    public void testLoginWithValidCred(){
        loginPage.loginIntoApplication("rahul","rahul@2021");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://qajobbyapp.ccbp.tech/"));
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL,"https://qajobbyapp.ccbp.tech/","URLs do not match");

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }




}
