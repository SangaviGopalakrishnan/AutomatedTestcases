package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features= {"src/main/java/feature/TC003Ajio2.feature"},
glue= {"steps"},
monochrome=true)
public class TC003Ajio2Runner extends AbstractTestNGCucumberTests{

}
