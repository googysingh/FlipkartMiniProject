package main.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class flipkartBookPage {
    private final WebDriver driver;
    public flipkartBookPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Using FindBy for locating elements
    @FindBy(how = How.XPATH, using ="//span[contains(text(),'Price')]/../../../div[3]/div/div[2]")
    public static WebElement slider;

    @FindBy(how = How.XPATH, using ="//a[@rel='noopener noreferrer']/div//div[contains(text(),'₹')][1]")
    public static List<WebElement> itemPrice;


    public void nonFrictionBooks() throws InterruptedException {
        System.out.println("Non Friction Books Method started");
        //Instantiate Action Class
        driver.get("https://www.flipkart.com/the-non-fiction-store");
        Actions builder = new Actions(driver);
        Thread.sleep(2000);
        System.out.println("Page Loaded " + driver.getTitle());
        //Retrieve WebElement 'slider' to perform mouse hover
        WebElement slider = flipkartBookPage.slider;
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
        List<WebElement> itemPriceElements = flipkartBookPage.itemPrice;

        // Create a HashMap to store element names and values
        Map<String, Integer> elementMap = new HashMap<>();

        // Iterate through the list and print the text of each element
        for (WebElement element : itemPriceElements) {

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
