import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HeaderSection;
import pages.HomePage;
import pages.LoginPage;

import java.time.Duration;

public class HomePageTest {

    WebDriver driver;
    WebDriverWait wait;
    LoginPage loginPage;
    HeaderSection headerSection;
    HomePage homePage;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\Downloads\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        headerSection = new HeaderSection(driver);
        homePage = new HomePage(driver);
        driver.get("https://qajobbyapp.ccbp.tech/login");

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

    @Test(priority = 1)
    public void testHeadingAndDescription(){
        Assert.assertEquals(homePage.homePageHeading(),"Find The Job That Fits Your Life","Heading text does not match");
        Assert.assertEquals(homePage.homePageDes(),"Millions of people are searching for jobs, salary information, company reviews. Find the job that fits your abilities and potential.","Description text does not match");


    }
    @Test(priority = 2)
    public void testJobsButtonFuns(){
        headerSection.clickOnJobsNavBarLink();
        String expectedURL = "https://qajobbyapp.ccbp.tech/jobs";
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL,expectedURL,"URLs do not match");

    }

}

