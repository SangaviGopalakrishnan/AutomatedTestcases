package pages;

import commonfiles.CommonClass;

public class HeadOfficePage extends CommonClass {
	
	public SampleLoanApplicationPage clickNewLoan() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElementByXPath("//a[@has-permission='CREATE_LOAN']").click();
		return new SampleLoanApplicationPage();
	}

}
