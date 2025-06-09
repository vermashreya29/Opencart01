package utilities;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCases.BaseClass;

public class ExtentreportManager implements ITestListener
{
	public ExtentSparkReporter sparkreport;
	public ExtentReports extent;
	public ExtentTest test;
	
 String reportname;
 
	public void onStart(ITestContext context)//ITestContext context-contains details of running test method
	
	{
		SimpleDateFormat df=new SimpleDateFormat("dd.mm.yy.hh.mm.ss");//will provide date in this format
		Date dt=new Date();
		String timestamp=df.format(dt);//will generate current date in this format
	    //or new SimpleDateFormat("dd.mm.yy.hh.mm.ss").format(new Date());
		
		//Generate report name with timestamp
		reportname="Test-Report-"+timestamp+".html";
		sparkreport=new ExtentSparkReporter(".\\reports\\"+reportname);
		//sparkreport=new ExtentSparkReporter("C:\\Users\\verma\\eclipse\\SeleniumCode\\Reports\\Report.html");//object of ExtentSparkReporter
		
		
		sparkreport.config().setDocumentTitle("Automation Testing Report");//Set Document Title
		sparkreport.config().setReportName("Functional Testing");//Set Report name
		sparkreport.config().setTheme(Theme.DARK);//Set theme as Dark
		
		extent=new ExtentReports();
		extent.attachReporter(sparkreport);
		extent.setSystemInfo("Application Automated", "Opencart");
		extent.setSystemInfo("Tester Name",System.getProperty("user.name"));//currently using user name will be displayed
		extent.setSystemInfo("Env Name", "Testing Environment");
		
		String os = context.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System","os");
		
		String browser = context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Operating System","browser");
		
		List<String> groupname=context.getCurrentXmlTest().getIncludedGroups();
		if(!groupname.isEmpty())
		{
			extent.setSystemInfo("Included groups",groupname.toString());
			
		}
		
	}

	public void onTestSuccess(ITestResult result) 
	{
	  test= extent.createTest(result.getTestClass().getName());//create new entry in report
	  test.assignCategory(result.getMethod().getGroups());//to display groups in report
	  test.log(Status.PASS, "Test Case Passed:"+result.getName());
	}

	public void onTestFailure(ITestResult result)
	{
		test= extent.createTest(result.getTestClass().getName());//create new entry in report
		test.assignCategory(result.getMethod().getGroups());//to display groups in report
		  
	    test.log(Status.FAIL, "Test Case Failed:"+result.getName());
	    test.log(Status.FAIL, "Reason for failure:"+result.getThrowable());
	    
	    
	    //Attach ss
	    try {
	    BaseClass bs=new BaseClass();
	   String imgpath= bs.TakingScreenShot(result.getName());
	   test.addScreenCaptureFromBase64String(imgpath);
	    }
	    catch(Exception e1)
	    {
	    	e1.printStackTrace();
	    }
	    
	  }


	public void onTestSkipped(ITestResult result) 
	{
	test=extent.createTest(result.getTestClass().getName());
	test.assignCategory(result.getMethod().getGroups());
	test.log(Status.SKIP, "Test Case Skipped:"+result.getName());
	test.log(Status.SKIP, "Reason for failure:"+result.getThrowable().getMessage());
	  }



	public void onFinish(ITestContext context) 
	{
	   extent.flush();//
	  }

	}



