import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class TC002NykaaAutomation{

	public static void main(String[] args) throws InterruptedException {
		
//		1) Open https://www.nykaa.com/
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions coptions = new ChromeOptions();
		coptions.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(coptions);
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
//		2)MouseOver on brands and mouseover on popular
		WebElement brands = driver.findElementByXPath("//a[text()='brands']");
		Actions action = new Actions(driver);
		action.moveToElement(brands).perform();
		action.moveToElement(driver.findElementByXPath("//a[text()='Popular']")).perform();
	
//		3) Click L'Oreal Paris
		driver.findElementByXPath("//a[@href='/brands/loreal-paris/c/595?eq=desktop']").click();
		Set<String> windowHandles = driver.getWindowHandles();
		ArrayList<String> list = new ArrayList<String>(windowHandles);
		Thread.sleep(5000);
		driver.switchTo().window(list.get(1));

//		4) Go to the newly opened window and check the title contains L'Oreal Paris
		String title = driver.getTitle();
		if (title.contains("Paris")) {
			System.out.println("Correct Title");
		} else {
			System.out.println("Incorrect Title");
		}
		
//		5) Click sort By and select customer top rated		
		WebElement sort = driver.findElementByXPath("//span[@class='pull-left']");
		Actions action1 = new Actions(driver);
		action1.moveToElement(sort).click().perform();
		driver.findElementByXPath("//span[text()='customer top rated']//following-sibling::div").click();
		Thread.sleep(5000);
		
//		6) Click Category and click Shampoo
		driver.findElementByXPath("//div[text()='Category']").click();
		driver.findElementByXPath("//span[contains(text(),'Shampoo (21)')]//following-sibling::div").click();
		Thread.sleep(5000);
		
//		7) check whether the Filter is applied with Shampoo
		String text = driver.findElementByXPath("//ul[@class='pull-left applied-filter-lists']/li[1]").getText();
		if (text.contains("Shampoo")) {
			System.out.println("Shampoo Filter applied");
		} else {
			System.out.println("Shampoo Filter not applied");
		}
		
//		8) Click on L'Oreal Paris Colour Protect Shampoo
		driver.findElementByXPath("//h2[@title=\"L'Oreal Paris Colour Protect Shampoo\"]").click();
		
//		9) GO to the new window and select size as 175ml
		Set<String> windowHandles1 = driver.getWindowHandles();
		ArrayList<String> list1 = new ArrayList<String>(windowHandles1);
		Thread.sleep(5000);
		driver.switchTo().window(list1.get(2));
		driver.findElementByXPath("//span[text()='175ml']").click();
		
//		10) Print the MRP of the product
		String price = driver.findElementByXPath("//span[@class='post-card__content-price-offer']").getText().replaceAll("\\D", "");
		System.out.println("Price is "+ price);
		
//		11) Click on ADD to BAG
		driver.findElementByXPath("//button[text()='ADD TO BAG']").click();
		
//		12) Go to Shopping Bag 
		driver.findElementByXPath("//div[@class='AddBagIcon']").click();
		
//		13) Print the Grand Total amount
		String grandtotal = driver.findElementByXPath("//div[@class='sticky-bottom proceed-cart-btn']//div[@class='value']").getText().replaceAll("\\D", "");
		System.out.println("Grand Total is "+grandtotal);
		
//		14) Click Proceed
		driver.findElementByXPath("//button[@class='btn full fill no-radius proceed ']").click();
		Thread.sleep(5000);
		
//		15) Click on Continue as Guest
		driver.findElementByXPath("//button[@class='btn full big']").click();
		
//		16) Print the warning message (delay in shipment)
		String message = driver.findElementByXPath("//div[@class='message']").getText();
		System.out.println("Message is "+message);
		
//		17) Close all windows	
		driver.quit();
	}
}
