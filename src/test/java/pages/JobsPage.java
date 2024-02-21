package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.support.*;

import java.time.Duration;
import java.util.List;

public class JobsPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(className = "profile-img")
    WebElement profileImageEle;

    @FindBy(className = "find-jobs-button")
    WebElement findJobsEle;

    @FindBy(className = "profile-name")
    WebElement profileNameEle;

    @FindBy(className = "short-bio")
    WebElement profileBioEle;

    @FindBy(css = "div[class ^= 'desktop-search'] input.search-input")
    WebElement searchInputEle;

    @FindBy(css = "div[class ^= 'desktop-search'] button.search-button")
    WebElement searchButtonEle;

    @FindBy(css = "ul.jobs-list a")
    List<WebElement> jobsListEle;

    @FindBy(className = "jobs-not-found-img")
    WebElement noJobsImageEle;

    @FindBy(className = "jobs-not-found-heading")
    WebElement noJobsTextEle;

    @FindBy(className = "jobs-not-found-description")
    WebElement noJobsDesEle;

    public JobsPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }

    public WebElement profileImage(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("profile-img")));
    }
    public void clickOnFindJobs(){
        findJobsEle.click();
    }

    public String profileName(){
        return profileNameEle.getText();
    }

    public String profileBio(){
        return profileBioEle.getText();
    }

    public void searchPosition(String position){
        searchInputEle.sendKeys(position);

    }

    public void searchButton(){
        searchButtonEle.click();
    }

    public int jobsCount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul.jobs-list a")));
        return jobsListEle.size();
    }

    public void search(String position) {
        searchPosition(position);
        searchButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("results-text")));
    }

    public WebElement noJobsImage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("jobs-not-found-img")));
        return noJobsImageEle;
    }

    public String noJobsText(){
        return noJobsTextEle.getText();
    }

    public String noJobsDescription(){
        return noJobsDesEle.getText();
    }








}
