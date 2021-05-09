package pages;
import commonfiles.CommonClass;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CreateClientPage extends CommonClass{

	
	WebDriverWait wait = new WebDriverWait(driver, 30);
	Actions action = new Actions(driver);
	
	 public CreateClientPage clickClient() throws InterruptedException{
		wait.until(ExpectedConditions.visibilityOf(driver.findElementById("client-dropdown")));
		action.moveToElement(driver.findElementById("client-dropdown")).build().perform();
		action.click(driver.findElementByXPath("(//a[text()='Clients'])[1]")).build().perform();
		Thread.sleep(5000);
		return this;
	}

	 public CreateClientPage mobileValidation() throws InterruptedException{
		 driver.findElementByXPath("//form/div//div[3]/a[@id='client_createbutton']").click();
			action.moveToElement(driver.findElementById("legalFormId_chosen")).click().build().perform();
			action.click(driver.findElementByXPath("(//li[text()='PERSON'])")).build().perform();
			driver.findElementById("firstname").sendKeys("Sampletest");
			driver.findElementById("lastname").sendKeys("sample");
			driver.findElementById("mobileNo").sendKeys("9856158789");
			action.moveToElement(driver.findElementById("save")).click().build().perform();
			String errortext = driver.findElementByXPath("(//label[@ng-hide='errorStatus'])[3]").getText();
			System.out.println(errortext);
			Assert.assertEquals(errortext,"validation.msg.client.address.cannot.be.empty -"); 
			return this;
		}
	 
	public ViewClientPage createClient() {

		//			3.	"Create Client" as a Person.
		driver.findElementByXPath("//form/div//div[3]/a[@id='client_createbutton']").click();
		action.moveToElement(driver.findElementById("legalFormId_chosen")).click().build().perform();
		action.click(driver.findElementByXPath("(//li[text()='PERSON'])")).build().perform();

		//			●	Fill in basic fields like dob, display name, first name, last name, gender, client type, client classification.
		driver.findElementById("firstname").sendKeys("Testcase");
		driver.findElementById("lastname").sendKeys("sample");

		Random val = new Random();      
		int num1 = 0;
		int num2 = 0;
		int num3 = 0;
		num1 = val.nextInt(600) + 100;
		num2 = val.nextInt(641) + 100;
		num3 = val.nextInt(8999) + 1000; 
		driver.findElementById("mobileNo").sendKeys(Integer.toString(num1)+Integer.toString(num2)+Integer.toString(num3));

		driver.findElementById("dateofbirth").click();
		Date date = new Date(); 
		DateFormat sdf = new SimpleDateFormat("dd"); 
		String today = sdf.format(date);
		driver.findElementByXPath("//td/button/span[text()='"+today+"']").click();

		action.moveToElement(driver.findElementById("genderId_chosen")).click().build().perform();
		action.click(driver.findElementByXPath("(//li[text()='Female'])")).build().perform();

		action.moveToElement(driver.findElementById("clientClassificationId_chosen")).click().build().perform();
		action.click(driver.findElementByXPath("(//li[text()='Single'])")).build().perform();

		driver.findElementByXPath("//i[@uib-tooltip='Add Address']").click();		
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByName("street0")));
		driver.findElementByName("street0").sendKeys("street");

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//span[text()='Select Address Type'])[1]")));
		action.moveToElement(driver.findElementByXPath("(//span[text()='Select Address Type'])[1]")).click().build().perform();
		action.click(driver.findElementByXPath("(//li[text()='Office'])")).build().perform();

		//			●	Once create button is clicked, it directs to client details page 
		action.moveToElement(driver.findElementById("save")).click().build().perform();

		return new ViewClientPage();

	}
	//			●	(e.g., https://salesdemo.habiletechnologies.com/#/viewclient/10294 - 10294 is the client id) and click "Activate" to activate the client.


	public CreateClientPage searchText() {
		driver.findElementByXPath("//input[@data-ng-model='searchText']").sendKeys("Testcase",Keys.ENTER);
		return this;
	}
	
	public HeadOfficePage openCreateClient() {
		driver.findElementByXPath("(//tr[@class='pointer-main ng-scope'])[1]").click();
		return new HeadOfficePage();
	}

	//			●	You can also verify the created client by opening "Clients" menu -> search by first name/client id in "Find by name/mobile/client" search box or "display name" in "Filter display by name" search box.

}
