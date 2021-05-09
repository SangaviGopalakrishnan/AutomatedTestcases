package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import commonfiles.CommonClass;

public class SampleLoanApplicationPage extends CommonClass {

	WebDriverWait wait = new WebDriverWait(driver, 30);
	Actions action = new Actions(driver);
	
	public TestingPage enterLoanDetails() throws InterruptedException {
		Thread.sleep(2000);
		WebElement product = driver.findElementById("productId");
		Select newproduct = new Select(product);
		newproduct.selectByVisibleText("Testing");
		
//    		â—�	On Page "Details", Fill "Disbursement On" as today, "Loan Purpose", "Loan Officer"
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementById("loanPurposeId_chosen")));
		action.moveToElement(driver.findElementById("loanPurposeId_chosen")).click().build().perform();
		action.click(driver.findElementByXPath("(//li[text()='test'])")).build().perform();
		
		WebElement loanOfficerId = driver.findElementById("loanOfficerId");
		Select OfficerId = new Select(loanOfficerId);
		OfficerId.selectByVisibleText("Mathew, Roger");
		
		driver.findElementById("expectedDisbursementDate").click();
		driver.findElementByXPath("(//td/button/span[text()='10'])[2]").click();
//		driver.findElementByXPath("(//tr[@ng-show='showTodayDate'])[2]").click();
		
//    		â—�	Select "Repayment mode" as "Cash/Cheque"
		action.moveToElement(driver.findElementById("repaymentModeId_chosen")).click().build().perform();
		action.click(driver.findElementByXPath("(//li[text()='Cash/Cheque'])")).build().perform();
		driver.findElementByXPath("(//button[@type='submit'])[12]").click();
		
//    		â—�	On Page "Terms", Fill in mandatory values (replace default values shown in UI)
//		e.g., "Principal",
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementById("principal")));
		driver.findElementById("principal").clear();
		driver.findElementById("principal").sendKeys("20000");
		
//		"Loan Term", 
		driver.findElementById("loanTermFrequency").clear();
		driver.findElementById("loanTermFrequency").sendKeys("5");
		
//		"Repaid every",
		driver.findElementById("repaymentEvery").clear();
		driver.findElementById("repaymentEvery").sendKeys("1");
		
//		"Nominal interest rate", 
		
		driver.findElementById("interestRatePerPeriod").clear();
		driver.findElementById("interestRatePerPeriod").sendKeys("10");
		
//		"Amortization",
		WebElement amortizationType = driver.findElementById("amortizationType");
		Select amortization = new Select(amortizationType);
		amortization.selectByVisibleText("Equal installments");
		
//		"Interest calculation period". Ignore optional fields for the scope of this exercise in this page.
		WebElement interestCalculationPeriodType = driver.findElementById("interestCalculationPeriodType");
		Select interestCalculationPeriod = new Select(interestCalculationPeriodType);
		interestCalculationPeriod.selectByVisibleText("Same as repayment period");
		driver.findElementByXPath("(//button[@type='submit'])[13]").click();
		
//    		â—�	Click "next" on charges page (no details required)
		driver.findElementByXPath("(//button[@type='submit'])[14]").click();
		return new TestingPage();
	}

}
