package pages;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import commonfiles.CommonClass;

public class ViewClientPage extends CommonClass{
	  
	WebDriverWait wait = new WebDriverWait(driver, 30);
	
	public ActivateClientPage clickActivateClient() {
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//a[@has-permission='ACTIVATE_CLIENT']")));
		driver.findElementByXPath("//a[@has-permission='ACTIVATE_CLIENT']").click();
		return new ActivateClientPage();
	}
}
