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

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class RegisterStepsBasic {

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

    private UUID uuid = UUID.randomUUID();

    // SCENARIO 1 Verify redirecting to register page

    @Given("User is on authentication page")
    public void user_is_on_authentication_page() {
        webDriver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        webDriver.manage().window().maximize();
    }

    @When("User clicks on Create an account button")
    public void user_clicks_on_create_an_account_button() {
        WebElement email_create = webDriver.findElement(By.id("email_create"));
        WebElement submitCreate = webDriver.findElement(By.id("SubmitCreate"));

        email_create.sendKeys(uuid + "@aaa.pl");
        submitCreate.click();
    }

    @Then("User is redirected to register page")
    public void user_is_redirected_to_register_page() {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);

        By classNamePageHeading = By.className("page-heading");
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(classNamePageHeading)));
        assertTrue(webDriver.findElement(classNamePageHeading).isDisplayed());

        By classNamePageSubHeading = By.className("page-subheading");
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(classNamePageSubHeading)));
        boolean pageSubHeadingIsDisplayed = webDriver.findElement(classNamePageSubHeading).isDisplayed();
        assertTrue(pageSubHeadingIsDisplayed);

        By classNameRegisterButton = By.id("submitAccount");
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(classNameRegisterButton)));
        boolean registerButtonIsDisplayed = webDriver.findElement(classNameRegisterButton).isDisplayed();
        assertTrue(registerButtonIsDisplayed);
    }


    // SCENARIO 2 Verify data used for registering

    @Given("User is on registering page")
    public void user_is_on_registering_page() {
        webDriver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation");
        webDriver.manage().window().maximize();
        WebElement email_create = webDriver.findElement(By.id("email_create"));
        WebElement submitCreate = webDriver.findElement(By.id("SubmitCreate"));

        email_create.sendKeys(uuid + "@aaa.pl");
        submitCreate.click();
    }

    @When("User enters empty first name, {int} characters password and invalid mobile phone number")
    public void user_enters_empty_first_name_characters_password_and_invalid_mobile_phone_number(Integer int1) {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        WebElement customer_firstname = wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.id("customer_firstname"))));
        WebElement passwd = wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.id("passwd"))));
        WebElement phone_mobilephone_mobile = wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.id("phone_mobile"))));
        WebElement submitAccount = wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.id("submitAccount"))));

        customer_firstname.sendKeys("");
        passwd.sendKeys("Asd2");
        phone_mobilephone_mobile.sendKeys("asdert4543422332");
        submitAccount.click();
    }

    @Then("An error message containing invalid fields is displayed")
    public void an_error_message_containing_invalid_fields_is_displayed() {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);

        By classNameErrorMessage = By.className("alert-danger");
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(classNameErrorMessage)));
        assertTrue(errorMessage.isDisplayed());

        String message = errorMessage.getText();
        assertTrue(message.contains("lastname is required"));
        assertTrue(message.contains("phone_mobile is invalid"));
        assertTrue(message.contains("passwd is invalid"));
    }

    // SCENARIO 3 Verify registering with valid data

    @Given("User is on registering page 2")
    public void user_is_on_registering_page_2() {
        webDriver.get("http://automationpractice.com/index.php?controller=authentication");
        webDriver.manage().window().maximize();
        WebElement email_create = webDriver.findElement(By.id("email_create"));
        WebElement submitCreate = webDriver.findElement(By.id("SubmitCreate"));
        email_create.sendKeys(uuid + "@aaa.pl");
        submitCreate.click();

    }

    @When("User enters valid data")
    public void user_enters_valid_data() {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        WebElement element = webDriver.findElement(By.id("id_gender1"));
        WebElement customer_firstname = webDriver.findElement(By.id("customer_firstname"));
        WebElement customer_lastname = webDriver.findElement(By.id("customer_lastname"));
        WebElement passwd = webDriver.findElement(By.id("passwd"));
        WebElement days =webDriver.findElement(By.id("days"));
        WebElement months = webDriver.findElement(By.id("months"));
        WebElement years = webDriver.findElement(By.id("years"));
        WebElement address1 = webDriver.findElement(By.id("address1"));
        WebElement city = webDriver.findElement(By.id("city"));
        WebElement id_state = webDriver.findElement(By.id("id_state"));
        WebElement postcode = webDriver.findElement(By.id("postcode"));
        WebElement id_country = webDriver.findElement(By.id("id_country"));

        WebElement phone_mobilephone_mobile = webDriver.findElement(By.id("phone_mobile"));
        WebElement alias = webDriver.findElement(By.id("alias"));
        WebElement submitAccount = webDriver.findElement(By.id("submitAccount"));
        WebElement email = webDriver.findElement(By.id("email"));

        element.click();
        customer_firstname.sendKeys("Jan");
        customer_lastname.sendKeys("Nowak");
        passwd.sendKeys("Asd24");
        days.sendKeys("2");
        months.sendKeys("January");
        years.sendKeys("2012");
        address1.sendKeys("P.O. Box");
        city.sendKeys("Los Angles");
        id_state.sendKeys("California");
        postcode.sendKeys("12343");
        id_country.sendKeys("United States");
        phone_mobilephone_mobile.sendKeys("123698547");

        alias.sendKeys(email.getText());
        submitAccount.click();

    }

    @Then("An account is created, user is redirected to My account page")
    public void an_account_is_created_user_is_redirected_to_my_account_page() {
        WebElement navigation_page = webDriver.findElement(By.className("navigation_page"));
        assertTrue(navigation_page.getText().contains("My account"));
    }

    // SCENARIO 4 Verify logging in with account created previously

    @Given("User is on authentication page 2")
    public void user_is_on_authentication_page_2() {
        webDriver.get("http://automationpractice.com/index.php?controller=authentication");
        webDriver.manage().window().maximize();
    }
    @When("User enters correct email and password")
    public void user_enters_correct_email_and_password() {
        WebElement email = webDriver.findElement(By.id("email"));
        WebElement password = webDriver.findElement(By.id("passwd"));

        WebElement butttonCreateAccount = webDriver.findElement(By.id("SubmitLogin"));

        email.sendKeys("asdfga@asd.pl");
        password.sendKeys("Asd24");
        butttonCreateAccount.click();
    }
    @Then("User is redirected to My account page")
    public void user_is_redirected_to_my_account_page() {
        WebElement navigation_page = webDriver.findElement(By.className("navigation_page"));
        assertTrue(navigation_page.getText().contains("My account"));
    }
}
