import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Qasca {
    private WebDriver driver;

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
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void LoginAndGoToAccountsPage() {
        driver.get("https://qasca-554.reallysimplesystems.com/login");
        driver.findElement(By.cssSelector("#email")).sendKeys("oleg.kutc@gmail.com");
        driver.findElement(By.cssSelector("#password")).sendKeys("Pass#12345");
        driver.findElement(By.cssSelector("#login_submit")).click();
        driver.findElement(By.cssSelector(".menu-item.menu-item-2")).click();
        assert driver.getCurrentUrl().contains("accounts");
    }

    @Test
    public void CheckAccountCount() {
        LoginAndGoToAccountsPage();
        List<WebElement> accounts = driver.findElements(By.cssSelector(".table-data"));
        Integer account_size = accounts.size();
        assert account_size.equals(8);
    }

    @Test
    public void CheckAccountSearch() {
        LoginAndGoToAccountsPage();
        WebElement firstAccount = driver.findElement(By.cssSelector("#grid-Accounts_wrapper table tbody tr:first-child td:nth-child(2) a"));
        String first_account_name = firstAccount.getText();
        driver.findElement(By.cssSelector("#Accounts_basic_filter")).sendKeys(first_account_name);
        WebDriverWait wait = new WebDriverWait(driver, 2);
        WebElement searchResult = driver.findElement(By.cssSelector("#grid-Accounts_wrapper table tbody tr:first-child td:nth-child(2) a"));
        String search_result_name = searchResult.getText();
        assert first_account_name.equals(search_result_name);
    }

}
