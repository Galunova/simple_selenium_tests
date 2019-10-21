import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.GeckoDriverInfo;

import java.util.concurrent.TimeUnit;

public class Test1 {
    WebDriver driverChrome;
//    WebDriver driverFirefox;

    @Before
    public void before(){
        System.setProperty("webdriver.chrome.driver", "/home/delya/zen_cources/src/test/resources/chromedriver");
//        System.setProperty("webdriver.gecko.driver", "/home/delya/zen_cources/src/test/resources/geckodriver");
        driverChrome = new ChromeDriver();
//        driverFirefox = new GeckoDriver();
        driverChrome.manage().deleteAllCookies();
        driverChrome.manage().window().maximize();
        driverChrome.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driverChrome.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    @Test
    public void openPage() {
        driverChrome.get("https://yandex.com");
    }
//    public void openPage() {
//        driverFirefox.get("https://yandex.com");
//    }

    @After
    public void closePage() {
        driverChrome.close();
    }
//    public void closePage() {
//        driverFirefox.close();
//    }
}
