import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test4 {

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
    public void OpenPage() {
        driverChrome.get("https://www.google.com/?hl=ru");
        WebElement input = driverChrome.findElement(By.xpath("//input[@title='Поиск']"));
        input.sendKeys("CSS vs XPATH locators");
        input.submit();
        assert driverChrome.getCurrentUrl().contains("CSS+vs+XPATH+locators");
        Integer rowCount =  driverChrome.findElements(By.xpath("//div[@class ='r' and not(ancestor::*[@class='related-question-pair'])]")).size();
        assert rowCount == 11;
    }
}
