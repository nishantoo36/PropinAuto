package prop.commonUtility;

import org.testng.annotations.*;
import prop.ReportsGenarationUtility.ExtentTestManager;
import prop.seleniumUtility.SeleniumUtility;
import org.testng.ITestResult;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class CommonPreAndPostConditions extends SeleniumUtility {
    public static String count = null;
    public static String actualResult = null;
    public static String url  = null;
    public static String actualData = null;

    public CommonPreAndPostConditions() {
        super();
    }

    @BeforeMethod()
    public void getActualData(Object[] val){
       int i=0;
       String [] val2 = new String[2];
        for (Object o : val) {
                if (i==0){
                    i++;
                    continue;
                }
               val2 = (String[]) o;
        }
        actualData = val2[0];
    }

    @BeforeMethod(dependsOnMethods ="getActualData" )
    public void startTest(Method method){
        ExtentTestManager.startTest(method.getName(),"Test for data  "+ actualData);
    }

    @BeforeClass
    public void openTheUrl(){
        openUrl(url);
    }

    @AfterClass
    public void closeBrowser() {
        super.closeBrowser();
    }

    @AfterMethod
    public void storeResult(ITestResult result) throws IOException {
        HashMap<String, String[]> val = new HashMap<String, String[]>();
        String[] data = {actualResult,getResult(result)};
        val.put(count,data);
        ExcelReader.rightResult(val);
    }

    public String getResult(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.SUCCESS) {
                return "pass";
            } else if (result.getStatus() == ITestResult.FAILURE) {
                return "failed";

            } else if (result.getStatus() == ITestResult.SKIP) {

                return "skip";

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}


