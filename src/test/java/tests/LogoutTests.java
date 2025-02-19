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
import pageobjects.ProfilePage;
import utils.ApiUtils;

import static org.junit.Assert.assertEquals;

@Epic("Выход из аккаунта")
public class LogoutTests extends BaseTest {

    private MainPage mainPage;
    private LoginPage loginPage;
    private ProfilePage profilePage;
    private User testUser;

    @Before
    @Step("Подготовка тестовой среды")
    @Description("Устанавливает окружение, открывает страницы и создаёт нового пользователя")
    public void setUp() {
        super.setUp();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);

        // Создание случайного пользователя и получение токена для удаления
        testUser = UserGenerator.getRandomUser();
        var response = ApiUtils.createUser(testUser.getEmail(), testUser.getPassword(), testUser.getName());
        accessToken = response.jsonPath().getString("accessToken");
    }

    @Test
    @Description("Тест проверяет, что пользователь может выйти из аккаунта")
    @DisplayName("Выход из аккаунта")
    public void testLogout() {
        loginAndNavigateToProfile();

        // Выход из аккаунта
        profilePage.clickLogoutButton();

        // Проверка на то, что мы находимся на странице логина
        loginPage.waitExpectedPage();
        assertEquals(loginPage.getPageUrl(), driver.getCurrentUrl());
    }

    @After
    @Step("Очистка данных после теста")
    @Description("Удаляет пользователя, созданного во время теста")
    public void tearDown() {
        super.tearDown();
    }

    @Step("Авторизация и переход в личный кабинет")
    private void loginAndNavigateToProfile() {
        loginPage.open();
        loginPage.enterEmail(testUser.getEmail());
        loginPage.enterPassword(testUser.getPassword());
        loginPage.clickLoginButton();
        mainPage.waitExpectedPage();
        mainPage.clickPersonalAccountLink();
        profilePage.waitExpectedPage();
    }
}
