package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features= {"src/main/java/feature/TC004BigBasket2.feature"},
glue= {"steps"},
monochrome=true)
public class TC004BigBasket2Runner extends AbstractTestNGCucumberTests{

}
