package ecommerce.selenium.pageobject;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import ecommerce.resources.ExtentReporterNG;
import ecommerce.selenium.abtractcomponents.abtractcomponent;

public class LoginPage extends abtractcomponent {
	//creating the page object 
	WebDriver driver; 
	
//	LoginPage lg = new LoginPage()
	public LoginPage(WebDriver driver)
	{
		
		super(driver); //sending from child to parent (means to abtract class)
		this.driver= driver;
		PageFactory.initElements(driver, this);  //to make driver know about findby method / annotation 
	}
    
	
	
	//page factory method 
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="div[role='alert']")
	WebElement errormsg;
// now we will learn about implementing action on page objects 
	
	
	public ProductCart LoginApplication(String user , String pass)
	{
		userEmail.sendKeys(user);
		password.sendKeys(pass);
	
		submit.click();
		ProductCart pc = new ProductCart(driver);
		return pc ;
		
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	

	
	public String validate_error_msg()
	{
		waitforelement(errormsg);
		return errormsg.getText();
	}
	
	
}
