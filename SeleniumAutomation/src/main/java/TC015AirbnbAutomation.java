import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC015AirbnbAutomation {

	public static void main(String[] args) throws InterruptedException {

		//1) Go to https://www.airbnb.co.in/
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.airbnb.co.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 30);

		//2) Type Coorg in location and Select Coorg, Karnataka
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//button[@class=\"optanon-allow-all accept-cookies-button\"]")));
		driver.findElementByXPath("//button[@class=\"optanon-allow-all accept-cookies-button\"]").click();
		driver.findElementById("bigsearch-query-attached-query").sendKeys("Coorg");
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[text()='Coorg, Karnataka']")));
		driver.findElementByXPath("//div[text()='Coorg, Karnataka']").click();

		//3) Select the Start Date as June 1st and End Date as June 5th
		//		Date date = new Date();
		//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		//		String one = formatter.format(date);
		//		System.out.println(one);
		//		
		//		Calendar cal = Calendar.getInstance();
		//		
		//		cal.set(Calendar.MONTH, 6);
		//		cal.set(Calendar.DATE, 1);
		//		String strt = formatter.format(cal.getTime());
		//		System.out.println(strt);
		//		
		//		cal.add(Calendar.DAY_OF_MONTH, 4);
		//		String end = formatter.format(cal.getTime());
		//		System.out.println(end);

		Actions action = new Actions(driver);
		action.moveToElement(driver.findElementByXPath("//div[text()='Add dates']")).perform();
		driver.findElementByXPath("//td[contains(@aria-label,\"June 1, 2020\")]").click();
		driver.findElementByXPath("//td[contains(@aria-label,\"June 5, 2020\")]").click();

		//4) Select guests as 6 adults, 3 child and Click Search
		driver.findElementByXPath("//div[text()='Add guests']/parent::button").click();
		for (int i = 0; i <6; i++) {
			driver.findElementByXPath("//div[@id=\"stepper-adults\"]/button[2]").click();
		}
		String text = driver.findElementByXPath("//div[@id=\"stepper-adults\"]/div/span[1]").getText();
		System.out.println(text);
		if (text.equals("6")) {
			System.out.println("Adult count is 6");
		}

		for (int i = 0; i <3; i++) {
			driver.findElementByXPath("//div[@id=\"stepper-children\"]/button[2]").click();
		}
		String text1 = driver.findElementByXPath("//div[@id=\"stepper-children\"]/div/span[1]").getText();
		System.out.println(text1);
		if (text1.equals("3")) {
			System.out.println("Children count is 3");
		}
		driver.findElementByXPath("//button[@type='submit']").click();

		//5) Click Cancellation flexibility and enable the filter and Save
		driver.findElementByXPath("//div[@id=\"menuItemButton-flexible_cancellation\"]/button").click();
		String attribute = driver.findElementById("filterItem-switch-flexible_cancellation-true").getAttribute("aria-checked");
		if (attribute.equals("false")) {
			driver.findElementById("filterItem-switch-flexible_cancellation-true").click();
		}
		driver.findElementById("filter-panel-save-button").click();

		//6) Select Type of Place as Entire Place and Save
		Thread.sleep(2000);
		driver.findElementByXPath("//div[@id='menuItemButton-room_type']/button").click();
		boolean selected = driver.findElementByXPath("//input[@name='Entire place']/parent::span/span").isSelected();
		if (selected == false) {
			driver.findElementByXPath("//input[@name='Entire place']/parent::span/span").click();
		}
		driver.findElementById("filter-panel-save-button").click();

		//7) Set Min price as 3000 and  max price as 5000
		Thread.sleep(2000);
		driver.findElementByXPath("//div[@id=\"menuItemButton-price_range\"]/button").click();
		driver.findElementById("price_filter_min").sendKeys(Keys.CONTROL + "a");
		driver.findElementById("price_filter_min").sendKeys(Keys.DELETE);
		driver.findElementById("price_filter_min").sendKeys("3000");
		//		driver.findElementByXPath("//div[@id=\"menuItemButton-price_range\"]/button").click();
		driver.findElementById("price_filter_max").sendKeys(Keys.CONTROL + "a");
		driver.findElementById("price_filter_max").sendKeys(Keys.DELETE);
		driver.findElementById("price_filter_max").sendKeys("5000");
		driver.findElementById("filter-panel-save-button").click();

		//8) Click More Filters and set 3 Bedrooms and 3 Bathrooms
		driver.findElementByXPath("//div[@id=\"menuItemButton-dynamicMoreFilters\"]/button").click();
		Thread.sleep(5000);
		for (int i = 0; i <3; i++) {
			driver.findElementByXPath("//div[@id=\"filterItem-stepper-min_bedrooms-0\"]/button[2]").click();
		}
		String text2 = driver.findElementByXPath("//div[@id=\"filterItem-stepper-min_bedrooms-0\"]/div/span[1]").getText();
		System.out.println(text2);
		if (text2.equals("3")) {
			System.out.println("Bedroom count is 3");
		}

		for (int i = 0; i <3; i++) {
			driver.findElementByXPath("//div[@id=\"filterItem-stepper-min_bathrooms-0\"]/button[2]").click();
		}
		String text3 = driver.findElementByXPath("//div[@id=\"filterItem-stepper-min_bathrooms-0\"]/div/span[1]").getText();
		System.out.println(text3);
		if (text3.equals("3")) {
			System.out.println("Bathroom count is 3");
		}

		//9) Check the Amenities with Kitchen, Facilities with Free parking on premisses, Property as House 
		//and Host Language as English    and click on Stays only when stays available
		Thread.sleep(2000);
		driver.findElementByXPath("//div[text()='Kitchen']/ancestor::label/span[1]").click();
		driver.findElementByXPath("//input[@name=\"Free parking on premises\"]/following-sibling::span").click();
		driver.findElementByXPath("//input[@name=\"House\"]/following-sibling::span").click();
		driver.findElementByXPath("//input[@name=\"English\"]/following-sibling::span").click();
		String text4 = driver.findElementByXPath("(//footer)[2]/button").getText();
		if (!(text4.startsWith("No"))) {
			driver.findElementByXPath("(//footer)[2]/button").click();
		}

		//10) Click Prahari Nivas, the complete house
		Thread.sleep(2000);
		driver.findElementByXPath("(//a[@aria-label=\"Prahari Nivas, the complete house\"])[2]").click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> list = new ArrayList<String>(windowHandles);
		driver.switchTo().window(list.get(1));

		//11) Click on "Show all * amenities"
		Thread.sleep(15000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//*[contains(text(),'amenities')]")));
		driver.findElementByXPath("//*[contains(text(),'amenities')]").click();

		//12) Print all the Not included amenities
		Thread.sleep(5000);
		//		JavascriptExecutor js = (JavascriptExecutor)driver;
		//		js.executeScript("window.scrollBy(0,1000)");
		List<WebElement> notincluded = driver.findElementsByXPath("//div/del");
		for (int i = 3; i <= notincluded.size(); i++) {
			String text5 = driver.findElementByXPath("(//div/del)["+i+"]").getText(); 
			if (text5.length()>0) {
				System.out.println(text5);
			}
		}
		driver.findElementByXPath("//div[@aria-label=\"Amenities\"]//button[@aria-label=\"Close\"]").click();

		//13) Verify the Check-in date, Check-out date and Guests
		String checkin,checkout,guest;
		try {
			checkin = driver.findElementByXPath("//input[@id=\"checkin\"]").getAttribute("value");
		} catch (Exception e) {
			checkin = driver.findElementByXPath("(//div[text()='Check-in']/following-sibling::div)[1]").getText();
		}
		System.out.println(checkin);

		try {
			checkout = driver.findElementByXPath("//input[@id=\"checkout\"]").getAttribute("value");
		} catch (Exception e) {
			checkout = driver.findElementByXPath("//div[text()='Checkout']/following-sibling::div").getText();
		}
		System.out.println(checkout);
		try {
			guest = driver.findElementByXPath("//div[@class=\"guest-label\"]/span").getText();
		} catch (Exception e) {
			guest = driver.findElementByXPath("//div[@id=\"GuestPicker-book_it-trigger\"]/div/span").getText();
		}
		System.out.println(guest);

		if (((checkin.equals("2020-06-01") && checkout.equals("2020-06-05"))&& guest.equals("9 guests")) ||
				((checkin.equals("06/01/2020") && checkout.equals("06/05/2020"))&& guest.equals("9 guests"))) {
			System.out.println("Check-in date, Check-out date and Guests are correct");
		} else {
			System.out.println("Check-in date, Check-out date and Guests are different from what user entered");
		}
		//14) Read all the Sleeping arrangements and Print
		Thread.sleep(5000);
		List<WebElement> loopbeds = driver.findElementsByXPath("//div[contains(text(),'Bedroom')]");
		Map<String, String> map = new TreeMap<String, String>();
		System.out.println(loopbeds.size());
		for (int i = 1; i <= loopbeds.size(); i++) {
			if (i>3) {
				if (i>4) {
					try {
						driver.findElementByXPath("(//div[text()='Sleeping arrangements']/parent::h2/parent::div/following::button)[2]").click();
					} catch (Exception e) {
						driver.findElementByXPath("(//div[@data-plugin-in-point-id=\"SLEEPING_ARRANGEMENT_DEFAULT\"]//following::button)[2]").click();
					}
				} else{
					try {
						driver.findElementByXPath("(//div[text()='Sleeping arrangements']/parent::h2/parent::div/following::button)[1]").click();
					} catch (Exception e) {
						driver.findElementByXPath("(//div[@data-plugin-in-point-id=\"SLEEPING_ARRANGEMENT_DEFAULT\"]//following::button)[1]").click();
					}
				}
			}
			String key = driver.findElementByXPath("(//div[contains(text(),'Bedroom')])["+i+"]").getText();
			System.out.println(key);
			String val = driver.findElementByXPath("(//div[contains(text(),'Bedroom')])["+i+"]/following::div[1]").getText();
			System.out.println(val);
			map.put(key, val);
		}
		Set<Entry<String, String>> entrySet = map.entrySet();
		for (Entry<String, String> entry : entrySet) {
			System.out.println(entry.getKey() +" --> "+ entry.getValue());
		}
		//15) Close all the browsers
		driver.quit();
	}

}
