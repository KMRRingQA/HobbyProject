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
import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    WebDriver driver;
    ExtentReports report;
    ExtentTest test;

    @BeforeTest
    public void startReport() {

        App app = new App();
        String[] args = null;
        App.main(args);
        assertTrue( app instanceof App );

        report = new ExtentReports(
                System.getProperty("user.dir") + "/test-output/Report.html",
                true
        );
        report
                .addSystemInfo("Host Name", "QA")
                .addSystemInfo("Tester", "Tadas");
        report.loadConfig(new File(System.getProperty("user.dir") + "\\extent-report.xml"));
    }

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
    }

    @Test
    public void testQATitle() throws InterruptedException, IOException {
        test = report.startTest("accessing localhost and checking title text");
        driver.manage().window().maximize();
        test.log(LogStatus.INFO, "Started chrome browser and made it fullscreen");
        driver.get("http://localhost:8181");
        test.log(LogStatus.INFO, "navigated to localhost");
        WebElement title = (new WebDriverWait(driver, 3)).until(ExpectedConditions.presenceOfElementLocated(By.id("Title")));
        test.log(LogStatus.INFO, "retreived title element");
        assertEquals("INFO-REVIT",title.getText());
        sleep(500);
    }

    @Test
    public void registerAndCreateDoorAndViewWindow() throws InterruptedException, IOException {
        test = report.startTest("Will register a manufacturer, create a door with it & check if it is visible.");
        driver.manage().window().maximize();
        test.log(LogStatus.INFO, "Started chrome browser and made it fullscreen");
        driver.get("http://localhost:8181");
        test.log(LogStatus.INFO, "navigated to localhost");
        sleep(500);
        WebElement manufacturerTools = (new WebDriverWait(driver, 3)).until(ExpectedConditions.presenceOfElementLocated(By.id("Manufacturer")));
        manufacturerTools.click();
        test.log(LogStatus.INFO, "navigate to manufacturer tools");
        sleep(500);
        WebElement register = (new WebDriverWait(driver, 3)).until(ExpectedConditions.presenceOfElementLocated(By.id("RegisterButton")));
        register.click();
        test.log(LogStatus.INFO, "pressed register");
        sleep(500);
        WebElement username = (new WebDriverWait(driver, 3)).until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
        username.sendKeys("SeleniumTester");
        test.log(LogStatus.INFO, "entered manufacturer name");
        sleep(500);
        WebElement email = (new WebDriverWait(driver, 3)).until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
        email.sendKeys("SeleniumTester@gmail.com");
        test.log(LogStatus.INFO, "entered email");
        sleep(500);
        WebElement password = (new WebDriverWait(driver, 3)).until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
        password.sendKeys("superSafePassword");
        test.log(LogStatus.INFO, "entered password");
        sleep(500);
        WebElement submit = (new WebDriverWait(driver, 3)).until(ExpectedConditions.presenceOfElementLocated(By.id("registerButton")));
        submit.click();
        test.log(LogStatus.INFO, "pressed submit");
        WebElement createItem = (new WebDriverWait(driver, 3)).until(ExpectedConditions.presenceOfElementLocated(By.id("CreateButton")));
        createItem.click();
        test.log(LogStatus.INFO, "navigate to create item");
        sleep(500);

        WebElement itemSelect = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("Item_Category")));
        Select itemSelector = new Select(driver.findElement(By.name("Item_Category")));
        itemSelector.selectByValue("Door");


        sleep(500);
        WebElement manufacturerID = (new WebDriverWait(driver, 3)).until(ExpectedConditions.presenceOfElementLocated(By.id("manufacturerID")));
        manufacturerID.sendKeys("1");
        WebElement modelName = (new WebDriverWait(driver, 3)).until(ExpectedConditions.presenceOfElementLocated(By.id("modelName")));
        modelName.sendKeys("Selenium Door");
        WebElement doorStyle = (new WebDriverWait(driver, 3)).until(ExpectedConditions.presenceOfElementLocated(By.id("type")));
        doorStyle.sendKeys("Test");
        WebElement bwfRating = (new WebDriverWait(driver, 3)).until(ExpectedConditions.presenceOfElementLocated(By.id("bwf")));
        bwfRating.sendKeys("FWD30");
        WebElement uValue = (new WebDriverWait(driver, 3)).until(ExpectedConditions.presenceOfElementLocated(By.id("thermal")));
        uValue.sendKeys("1.4");
        WebElement dimensions = (new WebDriverWait(driver, 3)).until(ExpectedConditions.presenceOfElementLocated(By.id("dimensions")));
        dimensions.sendKeys("1981x762x35");
        WebElement price = (new WebDriverWait(driver, 3)).until(ExpectedConditions.presenceOfElementLocated(By.id("cost")));
        price.sendKeys("40.00");
        test.log(LogStatus.INFO, "entered item data");
        sleep(500);

        WebElement createDoorButton = (new WebDriverWait(driver, 3)).until(ExpectedConditions.presenceOfElementLocated(By.id("createDoorButton")));
        createDoorButton.click();
        test.log(LogStatus.INFO, "pressed submit");
        sleep(500);

        WebElement browse = (new WebDriverWait(driver, 3)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/ul/li[2]/a")));
        browse.click();
        test.log(LogStatus.INFO, "navigated to browse menu");
        sleep(1000);

        WebElement newDoor = (new WebDriverWait(driver, 3)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[2]")));
        assertEquals("Selenium Door",newDoor.getText());
        test.log(LogStatus.INFO, "establishes that newly created door is present.");
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
