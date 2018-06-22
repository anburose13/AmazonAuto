package com.ecom.amazon.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.ecom.testBase.Initializer;

public class Homepage_PO extends Initializer{

	@FindBy(how=How.CSS, using="img.logo")
	public WebElement logo_img;
	@FindBy(how=How.XPATH, using="//a[@title='Orders']/span")
	public WebElement ord_histroy_btn;
	@FindBy(how=How.XPATH, using="//a[@title='My wishlists']/span")
	public WebElement wishlist_btn;
	
}
