package steps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TC003Ajio2Steps{

	public static ChromeDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js = (JavascriptExecutor) driver;
	List<Integer> sortedlist;
	List<Integer> list;
	Actions action;

	@Given("User opens the browser")
	public void user_opens_the_browser() {
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.get("https://www.ajio.com/shop/sale");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 30);
		action = new Actions(driver);
	}

	@Given("User hovers the mouse on Women, CATEGORIES and click on Kurtas")
	public void user_hovers_the_mouse_on_Women_CATEGORIES_and_click_on_Kurtas() throws InterruptedException {
		Thread.sleep(5000);
		action.moveToElement(driver.findElementByXPath("//a[@title=\"WOMEN\"]")).perform();
		Thread.sleep(2000);
		action.click(driver.findElementByXPath("(//a[text()='Kurtas'])[2]")).build().perform();
	}

	@Given("User clicks on Brands and choose Ajio")
	public void user_clicks_on_Brands_and_choose_Ajio() throws InterruptedException  {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,200)");
		driver.findElementByXPath("//span[text()='brands']").click();
		driver.findElementByXPath("//label[contains(text(),'AJIO')]").click();
		Thread.sleep(5000);
	}

	@Given("User checks all the results are Ajio")
	public void user_checks_all_the_results_are_Ajio() {
		int brandname = driver.findElementsByXPath("//div[@class=\"brand\"]").size();
		boolean flag = true;
		for (int i = 1; i <= brandname; i++) {
			String name = driver.findElementByXPath("(//div[@class=\"brand\"])["+i+"]").getText();
			if (!name.equals("AJIO")) {
				flag=false;
				System.out.println("Displayed results contains other brands");
			}
		}
		if (flag == true) {
			System.out.println("Displayed results contains only ajio");
		}	   
	}

	@Given("User sets Sort by the result as Discount")
	public void user_sets_Sort_by_the_result_as_Discount() {
		WebElement dropdown = driver.findElementByXPath("//div[@class='filter-dropdown']/select");
		Select sel = new Select(dropdown);
		sel.selectByVisibleText("Discount");
	}

	@Given("User selects any Color and click ADD TO BAG")
	public void user_selects_any_Color_and_click_ADD_TO_BAG() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,600)");
		driver.findElementByXPath("//span[text()='colors']").click();
		driver.findElementByXPath("//label[contains(text(),'Green')]").click();
		Thread.sleep(5000);

		action.moveToElement(driver.findElementByXPath("//div[text()='Printed Straight V-Neck Kurta with Tassels']")).build().perform();
		action.click(driver.findElementByXPath("//div[text()='Printed Straight V-Neck Kurta with Tassels']")).build().perform();
		Thread.sleep(5000);

		Set<String> window = driver.getWindowHandles();
		ArrayList<String> arr = new ArrayList<String>(window);
		driver.switchTo().window(arr.get(1));
		js.executeScript("window.scrollBy(0,300)");
		driver.findElementByXPath("//span[text()='ADD TO BAG']").click();	   
	}

	@Given("User verifies the error message Select your size to know your estimated delivery date")
	public void user_verifies_the_error_message_Select_your_size_to_know_your_estimated_delivery_date() throws InterruptedException {
		Thread.sleep(2000);
		String error = driver.findElementByXPath("//span[@class=\"ic-info-error\"]/following-sibling::span").getText();
		System.out.println(error);	   
	}

	@Given("User selects size and click ADD TO BAG")
	public void user_selects_size_and_click_ADD_TO_BAG() {
		driver.findElementByXPath("//div[text()='XS']").click();
		driver.findElementByXPath("//span[text()='ADD TO BAG']").click();
	}

	@Given("User clicks on Enter pin-code to know estimated delivery date")
	public void user_clicks_on_Enter_pin_code_to_know_estimated_delivery_date() throws InterruptedException {
		Thread.sleep(5000);
		driver.findElementByXPath("//span[text()='Enter Pin-code To Know Estimated Delivery Date']").click();
	}

	@Given("User enters the pincode as 603103 and click Confirm pincode")
	public void user_enters_the_pincode_as_and_click_Confirm_pincode() {
		driver.findElementByXPath("//input[@name=\"pincode\"]").click();
		driver.findElementByXPath("//input[@name=\"pincode\"]").sendKeys("630103");
		driver.findElementByXPath("//button[text()='CONFIRM PINCODE']").click();
	}

	@When("User prints the message and click Go to  Bag")
	public void user_prints_the_message_and_click_Go_to_Bag() {
		System.out.println("First message --> " + driver.findElementByXPath("//ul[@class=\"edd-message-success-details\"]").getText());
		System.out.println("Second message --> "+ driver.findElementByXPath("//span[@class=\"edd-message-success-details-note\"]").getText());
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)");
		driver.findElementByXPath("//span[text()='GO TO BAG']").click();
	}

	@Then("User clicks on Proceed to Shipping and clode the browser")
	public void user_clicks_on_Proceed_to_Shipping_and_clode_the_browser() {
		driver.findElementByXPath("//button[text()='Proceed to shipping']").click();
		driver.quit();	   
	}
}
