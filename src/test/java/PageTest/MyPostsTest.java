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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyPostsTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;
    LoginPage loginPage;
    PostsPage postsPage;

    private final static String loginURL = "https://test-stand.gb.ru/login";
    private final static String myPostsURL = "https://test-stand.gb.ru/?sort=createdAt&order=ASC";
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
    driver = new ChromeDriver(chromeOptions);
        WebDriverManager.chromedriver();
        driver.get(loginURL);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        loginPage = new LoginPage(driver);
        postsPage = new PostsPage(driver);
        loginPage.login(usernameCorrect, passwordCorrect);
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(100));
        webDriverWait.until(ExpectedConditions.visibilityOf(postsPage.getBlog()));
        driver.navigate().to(myPostsURL);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
    }

    @Test
    void displayedMyPostsPage() {
        postsPage.assertMyPostsPage();
    }

    @Test
    void displayedNextPageButton() {
        postsPage.assertDisplayedNextPageButton();
    }

    @Test
    void displayedImage() {
        postsPage.assertDisplayedImage();
    }
    @Test
    void displayedHeader() {
        postsPage.assertDisplayedHeader();
    }
    @Test
    void displayedDescription() {
        postsPage.assertDisplayedDescription();
    }

    @Test
    void goToAnotherPage() {
        postsPage.clicktNextPage();
        postsPage.assertSuccessSecondPage();
        postsPage.clicktPreviousPage();
        postsPage.assertSuccessFirstPage();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}

