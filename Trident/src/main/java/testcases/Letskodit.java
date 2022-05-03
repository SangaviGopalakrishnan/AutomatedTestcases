package testcases;
import org.testng.annotations.Test;

import commonfiles.CommonClass;
import pages.LetskoditPage;

public class Letskodit extends CommonClass{

	@Test
	public void makemytrip() throws InterruptedException {		
		new LetskoditPage(driver)
		.getTitleofthepage()
		.gethyperlink()
		.selectdropdown()
		.clickcheckbox();
	}

}
