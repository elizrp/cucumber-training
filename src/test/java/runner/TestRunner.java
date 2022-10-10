package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        glue = "stepDefinitions",
        tags = "@Text or @Calculator or @DataTable or @Hello or @Login or @Register")
public class TestRunner {
}
