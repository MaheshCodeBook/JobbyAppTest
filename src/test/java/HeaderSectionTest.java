import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.HeaderSection;

import java.time.Duration;

public class HeaderSectionTest {
    WebDriver driver;
    WebDriverWait wait;
    HeaderSection headerSection;
    LoginPage loginPage;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\Downloads\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        headerSection = new HeaderSection(driver);
        driver.get("https://qajobbyapp.ccbp.tech/login");

        loginPage.loginIntoApplication("rahul","rahul@2021");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://qajobbyapp.ccbp.tech/"));
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test(priority = 1)
    public void testAppLogoImage(){
        Assert.assertTrue(headerSection.appLogoImage().isDisplayed(),"App logo is not displayed");
    }

    @Test(priority = 2)
    public void testNavBarJobLink(){
        headerSection.clickOnJobsNavBarLink();
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL,"https://qajobbyapp.ccbp.tech/jobs","URLs do not match");
    }

    @Test(priority = 3)
    public void testNavBarLink(){
        headerSection.appLogoImage().click();
        headerSection.clickOnAppLogoLink();
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL,"https://qajobbyapp.ccbp.tech/","URLs do not match");
    }


    @Test(priority = 4)
    public void testNavBarHomeLink(){
        headerSection.clickOnJobsNavBarLink();
        headerSection.clickOnHomeNavBarLink();
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL,"https://qajobbyapp.ccbp.tech/","URLs do not match");
    }





}

