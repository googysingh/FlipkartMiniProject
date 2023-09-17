package main.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;

public class flipkartHomePage {
    public flipkartHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    private final WebDriver driver;
    private static WebDriverWait wait;


    // Using FindBy for locating elements
    @FindBy(how = How.XPATH, using ="//*[contains(text(),'✕')]")
    public static WebElement loginCloseButton;

    @FindBy(how = How.XPATH, using ="//img[contains(@src,'29327f40e9c4d26b')]/../../../../../../*")
    public static List<WebElement> menuElements;

    //@FindBy(how = How.XPATH, using ="//img[@alt='Beauty, Toys & More']")
    //public static WebElement beautyToyMenu;
    @FindBy(xpath = "//img[@alt='Beauty, Toys & More']")
    private static WebElement beautyToyMenu;

    public static String mouseHoverTop="Books";

    @FindBy(how = How.XPATH, using ="//*[contains(text(),'View All')]/../*[1]")
    public static WebElement moreBooks;


    public void pageOpen() {
        System.out.println("Page open Method Started");
        try {
            //WebElement closeButton = driver.findElement(By.xpath("//*[contains(text(),'✕')]"));
            //WebElement closeButton = flipkartHomePage.loginCloseButton;

            // Wait until the element is clickable
            wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.elementToBeClickable(loginCloseButton));

            // Click on the element
            loginCloseButton.click();
        } catch (TimeoutException e) {
            // Handle the case where the element didn't appear within the specified timeout
            System.out.println("Element did not appear, so it was not clicked.");
        }
        System.out.println("Page Open Method End");
    }


    public void menuValidation() throws InterruptedException {
        System.out.println("Menu Validation method started");
        driver.get("https://www.flipkart.com/");
        Thread.sleep(1000);
        List<WebElement> menuElements = flipkartHomePage.menuElements;
        String[] ExpectedMenuName = {"Grocery", "Mobiles", "Fashion", "Electronics", "Home & Furniture", "Appliances", "Travel", "Beauty, Toys & More", "Two Wheelers"};
        for (WebElement element : menuElements) {
            String menuName = element.getText();
            //System.out.println("Menu Name= "+menuName);

            // Assert that the array contains the expected string
            try {
                Assert.assertTrue(arrayContainsString(ExpectedMenuName, menuName));
                System.out.println("Assertion passed: " + menuName);
            } catch (AssertionError e) {
                System.out.println("Assertion failed: Actual value is not equal to expected value.");
                System.out.println("Expected Value: " + menuName);
            }
        }
        Thread.sleep(10000);
        System.out.println("Menu Validation Method ended");
    }

    // Custom method to check if an array of strings contains a given string
    public boolean arrayContainsString(String[] Actual, String MenuName) {
        for (String s : Actual) {
            if (MenuName.equals(s)) {
                return true;
            }
        }
        return false;
    }


    public void subMenuValidation() throws InterruptedException {
        System.out.println("Sub Menu Validation Method Started");

        WebElement beautyToyMenuElement = wait.until(ExpectedConditions.visibilityOf(beautyToyMenu));
        Actions actions = new Actions(driver);
        actions.moveToElement(beautyToyMenuElement).perform();
        //Hard coded wait given to handle animation
        Thread.sleep(1000);
        WebElement mouseHoverTopElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Books")));
        actions.moveToElement(mouseHoverTopElement).perform();
        System.out.println("Mouse Hover on Books");
        Thread.sleep(2000);

        //Move mouse to Books Submenu
        // Scroll the element into view
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", linkElement);

        //driver.findElement(By.xpath("//a[contains(text(),'View All')]")).click();
        WebElement moreBooksElement = moreBooks;
        actions.moveToElement(moreBooksElement).perform();
        System.out.println("Mouse Hover on More in Books");
        String expectedMenuName = "MORE IN BOOKS";
        try {
            Assert.assertEquals(moreBooks.getText(), expectedMenuName);
            System.out.println("Assertion passed: " + moreBooks.getText());
        } catch (AssertionError e) {
            System.out.println("Assertion failed: Actual value is not equal to expected value.");
            System.out.println("Expected Value: " + moreBooks.getText());
        }
        System.out.println("Sub Menu validation method ended");
    }


}