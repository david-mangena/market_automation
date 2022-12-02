package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;
    private By userNameField = By.id("okta-signin-username");
    private By passwordField = By.id("okta-signin-password");
    private By submitBtn = By.id("okta-signin-submit");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username){
        driver.findElement(userNameField).sendKeys(username);
    }

    public void enterPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }

    public HomePage ClickSubmitBtn(){
        driver.findElement(submitBtn).click();
        return new HomePage(driver);
    }
}
