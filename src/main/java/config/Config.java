package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new IllegalStateException("Файл application.properties не найден");
            }
            properties.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Ошибка при загрузке файла application.properties", ex);
        }
    }

    public static String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    public static String getYandexBrowserPath() {
        return properties.getProperty("yandex.browser.path");
    }

    public static String getYandexDriverPath() {
        return properties.getProperty("yandex.driver.path");
    }
}
