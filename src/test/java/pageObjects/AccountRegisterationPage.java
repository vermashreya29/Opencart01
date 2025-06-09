package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegisterationPage extends BasePage
{
   //Constructor
	public AccountRegisterationPage(WebDriver driver) 
	{
		super(driver);
	}
	
	//Locator
	@FindBy(xpath="//input[@id='input-firstname']") 
	WebElement Firstname;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement Lastname;
	
	@FindBy(xpath="//input[@id='input-email']") 
	WebElement Email;
	
	@FindBy(xpath="//input[@id='input-telephone']") 
	WebElement Telephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement Password;
	
	@FindBy(xpath="//input[@id='input-confirm']") 
	WebElement ConfirmPassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement agree;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement Continue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement confirmmessage;

	//ActionMethod
	public	void txt_firstname(String fname)
	{
		Firstname.sendKeys(fname);
	}
	
	public	void txt_lastname(String Lname)
	{
		Lastname.sendKeys(Lname);
	}
	
	
	public	void txt_email(String email)
	{
		Email.sendKeys(email);
	}
	
	
	public	void txt_telephone(String tele)
	{
		Telephone.sendKeys(tele);
	}
	
	public	void txt_password(String passwrd)
	{
		Password.sendKeys(passwrd);
	}
	
	public	void txt_Confirmpassword(String passwrd)
	{
		ConfirmPassword.sendKeys(passwrd);
	}
	
	public	void checkpolicy()
	{
		agree.click();
	}
	
	
	public	void Clkcontinue()
	{
		Continue.click();
		//Continue.submit();
		/*Actions act=new Actions(driver);
		act.moveToElement(Continue).click().build();*/
		//Continue.sendKeys(Keys.RETURN);
	}
   
	public String getconfirmationMessage()
	{
		try {
			return  (confirmmessage.getText()); 
		}
		catch(Exception e)
		{
			return (e.getMessage());
		}
	}
	

}
