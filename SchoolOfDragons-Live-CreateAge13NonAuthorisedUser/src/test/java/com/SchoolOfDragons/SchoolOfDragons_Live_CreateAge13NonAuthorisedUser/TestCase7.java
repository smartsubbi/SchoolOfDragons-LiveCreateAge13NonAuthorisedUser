package com.SchoolOfDragons.SchoolOfDragons_Live_CreateAge13NonAuthorisedUser;

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
		
		driver = BrowserFactory.getBrowser("chrome");
		logger.log(LogStatus.INFO, "Browser is up and running");
		String browserOpenedScreenshot=logger.addScreenCapture(CaptureScreenshot.takeScreenshot(driver, "Application"));
		logger.log(LogStatus.INFO, browserOpenedScreenshot);		
		driver.get("http://www.schoolofdragons.com");		
		logger.log(LogStatus.INFO, "Entered Authentication credentials successfully and Url is Loading");			
		CommonHeader header = PageFactory.initElements(driver, CommonHeader.class);		
		logger.log(LogStatus.INFO, "Home Page Title is verified");
		String homePageScreenshot=logger.addScreenCapture(CaptureScreenshot.takeScreenshot(driver, "Application"));
		logger.log(LogStatus.INFO, homePageScreenshot);				
		header.clickHeaderCreateAnAccountLink();
		logger.log(LogStatus.INFO, "Clicked the Create an Account Link on the Homepage header");		
		Thread.sleep(5000);		
		SignUpPage signUpPage = PageFactory.initElements(driver, SignUpPage.class);		
		logger.log(LogStatus.INFO, "Sign Up Page Title is verified");		
		String signUpPageScreenshot=logger.addScreenCapture(CaptureScreenshot.takeScreenshot(driver, "Application"));
		logger.log(LogStatus.INFO, signUpPageScreenshot);		
		signUpPage.confirmButtonDisabledElementValidation();
		logger.log(LogStatus.INFO, "Verified if the Confirm Button is disabled");		
		signUpPage.selectAge(age);
		logger.log(LogStatus.INFO, "Select age 13");		
		signUpPage.selectedAgeElementValidation(age);
		logger.log(LogStatus.INFO, "Verify is age selected is 13");		
		signUpPage.confirmButtonEnabledElementValidation();
		logger.log(LogStatus.INFO, "Verify if the confirm button is enabled");		
		String signUpPageAfterAgeSelection=logger.addScreenCapture(CaptureScreenshot.takeScreenshot(driver, "Application"));
		logger.log(LogStatus.INFO, signUpPageAfterAgeSelection);		
		signUpPage.clickConfirmButton();
		logger.log(LogStatus.INFO, "Click on the Confirm Button");		
		Thread.sleep(5000);		
		CreateAnAccountPage createAnAccountPage = PageFactory.initElements(driver, CreateAnAccountPage.class);		
		logger.log(LogStatus.INFO, "Create an account Page Title is verified");
		String createAnAccountPageScreenshot=logger.addScreenCapture(CaptureScreenshot.takeScreenshot(driver, "Application"));
		logger.log(LogStatus.INFO, createAnAccountPageScreenshot);		
		createAnAccountPage.selectedAgeElementValidation(age);
		logger.log(LogStatus.INFO, "Verify is age selected is 12");		
		String userName = RandomStringGenerator.generateRandomString();
		String emailAddress = GetNewEmail.getNewEmail(userName);		
		createAnAccountPage.enterEmail(emailAddress);
		logger.log(LogStatus.INFO, "Enter Email address");		
		createAnAccountPage.enterUserName(userName);
		logger.log(LogStatus.INFO, "Enter Username");		
		createAnAccountPage.enterPassword("123456");
		logger.log(LogStatus.INFO, "Enter Password");		
		String createAnAccountPageAfterEnteringAllDetailsScreenshot=logger.addScreenCapture(CaptureScreenshot.takeScreenshot(driver, "Application"));
		logger.log(LogStatus.INFO, createAnAccountPageAfterEnteringAllDetailsScreenshot);		
		createAnAccountPage.clickFinishAndPlayButton();		
		Thread.sleep(5000);		
		AlmostDonePopUp almostDonePopUp = PageFactory.initElements(driver, AlmostDonePopUp.class);
		almostDonePopUp.verifyAlmostDonePopUpTextsforPlayer();
		String almostDonePeopUpScreenshot=logger.addScreenCapture(CaptureScreenshot.takeScreenshot(driver, "Application"));
		logger.log(LogStatus.INFO, almostDonePeopUpScreenshot);		
		almostDonePopUp.clickAlmostDonePopUpPlayNowButton();		
		Thread.sleep(5000);		
		AfterLoggedInPage afterLoggedInPage = PageFactory.initElements(driver, AfterLoggedInPage.class);        			
		afterLoggedInPage.currentlyLoggedInText(userName).isDisplayed();
		afterLoggedInPage.afterLoggedInSuccessfully();
		logger.log(LogStatus.INFO, "After Logged in Page is verified successfully");
		String afterLoggedinPageScreenshot=logger.addScreenCapture(CaptureScreenshot.takeScreenshot(driver, "Application"));
		logger.log(LogStatus.INFO, afterLoggedinPageScreenshot);		
		count = count +1;		
		ExcelDataProvider excel = new ExcelDataProvider();	       
		BrowserFactory.closeBrowser();		
		logger.log(LogStatus.INFO, "Quitting the Browser Opened");			
		if(count==1)
		{
			WebDriver driver = BrowserFactory.getBrowser("chrome");		
			String emailReportPathToSend = ExtentManager.finalPath;
			String mailContent = "Non authorised user has been created.\n\nYou can refer to the below report for the run result\n"+emailReportPathToSend+"\nBelow are the details of the non authorised user created : \n";
			excel.writeToNextFreeCell(2,0,userName);		
			excel.writetoexcel();
			SendMail.sendMail(driver,subject,mailContent,age,userName,"123456",emailAddress,"No");
		}
		else if(count==2)
		{
			WebDriver driver = BrowserFactory.getBrowser("chrome");	
			String emailReportPathToSend = ExtentManager.finalPath;
			String mailContent = "Authorised user has been created.\n\nYou can refer to the below report for the run result\n"+emailReportPathToSend+"\nBelow are the details of the Authorised user created : \n";
			excel.writeToNextFreeCell(3,0,userName);		
			excel.writetoexcel();
			SendMail.sendMail(driver,subject,mailContent,age,userName,"123456",emailAddress,"Yes");			
		}
		else if (count==0)
		{
			WebDriver driver = BrowserFactory.getBrowser("chrome");		
			String emailReportPathToSend = ExtentManager.finalPath;
			String mailContent = "User cannot be created as there are issues You can refer to the below report for the run result\n"+emailReportPathToSend;
			SendMail.sendMail(driver,subject,mailContent,"Not Created","Not Created","Not Created","Not Created","Not Created");
			
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
