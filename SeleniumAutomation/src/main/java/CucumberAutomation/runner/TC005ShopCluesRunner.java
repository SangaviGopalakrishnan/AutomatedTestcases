package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features= {"src/main/java/feature/TC005ShopClues.feature"},
glue= {"steps"},
monochrome=true)
public class TC005ShopCluesRunner extends AbstractTestNGCucumberTests{

}
