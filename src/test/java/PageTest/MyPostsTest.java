package PageTest;

import Part4.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyPostsTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;
    LoginPage loginPage;


    private final static String loginURL = "https://test-stand.gb.ru/login";
    private final static String myPostsURL = "https://test-stand.gb.ru/?sort=createdAt&order=ASC";
    private final String usernameCorrect = "Petr_";
    private final String passwordCorrect = "e56ab09a52";

    @BeforeAll
    void registerDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        driver = new EventFiringDecorator().decorate(new ChromeDriver(chromeOptions));
        WebDriverManager.chromedriver();
    }

    @BeforeEach
    void initDriver() {

        driver.get(loginURL);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        loginPage = new LoginPage(driver);

        driver.get(myPostsURL);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        //postsPage = new PostsPage(driver);

    }

}
