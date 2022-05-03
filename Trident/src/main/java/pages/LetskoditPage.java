package pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import commonfiles.CommonClass;

public class LetskoditPage extends CommonClass{
	
	public LetskoditPage(RemoteWebDriver driver) {
		this.driver=driver;
	}

	public LetskoditPage getTitleofthepage() {
	     String title = driver.getTitle();
	     System.out.println("Title os the page is "+ title);
		return this;
	}
	
	public LetskoditPage gethyperlink() {
		 List<WebElement> allLinks = driver.findElements(By.tagName("a"));
	     for(WebElement link : allLinks){
	     System.out.println(link.getText() + " - " + link.getAttribute("href"));
	     }
		return this;
	}
	public LetskoditPage selectdropdown() {
		 WebElement cardropdown =  driver.findElementById("carselect");
		    Select select = new Select(cardropdown);
		    select.selectByVisibleText("Honda");
		return this;
	}
	public LetskoditPage clickcheckbox() {
		List<WebElement> list = driver.findElementsByXPath("//div[@id=\"radio-btn-example\"]/fieldset/label/input");
	    for(int i=1;i<=list.size();i++) {
	    	driver.findElementByXPath("(//div[@id=\"radio-btn-example\"]/fieldset/label/input)["+i+"]").click();
	    }
		return this;
	}
	
	
}
