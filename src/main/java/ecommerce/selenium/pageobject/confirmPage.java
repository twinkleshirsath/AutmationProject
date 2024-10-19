package ecommerce.selenium.pageobject;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecommerce.selenium.abtractcomponents.abtractcomponent;

public class confirmPage extends abtractcomponent {
	
	WebDriver driver;
	public confirmPage(WebDriver driver)
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);  //to make driver know about findby method / annotation 
	}
    
	
	@FindBy(css=".hero-primary")
	 WebElement finalmsg ;
	
	
	public String verifyorderplaced()
	{
		String msg= finalmsg.getText();
		return msg;
		
    }
    
}
