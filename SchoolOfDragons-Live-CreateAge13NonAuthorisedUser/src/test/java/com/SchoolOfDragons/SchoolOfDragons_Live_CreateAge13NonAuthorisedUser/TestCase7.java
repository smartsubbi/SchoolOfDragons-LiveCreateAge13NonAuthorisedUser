package com.SchoolOfDragons.SchoolOfDragons_Live_CreateAge13NonAuthorisedUser;

import java.net.InetAddress;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import DataProvider.ExcelDataProvider;
import Factory.BrowserFactory;
import Pages.ActivateAccountPage;
import Pages.AfterLoggedInPage;
import Pages.AlmostDonePopUp;
import Pages.CommonHeader;
import Pages.CreateAnAccountPage;
import Pages.SignUpPage;
import ReUse.AuthoriseMailMailinator;
import ReUse.SendMail;
import ReUse.writeToTextFile;
import Utility.CaptureScreenshot;
import Utility.GetNewEmail;
import Utility.RandomStringGenerator;

public class TestCase7 
{
	WebDriver driver;
	ExtentReports report;
	ExtentTest logger;	
	String age = "13";
	int count = 0;	
	String subject = "Create Age 13 Player (Non Authorized User)";
	
	@BeforeClass
	public void setUp() throws Throwable
	{		
		report=ExtentManager.Instance();
	}
	
	@Parameters(value="Category")
	@Test
	public void createNewUserAge12() throws Throwable
	{
		logger = report.startTest("Test Case 7: School Of Dragons - Live - Create Age 13 Player (Non Authorized User) ","This will verify if user can create an non Authorized user with age 13").assignCategory("none");
		
		driver = BrowserFactory.getBrowser("firefox");
		logger.log(LogStatus.INFO, "Browser is up and running");
		String browserOpenedScreenshot=logger.addScreenCapture(CaptureScreenshot.takeScreenshot(driver, "browserOpenedScreenshot"));
		logger.log(LogStatus.INFO, browserOpenedScreenshot);		
		driver.get("http://www.schoolofdragons.com");		
		logger.log(LogStatus.INFO, "Entered Authentication credentials successfully and Url is Loading");			
		CommonHeader header = PageFactory.initElements(driver, CommonHeader.class);		
		
		String homePageScreenshot=logger.addScreenCapture(CaptureScreenshot.takeScreenshot(driver, "homePageScreenshot"));
		logger.log(LogStatus.INFO, homePageScreenshot);				
		header.clickHeaderCreateAnAccountLink();
		logger.log(LogStatus.INFO, "Clicked the Create an Account Link on the Homepage header");		
		Thread.sleep(5000);		
		SignUpPage signUpPage = PageFactory.initElements(driver, SignUpPage.class);		
				
		String signUpPageScreenshot=logger.addScreenCapture(CaptureScreenshot.takeScreenshot(driver, "signUpPageScreenshot"));
		logger.log(LogStatus.INFO, signUpPageScreenshot);		
		signUpPage.confirmButtonDisabledElementValidation();
		logger.log(LogStatus.INFO, "Verified if the Confirm Button is disabled");		
		signUpPage.selectAge(age);
		logger.log(LogStatus.INFO, "Select age 13");		
		signUpPage.selectedAgeElementValidation(age);			
		signUpPage.confirmButtonEnabledElementValidation();
		logger.log(LogStatus.INFO, "Verify if age selected is 13 and confirm button is enabled");		
		String signUpPageAfterAgeSelection=logger.addScreenCapture(CaptureScreenshot.takeScreenshot(driver, "signUpPageAfterAgeSelection"));
		logger.log(LogStatus.INFO, signUpPageAfterAgeSelection);		
		signUpPage.clickConfirmButton();
		logger.log(LogStatus.INFO, "Click on the Confirm Button");		
		Thread.sleep(5000);		
		CreateAnAccountPage createAnAccountPage = PageFactory.initElements(driver, CreateAnAccountPage.class);		
		
		String createAnAccountPageScreenshot=logger.addScreenCapture(CaptureScreenshot.takeScreenshot(driver, "createAnAccountPageScreenshot"));
		logger.log(LogStatus.INFO, createAnAccountPageScreenshot);		
		createAnAccountPage.selectedAgeElementValidation(age);
		logger.log(LogStatus.INFO, "Verify if age selected is 12");		
		String userName = RandomStringGenerator.generateRandomString();
		String emailAddress = GetNewEmail.getNewEmail(userName);		
		createAnAccountPage.enterEmail(emailAddress);
		logger.log(LogStatus.INFO, "Enter Email address : "+emailAddress);		
		createAnAccountPage.enterUserName(userName);
		logger.log(LogStatus.INFO, "Enter Username : "+userName);		
		createAnAccountPage.enterPassword("123456");
		logger.log(LogStatus.INFO, "Enter Password : 123456");		
		String createAnAccountPageAfterEnteringAllDetailsScreenshot=logger.addScreenCapture(CaptureScreenshot.takeScreenshot(driver, "createAnAccountPageAfterEnteringAllDetailsScreenshot"));
		logger.log(LogStatus.INFO, createAnAccountPageAfterEnteringAllDetailsScreenshot);		
		createAnAccountPage.clickFinishAndPlayButton();		
		Thread.sleep(5000);		
		AlmostDonePopUp almostDonePopUp = PageFactory.initElements(driver, AlmostDonePopUp.class);
		almostDonePopUp.verifyAlmostDonePopUpTextsforPlayer();
		String almostDonePeopUpScreenshot=logger.addScreenCapture(CaptureScreenshot.takeScreenshot(driver, "almostDonePeopUpScreenshot"));
		logger.log(LogStatus.INFO, almostDonePeopUpScreenshot);		
		almostDonePopUp.clickAlmostDonePopUpPlayNowButton();		
		Thread.sleep(5000);		
		AfterLoggedInPage afterLoggedInPage = PageFactory.initElements(driver, AfterLoggedInPage.class);        			
		afterLoggedInPage.currentlyLoggedInText(userName).isDisplayed();
		afterLoggedInPage.afterLoggedInSuccessfully();
		logger.log(LogStatus.INFO, "After Logged in Page is verified successfully");
		String afterLoggedinPageScreenshot=logger.addScreenCapture(CaptureScreenshot.takeScreenshot(driver, "afterLoggedinPageScreenshot"));
		logger.log(LogStatus.INFO, afterLoggedinPageScreenshot);		
		count = count +1;		
		ExcelDataProvider excel = new ExcelDataProvider();	       
		BrowserFactory.closeBrowser();		
		logger.log(LogStatus.INFO, "Quitting the Browser Opened");
		InetAddress address = InetAddress.getLocalHost(); 
		String hostIP = address.getHostAddress();	 	    
		String pathToExcel = "file://"+hostIP+"/Usernames%20Excels/";
		if(count==1)
		{
			//WebDriver driver = BrowserFactory.getBrowser("chrome");		
			String emailReportPathToSend = ExtentManager.finalPath;
			String mailContent = "Non authorised user has been created, You can refer to the below report for the run result. Below are the details of the non authorised user created : ";
			excel.writeToNextFreeCell(2,0,userName);		
			excel.writetoexcel();
			//SendMail.sendMail(driver,subject,mailContent,age,userName,"123456",emailAddress,"No");
			
			String Line1 = mailContent;
			String Line2 = "Created Age 13 Player (Not Authorized User) is : "+userName;
			String Line3 = "Created Age 13 Player (Authorized User) password is : 123456";
			String Line4 = "Created Age 13 Player (Authorized User) email id is : "+emailAddress;
			String Line5 = "The report can be found here : "+emailReportPathToSend;
			String Line6 = "Path to the Excel file : "+pathToExcel;
			
			System.out.println("=====================================================================");
			System.out.println("Created Age 13 Player (Not Authorised User) is : "+userName);
			System.out.println("Created Age 13 Player (Not Authorised User) password is : 123456");
			System.out.println("Created Age 13 Player (Not Authorised User) email id is : "+emailAddress);
			System.out.println("The report can be found here : "+emailReportPathToSend);
			System.out.println("Path to the Excel file : "+pathToExcel);
			System.out.println("=====================================================================");
			
			logger.log(LogStatus.INFO,"=====================================================================");
			logger.log(LogStatus.INFO,"Created Age 13 Player (Not Authorised User) is : "+userName);
			logger.log(LogStatus.INFO,"Created Age 13 Player (Not Authorised User) password is : 123456");
			logger.log(LogStatus.INFO,"Created Age 13 Player (Not Authorised User) email id is : "+emailAddress);
			logger.log(LogStatus.INFO,"The report can be found here : "+emailReportPathToSend);
			logger.log(LogStatus.INFO,"Path to the Excel file : "+pathToExcel);
			logger.log(LogStatus.INFO,"=====================================================================");
			
			writeToTextFile.writeToTempTextFile(Line1,Line2, Line3, Line4, Line5, Line6);
		}
		else if(count==2)
		{
			//WebDriver driver = BrowserFactory.getBrowser("chrome");	
			String emailReportPathToSend = ExtentManager.finalPath;
			//String mailContent = "Authorised user has been created.\n\nYou can refer to the below report for the run result\n"+emailReportPathToSend+"\nBelow are the details of the Authorised user created : \n";
			excel.writeToNextFreeCell(3,0,userName);		
			excel.writetoexcel();
			//SendMail.sendMail(driver,subject,mailContent,age,userName,"123456",emailAddress,"Yes");	
			System.out.println("=====================================================================");
			System.out.println("Created Age 13 Player (Authorized User) is : "+userName);
			System.out.println("Created Age 13 Player (Authorized User) password is : 123456");
			System.out.println("Created Age 13 Player (Authorized User) email id is : "+emailAddress);	
			System.out.println("The report can be found here : "+emailReportPathToSend);
			System.out.println("Path to the Excel file : "+pathToExcel);
			System.out.println("=====================================================================");
			logger.log(LogStatus.INFO,"=====================================================================");
			logger.log(LogStatus.INFO,"Created Age 13 Player (Authorized User) is : "+userName);
			logger.log(LogStatus.INFO,"Created Age 13 Player (Authorized User) password is : 123456");
			logger.log(LogStatus.INFO,"Created Age 13 Player (Authorized User) email id is : "+emailAddress);	
			logger.log(LogStatus.INFO,"Path to the Excel file : "+pathToExcel);
			logger.log(LogStatus.INFO,"=====================================================================");		
			
		}
		else if (count==0)
		{
			//WebDriver driver = BrowserFactory.getBrowser("chrome");		
			String emailReportPathToSend = ExtentManager.finalPath;
			//String mailContent = "User cannot be created as there are issues You can refer to the below report for the run result\n"+emailReportPathToSend;
			//SendMail.sendMail(driver,subject,mailContent,"Not Created","Not Created","Not Created","Not Created","Not Created");
			System.out.println("=====================================================================");
			System.out.println("User cannot be created as there are issues You can refer to the below report for the run result\n"+emailReportPathToSend);				
			System.out.println("=====================================================================");	
			logger.log(LogStatus.INFO,"=====================================================================");
			logger.log(LogStatus.INFO,"User cannot be created as there are issues You can refer to the below report for the run result\n"+emailReportPathToSend);				
			logger.log(LogStatus.INFO,"=====================================================================");	
			
		}
		BrowserFactory.closeBrowser();
	}

	@AfterMethod
	public void afterTest(ITestResult result) throws Throwable
	{
		if(result.getStatus()==ITestResult.FAILURE)	
		{		
			logger.log(LogStatus.FAIL, "<pre>" + result.getThrowable().getMessage() + "</pre>");
			String failureScreenshot=logger.addScreenCapture(CaptureScreenshot.takeScreenshot(driver,result.getName()));			  
			logger.log(LogStatus.FAIL, failureScreenshot);				
		}		
	}
	
	@AfterClass
	public void tearDown()
	{
		report.endTest(logger);
		report.flush();	
		BrowserFactory.closeBrowser();
		report.close();				
	}		
}
