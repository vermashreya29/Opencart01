package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC_002LoginTest extends BaseClass
{
	@Test(groups={"Sanity","Master"})
	public void Verify_login()
	{
		logger.info("***Start of TC_002LoginTest*** ");
		try {
		//home Page
		HomePage hp=new HomePage(driver);
		hp.ClkMyAccount();
		hp.ClkLogin();
		
		//loginpage
		LoginPage lp=new LoginPage(driver);
		lp.setusername(p.getProperty("username"));
		lp.setpassword(p.getProperty("password"));
		lp.Clk_login();
		
		//MyAccount page
		MyAccountPage map=new MyAccountPage(driver);
        boolean Status=map.IsMyAccountDisplayed();
        Assert.assertEquals(Status, true);
        // or Assert.assertTrue(Status);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
        
		logger.info("***End of TC_002LoginTest*** ");
		
		
		
		
	}
	
	
}
