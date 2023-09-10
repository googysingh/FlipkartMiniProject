package main.java.test_cases;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import main.java.pages.flipkartHomePage;
import main.java.pages.flipkartBookPage;

public class TestBase{
    public static WebDriver driver ;
    public static WebDriverWait wait;

    @BeforeSuite
    public void initialize() {

        driver = new FirefoxDriver();

        // To maximize browser
        driver.manage().window().maximize();

        // Implicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // To open Flipkart site
        driver.get("https://www.flipkart.com/");
    }

    @AfterSuite
    // Test cleanup
    public void TeardownTest() {
        TestBase.driver.quit();
    }


    public void pageOpen() {
        System.out.println("Page open Method Started");
        try {
            //WebElement closeButton = driver.findElement(By.xpath("//*[contains(text(),'✕')]"));
            WebElement closeButton = flipkartHomePage.loginCloseButton;

            // Wait until the element is clickable
            wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.elementToBeClickable(closeButton));

            // Click on the element
            closeButton.click();
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
        List<WebElement> menuElements = driver.findElements((By) flipkartHomePage.menuElements);
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

        WebElement beautyToyMenu = wait.until(ExpectedConditions.visibilityOfElementLocated((By) flipkartHomePage.beautyToyMenu));
        Actions actions = new Actions(driver);
        actions.moveToElement(beautyToyMenu).perform();
        //Hard coded wait given to handle animation
        Thread.sleep(1000);
        WebElement mouseHoverTop = wait.until(ExpectedConditions.visibilityOfElementLocated((By) flipkartHomePage.mouseHoverTop));
        actions.moveToElement(mouseHoverTop).perform();
        System.out.println("Mouse Hover on Books");
        Thread.sleep(2000);

        //Move mouse to Books Submenu
        // Scroll the element into view
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", linkElement);

        //driver.findElement(By.xpath("//a[contains(text(),'View All')]")).click();
        WebElement moreBooks = driver.findElement((By) flipkartHomePage.moreBooks);
        actions.moveToElement(moreBooks).perform();
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

    public void nonFrictionBooks() throws InterruptedException {
        System.out.println("Non Friction Books Method started");
        //Instantiate Action Class
        driver.get("https://www.flipkart.com/the-non-fiction-store");
        Actions builder = new Actions(driver);
        Thread.sleep(2000);
        System.out.println("Page Loaded " + driver.getTitle());
        //Retrieve WebElement 'slider' to perform mouse hover
        WebElement slider = driver.findElement((By) flipkartBookPage.slider);
        //WebElement slider = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Price')]/../../../div[3]/div/div[2]")));

        //Here, getting x and y offset to drop source object on target object location
        //First, get x and y offset for from object
        int xOffset1 = slider.getLocation().getX();

        int yOffset1 = slider.getLocation().getY();
        System.out.println("xOffset1--->" + xOffset1 + " yOffset1--->" + yOffset1);

        //Find the xOffset and yOffset difference to find x and y offset needed in which from object required to dragged and dropped
        int xOffset = (int) (xOffset1 - 94.72);
        System.out.println("xOffset--->" + xOffset + " yOffset1--->" + yOffset1);
        //Perform dragAndDropBy
        builder.dragAndDropBy(slider, -100, 0).perform();

        Thread.sleep(2000);

        // Find the elements using the locator
        List<WebElement> elements = driver.findElements((By) flipkartBookPage.itemPrice);

        // Create a HashMap to store element names and values
        Map<String, Integer> elementMap = new HashMap<>();

        // Iterate through the list and print the text of each element
        for (WebElement element : elements) {

            WebElement itemName = driver.findElement(By.xpath("//div[contains(text(),'" + element.getText() + "')]/../../../a[2]"));
            elementMap.put(itemName.getAttribute("title"), Integer.valueOf(element.getText().substring(1)));
        }

        // Print the HashMap to see the     stored data
        for (Map.Entry<String, Integer> entry : elementMap.entrySet()) {
            System.out.println("Item Name: " + entry.getKey());
            System.out.println("Item Value: " + entry.getValue());
            int itemValue = entry.getValue();
            Assert.assertTrue(itemValue < 501, "Value should be less than 500");
        }
        System.out.println("Non Friction Book Method Ended");
    }
}
