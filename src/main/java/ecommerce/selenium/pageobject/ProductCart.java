package ecommerce.selenium.pageobject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ecommerce.selenium.abtractcomponents.abtractcomponent;

public class ProductCart extends abtractcomponent {
	//creating the page object 
	WebDriver driver; 
	public ProductCart(WebDriver driver)
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);  //to make driver know about findby method / annotation 
	}
    
	//page factory method 
	@FindBy(css=".col-lg-4")
	 List<WebElement> products ;
	
    By products1= By.cssSelector(".col-lg-4");
	By addtocart = By.cssSelector(".card-body button:last-of-type");
	By toastcontainer = By.cssSelector("#toast-container");
	By nganimating  = By.cssSelector(".ng-animating ");
	//By checkout = By.cssSelector(".btn.btn-custom > label");
	
	
	public List<WebElement> getProductList()
	{
		isvisible(products1);
		return products;
	}
	
	public WebElement getProductname(String productname )
	{
	      WebElement prod=  products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null) ;
	      return prod;
	 }
	
	public void Addcart(String productname)
	{
		WebElement item = getProductname(productname);
		item.findElement(addtocart).click();
		isvisible(toastcontainer);
		letinvisible(nganimating);
		
	}

	}
	

