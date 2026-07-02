package com.qacart.todo.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

public class DriverFactory {
    public WebDriver initializeDriver()
    {
        WebDriver driver;
        String browser = System.getProperty("browser" , "chrome");
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=new");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080");

                driver = new ChromeDriver(options);
                break;
                case "firefox": WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
                default: throw new RuntimeException("Browser not recognized");

        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        return driver;
    }
}
