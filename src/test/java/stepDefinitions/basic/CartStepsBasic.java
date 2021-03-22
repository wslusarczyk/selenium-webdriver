package stepDefinitions.basic;

import CucumberOptions.TestRunnerBasic;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class CartStepsBasic {

    private WebDriver webDriver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", TestRunnerBasic.DRIVER_PATH);
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @After
    public void cleanUp() {
        webDriver.close();
    }

    //SCENARIO 1 Verify adding items to cart

    @Given("User is on product page")
    public void user_is_on_product_page() {
        webDriver.get("http://automationpractice.com/index.php?id_product=3&controller=product");
        webDriver.manage().window().maximize();
    }

    @When("User adds product to cart")
    public void user_adds_product_to_cart() {
        WebElement addToCartButton = webDriver.findElement(By.xpath("//*[@id=\"add_to_cart\"]/button"));
        WebDriverWait wait = new WebDriverWait(webDriver, 6);
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));

        addToCartButton.click();

        WebElement proceedToCheckout = wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a"))));
        proceedToCheckout.click();
    }

    @Then("Added products are available in cart page")
    public void added_products_are_available_in_cart_page() {
        List<WebElement> cartItems = webDriver.findElements(By.className("cart_item"));

        assertTrue(!cartItems.isEmpty());
        assertEquals(1, cartItems.size());
    }
}
