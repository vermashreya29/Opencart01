package testCases;

import org.testng.Assert;
import org.testng.annotations.*;

import pageObjects.AccountRegisterationPage;
import pageObjects.HomePage;

public class TC001_RegistrationPage extends BaseClass
{
	


@Test(groups={"Regression","Master"})
void Registeration()
{
	logger.info("***Start of Execution of TC001_RegistrationPage");
	try {
	
	HomePage hp=new HomePage(driver);
	
	logger.info("Clicking on My Account link");
	hp.ClkMyAccount();
	
	logger.info("Clicking on My Register link");
	hp.ClkRegister();
	
	logger.info("Providing customer Details");
	AccountRegisterationPage rp=new AccountRegisterationPage(driver);	
     rp.txt_firstname(randomalphabets().toUpperCase());
     rp.txt_lastname(randomalphabets().toUpperCase());
     rp.txt_email(randomalphabets()+"@gmail.com");
     rp.txt_telephone(randomnumeric());
     
     String pass=randomAlphanumeric();
     rp.txt_password(pass);
     rp.txt_Confirmpassword(pass);
     
     rp.checkpolicy();
     rp.Clkcontinue();
     
     logger.info("Validating confirmation Message");
     String confirmmsg=  rp.getconfirmationMessage();
     if(confirmmsg.equals("Your Account Has Been Created!"))
     {
    	 Assert.assertTrue(true);
     }
     else
     {
    	 Assert.assertTrue(false);
    	 logger.error("Test case failed");
 		logger.debug("debugging required");
     }
   
	}
	catch(Exception e)
	{
		
		Assert.fail();
	}
	logger.info("***Finish of Execution of TC001_RegistrationPage");
    
}

}
