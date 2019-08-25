import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GistAutomate {
    public static ChromeDriver drive;
    public static ChromeOptions options;
    public static WebDriverWait wait;

    public static void openBrowser(String site){
        options = new ChromeOptions();
        options.addArguments("--window-size\\=1440,900", "--disable-extensions", "--no-sandbox" , "--ignore-ssl-errors");
        drive = new ChromeDriver(options);
        drive.get(site);
    }
    public static void closeBrowser(){
        drive.quit();
    }
    static void waitElementUntilItVisible (WebElement element, int timeoutInSec){
        wait = new WebDriverWait(drive, timeoutInSec);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    static WebElement findElementXpath(String id_xpath){
        WebElement element = drive.findElement(By.xpath(id_xpath));
        waitElementUntilItVisible(element, 3);
        return element;
    }
    static void clickElementXpath(String id_xpath) throws Exception{
        Thread.sleep(1000);
        findElementXpath(id_xpath).click();
        Thread.sleep(1000);
    }
    static void acceptAlert() throws Exception{
        drive.switchTo().alert().accept();
        Thread.sleep(1000);
    }

    static void inputText(String id_xpath, String text) throws Exception{
        Thread.sleep(1000);
        String a = findElementXpath(id_xpath).getAttribute("value");
        if(a != null){
            int x = a.length();
            while(x != 0){
                findElementXpath(id_xpath).sendKeys(Keys.BACK_SPACE);
                x--;
            }
        }
        findElementXpath(id_xpath).sendKeys(text);
        Thread.sleep(1000);
    }
}