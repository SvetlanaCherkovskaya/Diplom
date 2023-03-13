package Part4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BaseView {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "Home")
    public static WebElement home;
}
