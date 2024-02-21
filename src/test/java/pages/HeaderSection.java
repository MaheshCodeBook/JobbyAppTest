package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.*;

import java.time.Duration;

public class HeaderSection {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = "div[class *= 'large-container'] img.website-logo")
    WebElement appLogoImageEle;

    @FindBy(linkText = "Jobs")
    WebElement navJobsLinkEle;

    @FindBy(css = "div[class $= large-container]>a")
    WebElement applogoLinkEle;

    @FindBy(linkText = "Home")
    WebElement navHomeLinkEle;

    public HeaderSection(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }
    public WebElement appLogoImage(){
        return appLogoImageEle;
    }

    public void clickOnJobsNavBarLink(){
        navJobsLinkEle.click();
    }

    public void clickOnAppLogoLink(){
        applogoLinkEle.click();
    }

    public void clickOnHomeNavBarLink(){
        navHomeLinkEle.click();
    }




}

