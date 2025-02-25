package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import config.Config;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Метод получения URL
    public String getPageUrl() {
        return Config.getBaseUrl();
    }

    // Метод ввода текста в поле
    protected void enterText(By locator, String text) {
        WebElement element = waitForElementToBeVisible(driver.findElement(locator));
        element.clear();
        element.sendKeys(text);
    }

    // Метот ожидания перехода на страницу
    public void waitExpectedPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(
                ExpectedConditions.urlToBe(getPageUrl())
        );
    }

    // Метод ожидания видимости элемента
    protected WebElement waitForElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Метод ожидания кликабельности элемента
    protected WebElement waitForElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // Метод клика по элементу с ожиданием
    protected void clickWithWait(WebElement element) {
        waitForElementToBeClickable(element).click();
    }
}
