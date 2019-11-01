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

public class TestStringUtils {

    private WebDriver driver;
    private String baseURL = "https://onlinetestpad.com/ru/test/68962-tablica-umnozheniya";
    private String nxtButton = "//input[@id='btnNext']";
    private String result = "//*[@class='otp-item-result']/*[@class='title']";
    private String happyPass = "//td[contains(text(), \"Количество баллов (правильных ответов)\")]//..//td[@class=\"ta-c\"]";

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


    private String multiply() {
        String problem = driver.findElement(By.xpath("//span[@class='qtext']")).getText();
        String[] multiplier= problem.replaceAll("\\D", ",").split(",");
        int a = Integer.parseInt(multiplier[0]);
        int b = Integer.parseInt(multiplier[1]);
        int multiply = a*b;
        return Integer.toString(multiply);
    }

    @Test
    public void CheckCounter() {
        for (int i = 1; i < 11; i++) {
            WebElement problemInput = driver.findElement(By.xpath("//*[@class='qcontainer']/descendant::input"));
            problemInput.sendKeys(multiply());
            String expectedResult = (i + " из 10");
            String actualResult = driver.findElement(By.xpath("//*[@class='header']/descendant::span[@class='text']")).getText();
            assert expectedResult.equals(actualResult);
            clickButton(nxtButton);
        }
        assert driver.findElement(By.xpath(result)).isDisplayed();
        assert driver.findElement(By.xpath(happyPass)).getText().equals("10");
    }
}
