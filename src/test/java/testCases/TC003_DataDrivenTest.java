package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class TC003_DataDrivenTest extends BaseClass
{
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="Datadriven")//dataProviderClass=DataProviders.class used as dp is created in other packagee
	public void ValidatingDataDriven(String username,String password,String exp)
	{

		logger.info("***Start of TC003_DataDrivenTest***");
		//home Page
		HomePage hp=new HomePage(driver);
		hp.ClkMyAccount();
		hp.ClkLogin();
		
		//loginpage
		LoginPage lp=new LoginPage(driver);
		lp.setusername(username);
		lp.setpassword(password);
		lp.Clk_login();
		
		//MyAccount page
		MyAccountPage map=new MyAccountPage(driver);
        boolean target_page=map.IsMyAccountDisplayed();
        
        /*valid-->test pass->login success->logout
                -->test fail*
                
          invalid-->login success->logout->test fail
                  -->login fails--test pass*/
        
        if(exp.equalsIgnoreCase("Valid"))
        {
        	if(target_page==true)
        	{
        		map.Clklogoutbtn();
        		Assert.assertTrue(true);
        	}
        	else
        	{
        		Assert.assertTrue(false);
        	}
        	
        	
        }
        
        if(exp.equalsIgnoreCase("Invalid"))
        {
        	if(target_page==true)
        	{
        		map.Clklogoutbtn();
        		Assert.assertTrue(false);
        	}
        	else
        	{
        		Assert.assertTrue(true);
        	}
        	
        	
        }
        
        logger.info("***Finish of TC003_DataDrivenTest***");

	}
}
