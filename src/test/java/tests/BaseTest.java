package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    WebDriver driver;
    HomePage homePage;

    @BeforeMethod
    public void driver() throws InterruptedException {
        //System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");

        /*FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("start-maximized");
        driver = new FirefoxDriver(firefoxOptions);*/

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        driver = new ChromeDriver(chromeOptions);

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("http://localhost:8080/login");
        Thread.sleep(500);

    }

    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }
}