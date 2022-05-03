package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import commonfiles.CommonClass;

public class MakemytripPage extends CommonClass {

	public MakemytripPage(RemoteWebDriver driver) {
		this.driver=driver;
	}
	public MakemytripPage enterdepartureDetails()  {
		driver.findElementByXPath("//span[@class=\"langCardClose\"]").click();
		driver.findElement(By.xpath("//li[@data-cy=\"account\"]")).click();
		WebElement Fromcity = driver.findElementByXPath("//label[@for=\"fromCity\"]");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(Fromcity));
		Fromcity.click();
		driver.findElementByXPath("//input[@placeholder=\"From\"]").sendKeys("Goa");
		driver.findElementByXPath("//p[text()='Goa, India']").click();
		driver.findElementByXPath("//input[@placeholder=\"To\"]").sendKeys("New Delhi");
		driver.findElementByXPath("//p[text()='New Delhi, India']").click();
		return this;
	}
	public MakemytripPage selectDate()  {
		driver.findElementByXPath("//div[@aria-label=\"Sat May 14 2022\"]").click();
		return this;
	}
	public MakemytripPage clicksearchbutton()  {
		driver.findElementByXPath("//a[text()='Search']").click();
		return this;
	}

}
