import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test2 {

    private WebDriver driverChrome;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setupTest() {
        driverChrome = new ChromeDriver();
    }

    @After
    public void teardown() {
        driverChrome.quit();
    }

    @Test
    public void test1() {
        driverChrome.get("https://yandex.com");
    }
}

