import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Elvira {

    WebDriver driver;

    @BeforeClass
    public void setUp() {

        WebDriverManager.chromedriver().setup();

    }


    @BeforeMethod
    public  void goToWeb() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

//    @AfterMethod
//    public void closed(){
//    driver.close();
//    }




    @Test
    public void Cars() throws InterruptedException{
        driver.get("https://www.orbitz.com");
        WebElement cars = driver.findElement(By.id("primary-header-car"));
        cars.click();

        Faker faker = new Faker();

        String actualUrl = "https://www.orbitz.com";
        String expectedUrl = driver.getCurrentUrl();
        System.out.println(actualUrl);


        Assert.assertTrue(!expectedUrl.equals(actualUrl),"Expected URL is failed");



//           WebElement cars1 = driver.findElement(By.xpath("//ul[@class='tabs cf col']/../ul/li/button[@id='tab-car-tab-clp']/span[2]"));
//           System.out.println(cars1.isDisplayed());
//
//           String str = faker.address().cityName();
//           System.out.println(str);
//
//           String str1 = faker.address().cityName();
//           System.out.println(str1);
//
//           WebElement inputPickUp = driver.findElement(By.id("car-pickup-clp"));
//           inputPickUp.sendKeys(str);
//
//           WebElement inputDroppOff = driver.findElement(By.id("car-dropoff-clp"));
//           inputDroppOff.sendKeys(str1);
//
//           WebElement inputPickUpDate = driver.findElement(By.id("car-pickup-date-clp"));
//           inputPickUpDate.sendKeys("08.08.2019");
//
//           WebElement inputDroppingOffDate = driver.findElement(By.id("car-dropoff-date-clp"));
//           System.out.println(inputDroppingOffDate.isEnabled());
//
//           // inputDroppingOffDate.sendKeys("07.08.2019");
//
//           Actions action = new Actions(driver);
//           action.moveToElement(inputDroppingOffDate).doubleClick().build().perform();
//
//           System.out.println("Select");
//
//           Thread.sleep(1000);
//           WebElement search = driver.findElement(By.id("search-button-clp"));
//           search.click();
//
//           WebElement more = driver.findElement(By.id("all-in-header-shop-menu"));
//           more.click();


    }


    @Test
    public void carsAndHotels ()  {

        driver.get("https://www.orbitz.com");

        WebElement cars = driver.findElement(By.id("primary-header-car"));
        cars.click();

        WebElement carHotel = driver.findElement(By.id("tab-carHotel-tab-clp-hc"));
        carHotel.click();


        WebElement goingTo = driver.findElement(By.id("hotel-destination-clp-hc"));
        goingTo.sendKeys("Munchen, MUC");

        WebElement checkIn = driver.findElement(By.id("package-hc-departing-clp-hc"));
        checkIn.sendKeys("07.07.2019");

        WebElement checkOut = driver.findElement(By.id("package-hc-returning-clp-hc"));

        Actions action1 =new Actions(driver);
        action1.moveToElement(checkOut).doubleClick().build().perform();
        System.out.println("choose");
        System.out.println(checkOut.isEnabled());

        WebElement search = driver.findElement(By.xpath("//*[@id=\"search-button-clp-hc\"]"));
        search.click();


        WebElement message=driver.findElement(By.xpath("//*[@id=\"gcw-packages-form-clp-hc\"]/div[2]/h5"));
        Assert.assertTrue(message.isDisplayed());

    }

    @Test
    public void carHotelFlights(){

        driver.get("https://www.orbitz.com");
        Faker faker = new Faker();


        WebElement cars = driver.findElement(By.id("primary-header-car"));
        cars.click();

        WebElement carHotelFlights = driver.findElement(By.xpath("//span[@class='icon icon-packages-double-chf']/../span[2]"));
        carHotelFlights.click();

        String str2 = faker.address().city();
        String str3 = faker.address().city();

        WebElement flyingFrom = driver.findElement(By.id("package-origin-clp-fhc"));
        flyingFrom.sendKeys(str2);
        System.out.println(str2);

        WebElement flyingTo = driver.findElement(By.id("package-destination-clp-fhc"));
        flyingTo.sendKeys(str3);
        System.out.println(str3);

        WebElement departing = driver.findElement(By.id("package-departing-clp-fhc"));

        Actions action2 = new Actions(driver);
        action2.moveToElement(departing).doubleClick().build().perform();
        departing.sendKeys("07.09.2019"+ Keys.ENTER);

        WebElement checkbox = driver.findElement(By.id("partialHotelBooking-clp-fhc"));
        checkbox.click();

        WebElement search = driver.findElement(By.id("search-button-clp-fhc"));
        search.click();


        WebElement message1 = driver.findElement(By.xpath("//*[@id=\"gcw-packages-form-clp-fhc\"]/div[2]/h5"));
        Assert.assertTrue(message1.isDisplayed());





    }

}
