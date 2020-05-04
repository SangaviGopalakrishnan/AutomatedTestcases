import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC013ShikshaAutomation {

	public static void main(String[] args) throws InterruptedException {

		//1) Go to https://studyabroad.shiksha.com/
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://studyabroad.shiksha.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//2) Mouse over on Colleges and click MS in Computer Science &Engg under MS Colleges
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElementByXPath("(//label[text()='Colleges '])[1]")).build().perform();
		action.moveToElement(driver.findElementByXPath("//label[text()='MS Colleges']")).build().perform();
		driver.findElementByXPath("//a[text()='MS in Computer Science &Engg']").click();
		Thread.sleep(5000);
		
		//3) Select GRE under Exam Accepted and Score 300 & Below 
		driver.findElementByXPath("//p[text()='GRE']/preceding-sibling::span").click();
		Thread.sleep(5000);
		Select select = new Select(driver.findElementByXPath("(//select[@name='examsScore[]'])[1]"));
		select.selectByVisibleText("300 & below");
		
		//4) Max 10 Lakhs under 1st year Total fees, USA under countries
		Thread.sleep(5000);
		driver.findElementByXPath("//p[text()='Max 10 Lakhs']/preceding-sibling::span").click();
		Thread.sleep(5000);
		driver.findElementByXPath("//a[text()='USA']/parent::p/parent::label/span").click();
		Thread.sleep(5000);
		
		//5) Select Sort By: Low to high 1st year total fees.
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		Select select1 = new Select(driver.findElementById("categorySorter"));
		select1.selectByVisibleText("Low to high 1st year total fees");
		Thread.sleep(5000);		
		
		//6) Click Add to compare of the College having least fees with Public University, Scholarship and Accomadation facilities
		List<WebElement> colleges = driver.findElementsByXPath("//div[@class=\"tuple-title\"]");
		List<Double> list = new ArrayList<Double>();
		for (int i = 1; i <=colleges.size(); i++) {
			List<WebElement> ele = driver.findElementsByXPath("((//div[contains(@id,\"categoryPageListing_tuple\")])["+i+"]//span[@class=\"tick-mark\"])");
			int size = ele.size();
			if (size == 3) {
				double value = Double.parseDouble(driver.findElementByXPath("((//div[contains(@id,\"categoryPageListing_tuple\")])["+i+"]//div[@class='detail-col flLt']/p)[1]").getText().replaceAll("[^0-9.]", ""));
				list.add(value);
			}
		}
		Collections.sort(list);
		driver.findElementByXPath("//p[contains(text(),'"+list.get(0)+"')]//ancestor::div[@class=\"course-touple clearwidth\"]/div[@class=\"compare-box flRt customInputs\"]/label/span").click();
		
		//7) Select the first college under Compare with similar colleges 
		Thread.sleep(5000);
		driver.findElementByXPath("//ul[@class=\"sticky-suggestion-list\"]/li[1]").click();
		
		//8) Click on Compare College>
		driver.findElementByXPath("//strong[text()='Compare Colleges >']").click();
		
		//9) Select When to Study as 2021
		boolean selected = driver.findElementByXPath("//strong[text()=\"2021\"]/parent::p//parent::label/span").isSelected();
		if (selected == false) {
			driver.findElementByXPath("//strong[text()=\"2021\"]/parent::p//parent::label/span").click();
		}
		
		//10) Select Preferred Countries as USA
		action.moveToElement(driver.findElementByXPath("//div[@class=\"sp-frm selectCountryField signup-fld invalid \"]")).click().perform();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//label[text()[normalize-space()='USA']]/span")));
		driver.findElementByXPath("//label[text()[normalize-space()='USA']]/span").click();
		driver.findElementByXPath("//a[text()=\"ok\"]").click();
		
		//11) Select Level of Study as Masters
		driver.findElementByXPath("//strong[text()=\"Masters\"]/parent::p//parent::label/span").click();
		
		//12) Select Preferred Course as MS
		action.moveToElement(driver.findElementByXPath("//div[text()=\"Preferred Course\"]/parent::div")).click().perform();
		driver.findElementByXPath("//li[text()=\"MS\"]").click();
		
		//13) Select Specialization as "Computer Science & Engineering"
		Thread.sleep(5000);
		action.moveToElement(driver.findElementByXPath("//div[@class=\"sp-frm selectField signup-fld invalid  filled\"]")).click().perform();
		driver.findElementByXPath("//li[text()=\"Computer Science & Engineering\"]").click();
		
		//14) Click on Sign Up
		js.executeScript("window.scrollBy(0,600)");
		driver.findElementById("signup").click();
		
		//15) Print all the warning messages displayed on the screen for missed mandatory fields
		List<WebElement> ele = driver.findElementsByXPath("//div[@class=\"helper-text\"][contains(text(),'Please')]");
		for (int i = 1; i <=ele.size(); i++) {
			String text = driver.findElementByXPath("(//div[@class=\"helper-text\"][contains(text(),'Please')])["+i+"]").getText();
			if (text.length() > 0) {
				System.out.println(text);
			}
		}
	}

}
