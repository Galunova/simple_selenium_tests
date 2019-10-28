import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.*;
import java.util.Random;

public class Test5 {

    private WebDriver driverChrome;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setupTest() {
        driverChrome = new ChromeDriver();
        driverChrome.manage().window().maximize();
    }

    @After
    public void teardown() {
        driverChrome.quit();
    }

    @Test
    public void CheckInitialCounter() {
        driverChrome.get("https://onlinetestpad.com/ru/test/68962-tablica-umnozheniya");
        driverChrome.findElement(By.xpath("//input[@id='btnNext']")).click();
        driverChrome.findElement(By.xpath("//input[@id='btnNext']")).click();
        WebElement alert = driverChrome.findElement(By.xpath("//*[@class='alert alert-mini alert-warning']"));
        assert  alert.isDisplayed();
        for (int i = 1; i < 11; i++) {
            WebElement problemInput = driverChrome.findElement(By.xpath("//*[@class='qcontainer']/descendant::input"));
            problemInput.sendKeys("47");
            String expectedResult = String.valueOf(i).concat(" из 10");
            String actualResult = driverChrome.findElement(By.xpath("//*[@class='header']/descendant::span[@class='text']")).getText();
            assert expectedResult.equals(actualResult);
            driverChrome.findElement(By.xpath("//input[@id='btnNext']")).click();
        }
        assert driverChrome.findElement(By.xpath("//*[@class='otp-item-result']/*[@class='title']")).isDisplayed();
    }

}
