package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class SalesPage {

    private WebDriver driver;
    private By PipelineSales = By.id("pipelineFlowDropdownToggleButton");
    private By Filter_dateSelect = By.id("filter_dateSelect");
    private By Growth_Pipeline = By.linkText("Growth Pipeline");
//    private By Last_Month = By.linkText("Last Month");
    private By More_menu = By.cssSelector("#helpOverlayOptionsButton > a");

    private By ExportOpportunitiesToCSV = By.linkText("Export Opportunities to CSV");
    private By ExportManager = By.linkText("Export Manager");

    private By fileName = By.xpath("//*[@id=\"companyList\"]/div[2]/table/tbody/tr[1]/td[1]/a");



    public SalesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnPipelineSales(){
        driver.findElement(PipelineSales).click();
        driver.findElement(Growth_Pipeline).click();
    }

    public void clickOnFilter(){
        driver.findElement(Filter_dateSelect).click();
    }

    public void SelectDateRange(String Date_Ranges){
        WebElement Date_range = driver.findElement(By.className("datepickerPresets"));
        new Actions(driver)
                .moveToElement(Date_range).click()
                .build()
                .perform();
        driver.findElement(By.linkText(Date_Ranges)).click();
    }

    public void clickOnMoreMenu(){
        driver.findElement(More_menu).click();
    }

    public void ExportOpportunitiesToCSV() throws InterruptedException {
        driver.findElement(ExportOpportunitiesToCSV).click();
    }

    public void clickOnExportManager() throws InterruptedException {
        ArrayList<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(0));
//        driver.close();
        driver.switchTo().window(tabs2.get(1));
        Thread.sleep(6000);
        driver.findElement(ExportManager).click();
    }

    public void createCSVFIle() throws FileNotFoundException {
        driver.navigate().refresh();
        String fileNameTxt = driver.findElement(fileName).getText();
        String replacefileNameTxt = fileNameTxt.replace('-','_');

        Scanner sc = new Scanner(new File("c:\\Users\\dmangena\\Downloads\\"+replacefileNameTxt+".csv"));
        sc.useDelimiter("\n");
        while (sc.hasNext())
        {
            try(FileWriter fw = new FileWriter("c:\\Users\\dmangena\\Downloads\\Sharpsrping Data - Today.csv", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw))
            {
                String currentString = sc.nextLine();
                String finalString = currentString.replace(',', ',');
                out.println(finalString);
            } catch (IOException e) {
                System.err.println(e.toString());
            }
        }
        sc.close();  //closes the scanner
    }

    public void renameFile(){
        Path oldFile
                = Paths.get("c:\\Users\\dmangena\\Downloads\\Sharpspring Data - Today.csv");

        try {
            Files.move(oldFile, oldFile.resolveSibling(
                    "c:\\Users\\dmangena\\Downloads\\Sharpspring Data - Yesterday.csv"));
            System.out.println("File Successfully Rename");
        }
        catch (IOException e) {
            System.out.println("operation failed");
        }
    }

    public void downloadCSV_File(){
        List<WebElement> li = driver.findElements(By.className("listName"));;
        li.get(0).click();//If there are only two such element, here 1 is index of 2nd element in list returned.
    }

}

