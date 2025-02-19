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
import pageobjects.LoginPage;
import pageobjects.MainPage;
import pageobjects.RegisterPage;
import utils.ApiUtils;

import static org.junit.Assert.assertEquals;

@Epic("Регистрация пользователя")
public class RegistrationTests extends BaseTest {

    private RegisterPage registerPage;
    private LoginPage loginPage;
    private MainPage mainPage;
    private User testUser;

    @Before
    @Step("Подготовка тестовой среды")
    @Description("Устанавливает окружение и открывает страницу регистрации")
    public void setUp() {
        super.setUp();
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        registerPage.open();

        // Создание случайного пользователя
        testUser = UserGenerator.getRandomUser();
    }

    @Test
    @Description("Тест проверяет, что пользователь может успешно зарегистрироваться и войти")
    @DisplayName("Успешная регистрация пользователя")
    public void testSuccessfulRegistration() {
        registerUser(testUser);
        loginUser(testUser);

        mainPage.waitExpectedPage();
        assertEquals(mainPage.getPageUrl(), driver.getCurrentUrl());

        // Получение токена пользователя для последующего удаления
        var response = ApiUtils.loginUser(testUser.getEmail(), testUser.getPassword());
        accessToken = response.jsonPath().getString("accessToken");
    }

    @Test
    @Description("Тест проверяет сообщения об ошибках при вводе некорректного пароля")
    @DisplayName("Проверка ошибок для некорректного пароля")
    public void testErrorsForInvalidPasswords() {
        registerPage.enterName(testUser.getName());
        registerPage.enterEmail(testUser.getEmail());
        registerPage.enterPassword("12345"); // Некорректный пароль менее 6 символов
        registerPage.clickRegisterButton();

        String actualErrorMessage = registerPage.getPasswordErrorMessage();
        assertEquals("Некорректный пароль", actualErrorMessage);
    }

    @After
    @Step("Очистка данных после теста")
    @Description("Удаляет пользователя, созданного во время теста")
    public void cleanUpAfterUserRegistration() {
        if (accessToken != null) {
            ApiUtils.deleteUser(accessToken);
        }
    }

    @Step("Регистрация пользователя")
    private void registerUser(User user) {
        registerPage.enterName(user.getName());
        registerPage.enterEmail(user.getEmail());
        registerPage.enterPassword(user.getPassword());
        registerPage.clickRegisterButton();
        loginPage.waitExpectedPage();
        assertEquals(loginPage.getPageUrl(), driver.getCurrentUrl());
    }

    @Step("Вход в систему")
    private void loginUser(User user) {
        loginPage.enterEmail(user.getEmail());
        loginPage.enterPassword(user.getPassword());
        loginPage.clickLoginButton();
    }
}
