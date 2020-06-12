package prop.seleniumUtility;

import org.openqa.selenium.WebElement;

public class SeleniumUtility extends DriverInitialization {

    public SeleniumUtility(){
        super();
    }

    public void enterText(String text, WebElement element){
        element.clear();
        element.sendKeys(text);
    }

    public String getText(WebElement element){
        return element.getText();
    }

    public void click(WebElement element){
        element.click();
    }

    public void openUrl(String url){
        driver.get(url);
        driver.manage().window().maximize();
    }

    public void closeWindow(){
        driver.close();
    }

    public void closeBrowser(){
        driver.quit();
    }

    public void submit(WebElement element){
        element.submit();
    }
}
