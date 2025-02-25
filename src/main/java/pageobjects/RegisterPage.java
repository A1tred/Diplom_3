package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage extends BasePage {
    private static final By NAME_INPUT = By.xpath("//input[@name='name' and @type='text']");
    private static final By EMAIL_INPUT = By.xpath("(//input[@type='text'])[2]");
    private static final By PASSWORD_INPUT = By.xpath("//input[@name='Пароль' and @type='password']");
    private static final By REGISTER_BUTTON = By.xpath("//button[contains(@class, 'button_button__33qZ0') and text()='Зарегистрироваться']");
    private static final By PASSWORD_ERROR_TEXT = By.cssSelector("p.input__error.text_type_main-default");
    private static final By LOGIN_LINK = By.xpath("//a[contains(@class, 'Auth_link__1fOlj') and @href='/login']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    // Метод получения URL
    @Override
    public String getPageUrl() {
        return super.getPageUrl() + "register";
    }

    @Step("Открытие страницы регистрации")
    public void open() {
        driver.get(getPageUrl());
    }

    @Step("Ввод имени: {name}")
    public void enterName(String name) {
        WebElement nameInput = driver.findElement(NAME_INPUT);
        nameInput.sendKeys(name);
    }

    @Step("Ввод email: {email}")
    public void enterEmail(String email) {
        WebElement emailInput = driver.findElement(EMAIL_INPUT);
        emailInput.sendKeys(email);
    }

    @Step("Ввод пароля: {password}")
    public void enterPassword(String password) {
        WebElement passwordInput = driver.findElement(PASSWORD_INPUT);
        passwordInput.sendKeys(password);
    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public void clickRegisterButton() {
        WebElement registerButton = driver.findElement(REGISTER_BUTTON);
        clickWithWait(registerButton);
    }

    @Step("Получение сообщения об ошибке ввода пароля")
    public String getPasswordErrorMessage() {
        WebElement errorText = driver.findElement(PASSWORD_ERROR_TEXT);
        return waitForElementToBeVisible(errorText).getText();
    }

    @Step("Клик по ссылке 'Войти'")
    public void clickLoginLink() {
        WebElement loginLink = driver.findElement(LOGIN_LINK);
        clickWithWait(loginLink);
    }
}
