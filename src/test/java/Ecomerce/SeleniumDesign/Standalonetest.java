package Ecomerce.SeleniumDesign;


import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ecommerce.selenium.pageobject.LoginPage;
import ecommerce.selenium.pageobject.ProductCart;
import ecommerce.selenium.pageobject.cartPage;
import ecommerce.selenium.pageobject.submitOrderpage;

import java.time.Duration;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Standalonetest {

	public static void main(String[] args) {
		
		//using webdriver manager to launch 
		String productname ="ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		//login into the website 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("twinkleshirsath@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Twinkle@1404");
		driver.findElement(By.id("login")).click();
		
//		for resuseable code we will use abtract method 
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));
		
//		get the list of all the products 
        List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));
//        //list of products  with the help of stream we can iterate through the products
//        //Going to our required product
///        System.out.println(products.getFirst().getText());
      WebElement prod=  products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null) ;
//      //clicking on cart button and product is added to cart 
//      
     prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
//
////      List <String> items = products.stream().map(s->s.getText()).collect(Collectors.toList());
     //implementing expplicit wait to handle application synchronously for toast msg 
//      
    System.out.println("cart button and product is added to cart");
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
//      //ask dev for loading icon here it is ng-animating 
      wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating ")));
      driver.findElement(By.cssSelector(".btn.btn-custom > label")).click(); //clicking on the cart is successful now 
//      //now we will seethe logic to verify items in cart with streams 
      List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
      boolean match = cartProducts.stream().anyMatch(cartprod->cartprod.getText().equalsIgnoreCase(productname));
      Assert.assertTrue(match);
      driver.findElement(By.cssSelector(".totalRow button")).click();
      
      Actions a = new Actions(driver);
      a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
      wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));
      driver.findElement(By.cssSelector(".list-group.ng-star-inserted.ta-results > button:nth-of-type(2)")).click();
      driver.findElement(By.linkText("PLACE ORDER")).click();
       String msg=  driver.findElement(By.cssSelector(".hero-primary")).getText();
       Assert.assertTrue(msg.equalsIgnoreCase("Thankyou for the order."));
       System.out.println("Ordered is placed");
       driver.quit();
      
        
  }

}
