package com.qacart.todo.testcases;

import com.qacart.todo.api.RegisterApi;
import com.qacart.todo.utils.ConfigUtils;
import java.util.List;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.http.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class Dummy2 {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get(ConfigUtils.getInstance().getBaseUrl());
        driver.manage().window().maximize();

        RegisterApi registerApi = new RegisterApi();
        registerApi.register();

        List<Cookie> restAssuredCookie = registerApi.getRestAssuredCookies();

        for (Cookie cookie : restAssuredCookie) {
            org.openqa.selenium.Cookie seleniumCookie = new org.openqa.selenium.Cookie(cookie.getName(),cookie.getValue());
            driver.manage().addCookie(seleniumCookie);
        }


        driver.get(ConfigUtils.getInstance().getBaseUrl());

        Thread.sleep(6000);
        driver.quit();

    }
}
