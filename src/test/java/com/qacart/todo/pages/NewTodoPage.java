package com.qacart.todo.pages;

import com.qacart.todo.base.BasePage;
import com.qacart.todo.config.EndPoint;
import com.qacart.todo.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewTodoPage extends BasePage {

    public NewTodoPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css = "[data-testid=\"new-todo\"]")
    private WebElement newTodoItem;
    @FindBy(css = "[data-testid=\"submit-newTask\"]")
    private WebElement submitNewTask;
    @Step("")
    public TodoPage setNewTodoItem()
        {
            newTodoItem.sendKeys("learn selenium");
            submitNewTask.click();
            return new TodoPage(driver);
        }
    public NewTodoPage load()
    {
        driver.get(ConfigUtils.getInstance().getBaseUrl() + EndPoint.NEW_TODO_ENDPOINT);
        return this;
    }

}
