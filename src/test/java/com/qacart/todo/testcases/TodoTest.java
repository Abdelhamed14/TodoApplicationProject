package com.qacart.todo.testcases;

import com.qacart.todo.api.RegisterApi;
import com.qacart.todo.api.TasksApi;
import com.qacart.todo.base.BaseTest;
import com.qacart.todo.factory.DriverFactory;
import com.qacart.todo.pages.LoginPage;
import com.qacart.todo.pages.NewTodoPage;
import com.qacart.todo.pages.TodoPage;
import com.qacart.todo.utils.ConfigUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
@Feature("Todo Feature")
public class TodoTest extends BaseTest {

    @Story("Add Todo")
    @Description("It will login by filling email and password and navigate to the todo page")
    @Test(description = "Should be able to add new todo correctly")
    public void shouldBeAbleToAddNewTodo() {
        RegisterApi registerApi = new RegisterApi();
        registerApi.register();


       NewTodoPage newTodoPage = new NewTodoPage(getDriver());
       newTodoPage.load();
       injectCookiesToBrowser(registerApi.getRestAssuredCookies());
       String actualResult = newTodoPage
               .load()
               .setNewTodoItem()
               .getTodoItemText();

        String expectedResult = "learn selenium";
        Assert.assertEquals(actualResult, expectedResult);


    }
    @Story("Delete Todo")
    @Test(description = "Should be able to delete a todo correctly")
    public void shouldBeAbleToDeleteTodo() {


        RegisterApi registerApi = new RegisterApi();
        registerApi.register();

        TasksApi tasksApi = new TasksApi();
        tasksApi.addTask(registerApi.getAccessToken());
        TodoPage todoPage = new TodoPage(getDriver());
        todoPage.load();
        injectCookiesToBrowser(registerApi.getRestAssuredCookies());
        boolean isNOTodoMessageDisplayed = todoPage
                .load()
                .clickDeleteButton()
                .isNoTodoItemDisplayed();

        Assert.assertTrue(isNOTodoMessageDisplayed);





    }
}
