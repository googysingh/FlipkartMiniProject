package Flipkart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Flipkart2 {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.flipkart.com/the-non-fiction-store");

        //Maximise browser window
        driver.manage().window().maximize();
        //Adding wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Step 2: Hover over "Beauty, Toys and More" and then "Books"

        //Instantiate Action Class
        Actions builder = new Actions(driver);
        //Retrieve WebElement 'slider' to perform mouse hover
        WebElement slider = driver.findElement(By.xpath("//span[contains(text(),'Price')]/../../../div[3]/div/div[2]"));

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
        // Define a locator for the elements you want to check (e.g., XPath)
        By itemPrice = By.xpath("//a[@rel='noopener noreferrer']/div//div[contains(text(),'₹')][1]");

        // Find the elements using the locator
        List<WebElement> elements = driver.findElements(itemPrice);

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
            Assert.assertTrue(itemValue < 500, "Value should be less than 500");
        }
    }
}