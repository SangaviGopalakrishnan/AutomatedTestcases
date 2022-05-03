package commonfiles;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class CommonClass
{
	public RemoteWebDriver driver;
	
	@Parameters({"url"})
	@BeforeMethod
	public void commonclass(String url) 
		{
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			DesiredCapabilities des = new DesiredCapabilities();
			des.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			options.merge(des);
			driver = new ChromeDriver(options);
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		
		}
		
		@AfterMethod
		public void closingdriver()
			{
				driver.close();
			}
		
}