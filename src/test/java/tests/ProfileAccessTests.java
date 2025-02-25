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

@Epic("Переход в личный кабинет")
public class ProfileAccessTests extends BaseTest {

    private MainPage mainPage;
    private LoginPage loginPage;
    private ProfilePage profilePage;
    private User testUser;

    @Before
    @Step("Подготовка тестовой среды")
    @Description("Устанавливает окружение, открывает главную страницу и создаёт нового пользователя")
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
    @Description("Тест проверяет успешный переход в личный кабинет")
    @DisplayName("Переход в личный кабинет")
    public void testAccessProfilePage() {
        loginPage.open();
        loginPage.enterEmail(testUser.getEmail());
        loginPage.enterPassword(testUser.getPassword());
        loginPage.clickLoginButton();

        // Ожидание загрузки главной страницы после входа
        mainPage.waitExpectedPage();

        // Переход в личный кабинет
        mainPage.clickPersonalAccountLink();

        // Проверка на то, что мы находимся на странице личного кабинета
        profilePage.waitExpectedPage();
        assertEquals(profilePage.getPageUrl(), driver.getCurrentUrl());
    }

    @After
    @Step("Очистка данных после теста")
    @Description("Удаляет пользователя, созданного во время теста")
    public void tearDown() {
        super.tearDown();
    }
}
