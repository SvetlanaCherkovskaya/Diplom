package Part4;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PostsPage extends BaseView {

    public PostsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Next Page")
    private WebElement nextPage;

    @FindBy(linkText = "Previous Page")
    private WebElement previousPage;

    public final static String blogURL = "https://test-stand.gb.ru/";

    @FindBy(xpath = "//h1[contains(text(),'Blog')]")
    private WebElement blog;


    //@FindBy(xpath = "//a[@href='/posts/4011']/img")
    @FindBy(xpath = "//div[@class='content']/div/a[1]")
    private WebElement firstImg;

    //@FindBy(xpath = "//a[@href='/posts/4011']/h2")
    @FindBy(xpath = "//div[@class='content']/div/a[1]/h2")
    private WebElement firstHeader;

    //@FindBy(xpath = "//a[@href='/posts/4011']/div")
    @FindBy(xpath = "//div[@class='content']/div/a[1]/div")
    private WebElement firstDescript;


    public void assertSuccessPostsPage() {
        //System.out.println("Blog: " + blog.getText());
        webDriverWait.until(ExpectedConditions.visibilityOf(blog));
        Assertions.assertEquals(blogURL, driver.getCurrentUrl());
    }
}
