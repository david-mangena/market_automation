package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class HomePage {
    private WebDriver driver;

    private By Sales = By.id("sales");
    private By Opportunities = By.xpath("//*[@id=\"sales\"]/div[3]/div[2]/a[1]/div");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void close_model() throws InterruptedException {
        Thread.sleep(10000);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ESCAPE).build().perform();
    }

    public void clickOnSales(){
        driver.findElement(Sales).click();
    }

    public SalesPage clickOnOpportunities(){
        driver.findElement(Opportunities).click();
        return new SalesPage(driver);
    }


}
