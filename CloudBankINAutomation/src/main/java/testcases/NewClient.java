package testcases;

import org.testng.annotations.Test;

import commonfiles.CommonClass;
import pages.CreateClientPage;

public class NewClient extends CommonClass{

	@Test
	public void executeCreateLead() throws InterruptedException {
		new CreateClientPage()
		.clickClient()
		.createClient()
		.clickActivateClient()
		.selectActivateClientDate()
		.clickClient()
		.searchText();
	}

}
