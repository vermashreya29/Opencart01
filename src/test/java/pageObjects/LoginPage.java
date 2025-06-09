package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage
{
//constructor
	public LoginPage(WebDriver driver)
	{
		super(driver);
		
	}
	
	
	//Locator
	@FindBy(xpath="//input[@id='input-email']") 
	WebElement txt_username;
	
	@FindBy(xpath="//input[@id='input-password']") 
	WebElement txt_password;
	
	@FindBy(xpath="//input[@value='Login']") 
	WebElement btn_login;
	
	
	
	//Action Methods
	
	public void setusername(String uname)
	{
		txt_username.sendKeys(uname);
	}
	
	public void setpassword(String pword)
	{
		txt_password.sendKeys(pword);
	}
	
	
	public void Clk_login()
	{
		btn_login.click();;
	}

}
