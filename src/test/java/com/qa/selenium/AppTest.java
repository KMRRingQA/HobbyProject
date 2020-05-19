package com.qa.selenium;

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
    public void startReport(){
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
        test = report.startTest("buying cheapest first class UA flight to paris");
        driver.manage().window().maximize();
        test.log(LogStatus.INFO, "Started chrome browser and made it fullscreen");
        driver.get("http://www.newtours.demoaut.com/");
        test.log(LogStatus.INFO, "Started chrome browser and made it fullscreen");
        WebElement registerButton = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td[2]/a")));
        registerButton.click();

        WebElement userName = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
        userName.sendKeys("CirbysTestAccount" + (int) (Math.random() * 10000 + 10000));
        WebElement password = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        password.sendKeys("asdfasdf");
        WebElement password2 = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.name("confirmPassword")));
        password2.sendKeys("asdfasdf");
        WebElement registerButton2 = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.name("register")));
        registerButton2.click();
        test.log(LogStatus.INFO, "registered account");

        sleep(500);

        WebElement flightsButton = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/table/tbody/tr/td[1]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/a")));
        flightsButton.click();

        WebElement oneWayButton = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[2]/td[2]/b/font/input[2]")));
        oneWayButton.click();

        WebElement passengerSelect = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.name("passCount")));
        Select passengerSelector = new Select(driver.findElement(By.name("passCount")));
        passengerSelector.selectByValue(String.valueOf(2));

        WebElement departureSelect = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.name("fromPort")));
        Select departureSelector = new Select(driver.findElement(By.name("fromPort")));
        departureSelector.selectByValue("London");

        WebElement arrivalSelect = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.name("toPort")));
        Select arrivalSelector = new Select(driver.findElement(By.name("toPort")));
        arrivalSelector.selectByValue("Paris");

        WebElement firstClassButton = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[9]/td[2]/font/font/input[2]")));
        firstClassButton.click();

        WebElement airLineSelect = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.name("airline")));
        Select airLineSelector = new Select(driver.findElement(By.name("airline")));
        airLineSelector.selectByVisibleText("Unified Airlines");

        test.log(LogStatus.INFO, "selected flight details");

        sleep(500);

        WebElement continueButton = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.name("findFlights")));
        continueButton.click();

        WebElement continueButton2 = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.name("reserveFlights")));
        continueButton2.click();

        test.log(LogStatus.INFO, "continued to checkout");

        sleep(500);

        WebElement firstName1 = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.name("passFirst0")));
        firstName1.sendKeys("asdf");

        WebElement lastName1 = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.name("passLast0")));
        lastName1.sendKeys("asdf");

        WebElement firstName2 = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.name("passFirst1")));
        firstName2.sendKeys("asdf");

        WebElement lastName2 = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.name("passLast1")));
        lastName2.sendKeys("asdf");

        WebElement numberInput = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.name("creditnumber")));
        numberInput.sendKeys("1235324234");

        WebElement purchaseButton = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.name("buyFlights")));
        purchaseButton.click();

        test.log(LogStatus.INFO, "entered passenger details");

        sleep(500);

        WebElement success = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/table/tbody/tr[3]/td/p/font/b/font[2]")));

        File bookedOrder = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(bookedOrder, new File(System.getProperty("user.dir") + "/test-output/bookedOrder.jpg"));
        test.log(LogStatus.INFO, "took screenshot of booked order");
        test.log(LogStatus.PASS, "booked order successful", "<img src=bookedOrder.jpg>");

        assertEquals(success.getText(),"Your itinerary has been booked!");

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
