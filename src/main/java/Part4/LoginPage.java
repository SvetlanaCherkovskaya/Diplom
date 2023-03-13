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

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginBtn;

    private final static String lengthValidationLocator = "//*[contains(text(),'401')]";
    @FindBy(xpath = lengthValidationLocator)
    private WebElement lengthValidationElement;
    ////*[contains(text(),'Не менее 3 и не более 20 символов в одном поле')]

    private final static String nonExistentValidationLocator = "//*[contains(text(),'401')]";;
    @FindBy(xpath = nonExistentValidationLocator)
    private WebElement nonExistentValidationElement;
//Проверьте логин и пароль
    private final static String symbolValidationLocator = "//*[contains(text(),'401')]";;
    @FindBy(xpath = symbolValidationLocator)
    private WebElement symbolValidationElement;
    ////*[contains(text(),'Только латинские символов и цифр, без спецсимволов')]

    private final static String emptyValidationLocator = "//*[contains(text(),'401')]";
    @FindBy(xpath = emptyValidationLocator)
    private WebElement emptyValidationElement;
    //Поле не может быть пустым



    public void login(String login, String password) {
        usernameField.sendKeys(login);
        passwordField.sendKeys(password);
        loginBtn.click();
        // return new PostsPage(driver);

    }

    public void assertLengthValidation(){
        webDriverWait.until(ExpectedConditions.visibilityOf(lengthValidationElement));
        Assertions.assertTrue(driver.findElement(xpath(lengthValidationLocator)).isDisplayed());
    }

    public void assertSymbolValidation(){
        webDriverWait.until(ExpectedConditions.visibilityOf(symbolValidationElement));
        Assertions.assertTrue(driver.findElement(xpath(symbolValidationLocator)).isDisplayed());
    }

    public void assertEmptylValidation(){
        webDriverWait.until(ExpectedConditions.visibilityOf(emptyValidationElement));
        Assertions.assertTrue(driver.findElement(xpath(emptyValidationLocator)).isDisplayed());
    }

    public void assertNonExistentValidation(){
        webDriverWait.until(ExpectedConditions.visibilityOf(nonExistentValidationElement));
        Assertions.assertTrue(driver.findElement(xpath(nonExistentValidationLocator)).isDisplayed());
    }
    }
