import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
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

public class TC018SheinAutomation {

	public static void main(String[] args) throws InterruptedException {

		//1) open https://www.shein.in/
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,UnexpectedAlertBehaviour.DISMISS);
		option.merge(dc);
		ChromeDriver driver = new ChromeDriver(option);
		driver.get("https://www.shein.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		Actions action = new Actions(driver);
		try {
			driver.findElementByXPath("//div[@class=\"c-coupon-box\"]/i").click();
		} catch (Exception e) {
			System.out.println("Alert not appeared");
		}

		//2) Mouseover on Clothing and click Jeans
		action.moveToElement(driver.findElementByXPath("//span[text()='CLOTHING']")).build().perform();
		action.click(driver.findElementByXPath("//a[@title=\"Jeans\"]")).build().perform();
		
		//3) Choose Black under Jeans product count
		Thread.sleep(15000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//a[text()='Black']")));
		driver.findElementByXPath("//a[text()='Black']").click();
		
		//4) check size as medium
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)");
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='Size']")));
		driver.findElementByXPath("//span[text()='Size']").click();
		driver.findElementByXPath("//a[text()[normalize-space()=\"M\"]]/span").click();
		
		//6) check whether the color is black
		Thread.sleep(15000);
		String color = driver.findElementByXPath("//a[text()='Black']").getText();
		System.out.println(color);
		if (color.equalsIgnoreCase("Black")) {
			System.out.println("Color selected is black");
		} else {
			System.out.println("Color selected is not black");
		}
		
		//7) Click first item to Add to Bag
		js.executeScript("window.scrollBy(0,400)");
		Thread.sleep(5000);
		action.moveToElement(driver.findElementByXPath("(//div[@class=\"c-goodsitem__ratiowrap\"])[1]")).build().perform();
//		try {
//			action.click(driver.findElementByXPath("(//button[text()[normalize-space()=\"Cancel\"]])[1]")).build().perform();
//		} catch (Exception e) {
//			System.out.println("Button not present");
//		}
		action.click(driver.findElementByXPath("(//button[text()[normalize-space()=\"+ Add to Bag\"]])[1]")).build().perform();
		
		//8) Click the size as M abd click Submit
		action.click(driver.findElementByXPath("//span[text()[normalize-space()='M']]")).build().perform();
		action.click(driver.findElementByXPath("(//button[text()[normalize-space()='Submit']])[1]")).build().perform();
		
		//9) Click view Bag 
		js.executeScript("window.scrollBy(0,-400)");
		action.moveToElement(driver.findElementByXPath("//div[@class=\"header-right-dropdown-ctn header-right-no-relative fast-cart-wrap j-fast-cart\"]")).build().perform();
		
		//10) Check the size is Medium or not.
		String size = driver.findElementByXPath("//div[@class=\"goods-size\"]/span").getText().substring(5);
		if (size.equals("M")) {
			System.out.println("Size is correct");
		} else {
			System.out.println("Size is not correct");
		}
		
		//11) Close the browser.
		driver.quit();
	}
}
