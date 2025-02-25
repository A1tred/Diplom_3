package pageobjects;

import config.Config;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProfilePage extends BasePage {

    private static final By CONSTRUCTOR_BUTTON = By.xpath("//p[contains(@class, 'AppHeader_header__linkText__3q_va') and contains(@class, 'ml-2') and text()='Конструктор']");
    private static final By LOGOUT_BUTTON = By.xpath("//button[contains(@class, 'Account_button__14Yp3') and text()='Выход']");
    private static final By STELLAR_BURGER_LOGO = By.xpath("//div[contains(@class, 'AppHeader_header__logo__2D0X2')]");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    // Метод получения URL
    @Override
    public String getPageUrl() {
        return super.getPageUrl() + "account/profile";
    }

    @Step("Открытие страницы профиля")
    public void open() {
        driver.get(getPageUrl());
    }

    @Step("Клик по кнопке 'Конструктор'")
    public void clickConstructorButton() {
        WebElement constructorButton = driver.findElement(CONSTRUCTOR_BUTTON);
        clickWithWait(constructorButton);
    }

    @Step("Клик по логотипу Stellar Burgers")
    public void clickLogoButton() {
        WebElement logo = driver.findElement(STELLAR_BURGER_LOGO);
        clickWithWait(logo);
    }

    @Step("Клик по кнопке 'Выход'")
    public void clickLogoutButton() {
        WebElement logoutButton = driver.findElement(LOGOUT_BUTTON);
        clickWithWait(logoutButton);
    }
}
