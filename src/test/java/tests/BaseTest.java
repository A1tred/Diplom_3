package tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import utils.WebDriverFactory;

import static utils.ApiUtils.*;

public class BaseTest {
    protected WebDriver driver;
    protected String accessToken;

    @Before
    public void setUp() {
        driver = WebDriverFactory.createDriver();
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            deleteUser(accessToken);
        }
        if (driver != null) {
            driver.quit();
        }
    }

    protected void registerUser(String email, String password, String name) {
        var response = createUser(email, password, name);
        accessToken = response.jsonPath().getString("accessToken");
    }

    protected void authenticateUser(String email, String password) {
        var response = loginUser(email, password);
        accessToken = response.jsonPath().getString("accessToken");
    }
}
