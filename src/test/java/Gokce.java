import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Gokce {


    public static class RabiaStarbugzDealsUseTestNG {
        static WebDriver driver;
        @BeforeClass
        public void setUp(){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().fullscreen();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        }
        @BeforeMethod
        public void goToWebApp(){
            driver.get("https://www.orbitz.com/");

        }
        @AfterClass
        public void closed(){
            driver.close();
        }




        @Test

        public void dealsHotelOnly() throws InterruptedException {

            WebElement clickToDeals = driver.findElement(By.id("primary-header-deals"));
            clickToDeals.click();

            String destination = "Chicago";
            WebElement inputDestination = driver.findElement(By.xpath("//input[@name='PlaceName']"));
            inputDestination.sendKeys(destination);
            Thread.sleep(2000);
            driver.findElement(By.id("aria-option-0")).click();
            Thread.sleep(2000);

            //User should passed valid date- Date should not accept past days
            WebElement checkInDatehotelonly = driver.findElement(By.xpath("//label[@class='datepicker-label datepicker-arrow text icon-before']"));
            Assert.assertTrue(checkInDatehotelonly.isDisplayed(), "Destination input button is not displayed");
            checkInDatehotelonly.click();
            String selectCheckInDate =  "8/8/2019";
            checkInDatehotelonly.sendKeys(selectCheckInDate);
            Thread.sleep(2000);


            //Check-Out date shoul be after Check-in date , it should select  1 after day by default
            WebElement checkOutDate = driver.findElement(By.name("OutDate"));
            checkOutDate.click();
            Thread.sleep(3000);
            checkOutDate.findElement(By.xpath("//button[@data-day='30']")).click();

            WebElement rooms = driver.findElement(By.id("NumRoom"));
            //it should choose "1" by default
            Select roomsDropdown = new Select(rooms);
            String firstSelectedOption =  roomsDropdown.getFirstSelectedOption().getText();
            roomsDropdown.selectByValue("1");

            //it should give 2 by default
            WebElement adultsPlus18 = driver.findElement(By.id("NumAdult1"));
            Select adultsSelectDropdown = new Select(adultsPlus18);
            String adultsPlus18SelectedOption = adultsSelectDropdown.getFirstSelectedOption().getText();
            adultsSelectDropdown.selectByValue("1");

            //it should choose "0" by default
            WebElement children = driver.findElement(By.xpath("//select[@id='NumChild1'][1]"));
            Select childsSelectDropdown = new Select(children);
            String childSelectedOption = childsSelectDropdown.getFirstSelectedOption().getText();
            childsSelectDropdown.selectByValue("0");

            WebElement searchButton = driver.findElement(By.id("H-searchButtonExt1"));
            searchButton.click();

        }

        @Test
        public void dealsThingsToDo() throws InterruptedException{

            WebElement clickToDeals = driver.findElement(By.id("primary-header-deals"));
            clickToDeals.click();

            WebElement thingsToDo = driver.findElement(By.xpath("(//span[@class='tab-label'])[5]"));
            thingsToDo.click();

            WebElement inputDestination = driver.findElement(By.id("A-destination"));
            inputDestination.sendKeys("Chicago");
            WebElement weeklyDealsButton = driver.findElement(By.id("widgetcatalogWizard"));
            weeklyDealsButton.click();

            WebElement fromDate = driver.findElement(By.id("A-fromDate"));
            String selectCheckInDate = "8/8/2019";
            fromDate.sendKeys(selectCheckInDate);

            WebElement toDate = driver.findElement(By.id("A-toDate"));
            toDate.click();
            toDate.findElement(By.xpath("//button[@data-day='22']")).click();


            WebElement searchButton = driver.findElement(By.id("A-searchButtonExt1"));
            searchButton.click();
        }

        @Test
        public void dealsCarsOnly(){
            WebElement goTotheDealsPage = driver.findElement(By.id("primary-header-deals"));
            goTotheDealsPage.click();

            WebElement carsOnly = driver.findElement(By.xpath("(//span[@class='tab-label'])[4]"));
            carsOnly.click();

            WebElement pickingUp = driver.findElement(By.id("C-destination"));
            pickingUp.sendKeys("California");

            WebElement droppingOff = driver.findElement(By.id("C-dropOffLocation"));
            droppingOff.sendKeys("Chicago");

            WebElement pickUpTime = driver.findElement(By.id("PickupTime"));

            WebElement pickUpDate = driver.findElement(By.id("C-fromDate"));
            pickUpDate.click();
            pickUpDate.findElement(By.xpath("(//button[@data-day='8'])[1]")).click();


            WebElement dropOffDate = driver.findElement(By.id("C-toDate"));
            dropOffDate.click();
            dropOffDate.findElement(By.xpath("(//table[@class='datepicker-cal-weeks'])[2]/tbody//tr[4]//td[5]")).click();

            WebElement searchButtonForCars = driver.findElement(By.id("C-searchButtonExt1"));
            searchButtonForCars.click();

        }


    }

}
