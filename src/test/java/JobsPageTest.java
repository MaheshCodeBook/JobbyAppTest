import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;

import pages.*;

import java.time.Duration;


public class JobsPageTest {
    public WebDriver driver;
    WebDriverWait wait;
    LoginPage loginPage;
    HomePage homePage;
    HeaderSection headerSection;
    JobsPage jobsPage;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\Downloads\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        headerSection = new HeaderSection(driver);
        homePage = new HomePage(driver);
        jobsPage = new JobsPage(driver);
        driver.get("https://qajobbyapp.ccbp.tech/login");

        loginPage.loginIntoApplication("rahul","rahul@2021");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://qajobbyapp.ccbp.tech/"));
        jobsPage.clickOnFindJobs();

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test(priority = 1)
    public void testProfileContainerUI(){
        Assert.assertTrue(jobsPage.profileImage().isDisplayed(),"Profile image is not displayed");
        Assert.assertEquals(jobsPage.profileName(),"Rahul Attluri","Profile name does not match");
        Assert.assertEquals(jobsPage.profileBio(),"Lead Software Developer and AI-ML expert","Profile bio does not match");
    }

    @DataProvider
    public Object[][] Dataset(){
        return new Object[][] {
                {"Devops Engineer", 9},
                {"Backend Engineer", 11},
                {"Frontend Engineer", 13},
                {"Fullstack Developer", 6},
                {"Data Scientist", 11},
                {"ML Engineer", 10}
        };
    }

    @Test(priority = 2,dataProvider = "Dataset")
    public void testSuccessFullSearch(String position,int count){
        jobsPage.search(position);
        int jobsCount = jobsPage.jobsCount();
        Assert.assertEquals(jobsCount,count,"Jobs count does not match");
    }

    @Test(priority = 3)
    public void testUnsuccessFullSearch(){
        jobsPage.searchPosition("Netflix");
        jobsPage.searchButton();

        Assert.assertTrue(jobsPage.noJobsImage().isDisplayed(),"No jobs image is not displayed");
        Assert.assertEquals(jobsPage.noJobsText(),"No Jobs Found","Jobs not found heading does not match");
        Assert.assertEquals(jobsPage.noJobsDescription(),"We could not find any jobs. Try other filters.","Jobs not found description does not match");
    }

}
