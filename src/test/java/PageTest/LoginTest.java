package PageTest;

import Part4.LoginPage;
import Part4.PostsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;

    private final static String loginURL = "https://test-stand.gb.ru/login";
    private final String usernameCorrect = "Petr_";
    private final String passwordCorrect = "e56ab09a52";

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(chromeOptions);
        WebDriverManager.chromedriver();
        driver.get(loginURL);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        loginPage = new LoginPage(driver);
    }

    @Test
    void correctLogin() {
        loginPage.login(usernameCorrect, passwordCorrect);
        new PostsPage(driver).assertPostsPage();
    }

    @Test
    void lengthValidationLogin_2() {
        loginPage.login("ss", "dmla");
        loginPage.assertLengthValidation();
    }

    public LoginTest() {
        super();
    }

    @Test
    void lengthValidationLogin_21() {
        loginPage.login("sssssssssssssssssssss", "dmla");
        loginPage.assertLengthValidation();
    }

    @Test
    void nonExistentValidationLogin() {
        loginPage.login("ssssssssssssssssss", "dmla");
        loginPage.assertNonExistentValidation();
    }

    @Test
    void symbolValidationLogin() {
        loginPage.login("%#", "dmla");
        loginPage.assertSymbolValidation();
    }

    @Test
    void emptylValidationLogin() {
        loginPage.login("", "");
        loginPage.assertEmptylValidation();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
