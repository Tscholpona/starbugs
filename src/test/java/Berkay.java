import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Berkay {
    static WebDriver driver;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    @BeforeMethod

    public void  browser(){
        driver.get("https://orbitz.com");
    }

//     @AfterMethod
//
//      public void Quit() throws InterruptedException {
//
//         Thread.sleep(3000);
//         driver.close();
    //    }





    @Test
    public void OrbitzFlightModuleSameCitiesNoDate() {

        WebElement flightsModule = driver.findElement(By.xpath("//a[@id='primary-header-flight']"));
        flightsModule.click();
        WebElement flyingFrom = driver.findElement(By.xpath("//input[@id='flight-origin-flp']"));
        flyingFrom.click();
        flyingFrom.sendKeys("chicago");
        WebElement flyingTo = driver.findElement(By.xpath("//input[@id='flight-destination-flp']"));
        flyingTo.click();
        flyingTo.sendKeys("chicago");
        WebElement search = driver.findElement(By.xpath("//button[@class='btn-primary btn-action gcw-submit ']"));
        search.click();

        if (driver.findElement(By.xpath("//h5[@class='alert-title no-outline']")).getText().equalsIgnoreCase("Please correct the errors below.") &&
                driver.findElement(By.xpath("(//a[@class='error-link'])[1]")).getText().equalsIgnoreCase("Please choose a different destination from origin.") &&
                driver.findElement(By.xpath("(//a[@class='error-link'])[2]")).getText().equalsIgnoreCase("Enter your departure date in this format: mm/dd/yyyy.") &&
                driver.findElement(By.xpath("(//a[@class='error-link'])[3]")).getText().equalsIgnoreCase("Enter your return date in this format: mm/dd/yyyy.")) {

            System.out.println("Error expectations is PASSED!");
        } else {
            System.out.println("Error expectations is FAILLED!");
        }

    }

    @Test
    public void OrbitzFlightModuleSameCitiesSameDate(){

        WebElement flightsModule = driver.findElement(By.xpath("//a[@id='primary-header-flight']"));
        flightsModule.click();
        WebElement flyingFrom = driver.findElement(By.xpath("//input[@id='flight-origin-flp']"));
        flyingFrom.click();
        flyingFrom.sendKeys("chicago");
        WebElement flyingTo = driver.findElement(By.xpath("//input[@id='flight-destination-flp']"));
        flyingTo.click();
        flyingTo.sendKeys("chicago");
        WebElement search = driver.findElement(By.xpath("//button[@class='btn-primary btn-action gcw-submit ']"));
        search.click();
        WebElement departingDate = driver.findElement(By.xpath("//input[@id='flight-departing-flp']"));
        departingDate.sendKeys("07/15/2019");
        WebElement closeButtonOnDateWindow  = driver.findElement(By.xpath("//button[@class='datepicker-close-btn close btn-text']"));
        closeButtonOnDateWindow.click();
        WebElement search2 = driver.findElement(By.xpath("//button[@class='btn-primary btn-action gcw-submit ']"));
        search2.click();


        if( driver.findElement(By.xpath("//h5[@class='alert-title no-outline']")).getText().equalsIgnoreCase("Please correct the errors below.") &&
                driver.findElement(By.xpath("//div//li/a[@class='error-link']")).getText().equalsIgnoreCase("Please choose a different destination from origin.")){

            System.out.println("Expected Error = Please choose a different destination from origin. RESULT = PASSED!!");
        }else {
            System.out.println("Expected Error = Please choose a different destination from origin. RESULT = FAILED!!");
        }
    }

    @Test

    public void twoDifferentCitiesOnTheSameDate() {

        WebElement flightsModule = driver.findElement(By.xpath("//a[@id='primary-header-flight']"));
        flightsModule.click();
        WebElement flyingFrom = driver.findElement(By.xpath("//input[@id='flight-origin-flp']"));
        flyingFrom.click();
        flyingFrom.sendKeys("chicago");
        WebElement flyingTo = driver.findElement(By.xpath("//input[@id='flight-destination-flp']"));
        flyingTo.click();
        flyingTo.sendKeys("New York");
        WebElement search = driver.findElement(By.xpath("//button[@class='btn-primary btn-action gcw-submit ']"));
        search.click();


        if (driver.getCurrentUrl().equalsIgnoreCase("https://www.orbitz.com/Flights-Search?flight-type=on&starDate=07%2F15%2F2019&endDate=07%2F15%2F2019&_xpid=11905%7C1&mode=search&trip=roundtrip&leg1=from%3Achicago%2Cto%3ANew+York%2Cdeparture%3A07%2F15%2F2019TANYT&leg2=from%3ANew+York%2Cto%3Achicago%2Cdeparture%3A07%2F15%2F2019TANYT&passengers=children%3A0%2Cadults%3A1%2Cseniors%3A0%2Cinfantinlap%3AY")) {

            System.out.println("Expected to Show flight options. Result = PASSED!! ");
        } else {
            System.out.println("Expected to Show flight options. Result = FAILED!! ");

        }


    }

}
