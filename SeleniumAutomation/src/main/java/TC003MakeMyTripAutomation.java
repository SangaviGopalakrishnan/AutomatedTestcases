import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TC003MakeMyTripAutomation {

	public static void main(String[] args) throws InterruptedException {
		
//		1)Go to https://www.makemytrip.com/
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.makemytrip.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

//		2) Click Hotels
		driver.findElementByXPath("//li[@class='menu_Hotels']/a").click();
		Thread.sleep(3000);
		
//		3) Enter city as Goa, and choose Goa, India
		driver.findElementById("city").click();
		driver.findElementByXPath("//input[@placeholder='Enter city/ Hotel/ Area/ Building']").sendKeys("Goa");
		driver.findElementByXPath("//p[text()='Goa, India']").click();
		
//		4) Enter Check in date as Next month 15th (May 15) and Check out as start date+5
		driver.findElementById("checkin").click();
		driver.findElementByXPath("(//div[@class='DayPicker-Day'])[text()='15']").click();
		driver.findElementByXPath("//div[text()='19']").click();
		
//		5) Click on ROOMS & GUESTS and click 2 Adults and one Children(age 12). Click Apply Button.
		driver.findElementById("guest").click();
		driver.findElementByXPath("//li[@data-cy='adults-2']").click();
		driver.findElementByXPath("//li[@data-cy='children-1']").click();
		WebElement age = driver.findElementByClassName("ageSelectBox");
		Select opt = new Select(age);
		opt.selectByVisibleText("12");
		driver.findElementByXPath("//button[text()='APPLY']").click();
		
//		6) Click Search button
		driver.findElementByXPath("//button[text()='Search']").click();
		Thread.sleep(3000);
		driver.findElementByXPath("//div[@class='mmBackdrop wholeBlack']").click();
		
//		7) Select locality as Baga
		driver.findElementByXPath("//label[text()='Baga']").click();
		Thread.sleep(7000);
		
//		8) Select 5 start in Star Category under Select Filters
		JavascriptExecutor js = (JavascriptExecutor) driver;	
        js.executeScript("window.scrollBy(0,1000)");
		driver.findElementByXPath("//label[text()='5 Star']").click();
		Thread.sleep(2000);
		
//		9) Click on the first resulting hotel and go to the new window
		driver.findElementByXPath("(//p[@id='hlistpg_hotel_name'])[1]").click();
		Set<String> windowHandles = driver.getWindowHandles();
		ArrayList<String> list = new ArrayList<String>(windowHandles);
		Thread.sleep(5000);
		driver.switchTo().window(list.get(1));
 		
//		10) Print the Hotel Name 
		String text = driver.findElementByXPath("//h1[@id='detpg_hotel_name']").getText();
		System.out.println("Hotel Name is "+text);
		
//		11) Click MORE OPTIONS link and Select 3Months plan and close
		driver.findElementByXPath("(//span[text()='MORE OPTIONS'])[1]").click();
//		driver.findElementByXPath("//tr[2]/td/span[text()='SELECT']").click();
		WebElement table = driver.findElementByXPath("//table[@class='tblEmiOption']");
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		for (int i = 2; i <=rows.size(); i++) {
			String rowvalue = driver.findElementByXPath("//tr["+i+"]/td[1]").getText();
			System.out.println(rowvalue);
			if (rowvalue.contains("3")) {
				driver.findElementByXPath("//tr["+i+"]/td/span[text()='SELECT']").click();
			}
		}
		driver.findElementByXPath("//span[@class='close']").click();
//		12) Click on BOOK THIS NOW
		driver.findElementByXPath("//a[text()='BOOK THIS NOW']").click();
		
//		13) Print the Total Payable amount
		driver.findElementByXPath("//span[@class='close']").click();
		String amt = driver.findElementByXPath("//span[@id='revpg_total_payable_amt']").getText().replaceAll("\\D", "");
		System.out.println("Amount Payable is "+amt);
		
//		14) Close the browser
		driver.quit();
	}
}
