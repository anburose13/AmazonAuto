package com.ecom.amazon.login;

import java.io.IOException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ecom.testBase.Initializer;
import com.ecom.testUtils.UtilsClass;

public class LoginTest extends Initializer {
	
	@BeforeClass
	public void preset() throws IOException{
		start();
	}
	
	@Test(dataProvider="getData")
	public void testlogin(String tcid, String mod, String Desc, String precon,  String ts, String td, String postcon, String exp,  String runmod) throws IOException{
		UtilsClass c=new UtilsClass();
		String[] ip=c.spltTestData(td);
		//wd.manage().timeouts().pageLoadTimeout(30, Time);
		//wd.manage().timeouts().setScriptTimeout(arg0, arg1);
//		wd.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//		Wait wt=new WebDriverWait(wd,120);
//		wt.until(ExpectedConditions.presenceOfElementLocated(By.linkText(or.getProperty("lnktxt_signin_btn"))));
		//FluentWait fw=new FluentWait(wd);
		//fw.withTimeout(60,TimeUnit.SECONDS);
		//fw.until(ExpectedConditions.presenceOfElementLocated(By.linkText(or.getProperty("lnktxt_signin_btn")));
		//fw.ignoring(Exception.class);
		//fw.pollingEvery(2,TimeUnit.SECONDS);
		WebElement e1=getElements("lnktxt_signin_btn");
		e1.click();
		WebElement e2=getElements("css_email_txtbox");
		e2.sendKeys(ip[0]);
		WebElement e3=getElements("css_pswrd_txtbox");
		e3.sendKeys(ip[1]);
		WebElement e4=getElements("id_signin_btn");
		e4.click();
		UtilsClass.getSnapShot(tcid);
		Assert.assertEquals(exp,getElements("xpath_welcomeuname_lbl").getText());
		
	}
	
	
	@DataProvider
	public static Object[][] getData() throws IOException{
	return	UtilsClass.getExcelData(conf.getProperty("dataforLogin"));
		
		
	}
	
	
	@AfterClass
	public void posttest(){
		wd.quit();
	}
	
	

}
