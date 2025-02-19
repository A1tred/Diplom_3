package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import config.Config;

public class WebDriverFactory {

    public static WebDriver createDriver() {
        String browser = System.getProperty("browser", "chrome");
        WebDriver driver;

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(getCommonChromeOptions());
                break;
            case "yandex":
                System.setProperty("webdriver.chrome.driver", Config.getYandexDriverPath());
                ChromeOptions yandexOptions = getCommonChromeOptions();
                yandexOptions.setBinary(Config.getYandexBrowserPath());
                driver = new ChromeDriver(yandexOptions);
                break;
            default:
                throw new IllegalArgumentException("Неподдерживаемый браузер: " + browser);
        }

        driver.manage().window().maximize();
        return driver;
    }

    private static ChromeOptions getCommonChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        return options;
    }
}
