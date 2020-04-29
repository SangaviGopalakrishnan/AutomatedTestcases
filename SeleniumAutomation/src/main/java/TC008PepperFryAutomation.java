import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TC008PepperFryAutomation {

	public static void main(String[] args) throws InterruptedException, IOException {

		//1) Go to https://www.pepperfry.com/
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		options.merge(cap);
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.pepperfry.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		try {
			driver.findElementByXPath("//div[@id=\"regPopUp\"]/a").click();
			Thread.sleep(2000);
			driver.findElementByClassName("close").click();
		} catch (Exception e) {
			System.out.println("Not thrown");
		}

		//2) Mouseover on Furniture and click Office Chairs under Chairs
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElementByXPath("//a[@rel='meta-furniture']")).perform();
		driver.findElementByXPath("//div[text()='Chairs']/preceding::div[@class=\"hvr-col-listitem\"]/a[text()='Office Chairs']").click();
		Thread.sleep(5000);
		
		//3) click Executive Chairs
		driver.findElementByXPath("//h5[text()='Executive Chairs']").click();
		Thread.sleep(5000);
		
		//4) Change the minimum Height as 50 in under Dimensions
		WebElement minheight = driver.findElementByXPath("(//input[@class='clipFilterDimensionHeightValue'])[1]");
		minheight.clear();
		minheight.click();
		minheight.sendKeys("50",Keys.ENTER);
		Thread.sleep(5000);
		
		int itemadded = 0;
		//5) Add "Poise Executive Chair in Black Colour" chair to Wishlist
		action.moveToElement(driver.findElementByXPath("//a[text()='Poise Executive Chair in Black Colour']")).perform();
		driver.findElementByXPath("(//a[text()='Poise Executive Chair in Black Colour']/ancestor::div[@class='clip-crd-10x11 pf-white padding-2x1']/div/div/a)[1]").click();
		driver.findElementByXPath("//a[contains(text(),'Poise')]/ancestor::div[@class='item_details']/div/div/a").click();
		driver.findElementByXPath("//a[@class='gb-close pf-icon pf-icon-close']").click();
		itemadded++;
		Thread.sleep(5000);
		
		//6) Mouseover on Homeware and Click Pressure Cookers under Cookware
		action.moveToElement(driver.findElementByXPath("//a[@rel='meta-homeware']")).perform();
		driver.findElementByXPath("//div[text()='Cookware']/parent::div/div[2]/div/a[text()='Pressure Cookers']").click();
		
		//7) Select Prestige as Brand
		driver.findElementByXPath("//label[text()='Prestige']").click();
		Thread.sleep(5000);
		
		//8) Select Capacity as 1-3 Ltr
		driver.findElementByXPath("//label[text()='1 Ltr - 3 Ltr']").click();
		Thread.sleep(5000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		
		//9) Add "Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr" to Wishlist
		action.moveToElement(driver.findElementByXPath("//a[text()='Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr']")).perform();
		driver.findElementByXPath("(//a[text()='Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr']/ancestor::div[@class='clip-crd-10x11 pf-white padding-2x1']/div/div/a)[1]").click();
		driver.findElementByXPath("//a[contains(text(),'Nak')]/ancestor::div[@class='item_details']/div/div/a").click();
		itemadded++;
		
		//10) Verify the number of items in Wishlist
		//11) Navigate to Wishlist
		Thread.sleep(5000);
		driver.findElementByXPath("//a[@id='mini-userwishlist-tab']").click();
		Thread.sleep(5000);
		
		int item = Integer.parseInt(driver.findElementByXPath("//span[@id=\"wishlist_mini_cart\"]").getText());
		System.out.println(item);
		if (item == itemadded) {
			System.out.println("Number of items is same");
		} else {
			System.out.println("Number of items is not same");
		}
		Thread.sleep(5000);
		
		//12) Move Pressure Cooker only to Cart from Wishlist
		driver.findElementByXPath("//a[@data-tooltip='Compact view']").click();
		driver.findElementByXPath("//a[contains(text(),'Nak')]/ancestor::div[@class='item_details']/div/div/a").click();
		Thread.sleep(5000);
		
		//13) Check for the availability for Pincode 600128
		WebElement pin = driver.findElementByXPath("//input[@class='srvc_pin_text']");
		pin.click();
		pin.sendKeys("600128");
		driver.findElementByXPath("//a[@class='check_available']").click();
		Thread.sleep(5000);
		
		//14) Click Proceed to Pay Securely
		driver.findElementByXPath("//a[text()='Proceed to pay securely ']").click();
		
		//15 Click Proceed to Pay
		driver.findElementByXPath("(//a[text()='PLACE ORDER'])[1]").click();
		Thread.sleep(5000);
		
		//16) Capture the screenshot of the item under Order Item
		driver.findElementByXPath("//div[@class='nCheckout__accrodian-header-left']").click();
		File src = driver.findElementByXPath("//div[@class='slick-list draggable']//li").getScreenshotAs(OutputType.FILE);
		File dest = new File("./snaps/pepper.png");
		FileUtils.copyFile(src, dest);
		
		//17) Close the browser
		driver.close();
	}

}
