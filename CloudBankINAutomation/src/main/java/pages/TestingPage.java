package pages;

import org.testng.Assert;

import commonfiles.CommonClass;

public class TestingPage extends CommonClass {

	public ApprovalPage checkenteredDetails() throws InterruptedException {
		String ProductType = driver.findElementByXPath("//td[text()='Product']/following-sibling::td").getText();
		Assert.assertEquals(ProductType,"Testing"); 
		String LoanPurpose = driver.findElementByXPath("//td[contains(text(),'Loan purpose:')]/following-sibling::td").getText();
		Assert.assertEquals(LoanPurpose,"Cash/Cheque");
		String Principal = driver.findElementByXPath("//td[text()='Principal']/following-sibling::td").getText();
		Assert.assertEquals(Principal,"20000");
		driver.findElementByXPath("(//button[@type='submit'])[15]").click();
		return new ApprovalPage();
	}

}
