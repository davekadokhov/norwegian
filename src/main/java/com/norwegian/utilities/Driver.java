package com.norwegian.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Driver {
    private static WebDriver driver;

    private Driver() {
    }

    public synchronized static WebDriver getDriver(String browser) {

        // String browser ==> originally comes from xml file to test base class, from test base it comes here
        if (driver == null) {
            // first we check if the value from xml file is null or not
            // if the value from xml file NOT null we use
            // the value from xml file IS null, we get the browser from properties file

            browser = browser == null ? ConfigurationReader.getProperty("browser") : browser;

            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "chromeHeadless":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
                    break;
            }
        }
        return driver;
    }

    public static WebDriver getDriver() {

        return getDriver(null);
    }

    public  static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
