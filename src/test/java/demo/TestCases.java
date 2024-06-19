package demo;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class TestCases {

    WebDriver driver;
    HomePage hp;

    @BeforeSuite
    public void setup(){
        System.out.println("inside setup");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        hp = new HomePage(driver);
    }

    @AfterSuite
    public void tearDown(){
        System.out.println("inside tear down");
        driver.close();
        driver.quit();
    }

    @Test
    public void testCase01() throws InterruptedException{
        hp.WashingMachine();
    }

    @Test
    public void testCase02() throws InterruptedException {
        hp.iPhone();
    }

    @Test
    public void testCase03() throws InterruptedException {
        hp.coffeeMug();
    }
}
