package steps;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TC002TrivagoSteps{

	public static ChromeDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js = (JavascriptExecutor) driver;
	List<Integer> sortedlist;
	List<Integer> list;
	Actions action;

	@Given("open the trivago website")
	public void open_the_trivago_website() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.trivago.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 30);
	}

	@And("Type Agra in Destination and select Agra, Uttar Pradesh")
	public void type_Agra_in_Destination_and_select_Agra_Uttar_Pradesh() {
		driver.findElementById("querytext").sendKeys("Agra");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='City - Uttar Pradesh, India']")));
		driver.findElementByXPath("//span[text()='City - Uttar Pradesh, India']").click();
		action = new Actions(driver);
	}

	@And("Choose June25 as check in and June27 as check out")
	public void choose_June_as_check_in_and_June_as_check_out() throws InterruptedException {
		Thread.sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)");
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//time[@datetime=\"2020-06-25\"]")));
		driver.findElementByXPath("//time[@datetime=\"2020-06-25\"]").click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//time[@datetime=\"2020-06-27\"]")));
		driver.findElementByXPath("//time[@datetime=\"2020-06-27\"]").click();
		Thread.sleep(1000);

	}

	@And("Select Room as Family Room")
	public void select_Room_as_Family_Room() {
		driver.findElementByXPath("//span[text()='Family rooms']").click();
	}

	@And("Choose Number of Adults2, Childern1 and set Child's Age4")
	public void choose_Number_of_Adults_Childern_and_set_Child_s_Age_as() {
	    WebElement ele = driver.findElementByXPath("//select[@id=\"select-num-adults-2\"]");
	    Select sel = new Select(ele);
	    sel.selectByVisibleText("2");
	    WebElement child = driver.findElementByXPath("//select[@id=\"select-num-children-2\"]");
	    Select sel2 = new Select(child);
	    sel2.selectByVisibleText("1");
	    WebElement age = driver.findElementByXPath("//select[@id=\"select-ages-children-2-3\"]");
	    Select sel3 = new Select(age);
	    sel3.selectByVisibleText("4");
	}

	@And("Click Confirm button")
	public void click_Confirm_button() {
		driver.findElementByXPath("//span[text()='Confirm']").click();
	}

	@And("click Search")
	public void click_Search() {
		action.moveToElement(driver.findElementByXPath("//strong[text()='Accommodation']")).build().perform();
		action.click(driver.findElementByXPath("//input[@value=\"Hotels only\"]")).build().perform();
		action.click(driver.findElementByXPath("//button[@title=\"4-star hotels\"]")).build().perform();
	}

	@And("Select Accommodation type as Hotels only and choose 4stars")
	public void select_Accommodation_type_as_Hotels_only_and_choose_stars() {
		action.moveToElement(driver.findElementByXPath("//strong[text()='Guest rating']")).build().perform();
		action.click(driver.findElementByXPath("//span[text()='Very good']")).build().perform();
	}

	@And("Select Guest rating as Very Good")
	public void select_Guest_rating_as_Very_Good() throws InterruptedException {
		action.moveToElement(driver.findElementByXPath("//strong[text()='Hotel location']")).build().perform();
		WebElement location = driver.findElementByXPath("//select[@id=\"pois\"]");
		Select loc = new Select(location);
		loc.selectByVisibleText("Agra Fort");
		Thread.sleep(1000);
		driver.findElementByXPath("//button[text()='Done']").click();
	}

	@And("Set Hotel Location as Agra Fort")
	public void set_Hotel_Location_as_Agra_Fort() {
		action.moveToElement(driver.findElementByXPath("//strong[text()='More filters']")).build().perform();
		action.click(driver.findElementByXPath("//label[text()='Air conditioning']/parent::li/div/input")).build().perform();
		action.click(driver.findElementByXPath("//label[text()='Restaurant']/parent::li/div/input")).build().perform();
		action.click(driver.findElementByXPath("//label[text()='WiFi']/parent::li/div/input")).build().perform();
		driver.findElementByXPath("//button[text()='Done']").click();
	}

	@And("click Done")
	public void click_Done() throws InterruptedException {
		Thread.sleep(1000);
		WebElement sortby = driver.findElementByXPath("//select[@id=\"mf-select-sortby\"]");
		Select sort = new Select(sortby);
		sort.selectByVisibleText("Rating & Recommended");
	}

	@And("In more Filters, select Air conditioning, Restaurant and WiFi and click Done")
	public void in_more_Filters_select_Air_conditioning_Restaurant_and_WiFi_and_click_Done() throws InterruptedException {
		Thread.sleep(1000);
		WebElement sortby = driver.findElementByXPath("//select[@id=\"mf-select-sortby\"]");
		Select sort = new Select(sortby);
		sort.selectByVisibleText("Rating & Recommended");
	}

	@And("Sort the result as Rating & Recommended")
	public void sort_the_result_as_Rating_Recommended() throws InterruptedException {
		Thread.sleep(1000);
		WebElement sortby = driver.findElementByXPath("//select[@id=\"mf-select-sortby\"]");
		Select sort = new Select(sortby);
		sort.selectByVisibleText("Rating & Recommended");
	}

	@And("Print the Hotel name, Rating, Number of Reviews")
	public void print_the_Hotel_name_Rating_Number_of_Reviews() throws InterruptedException {
		String Hotelname = driver.findElementByXPath("//span[@dir=\"ltr\"]").getText();
		System.out.println("Hotelname ---> "+Hotelname);
		String rating = driver.findElementByXPath("//span[@itemprop=\"ratingValue\"]").getText();
		System.out.println("Rating ---> "+ rating);
		String review = driver.findElementByXPath("//strong[@class=\"item__rating-number\"]/parent::span").getText();
		System.out.println("Review ---> "+review);
	}

	@And("Click View Deal")
	public void click_View_Deal() throws InterruptedException {
		driver.findElementByXPath("//span[text()='View Deal']/parent::span/parent::button").click();
		Thread.sleep(5000);
	}

	@And("Print the URL of the Page")
	public void print_the_URL_of_the_Page() {
		Set<String> windowHandles = driver.getWindowHandles();
		ArrayList<String> arr = new ArrayList<String>(windowHandles);
		driver.switchTo().window(arr.get(1));
		System.out.println("URL --> "+driver.getCurrentUrl());
	}

	@When("Print the Price of the Room and click Choose Your Room")
	public void print_the_Price_of_the_Room_and_click_Choose_Your_Room() {
		String hotelPrice = driver.findElementByXPath("//div[@class='price-start ']//strong").getText(); 
		System.out.println("Hotel Price: " + hotelPrice); 
	}

	@Then("Click Reserve and I'll Reserve")
	public void click_Reserve_and_I_ll_Reserve() throws InterruptedException {
		driver.findElementByLinkText("BOOK NOW").click(); 
		driver.findElementByXPath("//button[@class='gotobook']").click();
		Thread.sleep(5000);
	    driver.quit();
	}

}
