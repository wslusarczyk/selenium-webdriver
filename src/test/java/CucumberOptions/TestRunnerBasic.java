package CucumberOptions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features/basic",
        glue = "stepDefinitions/basic"
)
public class TestRunnerBasic {
    public static final String DRIVER_PATH = "C:\\driver\\chromedriver_win32\\chromedriver.exe";
}