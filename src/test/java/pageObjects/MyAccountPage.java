package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage 
{
//Constructor
	public MyAccountPage(WebDriver driver)
	{
		super(driver);
		
	}
	
	//Locator
	@FindBy(xpath="//h2[normalize-space()='My Account']")
	WebElement Accountdisplaying;
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']") 
	WebElement btnlogout;
	
	
	//Action Method
	public boolean IsMyAccountDisplayed()
	{
		try {
		return (Accountdisplaying.isDisplayed());
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public void Clklogoutbtn()
	{
		btnlogout.click();
	}

}
