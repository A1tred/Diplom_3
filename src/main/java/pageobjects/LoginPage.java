package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    private static final By EMAIL_INPUT = By.xpath("//input[@name='name' and @type='text']");
    private static final By PASSWORD_INPUT = By.xpath("//input[@name='Пароль' and @type='password']");
    private static final By LOGIN_BUTTON = By.xpath("//button[contains(@class, 'button_button__33qZ0') and text()='Войти']");
    private static final By REGISTER_LINK = By.xpath("//a[contains(@class, 'Auth_link__1fOlj') and @href='/register']");
    private static final By FORGOT_PASSWORD_BUTTON = By.xpath("//a[contains(@class, 'Auth_link__1fOlj') and @href='/forgot-password']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Метод получения URL
    @Override
    public String getPageUrl() {
        return super.getPageUrl() + "login";
    }

    @Step("Открытие страницы логина")
    public void open() {
        driver.get(getPageUrl());
    }

    @Step("Ввод email: {email}")
    public void enterEmail(String email) {
        enterText(EMAIL_INPUT, email);
    }

    @Step("Ввод пароля: {password}")
    public void enterPassword(String password) {
        enterText(PASSWORD_INPUT, password);
    }

    @Step("Клик по кнопке 'Войти'")
    public void clickLoginButton() {
        WebElement loginButton = driver.findElement(LOGIN_BUTTON);
        clickWithWait(loginButton);
    }

    @Step("Клик по ссылке 'Зарегистрироваться'")
    public void clickRegisterLink() {
        WebElement registerLink = driver.findElement(REGISTER_LINK);
        clickWithWait(registerLink);
    }

    @Step("Клик по кнопке 'Восстановить пароль'")
    public void clickForgotPasswordButton() {
        WebElement forgotPasswordButton = driver.findElement(FORGOT_PASSWORD_BUTTON);
        clickWithWait(forgotPasswordButton);
    }
}
