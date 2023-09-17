package main.java.test_cases;

import main.java.pages.flipkartBookPage;
import main.java.pages.flipkartHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

public class FlipkartTestRun {
    public static WebDriver driver;
    public static WebDriverWait wait;
    private flipkartHomePage homePage;
    private flipkartBookPage bookPage;

    @BeforeSuite
    public void initialize() {

        driver = new FirefoxDriver();

        // To maximize browser
        driver.manage().window().maximize();

        // Implicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // To open Flipkart site
        driver.get("https://www.flipkart.com/");

        homePage = new flipkartHomePage(driver);
        bookPage = new flipkartBookPage(driver);
    }

    @AfterSuite
    // Test cleanup
    public void TeardownTest() {
        driver.quit();
    }


    @Test(priority = 1)
    public void pageOpen() {
        homePage.pageOpen();
        // Add assertions and further test steps here
    }

    @Test(priority = 2)
    public void subMenuValidation() throws Exception {
        homePage.subMenuValidation();
        // Add assertions and further test steps here
    }

    @Test(priority = 3)
    public void nonFrictionBooks() throws Exception {
        bookPage.nonFrictionBooks();
        // Add assertions and further test steps here
    }

    @Test(priority = 4)
    public void menuValidation () throws Exception {
        homePage.menuValidation();
        // Add assertions and further test steps here
    }
}
