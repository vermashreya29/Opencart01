package testCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseClass {
	
public	static WebDriver driver;
public Logger logger;
public Properties p;
    @Parameters({"os","browser"})
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	void setup(String OS,String br) throws IOException
	{
    	FileInputStream File=new FileInputStream("./src/test/resources/Config.properties");
    	p=new Properties();
    	p.load(File);
    	
    	
		logger=LogManager.getLogger(this.getClass());//will generate log of executing class
		
		if(p.getProperty("Execution_envir").equalsIgnoreCase("Remote")	)	
		{
			DesiredCapabilities cap=new DesiredCapabilities();
			//cap.setPlatform(Platform.WIN11);
			//cap.setBrowserName("chrome");
			
			//os
			if(OS.equalsIgnoreCase("Windows"))
			{
				cap.setPlatform(Platform.WIN11);
			}
			
			else if(OS.equalsIgnoreCase("MAC"))
			{
				cap.setPlatform(Platform.MAC);
			}
			else if(OS.equalsIgnoreCase("linux"))
			{
				cap.setPlatform(Platform.LINUX);
			}
			else
			{
				System.out.println("Os Mismatching");
				return;
			}
			
			//browser
			switch(br.toLowerCase()) {
		    case "chrome": cap.setBrowserName("chrome"); break;
		    case "edge": cap.setBrowserName("MicrosoftEdge"); break;
		    case "linux": cap.setBrowserName("Linux"); break;
		    // add support if needed: case "firefox": cap.setBrowserName("firefox"); break;
		    default: System.out.println("Browser Mismatching"); return;
		}

			driver = new RemoteWebDriver(new URL("http://192.168.178.94:4444/wd/hub"), cap);

		}
		
		
		
		
		else if(p.getProperty("Execution_envir").equalsIgnoreCase("local")	)	
		{
		switch(br.toLowerCase())
		{
		case "chrome":driver=new ChromeDriver();break;
		case "firefox":driver=new FirefoxDriver();break;
		case "edge":driver=new EdgeDriver();break;
		default: System.out.println("Invalid browser selected");return;
		
		}
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		//driver.get("https://tutorialsninja.com/demo/index.php?route=account/logout");//hardcoded
		driver.get(p.getProperty("appurl"));//reading url from properties file
		driver.manage().window().maximize();
		
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master"})
	void teardown()
	{
	driver.close();	
	}
	
	
	
	public String randomalphabets()
	{
		String randomalpha= RandomStringUtils.randomAlphabetic(5);
		return randomalpha;
	}

	public String randomnumeric()
	{
		String randomnum= RandomStringUtils.randomNumeric(9);
		return randomnum;
	}

	public String randomAlphanumeric()
	{
		//String randomalphanum= RandomStringUtils.randomAlphanumeric(8);
		String randomalpha= RandomStringUtils.randomAlphabetic(5);
		String randomnum= RandomStringUtils.randomNumeric(9);
		return (randomalpha+randomnum);
	}

	public String TakingScreenShot(String sName) {
        String timestamp = new SimpleDateFormat("dd.MM.yy.HH.mm.ss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + sName + "_" + timestamp + ".png";
        File targetFile = new File(targetFilePath);
        sourceFile.renameTo(targetFile);

        return targetFilePath;
    }
}
