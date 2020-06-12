package test.propin;

import prop.commonUtility.CommonPreAndPostConditions;
import prop.commonUtility.ExcelReader;
import prop.pages.PropinDataParser;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestDateParsing extends CommonPreAndPostConditions {

    public TestDateParsing(){
        super();
        if(url==null) {
            url = "https://vast-dawn-73245.herokuapp.com/";
        }
    }

    @Test(dataProvider="testData", dataProviderClass = ExcelReader.class)
    public void verifyDateParse(String val,String[] arr) throws InterruptedException {
        count = val;
        PropinDataParser parser =  new PropinDataParser();
        log("Enter data "+arr[0]);
        parser.enterTextInDateField(arr[0]);
        log("Entered value as "+arr[0]);
        log("Tap on Submit");
        parser.submit();
        log("Submitted");
        Thread.sleep(2000);
        actualResult = parser.getParsingResult();
        log("Verify that parsing result should shown as "+arr[1]);
        boolean result = actualResult.equalsIgnoreCase(arr[1]);
        if(!result){
            log(" \n Verification failed getting parsing result as [ "+arr[1]+" ] instead of [ "+actualResult+" ] for Enter value is "+arr[0]);
        }
        Assert.assertTrue(result," \n Verification failed getting parsing result as [ "+arr[1]+" ] instead of [ "+actualResult+" ] for Enter value is "+arr[0]);
    }
}
