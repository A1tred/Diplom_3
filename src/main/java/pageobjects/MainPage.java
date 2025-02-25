package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage extends BasePage {

    private static final By LOGIN_BUTTON = By.xpath("//button[contains(@class, 'button_button__33qZ0') and text()='Войти в аккаунт']");
    private static final By PERSONAL_ACCOUNT_LINK = By.xpath("//p[@class='AppHeader_header__linkText__3q_va ml-2' and text()='Личный Кабинет']");
    public static final By BUNS_TAB = By.xpath("//div[contains(@class, 'tab_tab__1SPyG') and .//span[text()='Булки']]");
    public static final By SAUCES_TAB = By.xpath("//div[contains(@class, 'tab_tab__1SPyG') and .//span[text()='Соусы']]");
    public static final By FILLINGS_TAB = By.xpath("//div[contains(@class, 'tab_tab__1SPyG') and .//span[text()='Начинки']]");
    private static final String ACTIVE_TAB_CLASS_LOCATOR = "tab_tab_type_current__2BEPc";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    // Метод получения URL
    @Override
    public String getPageUrl() {
        return super.getPageUrl();
    }

    @Step("Открытие главной страницы")
    public void open() {
        driver.get(getPageUrl());
    }

    @Step("Клик по кнопке 'Войти в аккаунт'")
    public void clickLoginButton() {
        WebElement loginButton = driver.findElement(LOGIN_BUTTON);
        clickWithWait(loginButton);
    }

    @Step("Клик по вкладке")
    public void clickTab(By tabLocator) {
        clickWithWait(driver.findElement(tabLocator));
    }

    @Step("Клик по ссылке 'Личный Кабинет'")
    public void clickPersonalAccountLink() {
        WebElement personalAccountLink = driver.findElement(PERSONAL_ACCOUNT_LINK);
        clickWithWait(personalAccountLink);
    }

    public boolean isTabActive(By tabLocator) {
        return driver.findElement(tabLocator).getAttribute("class").contains(ACTIVE_TAB_CLASS_LOCATOR);
    }
}
