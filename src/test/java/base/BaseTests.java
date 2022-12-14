package base;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.SalesPage;
//import pages.HomePage;
//import pages.LoginPage;
//import pages.SalesPage;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseTests {

    private WebDriver driver;
    protected HomePage homePage;
    protected static LoginPage loginPage;
    protected static SalesPage salesPage;


    String BASEURL = "https://inspiredtesting.marketingautomation.services/";

    @BeforeMethod
    public void setup() {
        ChromeOptions options= new ChromeOptions();
//        options.addArguments("--headless");
        System.setProperty("webdriver.chrome.driver","resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
        driver.get(BASEURL);

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);



    }

    @AfterMethod
    public void captureScreenShots(ITestResult testResult) {

        if(ITestResult.FAILURE == testResult.getStatus()){
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File source = screenshot.getScreenshotAs(OutputType.FILE);
            File destination = new File(System.getProperty("user.dir") +
                    "/resources/screenshots/Failed/"+ testResult.getName() + "_" + testResult.getStartMillis() +".png");
            try {
                FileHandler.copy(source, destination);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File source = screenshot.getScreenshotAs(OutputType.FILE);
            File destination = new File(System.getProperty("user.dir") +
                    "/resources/screenshots/Passed/"+ testResult.getName() + "_" + testResult.getStartMillis() +".png");
            try {
                FileHandler.copy(source, destination);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    @AfterMethod()
//    public void tearDown() {
//        driver.quit();
//    }
}
