package demo;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wrapper {
    WebDriver driver;
    WebDriverWait wait;

    public Wrapper(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void navigate(String url) {
        System.out.println("inside navigate function");
        driver.get(url);
    }

    public void click(By element) {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(element)));
        driver.findElement(element).click();
    }

    public void input(By element, String data){
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(element)));
        driver.findElement(element).sendKeys(data);
    }

    public void select(By element) {
        wait.until(ExpectedConditions.elementToBeSelected(driver.findElement(element)));
        driver.findElement(element).click();
    }

    public String getText(By element) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element).getText();
    }

    public void clear(By element) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        driver.findElement(element).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
    }
}