package testcases;

import org.testng.annotations.Test;

import commonfiles.CommonClass;
import pages.MakemytripPage;

public class Makemytrip extends CommonClass{

	@Test
	public void makemytrip() throws InterruptedException {		
			new MakemytripPage(driver)
			.enterdepartureDetails()
			.selectDate()
			.clicksearchbutton();
	}

}
