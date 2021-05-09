package pages;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import commonfiles.CommonClass;

public class ApprovalPage extends CommonClass{

	WebDriverWait wait = new WebDriverWait(driver, 30);
	Actions action = new Actions(driver);
	
	public TestingPage approveLoan() throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//a[@has-permission='APPROVE_LOAN']")));
		action.moveToElement(driver.findElementByXPath("//a[@has-permission='APPROVE_LOAN']")).click().build().perform();
		wait.until(ExpectedConditions.visibilityOf(driver.findElementById("approvedAmt")));
		driver.findElementById("approvedAmt").sendKeys("19000");
		driver.findElementByXPath("(//button[@type='submit'])[13]").click();
		return new TestingPage();
	}

	
}
