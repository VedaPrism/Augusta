package Generic_Library;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class Basefunctions {
	
	public WebDriver em;
	public static ExtentReports extentReports;
	public ExtentTest startTest;
	public String order;
	public String tc_id;
	public String browser_type;
	
	@BeforeSuite(groups = {"SMK", "REG", "UAT"})
	public void creatingreport(){
		
//		Creating the report file
		extentReports = new ExtentReports("D:\\mayil\\HPEdrive\\Selenium Scripts\\Report\\Aliexpress_"+get_datetimestamp()+".html",false);
	}
	
	@Parameters({"browser"})
	@BeforeMethod(groups = {"SMK", "REG", "UAT"})
	public void browserlaunch(String btype) throws Exception{
		
		browser_type = btype;                       //Assigning the browser type for report purpose 
		
		if(btype.equalsIgnoreCase("ch")){            //Choosing the browser as per user through testsuite
			
			System.setProperty("webdriver.chrome.driver","D:\\mayil\\Downloads Lenovo\\chromedriver_win32\\chromedriver.exe");
			em = new ChromeDriver();
		}else if (btype.equalsIgnoreCase("ff")){
			
			System.setProperty("webdriver.gecko.driver","D:\\mayil\\Downloads Lenovo\\Geckodriver\\geckodriver(1).exe");
			em = new FirefoxDriver();
		}else if(btype.equalsIgnoreCase("ie")){
			
			System.setProperty("webdriver.ie.driver","D:\\mayil\\Downloads Lenovo\\IEDriverServer_x64_2.53.1\\IEDriverServer.exe");
			em = new InternetExplorerDriver();
		}
		em.get(Utility.propertyvalue(Utility.propertyvalue("env")));   // getting the URL through Properties
		em.manage().window().maximize();
		em.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
	}
	
	@AfterMethod(groups={"SMK","UAT","REG"})          // Closing window function
	public void closeapp(){
		
		em.quit();
		extentReports.endTest(startTest);
		extentReports.flush();
	}
	
	//Getting the date and time stamp for report purpose
	public String get_datetimestamp() {
		
		Date d = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("dd-MMM-yyyy hh-mm-ss");
		String ds = sd.format(d);
		return ds;
	}
	
	//Capturing the screenshots
	public String screenshotcapture() throws Exception{
		
		TakesScreenshot ts = (TakesScreenshot)em;
		File fc = ts.getScreenshotAs(OutputType.FILE);
		String filepath = Utility.propertyvalue("Screenshotpath")+"_"+tc_id+"_"+ order+"_"+get_datetimestamp()+".png";
		FileUtils.copyFile(fc,new File(filepath));
		return filepath;
	}
	
//Common Validation
//	equals

	public void cv_equals(String actual,String expected,String stepname) throws Exception{
		
		if(actual.equals(expected)){
			startTest.log(LogStatus.PASS, stepname , "Passed as the Step "  + stepname + " ." + startTest.addScreenCapture(screenshotcapture()));
			
		}else{
			
			startTest.log(LogStatus.FAIL, stepname , "Failed the Step " +stepname+ " as the actual value is " + actual + " and the expected is " + expected  + startTest.addScreenCapture(screenshotcapture()));
		}
		
				
	}
	
	
//	contains
public void cv_contains(String actual,String expected,String stepname) throws Exception{
		
		if(actual.contains(expected)){
			startTest.log(LogStatus.PASS, stepname , "Passed as the Step "  + stepname + " ." + startTest.addScreenCapture(screenshotcapture()));
			
		}else{
			
			startTest.log(LogStatus.FAIL, stepname , "Failed the Step " +stepname+ " as the actual value is " + actual + " and the expected is " + expected  + startTest.addScreenCapture(screenshotcapture()));
		}
		
				
	}
}
