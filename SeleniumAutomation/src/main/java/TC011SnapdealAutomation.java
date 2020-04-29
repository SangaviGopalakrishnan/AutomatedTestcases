import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC011SnapdealAutomation {

	public static void main(String[] args) throws InterruptedException, IOException {

		//1) https://www.snapdeal.com/

		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		//2) Mouse over on Toys, Kids' Fashion & more and click on Toys
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElementByXPath("//span[text()=\"Toys, Kids' Fashion & more\"]")).build().perform();
		driver.findElementByXPath("//span[text()='Toys']").click();
		Thread.sleep(5000);

		//3) Click Educational Toys in Toys & Games
		driver.findElement(By.xpath("//div[text()=\"Electronic Toys\"]")).click();
		Thread.sleep(5000);

		//4) Click the Customer Rating 4 star and Up 
		WebDriverWait wait = new WebDriverWait(driver, 10);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//label[@for='avgRating-4.0']")));
		driver.findElementByXPath("//label[@for='avgRating-4.0']").click();
		Thread.sleep(5000);

		//5) Click the offer as 40-50
		js.executeScript("window.scrollBy(0,1000)");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//label[@for='discount-40%20-%2050']")));
		driver.findElementByXPath("//label[@for='discount-40%20-%2050']").click();
		Thread.sleep(1000);

		//6) Check the availability for the pincode
		WebElement pincode = driver.findElementByXPath("//input[@placeholder=\"Enter your pincode\"]");
		pincode.click();
		pincode.sendKeys("600073");
		driver.findElementByXPath("//button[text()='Check']").click();
		Thread.sleep(5000);

		//7) Click the Quick View of the first product 
		action.moveToElement(driver.findElementByXPath("(//p[@class=\"product-title\"])[1]")).build().perform();
		driver.findElementByXPath("(//div[@class=\"clearfix row-disc\"]/div)[1]").click();
		Thread.sleep(5000);

		//8) Click on View Details
		driver.findElementByXPath("//a[@class=\" btn btn-theme-secondary prodDetailBtn\"]").click();
		Thread.sleep(5000);

		//9) Capture the Price of the Product and Delivery Charge
		int price = Integer.parseInt(driver.findElementByXPath("//span[@itemprop=\"price\"]").getText());
		int deliverycharge = Integer.parseInt(driver.findElementByXPath("(//span[@class=\"availCharges\"])[2]").getText().replaceAll("\\D", ""));
		int total = price+ deliverycharge;

		//10) Validate the You Pay amount matches the sum of (price+deliver charge)
		driver.findElementByXPath("//span[text()='add to cart']").click();
		Thread.sleep(5000);
		int actualamt = Integer.parseInt(driver.findElementByXPath("(//span[@class=\"price\"])[2]").getText().replaceAll("\\D", ""));
		if (total == actualamt) {
			System.out.println("Pay amount matches the sum of (price+deliver charge)");
		}else {
			System.out.println("Pay amount doesnot matches the sum of (price+deliver charge)");
		}

		//11) Search for Sanitizer
		driver.findElementById("inputValEnter").sendKeys("Sanitizer",Keys.ENTER);

		//12) Click on Product "BioAyurveda Neem Power Hand Sanitizer"
		driver.findElementByXPath("//p[contains(@title,'BioAyurveda Neem Power  Hand Sanitizer')]").click();
		Thread.sleep(5000);
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> list = new ArrayList<String>(windowHandles);
		driver.switchTo().window(list.get(1));

		//13) Capture the Price and Delivery Charge
		int price1 = Integer.parseInt(driver.findElementByXPath("//span[@itemprop=\"price\"]").getText());
		int deliverycharge1 = Integer.parseInt(driver.findElementByXPath("(//span[@class=\"availCharges\"])[2]").getText().replaceAll("\\D", ""));
		int total1 = price1+ deliverycharge1;

		//14) Click on Add to Cart
		Thread.sleep(5000);
		driver.findElementByXPath("(//span[text()='ADD TO CART'])[1]").click();

		//15) Click on Cart 
		Thread.sleep(5000);
		driver.findElementByXPath("//span[text()='Cart']").click();

		//16) Validate the Proceed to Pay matches the total amount of both the products
		int amt = total+total1;
		int amtretrved = Integer.parseInt(driver.findElementByXPath("//input[@class='btn btn-xl rippleWhite cart-button']").getAttribute("value").replaceAll("\\D", ""));
		if (amt == amtretrved) {
			System.out.println("Proceed to Pay matches the total amount of both the products");
		}else {
			System.out.println("Proceed to Pay doesnot matches the total amount of both the products");
		}

		//17) Close all the windows
		driver.quit();
	}
}