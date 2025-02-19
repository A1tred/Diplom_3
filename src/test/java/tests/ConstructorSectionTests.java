package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import pageobjects.MainPage;

import static org.junit.Assert.assertTrue;

@Epic("Переходы в разделе Конструктор")
@RunWith(Parameterized.class)
public class ConstructorSectionTests extends BaseTest {

    private MainPage mainPage;
    private final By targetTabLocator;
    private final String tabName;

    public ConstructorSectionTests(By targetTabLocator, String tabName) {
        this.targetTabLocator = targetTabLocator;
        this.tabName = tabName;
    }

    @Parameterized.Parameters(name = "Проверка вкладки: {1}")
    public static Object[][] data() {
        return new Object[][]{
                {MainPage.BUNS_TAB, "Булки"},
                {MainPage.SAUCES_TAB, "Соусы"},
                {MainPage.FILLINGS_TAB, "Начинки"}
        };
    }

    @Before
    @Step("Подготовка тестовой среды")
    @Description("Устанавливает окружение и открывает главную страницу")
    public void setUp() {
        super.setUp();
        mainPage = new MainPage(driver);
        mainPage.open();
    }

    @Test
    @Description("Тест проверяет, что вкладка активируется корректно")
    @DisplayName("Проверка активации вкладки")
    public void testTabActivation() {
        // Если целевая вкладка уже активна, переключаемся на другую, чтобы изменить состояние
        if (mainPage.isTabActive(targetTabLocator)) {
            mainPage.clickTab(targetTabLocator == MainPage.BUNS_TAB ? MainPage.SAUCES_TAB : MainPage.BUNS_TAB);
        }

        // Переключаемся на целевую вкладку и проверяем её активность
        mainPage.clickTab(targetTabLocator);
        assertTrue(mainPage.isTabActive(targetTabLocator));
    }
}
