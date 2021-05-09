package testcases;

import org.testng.annotations.Test;

import commonfiles.CommonClass;
import pages.CreateClientPage;

public class CheckValidMobileNumber extends CommonClass{

	@Test
	public void mobileNumberValidation() throws InterruptedException {
		new CreateClientPage()
		.clickClient()
		.mobileValidation();
	}
}
