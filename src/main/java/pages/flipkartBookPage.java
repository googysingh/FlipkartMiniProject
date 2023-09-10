package main.java.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class flipkartBookPage {

    // Using FindBy for locating elements
    @FindBy(how = How.XPATH, using ="//span[contains(text(),'Price')]/../../../div[3]/div/div[2]")
    public static WebElement slider;

    @FindBy(how = How.XPATH, using ="//a[@rel='noopener noreferrer']/div//div[contains(text(),'₹')][1]")
    public static WebElement itemPrice;

}
