package ReportGeneration;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ReportwithTestNG {
	
	public static ChromeDriver driver;
	ExtentHtmlReporter html;
	ExtentReports extent;
	
	@BeforeTest
	public void beftest() {
		html = new ExtentHtmlReporter("./snaps/report.html");
		html.setAppendExisting(true);
		extent = new ExtentReports();
		extent.attachReporter(html);
	}
	
	@BeforeMethod
	public void beforem() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://leaftaps.com/opentaps/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void starttt() {
		ExtentTest test = extent.createTest("rep2","info");
		test.assignAuthor("Sangavi");
		test.assignCategory("Smoke");
		try {
			driver.findElementById("username").sendKeys("Demosalesmanager");
			test.pass("1",MediaEntityBuilder.createScreenCaptureFromPath("./../snaps/bus[1].png").build());
		} catch (Exception e) {
			test.fail("1fail");
		}
		
		try {
			driver.findElementById("password").sendKeys("crmsfa");
			test.pass("2");
		} catch (Exception e) {
			test.fail("2fail");
		}
		
		try {
			driver.findElementByClassName("decorativeSubmit").click();
			test.pass("3");
		} catch (Exception e) {
			test.fail("3fail");
		}
		
		try {
			driver.findElementByLinkText("CRM/SFA").click();
			test.pass("4");
		} catch (Exception e) {
			test.fail("4fail");
		}
	}
	
	@AfterMethod
	public void afterm() {
		driver.quit();
	}
	
	@AfterTest
	public void aftert() {
		extent.flush();
	}
	

}
