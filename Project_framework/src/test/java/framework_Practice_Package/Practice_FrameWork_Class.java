package framework_Practice_Package;

import java.util.Hashtable;

import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

public class Practice_FrameWork_Class extends BaseTest {
	WebDriver driver ;
	Xls_Reader xls;
	public String testCaseName = "LoginTest";
	@Test(dataProvider ="getData")
	public void test(Hashtable<String,String> data) {
		
		
	    test = rep.startTest("test");
		//init();
		if(!DataUtil.isRunnable(testCaseName, xls) ||  data.get("Runmode").equals("N")){
			test.log(LogStatus.SKIP, "Skipping the test as runmode is N");
			throw new SkipException("Skipping the test as runmode is N");
		}
		openBrowser("Chrome");
		
		reportPass("Pass");
		navigate("appurl");
		waitForPageToLoad();
		boolean expectedresult;
		boolean actualResult = dologin(data.get("Username"),data.get("Password"));
		if(data.get("ExpectedResult").equals("Y")) {
			expectedresult = true;}
		else {
				expectedresult=false;
		if(expectedresult!=actualResult)
			reportFailure("Login Test Failed.");
		
		reportPass("Login Test Passed");
		}
	}
	
	@AfterMethod
	public void quit() {
		rep.endTest(test);
		rep.flush();
		
	}
	
	@DataProvider
	public Object[][] getData(){		
		super.init();	
		xls = new Xls_Reader(prop.getProperty("xlspath"));
		return DataUtil.getTestData(xls, testCaseName);
		
	}
	
}
