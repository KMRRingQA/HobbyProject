package com.qa.selenium;
import com.qa.App;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    WebDriver driver;
    ExtentReports report;
    ExtentTest test;

    @BeforeTest
    public void startReport() throws InterruptedException {
        report = new ExtentReports(
                System.getProperty("user.dir") + "/test-output/Report.html",
                true
        );
        report
                .addSystemInfo("Host Name", "QA")
                .addSystemInfo("Tester", "Tadas");
        report.loadConfig(new File(System.getProperty("user.dir") + "\\extent-report.xml"));
//        SpringApplication.run(App.class);
//        sleep(10000);
    }

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
    }

    @Test
    public void testQATitle() throws InterruptedException, IOException {
        test = report.startTest("buying cheapest first class UA flight to paris");
        driver.manage().window().maximize();
        test.log(LogStatus.INFO, "Started chrome browser and made it fullscreen");
        driver.get("http://localhost:8181/");
        sleep(5000);
        test.log(LogStatus.INFO, "Started chrome browser and made it fullscreen");
        WebElement browse = (new WebDriverWait(driver, 1000)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#menu > ul > li:nth-child(2) > a")));
        browse.click();

        WebElement search = (new WebDriverWait(driver, 1000)).until(ExpectedConditions.presenceOfElementLocated(By.id("search")));


        assertEquals(search.getText(),"SEARCH");

    }

    @AfterMethod
    public void getResult(ITestResult result){
        driver.close();
        if(result.getStatus() == ITestResult.FAILURE){
            test.log(LogStatus.FAIL, "Test has failed " + result.getName());
            test.log(LogStatus.FAIL, "Test has failed " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(LogStatus.PASS, "Test has passed " + result.getName());
        }
        report.endTest(test);
    }

    @AfterTest
    public void endReport(){
        report.flush();
        report.close();
    }

}
