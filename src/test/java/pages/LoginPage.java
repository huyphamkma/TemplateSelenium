package pages;

import helpers.UIHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage {
    WebDriver driver;
    UIHelpers uiHelpers;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        uiHelpers = new UIHelpers(driver);
    }

    By emailInput = By.xpath("//input[@name='email']");
    By passwortInput = By.xpath("//input[@name='password']");
    By signinBtn = By.xpath("//button[text()='Sign in']");
    By headerText = By.xpath("//h2[text()='Sign in']");


    public void signIn(String email, String password){
        uiHelpers.waitForPageLoaded();
        Assert.assertTrue(uiHelpers.verifyTextElement(headerText, "Sign in"), "The Sign in page hasn't loaded");
        uiHelpers.setText(emailInput, email);
        uiHelpers.setText(passwortInput, password);
        uiHelpers.clickElement(signinBtn);
    }

}
