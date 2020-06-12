package prop.pages;

import prop.seleniumUtility.SeleniumUtility;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PropinDataParser extends SeleniumUtility {

    public PropinDataParser(){
        super();
    }

    @FindBy(name = "date")
    WebElement dateField;

    @FindBy(xpath = "//input[@type='submit']")
    WebElement submitBtn;

    @FindBy(xpath = "//h3[text()='Results']/following::div")
    WebElement result;

    public void enterTextInDateField(String text){
        enterText(text,dateField);
    }
    public void submit(){
        submit(submitBtn);
    }

    public String  getParsingResult(){
      return   getText(result);
    }
}
