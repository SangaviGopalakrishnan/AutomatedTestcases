package pages;

import org.openqa.selenium.interactions.Actions;

import commonfiles.CommonClass;

public class ActivateClientPage extends CommonClass{
	
	Actions action = new Actions(driver);

	public CreateClientPage selectActivateClientDate() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElementByXPath("(//input[@id='activationDate'])[2]").click();
		driver.findElementByXPath("(//tr[@ng-show='showTodayDate'])[2]").click();
		action.moveToElement(driver.findElementById("save")).click().build().perform();
		Thread.sleep(5000);
		return new CreateClientPage();
	}
}
