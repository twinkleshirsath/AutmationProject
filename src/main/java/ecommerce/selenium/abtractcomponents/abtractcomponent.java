package ecommerce.selenium.abtractcomponents;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ecommerce.selenium.pageobject.OrderPage;
import ecommerce.selenium.pageobject.cartPage;

public class abtractcomponent {
	
	WebDriver driver ;
	public abtractcomponent(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
  
	@FindBy(css=".btn.btn-custom > label")
	WebElement cart ;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement ordbtn ;
	
	
	public void isvisible(By findBy)
	{
		
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		
	}
	
	
	public void waitforelement(WebElement ele)
	{
		
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void letinvisible(By findBy)
	{
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
	
	public cartPage gotocartpage()
	{
		cart.click();
		cartPage cpage = new cartPage(driver);
        return cpage; 
	}
	
	public void selectautosuggestive(WebElement select , String character)
	{
		Actions a = new Actions(driver);
	    a.sendKeys(select,character).build().perform();
	}
	
	
	public OrderPage gotoorderspage()
	{
		ordbtn.click();
		System.out.println("on the order page ");
		OrderPage orpage = new OrderPage(driver);
		return orpage;
	}

	public String getScreenshotfortest(String testcasename ) throws IOException 
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source =ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//"+testcasename+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testcasename+".png";

	}
}
