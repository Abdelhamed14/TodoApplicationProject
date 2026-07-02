package com.qacart.todo.pages;

import com.qacart.todo.base.BasePage;
import com.qacart.todo.config.EndPoint;
import com.qacart.todo.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TodoPage extends BasePage{

    public TodoPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[data-testid=\"welcome\"]")
    private WebElement welcomeMessage;

    @FindBy(css = "[data-testid=\"add\"]")
    private WebElement addToDo;

    @FindBy(css = "[data-testid=\"todo-item\"]")
    private  WebElement todoItem;

    @FindBy(css = "[data-testid=\"delete\"]")
    private WebElement deleteButton;

    @FindBy(css = "[data-testid=\"no-todos\"]")
    private WebElement noTodos;

    @Step("")
    public boolean isWelcomeMessageDisplayed() {
        return welcomeMessage.isDisplayed();
    }
    public NewTodoPage cliickPlusButton() {
        addToDo.click();
        return new NewTodoPage(driver);

    }
    @Step("")
    public String getTodoItemText() {
        return todoItem.getText();
    }
    @Step("")
    public TodoPage clickDeleteButton() {
        deleteButton.click();
        return this;
    }
    @Step("")
    public boolean isNoTodoItemDisplayed() {
        return todoItem.isDisplayed();
    }
    @Step("")
    public TodoPage load()
    {
        driver.get(ConfigUtils.getInstance().getBaseUrl()+ EndPoint.TODO_PAGE_ENDPOINT);
        return this;
    }

}
