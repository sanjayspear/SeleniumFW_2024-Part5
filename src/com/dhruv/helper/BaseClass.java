package com.dhruv.helper;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseClass{
    protected WebDriver driver;
    protected static Logger logger = LogManager.getLogger(BaseClass.class);
    protected String browser;

    @BeforeSuite
    public void configureLog4j() {
        System.setProperty("log4j.configurationFile", "src/com/dhruv/helper/log4j2.xml");
    }

    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser) {
        logger.info("Setting up WebDriver for browser: " + browser);
        this.browser = browser;
        driver = initializeDriver(browser);
    }

    @AfterMethod
    public void tearDown() {
        logger.info("Tearing down WebDriver instance");
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver initializeDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
            	ChromeOptions options = new ChromeOptions();
        		options.addArguments("--remote-allow-origins=*");
        		String desiredVersion = "124.0.6367.207";
        		WebDriverManager.chromedriver().driverVersion(desiredVersion).setup();
        		driver = new ChromeDriver(options);
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }
}
