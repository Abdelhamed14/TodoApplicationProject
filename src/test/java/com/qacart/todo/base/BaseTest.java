package com.qacart.todo.base;

import com.qacart.todo.factory.DriverFactory;
import com.qacart.todo.utils.CookieUtils;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.http.Cookie;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class BaseTest {
    protected ThreadLocal<WebDriver> driver =  new ThreadLocal<>();
    public void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }
    public WebDriver getDriver() {
        return this.driver.get();
    }
    @BeforeMethod
    public void beforeTest() {
        WebDriver driver = new DriverFactory().initializeDriver();
       setDriver(driver);
    }

    @Step("")
    public void injectCookiesToBrowser(List<Cookie> restAssuredCookie) {

        List<org.openqa.selenium.Cookie> seleniumCookies = CookieUtils.convertRestAssuredCookieToSeleniumCookies(restAssuredCookie);
        for (org.openqa.selenium.Cookie seleniumCookie : seleniumCookies) {
            getDriver().manage().addCookie(seleniumCookie);
        }
    }
    @AfterMethod
    public void afterTest()  {
        getDriver().quit();

    }


}
