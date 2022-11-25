package com.yahoo.qa.pages;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.yahoo.qa.base.BaseClass;
import com.yahoo.qa.utilities.ExcelUtils;

public class YahooSignUpPage extends BaseClass{
	
	//WebDriverWait wait = new WebDriverWait(driver,30);
	
	public YahooSignUpPage(){

		PageFactory.initElements(driver, this);
	}
	
	
	/*WebElement for Field First Name*/
	@FindBy(id="usernamereg-firstName")
	private WebElement firstname;

	/*WebElement for Field Last Name*/
	@FindBy(id="usernamereg-lastName")
	public WebElement lastname;

	/*WebElement for Field Email ID*/
	@FindBy(id="usernamereg-yid")
	private WebElement email;

	/*WebElement for Field Password*/
	@FindBy(id="usernamereg-password")
	private WebElement password;

	/*WebElement for From Date*/
	@FindBy(xpath="//label[@for='departure']")
	private WebElement fromdateclick;

	/*WebElement for Field Mobile*/
	@FindBy(id="usernamereg-phone")
	private WebElement mobile;

	/*WebElement for Field BirthMonth*/
	@FindBy(id="usernamereg-month")
	private WebElement birthmonth;

	/*WebElement for Field BirthMonth*/
	@FindBy(id="usernamereg-day")
	private WebElement day;
	
	/*WebElement for Field BirthMonth*/
	@FindBy(id="usernamereg-year")
	private WebElement year;
	
	/*WebElement for Field BirthMonth*/
	@FindBy(id="reg-submit-button")
	private WebElement continuebtn;
	
	/*WebElement for Field BirthMonth*/
	@FindBy(xpath="//a[@class='termsLink']")
	private WebElement terms;
	
	/*WebElement for Field BirthMonth*/
	@FindBy(xpath="//a[@class='privacyLink']")
	private WebElement policy;
	
	/*WebElement for message "A Yahoo account already exists with this email address." */
	@FindBy(xpath="//div[@id='reg-error-yid']")
	private WebElement mailalready;
	
	/*WebElement for message "That email address is too short, please use a longer one." and "You can only use letters, numbers, periods (�.�), and underscores (�_�) in your username.",
	 *  "Your username has to start with a letter." */
	@FindBy(xpath="//div[@id='reg-error-yid']")
	private WebElement mailstrength;
	
	/*WebElement for message "Your password isn�t strong enough, try making it longer.","Your password cannot include your name." */
	@FindBy(xpath="//div[@id='reg-error-password']")
	private WebElement passwrdstrgth;
	
	/*WebElement for message "That doesn�t look right, please re-enter your phone number.","This is too long." */
	@FindBy(xpath="//div[@id='reg-error-phone']")
	private WebElement invalidmobile;
	
	/*WebElement for message "That doesn�t look right, please re-enter your birthday." */
	@FindBy(xpath="//div[@id='reg-error-birthDate']")
	private WebElement futurebday;
	
	/*WebElement for first name mandatory message" */
	@FindBy(xpath="//div[@id='reg-error-firstName']")
	private WebElement mandfname;
	
	/*WebElement for last name mandatory message" */
	@FindBy(xpath="//div[@id='reg-error-lastName']")
	private WebElement mandlname;
	
	/*WebElement for email mandatory message" */
	@FindBy(xpath="//div[@id='reg-error-yid']")
	private WebElement mandemail;
	
	/*WebElement for password mandatory message" */
	@FindBy(xpath="//div[@id='reg-error-password']")
	private WebElement mandpasword;
	
	/*WebElement for phone mandatory message" */
	@FindBy(xpath="//div[@id='reg-error-phone']")
	private WebElement mandphone;
	
	/*WebElement for birthday mandatory message" */
	@FindBy(xpath="//div[@id='reg-error-birthDate']")
	private WebElement mandbdate;
	
	/*This method Fills the Yahoo Sign Up form fields*/
	public void fill_signup_form(String fname, String lname, String mail, String passwrd, String mobileno, String bmonth, String bday, String byear ) throws InterruptedException{

		driver.navigate().refresh();
		/*Method to select the month drop down*/
		Select select=new Select(birthmonth);
		
		firstname.sendKeys(fname);
		lastname.sendKeys(lname);
		email.sendKeys(mail);
		password.sendKeys(passwrd);
		mobile.sendKeys(mobileno);
		select.selectByVisibleText(bmonth);
		day.sendKeys(bday);
		year.sendKeys(byear);
		continuebtn.click();
		Thread.sleep(2000);
	}	
	
	/*This method to cover mail field related cases*/
	public String mail_cases(String mailentry) throws InterruptedException{
		
		driver.navigate().refresh();
		email.sendKeys(mailentry);
		email.sendKeys(Keys.TAB);
		//Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(mailalready));
		String mailused=mailalready.getText();
		return mailused;
	}
	
	/*This method to cover password field related cases*/
	public String password_cases(String pwd) throws InterruptedException{
		
		//driver.navigate().refresh();
		password.sendKeys(pwd);
		password.sendKeys(Keys.TAB);
		//Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(passwrdstrgth));
		String paswrdcase=passwrdstrgth.getText();
		return paswrdcase;
	}
	
	/*This method cover mobile field related cases*/
	public String mobile_cases(String mobiledi) throws InterruptedException{
		
		driver.navigate().refresh();
		mobile.sendKeys(mobiledi);
		mobile.sendKeys(Keys.TAB);
		//Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(invalidmobile));
		String paswrdcase=invalidmobile.getText();
		return paswrdcase;
	}
	
	/*This method cover dob field related cases*/
	public String dob_cases(String mnth,String days, String years) throws InterruptedException{
		
		driver.navigate().refresh();
		Select select=new Select(birthmonth);
		select.selectByVisibleText(mnth);
		day.sendKeys(days);
		year.sendKeys(years);
		year.sendKeys(Keys.TAB);
		//Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(futurebday));
		String bdaycase=futurebday.getText();
		return bdaycase;
	}
	
	/*This method covers mandatory messages related cases*/
	public boolean mandatory_cases() throws InterruptedException{
		
		driver.navigate().refresh();
		continuebtn.click();
		Thread.sleep(2000);
		
		if(mandlname.getText().equalsIgnoreCase(prop.getProperty("mandmsg"))&&mandemail.getText().equalsIgnoreCase(prop.getProperty("mandmsg"))&&
				mandpasword.getText().equalsIgnoreCase(prop.getProperty("mandmsg"))&&mandphone.getText().equalsIgnoreCase(prop.getProperty("mandmsg"))&&
				mandbdate.getText().equalsIgnoreCase(prop.getProperty("mandmsg"))&&mandfname.getText().equalsIgnoreCase(prop.getProperty("mandmsg"))){
			
			return true;
		}
		return false;
	
	}
	
	/*This method covers Terms link page*/
	public String terms_conditions() throws InterruptedException{
		
		driver.navigate().refresh();
		String parentwin=ExcelUtils.parentwindow();
		terms.click();
		driver.switchTo().window(ExcelUtils.childwindow());
		String title=driver.getTitle();
		Thread.sleep(2000);
		driver.close();
		driver.switchTo().window(parentwin);
		return title;
	}
	
	/*This method covers Privacy policy page*/
	public String privacy_conditions() throws InterruptedException{
		
		driver.navigate().refresh();
		String parentwin=ExcelUtils.parentwindow();
		policy.click();
		driver.switchTo().window(ExcelUtils.childwindow());
		String title=driver.getTitle();	
		Thread.sleep(2000);
		driver.close();
		driver.switchTo().window(parentwin);
		return title;
	}
	
	
	
	}
