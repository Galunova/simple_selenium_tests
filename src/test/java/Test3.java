import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Test3 {
    private WebDriver driverFirefox;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.firefoxdriver().setup();
    }

    @Before
    public void setupTest() {
        driverFirefox = new FirefoxDriver();
    }

    @After
    public void teardown() {
        driverFirefox.quit();
    }

    @Test
    public void test2() {
        driverFirefox.get("https://yandex.com");
    }
}
