package ecommerce.selenium.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecommerce.selenium.abtractcomponents.abtractcomponent;

public class OrderPage extends abtractcomponent {

	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> productinorder;

	public Boolean Verfifyproductinorder(String productname) {
		
        System.out.println("Verfiy producst order page ");
		Boolean match = productinorder.stream().anyMatch(cartprod -> cartprod.getText().equalsIgnoreCase(productname));
		System.out.println(match);
		return match;
	}

}
