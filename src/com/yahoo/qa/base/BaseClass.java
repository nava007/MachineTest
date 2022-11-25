package com.yahoo.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaseClass {

	public static WebDriver driver;
	public static Properties prop;
	public static String path = System.getProperty("user.dir");
	public static String path0=path.replace("\\", "/");
	/*Properties file defined*/
	public BaseClass(){
		
		
		try {

			prop=new Properties();
			FileInputStream ip=new FileInputStream(path0+"/src/com/yahoo/qa/config/config.properties");
			prop.load(ip);
			

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}


	}
	
	/*This method is to initialize the driver*/
	public void initialisation(){
		
		//String path1=path.replace("\\", "/");
		String str=prop.getProperty("browser");
		
		if(str.equals("ie"))
		{

			System.setProperty("webdriver.ie.driver","");
			driver=new InternetExplorerDriver();

		}

		else if(str.equals("chrome"))
		{
			/*Opens chrome in incognito mode*/
			
			System.setProperty("webdriver.chrome.driver",path0+"/lib/chromedriver.exe");
			
			driver=new ChromeDriver();

		}

		else{

			System.out.println("Check the browser name in configuration file");
		}


		driver.manage().window().maximize(); 
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}



}
