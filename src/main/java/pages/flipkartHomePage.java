package main.java.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class flipkartHomePage {

    WebDriver driver;

    public flipkartHomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Using FindBy for locating elements
    @FindBy(how = How.XPATH, using ="//*[contains(text(),'✕')]")
    public static WebElement loginCloseButton;

    @FindBy(how = How.XPATH, using ="//img[contains(@src,'29327f40e9c4d26b')]/../../../../../../*")
    public static WebElement menuElements;

    @FindBy(how = How.XPATH, using ="//img[@alt='Beauty, Toys & More']")
    public static WebElement beautyToyMenu;

    @FindBy(how = How.LINK_TEXT, using ="Books")
    public static WebElement mouseHoverTop;

    @FindBy(how = How.XPATH, using ="//*[contains(text(),'View All')]/../*[1]")
    public static WebElement moreBooks;

}
