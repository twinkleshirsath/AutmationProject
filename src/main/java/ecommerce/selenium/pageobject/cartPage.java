package ecommerce.selenium.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecommerce.selenium.abtractcomponents.abtractcomponent;

public class cartPage extends abtractcomponent {
	
	WebDriver driver;
	public cartPage(WebDriver driver)
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);  //to make driver know about findby method / annotation 
	}
    
	@FindBy(css=".cartSection h3")
	 List<WebElement> cartprod ;
	
	@FindBy(css=".totalRow button")
	 WebElement checkoutele ;
	
	public List<WebElement> getcartlist()
	{
		  List <WebElement> cartProducts = cartprod;
		return cartProducts ;
				
	}
    public Boolean Verfifyproduct (String productname)
    {
    	Boolean match=getcartlist().stream().anyMatch(cartprod->cartprod.getText().equalsIgnoreCase(productname));
    	return match;
    }
    
    public submitOrderpage checkout()
    {
    	checkoutele.click();
    	submitOrderpage sbpage = new submitOrderpage(driver);
       return sbpage;
    }
}
