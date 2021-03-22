package stepDefinitions.basic;

import CucumberOptions.TestRunnerBasic;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class LoginStepsBasic {

    private WebDriver webDriver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", TestRunnerBasic.DRIVER_PATH);
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @After
    public void cleanUP() {
        webDriver.close();
    }

    // SCENARIO 1 Verify invalid data

    @Given("User is on login page")
    public void user_is_on_loagin_page() {
        webDriver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        webDriver.manage().window().maximize();
    }

    @When("User tries to login on the page with incorrect mail and corret password")
    public void user_try_to_login_on_the_pages_with_corret_mail_and_corret_password() {
        WebElement email = webDriver.findElement(By.id("email"));
        WebElement password = webDriver.findElement(By.id("passwd"));

        WebElement butttonCreateAccount = webDriver.findElement(By.id("SubmitLogin"));

        email.sendKeys("asdf@wp.pl");
        password.sendKeys("asdf1234@");
        butttonCreateAccount.click();
    }

    @Then("An error message is displayed")
    public void an_error_message_is_displayed() {
        WebDriverWait wait = new WebDriverWait(webDriver, 6);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert-danger")));

        WebElement errorMesggae = webDriver.findElement(By.className("alert-danger"));
        boolean errorMessageDisplayed = errorMesggae.isDisplayed();
        Assert.assertTrue(errorMessageDisplayed);
    }

    // SCENARIO 2 Verify valid email and empty password
    @Given("User is on login page 1")
    public void user_is_on_login_page_1() {
        webDriver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation");
        webDriver.manage().window().maximize();
    }

    @When("User tries to login on the page with correct email and empty password")
    public void user_tries_to_login_on_the_page_with_correct_email_and_empty_password() {
        WebElement email = webDriver.findElement(By.id("email"));
        WebElement password = webDriver.findElement(By.id("passwd"));

        WebElement butttonCreateAccount = webDriver.findElement(By.id("SubmitLogin"));

        email.sendKeys("asdfga@asd.pl");
        password.sendKeys("");
        butttonCreateAccount.click();

    }

    @Then("An error message is displayed 1")
    public void an_error_message_is_displayed_1() {
        WebDriverWait wait = new WebDriverWait(webDriver, 6);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.className("alert-danger"))));

        assertTrue(errorMessage.getText().contains("Password is required"));
    }

    // SCENARIO 3 Verify valid email and password
    @Given("User is on login page 2")
    public void user_is_on_login_page_2() {
        webDriver.get("http://automationpractice.com/index.php?controller=authentication");
        webDriver.manage().window().maximize();
    }

    @When("User tries to login on the page with incorrect email and password")
    public void user_tries_to_login_on_the_page_with_correct_email_and_password() {
        WebElement email = webDriver.findElement(By.id("email"));
        WebElement passwd = webDriver.findElement(By.id("passwd"));
        WebElement submitLogin = webDriver.findElement(By.id("SubmitLogin"));

        email.sendKeys("asdfgx");
        passwd.sendKeys("asadad");
        submitLogin.click();
    }

    @Then("Then An error message is displayed 2")
    public void then_an_error_message_is_displayed_2() {
        WebDriverWait wait = new WebDriverWait(webDriver, 6);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.className("alert-danger"))));
        assertTrue(errorMessage.getText().contains("Invalid email address"));
    }
}
