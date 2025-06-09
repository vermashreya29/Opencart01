package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage
{

	//Constructor
	public HomePage(WebDriver driver) 
	{
		super(driver);
		
	}
	
	//Locators
	
	@FindBy(xpath="//span[normalize-space()='My Account']") WebElement MyAccount;
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Register']") WebElement Register;
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']") WebElement Login;
	
	//Action methods
	
   public	void ClkMyAccount()
	{
	   MyAccount.click();
	}
   
   
   public	void ClkRegister()
	{
	   Register.click();
	}
   
   
   public	void ClkLogin()
	{
	   Login.click();
	}

}
