package common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

public class BaseSetup {
    static WebDriver driver;
//    private String url = "https://rise.fairsketch.com/signin";

//    public String getUrl() {
//        return url;
//    }

    public WebDriver getDriver() {
        return driver;
    }


    @Parameters({"browserType", "appURL"})
    @BeforeClass
    public void initDriver(String browserType, String appURL) {
        setBrowserType(browserType);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(appURL);
    }


    private void setBrowserType(String browserType) {
        switch (browserType.toLowerCase()){
            case "firefox":
                initFirefoxBrowser();
                break;
            case "edge":
                initEdgeBrowser();
                break;
            default:
                initChromeBrowser();
                break;
        }
    }

    private void initChromeBrowser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    private void initFirefoxBrowser() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    private void initEdgeBrowser() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
    }
}
