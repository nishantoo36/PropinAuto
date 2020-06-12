package prop.commonUtility;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Reporter;
import prop.ReportsGenarationUtility.ExtentTestManager;

public class Logging {
    public static void log(String args) {
        ExtentTestManager.getTest().log(LogStatus.INFO, args);
        Reporter.log(args + "\n",true);
    }
}

