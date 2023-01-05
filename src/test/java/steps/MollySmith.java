package steps;

import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.Assert;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class MollySmith extends Steps {

    private static WebDriver driver = null;

    @Given("a user is on the listings page")
    public void init() throws InterruptedException {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "src/test/java/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-extensions");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        }
        //driver.manage().deleteAllCookies();

        String file = new File("src/main/html/products.html").getAbsolutePath();
        driver.get("file:///" + file);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        //Thread Sleep is SOLELY for the purpose of actually seeing what happens during the recording
        Thread.sleep(4000);

    }

    @When("the user presses one of the listings")
    public void clickOn() throws InterruptedException {
        WebElement product = driver.findElement(By.xpath("/html/body/section/div/div[2]/div/div/div/div/a"));
        product.click();
        //Thread Sleep is SOLELY for the purpose of actually seeing what happens during the recording
        Thread.sleep(4000);
    }

    @Then("the user sees the information about the listing")
    public void checkContent() throws InterruptedException {
        WebElement price = driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/strong"));
        Assert.assertEquals("DKK 300,000.00", price.getText());
        //Thread Sleep is SOLELY for the purpose of actually seeing what happens during the recording
        Thread.sleep(4000);
    }

    @AfterStory
    public void closeSession() {
        driver.quit();
    }
}