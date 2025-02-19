package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import models.User;
import models.UserGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.LoginPage;
import pageobjects.MainPage;
import pageobjects.RegisterPage;
import utils.ApiUtils;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

@Epic("Авторизация пользователя")
public class LoginTests extends BaseTest {

    private User testUser;
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;

    @Before
    @Step("Подготовка тестовой среды")
    @Description("Устанавливает окружение, открывает главную страницу и создаёт нового пользователя")
    public void setUp() {
        super.setUp();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        mainPage.open();

        // Создание случайного пользователя
        testUser = UserGenerator.getRandomUser();
        var response = ApiUtils.createUser(testUser.getEmail(), testUser.getPassword(), testUser.getName());
        accessToken = response.jsonPath().getString("accessToken");
    }

    @Test
    @Description("Тест проверяет вход через кнопку 'Войти в аккаунт' на главной странице и успешность входа")
    @DisplayName("Проверка входа через кнопку 'Войти в аккаунт'")
    public void testLoginFromEnterToAccountButton() {
        mainPage.clickLoginButton();
        enterCredentialsAndLogin();
        assertEquals(mainPage.getPageUrl(), driver.getCurrentUrl());
    }

    @Test
    @Description("Тест проверяет вход через кнопку 'Личный Кабинет' на главной странице и успешность входа")
    @DisplayName("Проверка входа через кнопку 'Личный Кабинет'")
    public void testLoginWithPersonalCabinetButton() {
        mainPage.clickPersonalAccountLink();
        enterCredentialsAndLogin();
        assertEquals(mainPage.getPageUrl(), driver.getCurrentUrl());
    }

    @Test
    @Description("Тест проверяет успешный вход через кнопку в форме регистрации")
    @DisplayName("Проверка входа через регистрацию")
    public void testLoginThroughRegistrationButton() {
        mainPage.clickLoginButton();
        loginPage.clickRegisterLink();
        registerPage.clickLoginLink();
        enterCredentialsAndLogin();
        assertEquals(mainPage.getPageUrl(), driver.getCurrentUrl());
    }

    @Test
    @Description("Тест проверяет успешный вход через кнопку в форме восстановления пароля")
    @DisplayName("Проверка входа через восстановление пароля")
    public void testLoginThroughRecoveryPasswordTemplate() {
        mainPage.clickLoginButton();
        loginPage.clickForgotPasswordButton();
        registerPage.clickLoginLink();
        enterCredentialsAndLogin();
        assertEquals(mainPage.getPageUrl(), driver.getCurrentUrl());
    }

    @After
    @Step("Очистка данных после теста")
    @Description("Удаляет пользователя, созданного перед тестом")
    public void tearDown() {
        super.tearDown();
        if (accessToken != null) {
            ApiUtils.deleteUser(accessToken);
        }
    }

    @Step("Ввод email и пароля, затем клик по кнопке 'Войти'")
    private void enterCredentialsAndLogin() {
        loginPage.enterEmail(testUser.getEmail());
        loginPage.enterPassword(testUser.getPassword());
        loginPage.clickLoginButton();

        // Ожидание перехода после нажатия кнопки
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(
                ExpectedConditions.urlToBe(mainPage.getPageUrl())
        );
    }
}
