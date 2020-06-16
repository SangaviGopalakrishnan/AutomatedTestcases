import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC019ShopCluesAutomation {

	public static void main(String[] args) throws InterruptedException {

		//1) Go to https://www.shopclues.com/
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
//		DesiredCapabilities dc = new DesiredCapabilities();
//		dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.shopclues.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		Actions action = new Actions(driver);
		
		
		try {
			driver.findElementByXPath("//button[text()=\"Don't Allow\"]").click();
		} catch (Exception e) {
			System.out.println("Alert not found");
		}

		//2) Mouseover on women and click Casual Shoes
		Thread.sleep(1000);
		action.click(driver.findElementByXPath("//a[text()='WOMEN']")).build().perform();
		action.click(driver.findElementByXPath("//a[text()='Casual Shoes']")).build().perform();
		Set<String> windowHandles = driver.getWindowHandles();
		ArrayList<String> arr = new ArrayList<>(windowHandles);
		driver.switchTo().window(arr.get(1));
		
		//3) Select Color as Black
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,3000)");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click()", driver.findElementByXPath("//label[@for='Black']"));
		Thread.sleep(5000);
		
		//4) Check whether the product name contains the word black
		//5) If so, add the product name and price in to Map
		List<WebElement> products = driver.findElementsByXPath("//span[@class=\"prod_name \"]");
		TreeMap<Integer,String> map = new TreeMap<Integer,String>();
		System.out.println(products.size());
		for (int i = 1; i <= products.size() ; i++) {
			String name = driver.findElementByXPath("(//span[@class=\"prod_name \"])["+i+"]").getText();
			if (name.contains("Black")) {
				int price = Integer.parseInt(driver.findElementByXPath("(//span[@class=\"p_price\"])["+i+"]").getText().replace("Rs.", ""));
				map.put(price, name);
			}
		}
		
		//6) Check Display the count of shoes which are more than 500 rupees
		Set<Entry<Integer, String>> entrySet = map.entrySet();
		for (Entry<Integer, String> entry : entrySet) {
			if (entry.getKey() > 500) {
				System.out.println(entry.getKey() +" ----> "+entry.getValue());
			}
		}
		
		Entry<Integer, String> lastEntry = map.lastEntry();
		String largestname = lastEntry.getValue();
		System.out.println("Large -->"+ largestname);
		
		//7) Click the highest price of the shoe
		driver.findElementByXPath("//span[contains(text(),\""+largestname+"\")]").click();
		Thread.sleep(5000);
		
		//8) Get the current page URL and check with the product ID
		Set<String> windowHandles1 = driver.getWindowHandles();
		ArrayList<String> arr1 = new ArrayList<>(windowHandles1);
		driver.switchTo().window(arr1.get(2));
		String urlidd = driver.getCurrentUrl().replaceAll("\\D", "");
		String id = driver.findElementByXPath("//span[@class=\"pID\"]").getText().replaceAll("\\D", "");
		if (urlidd.equals(id)) {
			System.out.println("Id matches");
		} else {
			System.out.println("Id not matched");
		}
		
		//9) Copy the offercode 
		String coupon = driver.findElementByXPath("//div[@class=\"coupons_code\"]/span").getText();
		
		//10) Select size, color and click ADD TO CART
		driver.findElementByXPath("//span[text()='40 (EUR)']").click();
		driver.findElementById("add_cart").click();
		
		//11) Mouse over on Shopping cart and click View Cart 
		action.moveToElement(driver.findElementByXPath("//a[@class=\"cart_ic\"]")).build().perform();
		action.click(driver.findElementByXPath("//a[text()='View Cart']")).build().perform();
		Thread.sleep(5000);
		
		//12) Type Pincode as 600016 click Submit and Place Order
		driver.findElementByXPath("//input[@id=\"pin_code_popup\"]").sendKeys("600016");
		driver.findElementByXPath("//input[@id=\"get_pincode_popup\"]").click();
		driver.findElementByXPath("//div[text()='Place Order']").click();
		
		//13) Close the Browser
		driver.quit();
	}
}
