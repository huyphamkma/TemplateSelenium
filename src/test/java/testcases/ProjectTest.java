package testcases;

import common.BaseSetup;
import common.TestListener;
import helpers.ExcelHelpers;
import helpers.RecordVideoHelpers;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utilities.Log;
import utilities.PropertiesFile;

@Listeners(TestListener.class)
@Epic("Regression Tests")
@Feature("Project Tests")
public class ProjectTest extends BaseSetup{
    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    ExcelHelpers excelHelpers;

    @BeforeClass
    private void setUp() throws Exception {
        driver = getDriver();
        excelHelpers = new ExcelHelpers();
//        RecordVideoHelpers.startRecordATU("Project Test");
    }

    @Test(description = "login to the website")
//    @Step("login to the website")
    public void loginTest() throws Exception {
        loginPage = new LoginPage(driver);
        PropertiesFile.setPropertiesFile();
        excelHelpers.setExcelFile(PropertiesFile.getPropValue("ExcelPath"), "Sheet1");
        String email = excelHelpers.getCellData("email", 1);
        String password = excelHelpers.getCellData("password", 1);
        loginPage.signIn(email, password);
    }

    @Test(description = "go to Projects page")
    @Description("description")
    public void goToProjectPage() {
        dashboardPage = new DashboardPage(driver);
        dashboardPage.goToProjectsPage();
        Assert.assertTrue(false, "co tinh fail");
    }

    @Test(description = "go to Events page")

    public void goToEventsPage() {
        dashboardPage = new DashboardPage(driver);

        dashboardPage.goToEventsPage();

    }


    @AfterClass
    public void tearDown() throws Exception {
//        RecordVideoHelpers.stopRecordATU();
    }
}
