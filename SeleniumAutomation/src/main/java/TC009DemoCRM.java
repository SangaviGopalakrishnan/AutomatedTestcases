import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class TC009DemoCRM {

	public static void main(String[] args) throws InterruptedException, ParseException {

		//1) Go to https://demo.1crmcloud.com/
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://demo.1crmcloud.com/");
		
		//2) Give username as admin and password as admin
		driver.findElementById("login_user").sendKeys("admin");
		driver.findElementById("login_pass").sendKeys("admin");
		
		//3) Choose theme as Claro Theme
		WebElement theme = driver.findElementById("login_theme");
		Select select = new Select(theme);
		select.selectByVisibleText("Claro Theme");
		
		boolean selected = driver.findElementByXPath("(//input[@name='user_remember'])[2]").isSelected();
		if (selected == true) {
			driver.findElementByXPath("(//input[@name='user_remember'])[2]").click();
		}
		driver.findElementByXPath("//span[text()='Login']").click();
		
		//4) Click on Sales and Marketting 
		Thread.sleep(5000);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElementByXPath("//div[text()='Sales & Marketing']")).perform();
		driver.findElementByXPath("//div[text()='Contacts']").click();
		
		//5) Click Create contact
		driver.findElementByXPath("//div[text()='Create Contact']").click();
		Thread.sleep(3000);
		
		//6) Select Title and type First name, Last Name, Email and Phone Numbers
		driver.findElementById("DetailFormsalutation-input").click();
		driver.findElementByXPath("//div[text()='Ms.']").click();
		driver.findElementById("DetailFormfirst_name-input").sendKeys("Sangavi");
		driver.findElementById("DetailFormlast_name-input").sendKeys("Gopalakrishnan");
		driver.findElementById("DetailFormemail1-input").sendKeys("hello@gmail.com");
		driver.findElementById("DetailFormphone_work-input").sendKeys("9123547802");
		
		//7) Select Lead Source as "Public Relations"
		action.moveToElement(driver.findElementById("DetailFormlead_source-input-label")).perform();
		driver.findElementByXPath("//div[text()='Public Relations']").click();
		
		//8) Select Business Roles as "Sales"
		action.moveToElement(driver.findElementById("DetailFormbusiness_role-input")).perform();
		driver.findElementByXPath("//div[text()='Sales']").click();
		
		//9) Fill the Primary Address, City, State, Country and Postal Code and click Save
		driver.findElementById("DetailFormprimary_address_street-input").sendKeys("4/32 Rajiv street,dollar");
		driver.findElementById("DetailFormprimary_address_city-input").sendKeys("Chennai");
		driver.findElementById("DetailFormprimary_address_state-input").sendKeys("TamilNadu");
		driver.findElementById("DetailFormprimary_address_country-input").sendKeys("India");
		driver.findElementById("DetailFormprimary_address_postalcode-input").sendKeys("600028");
		driver.findElementByXPath("(//span[text()='Save'])[2]").click();
		Thread.sleep(5000);
		
//		10) Mouse over on Today's Activities and click Meetings
		WebElement ele = driver.findElementByXPath("//li[@id='grouptab-0']");
		action.moveToElement(ele).perform();
		driver.findElementByXPath("//div[text()='Meetings']").click();
		Thread.sleep(4000);
		
//		11) Click Create
		driver.findElementByXPath("(//span[text()='Create'])[1]").click();
		Thread.sleep(3000);
		
//		12) Type Subject as "Project Status" , Status as "Planned" 
		driver.findElementByXPath("//input[@id='DetailFormname-input']").sendKeys("Project Status");
		String status = driver.findElementByXPath("//div[@id='DetailFormstatus-input-label']").getText();
		if (status.equals("Planned")) {
			System.out.println("Status is : "+status);
		}
		else 
			{
			System.out.println("Status is : "+status);
			driver.findElementByXPath("//div[@id='DetailFormstatus-input-label']").click();
			Thread.sleep(200);
			driver.findElementByXPath("(//div[text()='Planned'])[1]").click();
			Thread.sleep(1000);
			}
		
//		13) Start Date & Time as tomorrow 3 pm and Duration as 1hr

		Date date = new Date();  
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    String oldDate = formatter.format(date);
	    
		Calendar c = Calendar.getInstance();
		//Setting the date to the given date
		c.setTime(formatter.parse(oldDate));
		
		//Number of Days to add
		int days = 1;
		c.add(Calendar.DAY_OF_MONTH, days);  
		
		//Date after adding the days to the given date
		String newDate = formatter.format(c.getTime()); 
		
		//Displaying the new Date after addition of Days
		System.out.println("Date after Addition: "+newDate);
		
		driver.findElementByXPath("//div[@class='input-label datetime-label text-number']").click();
		Thread.sleep(200);
		driver.findElementByXPath("//div[@id='DetailFormdate_start-calendar-text']//input").sendKeys(newDate,Keys.ENTER);
		Thread.sleep(200);
		driver.findElementByXPath("//div[@id='DetailFormdate_start-calendar-text']//input").sendKeys("15:00",Keys.ENTER);
		Thread.sleep(200);
		
//		14) Click Add paricipants, add your created Contact name and click Save
		driver.findElementByXPath("//span[text()=' Add Participants']").click();
		Thread.sleep(200);
		String srchtxt = "Sangavi";
		driver.findElementByXPath("//div[@id='app-search-text']//input").sendKeys(srchtxt);
		Thread.sleep(200);
		driver.findElementByXPath("(//span[text()='Search Results (Invitees)']/ancestor::div[@class='card-header panel-subheader'])/following-sibling::div//div[contains(text(),'" + srchtxt + "')]").click();
		Thread.sleep(4000);
		driver.findElementByXPath("(//span[text()='Save'])[2]").click();
		Thread.sleep(4000);
		
//		15) Go to Sales and Marketting-->Contacts
		WebElement ele2 = driver.findElementByXPath("//div[text()='Sales & Marketing']");
		action.moveToElement(ele2).perform();
		Thread.sleep(1000);
		driver.findElementByXPath("//div[text()='Contacts']").click();
		Thread.sleep(1000);
		
//		16) search the lead Name and click the name from the result
		driver.findElementByXPath("//input[@id='filter_text']").sendKeys(srchtxt);
		Thread.sleep(1000);
		driver.findElementByXPath("//input[@id='filter_text']").sendKeys(Keys.ENTER);
		Thread.sleep(1400);
		driver.findElementByXPath("//td[@class='listViewTd']//a[contains(text(),'"+ srchtxt + "')]").click();
		Thread.sleep(4000);
		
//		17) Check weather the Meeting is assigned to the contact under Activities Section.
		List<WebElement> tr = driver.findElementsByXPath("//span[@id='subpanel-activities']//ancestor::div[@class='card-outer panel-outer listview-panel panel-system panel-border']//table//tbody//tr");
		
		for (int i = 0;i<tr.size();i++)
		{
			List<WebElement> td = tr.get(i).findElements(By.tagName("td"));
			System.out.println(td.get(4).getText());
			if (td.get(4).getText().contains("Planned")) System.out.println("Matching");
			else System.out.println("Not Matching");
		}
		
		driver.close();
		
	}

}
