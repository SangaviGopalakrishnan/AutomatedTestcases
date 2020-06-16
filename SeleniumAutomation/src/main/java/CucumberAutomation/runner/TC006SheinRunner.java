package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features= {"src/main/java/feature/TC006Shein.feature"},
glue= {"steps"},
monochrome=true)
public class TC006SheinRunner extends AbstractTestNGCucumberTests{

}
