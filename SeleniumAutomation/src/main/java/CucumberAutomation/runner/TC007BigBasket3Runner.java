package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features= {"src/main/java/feature/TC007BigBasket3.feature"},
glue= {"steps"},
monochrome=true)
public class TC007BigBasket3Runner extends AbstractTestNGCucumberTests{

}
