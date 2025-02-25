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

import static org.junit.Assert.assertEquals;

@Epic("Навигация по страницам")
public class NavigationTests extends BaseTest {

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
        registerUser(testUser.getEmail(), testUser.getPassword(), testUser.getName());
    }

    @Test
    @Description("Тест проверяет переход из личного кабинета в конструктор")
    @DisplayName("Переход в конструктор")
    public void testNavigateToConstructorFromProfile() {
        loginAndNavigateToProfile();

        // Переход в конструктор
        profilePage.clickConstructorButton();
        mainPage.waitExpectedPage();

        // Проверка на то, что мы находимся на странице конструктора
        assertEquals(mainPage.getPageUrl(), driver.getCurrentUrl());
    }

    @Test
    @Description("Тест проверяет переход по клику на логотип Stellar Burgers")
    @DisplayName("Переход по клику на логотип")
    public void testNavigateToMainPageFromLogo() {
        loginAndNavigateToProfile();

        // Переход на главную страницу по клику на логотип
        profilePage.clickLogoButton();
        mainPage.waitExpectedPage();

        // Проверка на то, что мы находимся на главной странице
        assertEquals(mainPage.getPageUrl(), driver.getCurrentUrl());
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
