package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.support.*;

import java.time.Duration;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(className = "home-heading")
    WebElement homePageHeadingEle;

    @FindBy(className = "home-description")
    WebElement homePageDescritionEle;


    public HomePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }

    public String homePageHeading(){
        return homePageHeadingEle.getText();
    }

    public String homePageDes(){
        return  homePageDescritionEle.getText();
    }



}

