package ecommerce.selenium.pageobject;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecommerce.selenium.abtractcomponents.abtractcomponent;

public class submitOrderpage extends abtractcomponent {
	
	WebDriver driver;
	public submitOrderpage(WebDriver driver)
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);  //to make driver know about findby method / annotation 
	}
    
	
	
	@FindBy(css="[placeholder='Select Country']")
	 WebElement countryselect ;
	
	
	@FindBy(css=".list-group.ng-star-inserted.ta-results > button:nth-of-type(2)")
	 WebElement selectcountry ;
	
	@FindBy(linkText="PLACE ORDER")
	 WebElement placeorder; 
	
	@FindBy(css=".hero-primary")
	 WebElement finalmsg ;
	By result = By.cssSelector(".ta-results");
	
	public void clickoncountry(String cha )
	{
		
		selectautosuggestive(countryselect,cha);
	    isvisible(result);
	    selectcountry.click();
	   
	}
	
	
	public confirmPage placeorder()
	{
		 placeorder.click();
		    confirmPage confirmpg = new confirmPage(driver);
		    return confirmpg;
		   
	}
	
    
}
