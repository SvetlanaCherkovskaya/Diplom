package Part4;


import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.openqa.selenium.By.xpath;

public class LoginPage extends BaseView {

    public LoginPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//input[@type='text']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordField;
    private final static String loginButtonLocator = "//button[@type='submit']";
    @FindBy(xpath = loginButtonLocator)
    private WebElement loginButton;


    private final static String errorTextLocator = "//div[contains(@class,'error')]/p[1]";

    private final static String lengthValidationText = "Может быть не менее 3 и не более 20 символов";
    private final static String nonExistentValidationText = "Проверьте логин и пароль";
    private final static String symbolValidationText = "Только латинские символов и цифр, без спецсимволов";
    private final static String emptyValidationText = "Поле не может быть пустым";

    public void login(String login, String password) {
        usernameField.sendKeys(login);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    public void assertLengthValidation() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(xpath(loginButtonLocator)));
        Assertions.assertTrue(driver.findElement(xpath(errorTextLocator)).getText().contains(lengthValidationText));

    }

    public void assertSymbolValidation() {
        //webDriverWait.until(ExpectedConditions.visibilityOf(symbolValidationElement));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(xpath(loginButtonLocator)));
        Assertions.assertTrue(driver.findElement(xpath(errorTextLocator)).getText().contains(symbolValidationText));
    }

    public void assertEmptylValidation() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(xpath(loginButtonLocator)));
        Assertions.assertTrue(driver.findElement(xpath(errorTextLocator)).getText().contains(emptyValidationText));
    }

    public void assertNonExistentValidation() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(xpath(loginButtonLocator)));
        Assertions.assertTrue(driver.findElement(xpath(errorTextLocator)).getText().contains(nonExistentValidationText));
    }
}
