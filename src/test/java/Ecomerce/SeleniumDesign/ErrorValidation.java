package Ecomerce.SeleniumDesign;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.sun.net.httpserver.Authenticator.Retry;

import ecommerce.resources.ExtentReporterNG;
import ecommerce.selenium.pageobject.ProductCart;
import ecommerce.selenium.pageobject.cartPage;
import ecommerce.selenium.testcomponent.BaseTest;
import org.testng.IRetryAnalyzer;



public class ErrorValidation extends BaseTest   {
	
	


	 @Test(groups = {"ErrorHandling"}, retryAnalyzer = ecommerce.selenium.testcomponent.Retry.class)
	    public void validateLoginDetails() throws IOException {
	     //   lg = lunch_application(); // Ensure WebDriver is initialized
	        lg.LoginApplication("twinkleshirsath@gmail.com", "Twinkle1404");
	        String errorMessage = lg.validate_error_msg();
	        Assert.assertEquals(errorMessage, "Incorrect email or password."); 
//	        		"Error message mismatch.");
	        System.out.println("Login error validation test passed");
	       
	    }

	 @Test
	    public void validateCartProduct() throws IOException {
	        String productname = "ZARA COAT 3";
	      //  lg = lunch_application(); // Ensure WebDriver is initialized
	        lg.LoginApplication("twinkleshirsath@gmail.com", "Twinkle@1404");

	        ProductCart pc = new ProductCart(driver);
	        pc.getProductname(productname);
	        pc.Addcart(productname);
	        cartPage cpage = pc.gotocartpage(); // Navigate to cart page

	        // Verify the product in the cart; expected product name should match
	        Boolean match = cpage.Verfifyproduct("ZARA COAT 33"); // Intentionally incorrect name for error validation
	        Assert.assertFalse(match, "Product should not be in the cart, but it was found.");
	        System.out.println("Cart product validation test passed");
	       
	    }
	
}
