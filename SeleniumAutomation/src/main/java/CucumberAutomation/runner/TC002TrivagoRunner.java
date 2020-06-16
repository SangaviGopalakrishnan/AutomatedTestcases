package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features= {"src/main/java/feature/TC002Trivago.feature"},
glue= {"steps"},
monochrome=true)
public class TC002TrivagoRunner extends AbstractTestNGCucumberTests{

}
