package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features= {"src/main/java/feature/carwale.feature"},
glue= {"steps"},
monochrome=true)
public class Carwale extends AbstractTestNGCucumberTests{

}
