import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC017TrivagoAutomation {

	public static void main(String[] args) throws InterruptedException {

		//1) Go to https://www.trivago.com/
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.trivago.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 30);

		//2) Type Agra in Destination and select Agra, Uttar Pradesh.
		driver.findElementById("querytext").sendKeys("Agra");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='City - Uttar Pradesh, India']")));
		driver.findElementByXPath("//span[text()='City - Uttar Pradesh, India']").click();
		Actions action = new Actions(driver);

		//			3) Choose May 15 as check in and May 30 as check out
		Thread.sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)");
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//time[@datetime=\"2020-06-25\"]")));
		driver.findElementByXPath("//time[@datetime=\"2020-06-25\"]").click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//time[@datetime=\"2020-06-27\"]")));
		driver.findElementByXPath("//time[@datetime=\"2020-06-27\"]").click();
		Thread.sleep(1000);

		//			4) Select Room as Family Room
		driver.findElementByXPath("//span[text()='Family rooms']").click();

		//			5) Choose Number of Adults 2, Childern 1 and set Child's Age as 4
		WebElement ele = driver.findElementByXPath("//select[@id=\"select-num-adults-2\"]");
		Select sel = new Select(ele);
		sel.selectByVisibleText("2");
		WebElement child = driver.findElementByXPath("//select[@id=\"select-num-children-2\"]");
		Select sel2 = new Select(child);
		sel2.selectByVisibleText("1");
		WebElement age = driver.findElementByXPath("//select[@id=\"select-ages-children-2-3\"]");
		Select sel3 = new Select(age);
		sel3.selectByVisibleText("4");

		//			6) Click Confirm button and click Search
		driver.findElementByXPath("//span[text()='Confirm']").click();

		//			7) Select Accommodation type as Hotels only and choose 4 stars
		action.moveToElement(driver.findElementByXPath("//strong[text()='Accommodation']")).build().perform();
		action.click(driver.findElementByXPath("//input[@value=\"Hotels only\"]")).build().perform();
		action.click(driver.findElementByXPath("//button[@title=\"4-star hotels\"]")).build().perform();

		//			8) Select Guest rating as Very Good
		action.moveToElement(driver.findElementByXPath("//strong[text()='Guest rating']")).build().perform();
		action.click(driver.findElementByXPath("//span[text()='Very good']")).build().perform();

		//			9) Set Hotel Location as Agra Fort and click Done
		action.moveToElement(driver.findElementByXPath("//strong[text()='Hotel location']")).build().perform();
		WebElement location = driver.findElementByXPath("//select[@id=\"pois\"]");
		Select loc = new Select(location);
		loc.selectByVisibleText("Agra Fort");
		Thread.sleep(1000);
		driver.findElementByXPath("//button[text()='Done']").click();

		//			10) In more Filters, select Air conditioning, Restaurant and WiFi and click Done
		action.moveToElement(driver.findElementByXPath("//strong[text()='More filters']")).build().perform();
		action.click(driver.findElementByXPath("//label[text()='Air conditioning']/parent::li/div/input")).build().perform();
		action.click(driver.findElementByXPath("//label[text()='Restaurant']/parent::li/div/input")).build().perform();
		action.click(driver.findElementByXPath("//label[text()='WiFi']/parent::li/div/input")).build().perform();
		driver.findElementByXPath("//button[text()='Done']").click();

		//			11) Sort the result as Rating & Recommended
		Thread.sleep(1000);
		WebElement sortby = driver.findElementByXPath("//select[@id=\"mf-select-sortby\"]");
		Select sort = new Select(sortby);
		sort.selectByVisibleText("Rating & Recommended");

		//			12) Print the Hotel name, Rating, Number of Reviews and Click View Deal
		String Hotelname = driver.findElementByXPath("//span[@dir=\"ltr\"]").getText();
		System.out.println("Hotelname ---> "+Hotelname);
		String rating = driver.findElementByXPath("//span[@itemprop=\"ratingValue\"]").getText();
		System.out.println("Rating ---> "+ rating);
		String review = driver.findElementByXPath("//strong[@class=\"item__rating-number\"]/parent::span").getText();
		System.out.println("Review ---> "+review);
		driver.findElementByXPath("//span[text()='View Deal']/parent::span/parent::button").click();
		Thread.sleep(5000);

		//			13) Print the URL of the Page
		Set<String> windowHandles = driver.getWindowHandles();
		ArrayList<String> arr = new ArrayList<>(windowHandles);
		driver.switchTo().window(arr.get(1));
		System.out.println("URL --> "+driver.getCurrentUrl());

		//			14) Print the Price of the Room and click Choose Your Room
		String hotelPrice = driver.findElementByXPath("//div[@class='price-start ']//strong").getText(); 
		System.out.println("Hotel Price: " + hotelPrice); 

		//			15) Click Reserve and I'll Reserve
		driver.findElementByLinkText("BOOK NOW").click(); 
		driver.findElementByXPath("//button[@class='gotobook']").click();
		Thread.sleep(5000);

		//			16) Close the browser
		driver.quit();
	}
}
