package pages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.*;

import java.time.Duration;

public class LoginPage {
    WebDriverWait wait;
    WebDriver driver;

    @FindBy(className = "login-website-logo")
    WebElement appLogoEle;

    @FindBy(css = "label[for='userNameInput']")
    WebElement userNameLabelEle;

    @FindBy(css = "label[for='passwordInput']")
    WebElement passwordLabelEle;

    @FindBy(id = "userNameInput")
    WebElement userNameEle;

    @FindBy(id = "passwordInput")
    WebElement passwordEle;

    @FindBy(className = "login-button")
    WebElement logInButtonEle;

    @FindBy(className = "error-message")
    WebElement errorMessageEle;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }

    public WebElement appLogo(){
        return  appLogoEle;
    }

    public String userNameLabel(){
        return userNameLabelEle.getText();
    }

    public String passwordLabel(){
        return passwordLabelEle.getText();
    }

    public void enterUserName(String username){
        userNameEle.sendKeys(username);
    }

    public void enterPassword(String password){
       passwordEle.sendKeys(password);
    }
    public void clickOnLogin(){
        logInButtonEle.click();
    }

    public void loginIntoApplication(String username,String password){
        enterUserName(username);
        enterPassword(password);
        clickOnLogin();
    }

    public String errorMessage(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message"))).getText();
    }




}
