package Part4;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.xpath;

public class PostsPage extends BaseView {

    public PostsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[contains(text(),'Next Page')]")
    private WebElement nextPage;

    @FindBy(xpath = "//a[contains(text(),'Previous Page')]")
    private WebElement previousPage;

    public final static String blogURL = "https://test-stand.gb.ru/";
    public final static String myPostsURL = "https://test-stand.gb.ru/?sort=createdAt&order=ASC";
    public final static String firstMyPostsURL = "https://test-stand.gb.ru/?page=1";
    public final static String secondMyPostsURL = "https://test-stand.gb.ru/?page=2";

    @FindBy(xpath = "//h1[contains(text(),'Blog')]")
    private WebElement blog;

    public WebElement getBlog() {
        return blog;
    }

    private final static String imageLocator = "//a[contains(@class,'post')]/img";
    //@FindBy(xpath = "//a[contains(@class,'post')]/img")
    //private WebElement image;

    private final static String headerLocator = "//h2[contains(@class,'svelte')]";
   // @FindBy(xpath = "//h2[contains(@class,'svelte')]")
    //private WebElement header;

    private final static String descriptionLocator = "//div[contains(@class,'description')]";
   // @FindBy(xpath = "//div[contains(@class,'description')]")
    //private WebElement description;


    public void assertPostsPage() {
        webDriverWait.until(ExpectedConditions.visibilityOf(blog));
        Assertions.assertEquals(blogURL, driver.getCurrentUrl());
    }

    public void assertMyPostsPage() {
        webDriverWait.until(ExpectedConditions.visibilityOf(blog));
        Assertions.assertEquals(myPostsURL, driver.getCurrentUrl());
    }

    public void assertDisplayedNextPageButton() {
        webDriverWait.until(ExpectedConditions.visibilityOf(blog));
        Assertions.assertTrue(driver.findElement(linkText("Next Page")).isEnabled());
    }

    public void assertDisplayedImage() {
        webDriverWait.until(ExpectedConditions.visibilityOf(blog));
        Assertions.assertTrue(driver.findElement(xpath(imageLocator)).isDisplayed());
    }

    public void assertDisplayedHeader() {
        webDriverWait.until(ExpectedConditions.visibilityOf(blog));
        Assertions.assertTrue(driver.findElement(xpath(headerLocator)).isDisplayed());
    }

    public void assertDisplayedDescription() {
        webDriverWait.until(ExpectedConditions.visibilityOf(blog));
        //Assertions.assertTrue(driver.findElement(xpath(descriptionLocator)).isDisplayed());

        Assertions.assertTrue(driver.findElement(xpath(descriptionLocator)).isDisplayed());
    }

    public void clicktNextPage() {
        webDriverWait.until(ExpectedConditions.visibilityOf(blog));
        nextPage.click();
    }

    public void clicktPreviousPage() {
        webDriverWait.until(ExpectedConditions.visibilityOf(blog));
        previousPage.click();
    }

    public void assertSuccessFirstPage() {
        //webDriverWait.until(ExpectedConditions.visibilityOf(blog));
       // webDriverWait.until(ExpectedConditions.elementToBeClickable(previousPage));
        webDriverWait.until(ExpectedConditions.attributeContains(previousPage,"class","disabled"));
        Assertions.assertEquals(firstMyPostsURL, driver.getCurrentUrl());
    }

    public void assertSuccessSecondPage() {
        //webDriverWait.until(ExpectedConditions.attributeContains(previousPage,"class","disabled"));
        webDriverWait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(previousPage,"class","disabled")));
        Assertions.assertEquals(secondMyPostsURL, driver.getCurrentUrl());
    }

}
