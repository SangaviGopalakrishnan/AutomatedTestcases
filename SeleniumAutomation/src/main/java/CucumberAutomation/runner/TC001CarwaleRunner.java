package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features= {"src/main/java/feature/TC001Carwale.feature"},
glue= {"steps"},
monochrome=true)
public class TC001CarwaleRunner extends AbstractTestNGCucumberTests{

}
