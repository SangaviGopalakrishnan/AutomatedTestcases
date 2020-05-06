import static org.testng.Assert.expectThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC015AjioAutomation {

	public static void main(String[] args) throws InterruptedException {

		//1)Go to https://www.ajio.com/
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.ajio.com/shop/sale");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 30);

		//2) Enter Bags in the Search field and Select Bags in Women Handbags
		driver.findElementByName("searchVal").sendKeys("Bags");
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//span[text()='Bags in '])[1]")));
		driver.findElementByXPath("(//span[text()='Bags in '])[1]").click();
		
		//3) Click on five grid and Select SORT BY as "What's New"
		driver.findElementByXPath("//div[@class=\"five-grid\"]").click();
		WebElement ele = driver.findElementByXPath("//div[@class=\"filter-dropdown\"]/select");
		Select option = new Select(ele);
		option.selectByVisibleText("What's New");
		Thread.sleep(5000);
		
		//4) Enter Price Range Min as 2000 and Max as 5000
		driver.findElementByXPath("//span[text()='price']").click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElementById("minPrice")));
		driver.findElementById("minPrice").sendKeys("2000");
		driver.findElementById("maxPrice").sendKeys("5000");
		driver.findElementByXPath("//button[@class=\"rilrtl-button ic-toparw\"]").click();
		
		//5) Click on the product "Puma Ferrari LS Shoulder Bag"
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,-1000)");
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[text()='Ferrari LS Shoulder Bag']")));
		Actions action = new Actions(driver);
		Thread.sleep(5000);
		driver.findElementByXPath("//div[text()='Ferrari LS Shoulder Bag']").click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> list = new ArrayList<String>(windowHandles);
		driver.switchTo().window(list.get(1));
		
		//6) Verify the Coupon code for the price above 2690 is applicable for your product, 
		//if applicable the get the Coupon Code and Calculate the discount price for the coupon
		
		int actualprice = Integer.parseInt(driver.findElementByXPath("//div[@class=\"prod-sp\"]").getText().replaceAll("\\D", ""));
		String couponcode ="";
		if (actualprice > 2690) {
//			couponcode = driver.findElementByXPath("//div[@class=\"promo-title\"]/text()[preceding-sibling::br]").getText();
//			WebElement coupon = driver.findElementByXPath("//div[@class=\"promo-title\"]//following-sibling::text()");
//			WebElement coupon = driver.findElementByXPath("//div[@class=\"promo-title\"]/child::node()[3]");
			couponcode = driver.findElementByXPath("//div[@class=\"promo-title\"]").getText().substring(9);
			System.out.println(couponcode);
		}
		String text = driver.findElementByXPath("//div[@class=\"promo-desc\"]").getText();
		String[] split = text.split(" ");
		String per = "";
		for (int i = 0; i <split.length; i++) {
			String s = split[i];
			if (s.endsWith("%")) {
				 per = split[i];
			}
		}
		int actualpercentage = Integer.parseInt(per.replaceAll("\\D", ""));
		float discount = actualprice*actualpercentage/100.0f;
		
		//7) Check the availability of the product for pincode 560043, print the expected delivery date if it is available
		driver.findElementByXPath("//span[text()='Enter pin-code to know estimated delivery date.']").click();
		driver.findElementByName("pincode").sendKeys("560043");
		driver.findElementByXPath("//button[text()='CONFIRM PINCODE']").click();
		System.out.println("Expected delivery date --> "+driver.findElementByXPath("//span[@class=\"edd-message-success-details-highlighted\"]").getText());
		
		//8) Click on Other Informations under Product Details and Print the Customer Care address, phone and email
		js.executeScript("window.scrollBy(0,500)");
		driver.findElementByXPath("//div[text()='Other information']").click();
		Thread.sleep(5000);
		js.executeScript("window.scrollBy(0,500)");
		String CCA = driver.findElementByXPath("//span[text()='Customer Care Address']/following::span[2]").getText();
		System.out.println(CCA);
		Thread.sleep(5000);
		
		//9) Click on ADD TO BAG and then GO TO BAG
		js.executeScript("window.scrollBy(0,-1000)");
		Thread.sleep(2000);
		driver.findElementByXPath("//span[text()='ADD TO BAG']").click();
		Thread.sleep(5000);
		driver.findElementByXPath("//div[@class=\"ic-cart \"]").click();
		
		//10) Check the Order Total before apply coupon
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[@class=\"price-value bold-font\"]")));
		int before = Integer.parseInt(driver.findElementByXPath("//span[@class=\"price-value bold-font\"]").getText().replaceAll("\\D", "").substring(0,4));
		if (actualprice == before) {
			System.out.println("Order Total before apply coupon are equal");
		}else {
			System.out.println("Order Total before apply coupon are not equal");
		}
		
		//11) Enter Coupon Code and Click Apply
		driver.findElementById("couponCodeInput").sendKeys(couponcode);
		driver.findElementByXPath("//button[text()='Apply']").click();
		Thread.sleep(5000);
		
		//12) Verify the Coupon Savings amount(round off if it in decimal) under
		//Order Summary and the matches the amount calculated in Product details
		float ordersum = Float.parseFloat(driver.findElementByXPath("//div[@class=\"net-price best-price-strip\"]").getText().replaceAll("Rs. ", "").replace(",", ""));
		float amtcal = actualprice - discount;
		if (ordersum == amtcal) {
			System.out.println("Order Summary matches the amount calculated in Product details");
		}else {
			System.out.println("Order Summary differs from the amount calculated in Product details");
		}
		//13) Click on Delete and Delete the item from Bag
		driver.findElementByXPath("//div[@class=\"delete-btn\"]").click();
		driver.findElementByXPath("(//div[@class=\"delete-btn\"])[2]").click();
		
		//14) Close all the browsers
		driver.quit();
	}

}
