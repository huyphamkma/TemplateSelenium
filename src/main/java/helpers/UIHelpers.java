package helpers;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class UIHelpers {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    int timeToWaitElement = 20;
    int timeToWaitPageLoad = 30;

    public UIHelpers(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, timeToWaitElement);
        js = (JavascriptExecutor) driver;
    }

    public void clickElement(By element) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(element).click();
    }

    public void setText(By element, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        driver.findElement(element).clear();
        driver.findElement(element).sendKeys(text);
    }

    public void waitForPageLoaded() {
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return (Long) js.executeScript("return jQuery.active") == 0;
                }catch (Exception e) {
                    return true;
                }
            }
        };
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        try {
            wait = new WebDriverWait(driver, timeToWaitPageLoad);
            wait.until(jQueryLoad);
            wait.until(jsLoad);
        }catch (Throwable error){
            Assert.fail("Timeout waiting for page load request");
        }
    }

    public boolean verifyUrl(String value) {
        return driver.getCurrentUrl().contains(value);
    }

    public boolean verifyTextElement(By element, String text){
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element).getText().equals(text);
    }

    public boolean verifyElementExist(By element) {
        List<WebElement> elementList = driver.findElements(element);
        return elementList.size() > 0;
    }
}
