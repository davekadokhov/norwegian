package com.norwegian.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class TestBase {

    protected WebDriver driver;
    protected SoftAssert softAssert;
    protected static ExtentReports report;
    protected static ExtentHtmlReporter htmlReporter;
    protected static ExtentTest extentLogger;
    private static final Logger logger = LogManager.getLogger();
    protected String testResults;


    @BeforeSuite(alwaysRun = true)
    @Parameters("test")
    public void setUpTest(@Optional String test) {
        // actual reporter
        report = new ExtentReports();
        // System.getProperty("user.dir") ---> get the path to current project
        // test-output --> folder in the current project, will be created by testng if
        // it does not already exist
        // report.html --> name of the report file
        if (test == null) {
            test = "reports";
        }
        String filePath = System.getProperty("user.dir") + "/test-output/" + test + "/" + LocalDate.now().format(DateTimeFormatter.ofPattern("MM_dd_yyyy")) + "/report.html";
        htmlReporter = new ExtentHtmlReporter(filePath);
        logger.info("Report path: "+filePath);
        report.attachReporter(htmlReporter);
        report.setSystemInfo("ENV", "qa");
        report.setSystemInfo("browser", ConfigurationReader.getProperty("browser"));
        report.setSystemInfo("OS", System.getProperty("os.name"));
        if (System.getenv("runner") != null) {
            extentLogger.info("Running: " + System.getenv("runner"));
        }
    }


    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setup(@Optional String browser) {
        driver = Driver.getDriver(browser);
        softAssert = new SoftAssert();
        driver.manage().timeouts().implicitlyWait(Long.valueOf(ConfigurationReader.getProperty("implicitwait")), TimeUnit.SECONDS);
        driver.manage().window().maximize();
        String URL = ConfigurationReader.getProperty("url");
        driver.get(URL);
        logger.info("URL: "+URL);
    }


    @AfterMethod(alwaysRun = true)
    @Parameters("browser")
    public void teardown(@Optional String browser, ITestResult result) {

        if(Driver.getDriver()!=null){
            Driver.closeDriver();
        }
        String res;
        switch (result.getStatus()){
            case ITestResult.SUCCESS:
                res="PASSED";
                break;

                case ITestResult.FAILURE:
                    res="FAILED";
                    break;

                    case ITestResult.SKIP:
                        res="SKIPPED BLOCKED";
                        break;

                        default:
                            throw new RuntimeException("Invalid status");
        }

        ITestContext tc = Reporter.getCurrentTestResult().getTestContext();
        testResults =
                "Total:"
                +Reporter.getCurrentTestResult().getTestContext().getSuite().getAllMethods().size()
                +"|Executed:"
                +(tc.getPassedTests().getAllResults().size()
                +tc.getSkippedTests().getAllResults().size()
                +"|Current:"+res);
        logger.info("TEST DONE [{}]: "+getClass().getName(),testResults);

    }

    @AfterSuite(alwaysRun = true)
    public void tearDownTest() {
        logger.info(":: FLUSHING REPORT ::");
        report.flush();
    }
}
