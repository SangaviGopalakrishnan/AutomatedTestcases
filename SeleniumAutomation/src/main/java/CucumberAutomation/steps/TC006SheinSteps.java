package steps;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TC006SheinSteps{

	public static ChromeDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js ;
	List<Integer> sortedlist;
	List<Integer> list;
	Actions action;

	@Given("User launches browser for Shein")
	public void user_launches_browser_for_Shein() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,UnexpectedAlertBehaviour.DISMISS);
		option.merge(dc);
		 driver = new ChromeDriver(option);
		driver.get("https://www.shein.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 30);
		action = new Actions(driver);
		try {
			driver.findElementByXPath("//div[@class=\"c-coupon-box\"]/i").click();
		} catch (Exception e) {
			System.out.println("Alert not appeared");
		}  
	}

	@Given("User mouse hovers on Clothing and click Jeans")
	public void user_mouse_hovers_on_Clothing_and_click_Jeans() throws InterruptedException {
		Thread.sleep(5000);
		action.moveToElement(driver.findElementByXPath("//span[text()='CLOTHING']")).build().perform();
		Thread.sleep(2000); 
		action.click(driver.findElementByXPath("//a[@title=\"Jeans\"]")).build().perform();
		Thread.sleep(2000); 
	}

	@Given("User chooses Black under Jeans product count")
	public void user_chooses_Black_under_Jeans_product_count() throws InterruptedException {
		Thread.sleep(15000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//a[text()='Black']")));
		driver.findElementByXPath("//a[text()='Black']").click();
	}

	@Given("User checks size as medium")
	public void user_checks_size_as_medium() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)");
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='Size']")));
		driver.findElementByXPath("//span[text()='Size']").click();
		driver.findElementByXPath("//a[text()[normalize-space()=\"M\"]]/span").click();
	}


	@Given("User checks whether the color is black")
	public void user_checks_whether_the_color_is_black() throws InterruptedException{
		Thread.sleep(15000);
		String color = driver.findElementByXPath("//a[text()='Black']").getText();
		System.out.println(color);
		if (color.equalsIgnoreCase("Black")) {
			System.out.println("Color selected is black");
		} else {
			System.out.println("Color selected is not black");
		}
	}

	@Given("User clicks first item to Add to Bag")
	public void user_clicks_first_item_to_Add_to_Bag() throws InterruptedException{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,400)");
		Thread.sleep(5000);
		action.moveToElement(driver.findElementByXPath("(//div[@class=\"c-goodsitem__ratiowrap\"])[1]")).build().perform();
		action.click(driver.findElementByXPath("(//button[text()[normalize-space()=\"+ Add to Bag\"]])[1]")).build().perform();
	}

	@Given("User clicks the size as M abd click Submit")
	public void user_clicks_the_size_as_M_abd_click_Submit() {
		action.click(driver.findElementByXPath("//span[text()[normalize-space()='M']]")).build().perform();
		action.click(driver.findElementByXPath("(//button[text()[normalize-space()='Submit']])[1]")).build().perform();
	}

	@When("User clicks view Bag")
	public void user_clicks_view_Bag() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-400)");
		action.moveToElement(driver.findElementByXPath("//div[@class=\"header-right-dropdown-ctn header-right-no-relative fast-cart-wrap j-fast-cart\"]")).build().perform();
	}

	@Then("User checks the size is Medium or not and closes the browser")
	public void user_checks_the_size_is_Medium_or_not_and_closes_the_browser() {
		String size = driver.findElementByXPath("//div[@class=\"goods-size\"]/span").getText().substring(5);
		if (size.equals("M")) {
			System.out.println("Size is correct");
		} else {
			System.out.println("Size is not correct");
		}
		driver.quit();
	}

}
