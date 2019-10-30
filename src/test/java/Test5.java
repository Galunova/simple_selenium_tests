import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Test5 {

    private WebDriver driver;

    private String baseURL = "https://onlinetestpad.com/ru/test/68962-tablica-umnozheniya";
    private String warningAlert = "//*[@class='alert alert-mini alert-warning']";
    private String nxtButton = "//input[@id='btnNext']";
    private String result = "//*[@class='otp-item-result']/*[@class='title']";

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setupTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.get(baseURL);
        clickButton(nxtButton);
    }

    public void clickButton(String button) {
        driver.findElement(By.xpath(button)).click();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void CheckInitialCounter() {
        clickButton(nxtButton);
        WebElement alert = driver.findElement(By.xpath(warningAlert));
        assert alert.isDisplayed();
    }

    @Test
    public void CheckCounter() {
        for (int i = 1; i < 11; i++) {
            WebElement problemInput = driver.findElement(By.xpath("//*[@class='qcontainer']/descendant::input"));
            problemInput.sendKeys("47");
            String expectedResult = (i + " из 10");
            String actualResult = driver.findElement(By.xpath("//*[@class='header']/descendant::span[@class='text']")).getText();
            assert expectedResult.equals(actualResult);
            clickButton(nxtButton);
        }
        assert driver.findElement(By.xpath(result)).isDisplayed();
    }
}