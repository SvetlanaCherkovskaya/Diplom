package PageTest;

import Part4.LoginPage;
import Part4.PostsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;
    LoginPage loginPage;


    private final static String loginURL = "https://test-stand.gb.ru/login";
    private final String usernameCorrect = "Petr_";
    private final String passwordCorrect = "e56ab09a52";
    //private final String home = "Home";


//    @BeforeAll
//    void registerDriver() {
//        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
//        WebDriverManager.chromedriver().setup();
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--start-maximized");
//        driver = new EventFiringDecorator().decorate(new ChromeDriver(chromeOptions));
//        WebDriverManager.chromedriver();
//    }



        @BeforeEach
        void initDriver() {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--start-maximized");
            driver = new EventFiringDecorator().decorate(new ChromeDriver(chromeOptions));
            WebDriverManager.chromedriver();

        driver.get(loginURL);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        loginPage = new LoginPage(driver);
    }

    @Test
    void correctLogin() {
        loginPage.login(usernameCorrect, passwordCorrect);
        //webDriverWait.until(ExpectedConditions.visibilityOf(blog));
        //driver.get(PostsPage.blogURL);
        new PostsPage(driver).assertSuccessPostsPage();
    }

    @Test
    void lengthValidationLogin_2() {
        loginPage.login("ss", "dmla");
        loginPage.assertLengthValidation();
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
        driver.quit();
    }
}
