package com.ecom.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Listeners;

import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

@Listeners({ConfigurationListener.class,MethodListener.class,ATUReportsListener.class})
public class Initializer {
	

	public static WebDriver wd = null;
	public static Properties conf=null;
	public static Properties or=null;
	
	public static Logger lg=null;

	
	public static void start() throws IOException {

		File f = new File(System.getProperty("user.dir") + "/src/test/resources/configurations/Config.properties");
		FileInputStream fis = new FileInputStream(f);
		 conf = new Properties();
		 conf.load(fis);
		 File f1 = new File(System.getProperty("user.dir") + "/src/test/resources/configurations/OR.properties");
			FileInputStream fis1 = new FileInputStream(f1);
			 or = new Properties();
			 or.load(fis1);
			 System.out.println(System.getProperty("user.dir"));
			 PropertyConfigurator.configure(System.getProperty("user.dir") +"\\Lib\\log4j.properties");
			

		if (conf.getProperty("browser").equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\User\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");

			wd = new ChromeDriver();

		} else if (conf.getProperty("browser").equalsIgnoreCase("firefox")) {
			wd = new FirefoxDriver();
		} else {
			System.setProperty("webdriver.ie.driver", "C:\\Users\\User\\Downloads\\IEDriverServer.exe");

			wd = new InternetExplorerDriver();
		}
		
		wd.get(conf.getProperty("testurl"));
		wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		lg=Logger.getLogger(Initializer.class);
		lg.info("Log file initiated here");
	}
	
	public WebElement getElements(String locElmt){
		
	System.out.println("Locator Key from OR file is "+locElmt);
		if(locElmt.startsWith("lnktxt")){
			
			return wd.findElement(By.linkText(or.getProperty(locElmt)));
		}else if(locElmt.startsWith("css")){

			return wd.findElement(By.cssSelector(or.getProperty(locElmt)));
		}else if(locElmt.startsWith("id")){
			return wd.findElement(By.id(or.getProperty(locElmt)));
		}else if(locElmt.startsWith("xpath")){
			return wd.findElement(By.xpath(or.getProperty(locElmt)));
		
		}else{
			return null;
		}
	}
}

