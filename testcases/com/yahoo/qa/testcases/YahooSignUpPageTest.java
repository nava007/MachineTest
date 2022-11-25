package com.yahoo.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.yahoo.qa.base.BaseClass;
import com.yahoo.qa.pages.YahooSignUpPage;
import com.yahoo.qa.utilities.ExcelUtils;



public class YahooSignUpPageTest extends BaseClass {

	YahooSignUpPage signup;
	
	/*This method initialize the browser and enter the particular url from properties file/added*/
	@BeforeClass
	public void setup(){
		
		initialisation();
		signup=new YahooSignUpPage();
	}
	
	/*This method reads data from the excel*/
	@DataProvider(name="testdata")
	public Object[][] getdata(){
		
		Object ob[][]=ExcelUtils.excelread(path0+"/DataInput/TestCase and RegistrationData.xlsx", 1);
		return ob;
	}
	
	/*This case checks mail id entered in mail address field already used or not*/
	@Test(priority=0,enabled=true)
	public void TC_YAH_001_mail_already_used() throws InterruptedException{
		
		
		String actual=signup.mail_cases(prop.getProperty("mailalreadyval"));
		String actual01=actual.replaceAll("Sign in.", "");
		actual01.trim();

		Assert.assertEquals(actual01.trim(),prop.getProperty("mailalready"));
		
	}
	
	/*This case checks whether mail id field accepts data with low strength*/
	@Test(priority=1,enabled=true)
	public void TC_YAH_002_mail_strength() throws InterruptedException{
		
				
		String actual=signup.mail_cases(prop.getProperty("mailstrengthval"));
		
		Assert.assertEquals(actual,prop.getProperty("mailstrength"));

	}
	
	/*This case checks mail id field accepts special characters*/
	@Test(priority=2,enabled=true)
	public void TC_YAH_003_mail_special_char() throws InterruptedException{
		
		String actualresult=signup.mail_cases(prop.getProperty("mailspecialcharval"));
		
		Assert.assertEquals(actualresult,prop.getProperty("mailspecialchar"));

	}
	
	/*This case checks the mail id fields with digits*/
	@Test(priority=3,enabled=true)
	public void TC_YAH_004_mail_digits_input() throws InterruptedException{
		
		String actualresult=signup.mail_cases(prop.getProperty("maildigitval"));
		
		Assert.assertEquals(actualresult, prop.getProperty("maildigit"));
		
	}
	
	/*This case checks password fields with weak password*/
	@Test(priority=4,enabled=true)
	public void TC_YAH_005_password_strength() throws InterruptedException{
		
		driver.navigate().refresh();
		String actualresult=signup.password_cases(prop.getProperty("pwdstrgthval"));
		
		Assert.assertEquals(actualresult, prop.getProperty("pwdstrgth"));
		
	}
	
	/*This case checks when we enter password same as of name field*/
	@Test(priority=5,enabled=true)
	public void TC_YAH_006_password_similar_name() throws InterruptedException{
		
		
		signup.lastname.sendKeys(prop.getProperty("pwdnameval"));
		String actualresult=signup.password_cases(prop.getProperty("pwdnameval"));
		
		Assert.assertEquals(actualresult, prop.getProperty("pwdname"));
		
	}
	
	/*This case checks the mobile field with an invalid entry*/
	@Test(priority=6,enabled=true)
	public void TC_YAH_007_mobile_invalid() throws InterruptedException{
		
		String actualresult=signup.mobile_cases(prop.getProperty("mobileinvalidval"));

		Assert.assertEquals(actualresult, prop.getProperty("mobileinvalid"));
		
	}
	
	/*This case checks when we enter data in mobile field which is higher than the limit*/
	@Test(priority=7,enabled=true)
	public void TC_YAH_008_mobile_strength() throws InterruptedException{
		
		String actualresult=signup.mobile_cases(prop.getProperty("mobilestrngthval"));
		
		Assert.assertEquals(actualresult, prop.getProperty("mobilestrngth"));
		
	}
	
	/*This case checks when dob field with a future date*/
	@Test(priority=8,enabled=true)
	public void TC_YAH_009_bday_futuredate() throws InterruptedException{
		
		String actualresult=signup.dob_cases(prop.getProperty("dobmnth"),prop.getProperty("dobday"),prop.getProperty("dobyear"));

		Assert.assertEquals(actualresult, prop.getProperty("dobmsg"));
		
	}
	
	/*This case checks when we enter alpha numeric character in dob fields*/
	@Test(priority=9,enabled=true)
	public void TC_YAH_010_bday_alphanumeric() throws InterruptedException{
		
		String actualresult=signup.dob_cases(prop.getProperty("dobmnth"),prop.getProperty("dobdayal"),prop.getProperty("dobyearal"));
		
		Assert.assertEquals(actualresult, prop.getProperty("dobmsg"));
		
	}
	
	/*This case checks whether mandatory messages are displaying for the required fields when there is no value entered*/
	@Test(priority=10,enabled=true)
	public void TC_YAH_011_mandatory_field_verify() throws InterruptedException{
		
		boolean e=signup.mandatory_cases();
		
		Assert.assertEquals(e, true);
		
	}
	
	/*This case checks the Terms link is redirecting to the correct page*/
	@Test(priority=11,enabled=true)
	public void TC_YAH_012_terms_link_verify() throws InterruptedException{
				
		String actualresult=signup.terms_conditions();
		
		Assert.assertEquals(actualresult, prop.getProperty("termsmsg"));

	}
	
	/*This case checks the Privacy policy link is redirecting to the correct page*/
	@Test(priority=12,enabled=true)
	public void TC_YAH_013_privacy_link_verify() throws InterruptedException{
		
		
		String actualresult=signup.privacy_conditions();
		
		Assert.assertEquals(actualresult, prop.getProperty("privacymsg"));

	}
	
	/*This case checks the registration process until the verification page and this fills the Sign up form after fetching the data from excel file*/
	@Test(dataProvider="testdata",priority=13,enabled=true)
	public void TC_YAH_014_registration_upto_verificationpage_test(String FirstName, String LastName, String Email, String	Password, String Mobile, String	BirthMonth, String Day, String	Year) throws InterruptedException{
		
		
		signup.fill_signup_form(FirstName, LastName, Email, Password, Mobile, BirthMonth, Day, Year);
		
		String actualresult=driver.getTitle();
		/*Assertion checks whether the expected and actual value of page title are same*/
		Assert.assertEquals(actualresult,"Yahoo");
	}
	

	
	@AfterClass
	public void teardown(){
		
		driver.quit();
	}
}
