package steps;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TC005ShopCluesSteps{

	public static ChromeDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js = (JavascriptExecutor) driver;
	List<Integer> sortedlist;
	List<Integer> list;
	Actions action;
	TreeMap<Integer,String> map;
	String largestname;

	@Given("User launches the Browser for ShopClues")
	public void user_launches_the_Browser_for_ShopClues() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		 driver = new ChromeDriver();
		driver.get("https://www.shopclues.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 30);
		action = new Actions(driver);
		try {
			driver.findElementByXPath("//button[text()=\"Don't Allow\"]").click();
		} catch (Exception e) {
			System.out.println("Alert not found");
		}
	}

	@Given("User mouse hovers on women and clicks Casual Shoes")
	public void user_mouse_hovers_on_women_and_clicks_Casual_Shoes() throws InterruptedException {
		Thread.sleep(1000);
		action.click(driver.findElementByXPath("//a[text()='WOMEN']")).build().perform();
		action.click(driver.findElementByXPath("//a[text()='Casual Shoes']")).build().perform();
		Set<String> windowHandles = driver.getWindowHandles();
		ArrayList<String> arr = new ArrayList<String>(windowHandles);
		driver.switchTo().window(arr.get(1));
	}

	@Given("User selects colour as Black")
	public void user_selects_colour_as_Black() throws InterruptedException {
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,3000)");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click()", driver.findElementByXPath("//label[@for='Black']"));
		Thread.sleep(5000);
	}

	@Given("User checks whether the product name contains the word Black and If so user adds the product name and price to Map")
	public void user_checks_whether_the_product_name_contains_the_word_Black() {
		List<WebElement> products = driver.findElementsByXPath("//span[@class=\"prod_name \"]");
		map = new TreeMap<Integer,String>();
		System.out.println(products.size());
		for (int i = 1; i <= products.size() ; i++) {
			String name = driver.findElementByXPath("(//span[@class=\"prod_name \"])["+i+"]").getText();
			if (name.contains("Black")) {
				int price = Integer.parseInt(driver.findElementByXPath("(//span[@class=\"p_price\"])["+i+"]").getText().replace("Rs.", ""));
				map.put(price, name);
			}
		}
	}

	@Given("User checks display the count of shoes which are display more than 500Rupees")
	public void user_checks_display_the_count_of_shoes_which_are_display_more_than_Rupees() {
		Set<Entry<Integer, String>> entrySet = map.entrySet();
		for (Entry<Integer, String> entry : entrySet) {
			if (entry.getKey() > 500) {
				System.out.println(entry.getKey() +" ----> "+entry.getValue());
			}
		}

		Entry<Integer, String> lastEntry = map.lastEntry();
		largestname = lastEntry.getValue();
		System.out.println("Large -->"+ largestname);	
	}

	@Given("User clicks the highest price of the Shoe")
	public void user_clicks_the_highest_price_of_the_Shoe() throws InterruptedException {
		driver.findElementByXPath("//span[contains(text(),\""+largestname+"\")]").click();
		Thread.sleep(5000);
	}

	@Given("User gets the current page Url and checks with the product ID")
	public void user_gets_the_current_page_Url_and_checks_with_the_product_ID() {
		Set<String> windowHandles1 = driver.getWindowHandles();
		ArrayList<String> arr1 = new ArrayList<String>(windowHandles1);
		driver.switchTo().window(arr1.get(2));
		String urlidd = driver.getCurrentUrl().replaceAll("\\D", "");
		String id = driver.findElementByXPath("//span[@class=\"pID\"]").getText().replaceAll("\\D", "");
		if (urlidd.equals(id)) {
			System.out.println("Id matches");
		} else {
			System.out.println("Id not matched");
		}
	}

	@Given("User copies the offer code")
	public void user_copies_the_offer_code() {
		String coupon = driver.findElementByXPath("//div[@class=\"coupons_code\"]/span").getText();
		System.out.println(coupon);
	}

	@Given("User selects the size, colour and clicks Add to Cart")
	public void user_selects_the_size_colour_and_clicks_Add_to_Cart() {
		driver.findElementByXPath("//span[text()='40 (EUR)']").click();
		driver.findElementById("add_cart").click();
	}

	@Given("User mouse hovers on Shopping Cart and clicks View Cart")
	public void user_mouse_hovers_on_Shopping_Cart_and_clicks_View_Cart() throws InterruptedException {
		action.moveToElement(driver.findElementByXPath("//a[@class=\"cart_ic\"]")).build().perform();
		action.click(driver.findElementByXPath("//a[text()='View Cart']")).build().perform();
		Thread.sleep(5000);
	}

	@When("User types Pincode as 600016 clicks Submit and Place Order")
	public void user_types_Pincode_as_clicks_Submit_and_Place_Order() {
		driver.findElementByXPath("//input[@id=\"pin_code_popup\"]").sendKeys("600016");
		driver.findElementByXPath("//input[@id=\"get_pincode_popup\"]").click();
		driver.findElementByXPath("//div[text()='Place Order']").click();
	}

	@Then("User closes the Browser")
	public void user_closes_the_Browser() {
		driver.quit();
	}
}
