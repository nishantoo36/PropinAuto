package prop.seleniumUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import prop.commonUtility.Logging;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.PageFactory;

public class DriverInitialization extends Logging {
    public static WebDriver driver = null;
    public DriverInitialization() {
        PageFactory.initElements(getDriver(), this);
    }

    public WebDriver getDriver(){
        SessionId sessionId =null;
        try {
            sessionId = ((RemoteWebDriver) driver).getSessionId();
        }catch (NullPointerException e){
            System.out.println("Create new driver");
        }
        if(sessionId==null || driver==null){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        return driver;
    }
}
