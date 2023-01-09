package base;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SalesPage;

import java.io.IOException;


public class Market_automation_script extends BaseTests {

    @Test(dataProvider = "LoginData")
    public void marketAutomationScript(String UserName, String Password) throws InterruptedException, IOException {
        loginPage.enterUsername(UserName);
        loginPage.enterPassword(Password);
        loginPage.ClickSubmitBtn();

//        HomePage homePage = loginPage.ClickSubmitBtn();
        homePage.close_model();
        homePage.clickOnSales();

        SalesPage salesPage = homePage.clickOnOpportunities();
        salesPage.clickOnPipelineSales();
        salesPage.clickOnFilter();
        salesPage.SelectDateRange("All Dates");
        salesPage.clickOnMoreMenu();
        salesPage.ExportOpportunitiesToCSV();


        salesPage.clickOnExportManager();
        salesPage.downloadCSV_File();

        //waiting for csv file to complete download
        Thread.sleep(6000);
//        salesPage.renameFile();
        salesPage.createCSVFIle();
//        salesPage.deleteDownloadedCSV_File();

    }

    @DataProvider()
    public Object[][] LoginData(){
        Object[][] data = new Object[1][2];
        data[0][0] = "automationscript"; data[0][1] = "1nsp1r3d";
        return data;
    }
}
