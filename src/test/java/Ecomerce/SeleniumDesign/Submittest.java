package Ecomerce.SeleniumDesign;

import io.github.bonigarcia.wdm.WebDriverManager;



import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import ecommerce.resources.ExtentReporterNG;
import ecommerce.selenium.pageobject.LoginPage;
import ecommerce.selenium.pageobject.OrderPage;
import ecommerce.selenium.pageobject.ProductCart;
import ecommerce.selenium.pageobject.cartPage;
import ecommerce.selenium.pageobject.confirmPage;
import ecommerce.selenium.pageobject.submitOrderpage;
import ecommerce.selenium.testcomponent.BaseTest;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ecommerce.selenium.testcomponent.Listeners;
public class Submittest extends BaseTest {

	

	// using webdriver manager to launch
	String productname = "ZARA COAT 3";
	
	@Test(dataProvider="getData",groups = {"Purchase"})
	public void submitOrder(HashMap <String ,String>input ) throws IOException {


     
		
	//	lg = lunch_application();
		ProductCart pc =lg.LoginApplication(input.get("email"), input.get("password"));
		
		pc.getProductname(input.get("products"));
		pc.Addcart(input.get("products"));
		
		 // got from common abtract class
		cartPage cpage = pc.gotocartpage(); 
		
		Boolean match = cpage.Verfifyproduct(input.get("products"));
		Assert.assertTrue(match);

		submitOrderpage sbpage =cpage.checkout();;
		sbpage.clickoncountry("ind");
		confirmPage confirmpg = sbpage.placeorder();
	

		String msg = confirmpg.verifyorderplaced();
		Assert.assertTrue(msg.equalsIgnoreCase("Thankyou for the order."));

		System.out.println("Ordered is placed");
		
	
	}
	
	
	@Test(dependsOnMethods = {"submitOrder"}, dataProvider="getData")
	public void orderhistory(HashMap<String, String> input) throws IOException {
	    
	    // Reinitialize driver for order history check
	   // lg = lunch_application();

	    // Login and go to the order page
	    ProductCart pc = lg.LoginApplication(input.get("email"), input.get("password"));
	    OrderPage orpage = pc.gotoorderspage();

	    // Verify if the product exists in the order history
	    Assert.assertTrue(orpage.Verfifyproductinorder(input.get("products")));

	    System.out.println("Order verified in history for: " + input.get("email"));

	    // Quit the driver after test
	 // driver.quit();
	}
	
	@DataProvider 
	
	public Object[][] getData() throws IOException
	
	
	{
		List<HashMap <String , String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//ecommerce//selenium//data//PurchaseOrder.json");
	
		return new Object [][] {{data.get(0)},{data.get(1)}};
	}
	
	public LoginPage checkdriver() throws IOException
	{
		if (driver==null)
		{
			lg = lunch_application();
		}
		return lg ;
	}

}




