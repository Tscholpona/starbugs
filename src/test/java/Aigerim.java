import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
public class Aigerim {
    WebDriver driver;

    @Test(priority = 0)
    public void happyPathSearchPackages(){
        driver.get("https://www.orbitz.com/");


        WebElement city = driver.findElement(By.id("package-origin-hp-package"));
        city.sendKeys("Chicago");

        WebElement destinationCity = driver.findElement(By.id("package-destination-hp-package"));
        destinationCity.sendKeys("BBishkek");

        WebElement departingDate = driver.findElement(By.id("package-departing-hp-package"));
        departingDate.sendKeys("09/01/2019");

        WebElement returningDate = driver.findElement(By.id("package-returning-hp-package"));
        returningDate.sendKeys("09/06/2019");

        WebElement roomsDropdown = driver.findElement(By.id("package-rooms-hp-package"));
        Select selectObj = new Select(roomsDropdown);
        selectObj.selectByVisibleText("2");

        driver.findElement(By.id("packageDirectFlight-hp-package")).click();

        driver.findElement(By.id("search-button-hp-package")).click();


        System.out.println("Search is over");
        if(driver.getCurrentUrl().contains("Bishkek")){
            System.out.println("Search is succesfull");
        }
    }
    @Test(priority = 1)
    public void negativeSearchPackages(){
        driver.get("https://www.orbitz.com/");


        WebElement city = driver.findElement(By.id("package-origin-hp-package"));
        city.sendKeys("Chicago");

        WebElement destinationCity = driver.findElement(By.id("package-destination-hp-package"));
        destinationCity.sendKeys("BBishkek");

        WebElement departingDate = driver.findElement(By.id("package-departing-hp-package"));
        departingDate.sendKeys("09/01/2018");

        WebElement returningDate = driver.findElement(By.id("package-returning-hp-package"));
        returningDate.sendKeys("09/06/2018");

        WebElement roomsDropdown = driver.findElement(By.id("package-rooms-hp-package"));
        Select selectObj = new Select(roomsDropdown);
        selectObj.selectByVisibleText("1");

        driver.findElement(By.id("packageDirectFlight-hp-package")).click();

        driver.findElement(By.id("search-button-hp-package")).click();


        System.out.println("Search is over");

        String errorMessage = "Dates must be between 7/5/2019 and 5/29/2020.";
        WebElement error = driver.findElement(By.linkText("Dates must be between 7/5/2019 and 5/29/2020."));
        if(error.getText().equals("Dates must be between 7/5/2019 and 5/29/2020.")){
            System.out.println("Test is successfull");
        }
    }
    @Test(priority = 2)
    public void negative2SearchPackages(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.orbitz.com/");


        driver.findElement(By.id("search-button-hp-package")).click();


        System.out.println("Search is over");

        String errorMessage = "Please complete the highlighted origin field below.";
        WebElement error = driver.findElement(By.linkText("Please complete the highlighted origin field below."));
        if(error.getText().equals("Please complete the highlighted origin field below.")){
            System.out.println("Test is successfull");
        }
    }
    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
