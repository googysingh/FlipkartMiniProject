package main.java.test_cases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import static main.java.test_cases.TestBase.driver;
import main.java.pages.flipkartHomePage;
import main.java.pages.flipkartBookPage;

public class FlipkartTestRun extends TestBase{

    @Test
    public void init() throws Exception {

        FlipkartTestRun run = PageFactory.initElements(driver, FlipkartTestRun.class);
        run.pageOpen();
        run.subMenuValidation();
        run.nonFrictionBooks();
        run.menuValidation();
    }
}
