package demo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    Wrapper wp;

    private WebDriver driver;
    By searchBox = By.xpath("//input[@name='q']");
    By searchBox2 = By.xpath("//input[@class='zDPmFV']");
    By searchButton = By.xpath("//div[@class='_3NorZ0 _3jeYYh']//button");
    By searchButton2 = By.xpath("//button[@class='MJG8Up']");
    By sortByPopularity = By.xpath("//div[text()='Popularity']");
    By rating5Star = By.xpath("//div[@class='_6NESgJ']/ul/li[2][contains(text(),5)]");
    By priceLowToHigh = By.xpath("//div[text()='Price -- Low to High']");
    By discountMoreThan17 = By.xpath("//span[number(substring-before(text(), '%')) > 17]");
    By titleForDiscountMoreThan17 = By.xpath("//span[number(substring-before(text(), '%')) > 17]/../../../../../div[1]/div[@class='KzDlHZ']");
    By starRating4orMore = By.xpath("(//label[@class='tJjCVx _3DvUAf'])[1]");
    By reviews = By.xpath("//span[@class='Wphh3N']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        wp = new Wrapper(driver);
        wp.navigate("http://www.flipkart.com");
    }

    public void WashingMachine() throws InterruptedException{
       
        wp.input(searchBox, "Washing Machine");
        wp.click(searchButton);

        Thread.sleep(5000);

        wp.click(sortByPopularity);

        Thread.sleep(5000);

        List<WebElement> all5Rating = driver.findElements(rating5Star);
        System.out.println("5 Raitng items : "+ all5Rating.size());

    }

    public void iPhone() throws InterruptedException {

        wp.clear(searchBox2);
        Thread.sleep(2000);
        wp.input(searchBox2, "iPhone");
        wp.click(searchButton2);

        Thread.sleep(5000);

        wp.click(priceLowToHigh);

        Thread.sleep(5000);

        List<WebElement> allItems = driver.findElements(titleForDiscountMoreThan17);
        List<WebElement> allDiscount = driver.findElements(discountMoreThan17);

        int count = 0;

        for (WebElement item : allItems) {
            String title = item.getText();
            String discount = allDiscount.get(count).getText();
            System.out.println("Item : "+ title + " discount : "+ discount);
            count++; 
        }

    }

    public void coffeeMug() throws InterruptedException{
        wp.clear(searchBox2);
        Thread.sleep(2000);
        wp.input(searchBox2, "Coffee Mug");
        wp.click(searchButton2);

        Thread.sleep(5000);

        wp.click(starRating4orMore);

        Thread.sleep(5000);

         // Extract all relevant elements
        List<WebElement> elements = driver.findElements(reviews);

        // Create a list to hold the elements and their ratings
        List<ElementWithRating> elementList = new ArrayList<>();

        // Parse the text content and extract ratings
        for (WebElement elem : elements) {
            String text = elem.getText();
            int rating = Integer.parseInt(text.replaceAll("[,()]", ""));
            elementList.add(new ElementWithRating(rating, elem));
        }

        // Sort the list in descending order based on the rating
        elementList.sort(Comparator.comparingInt(ElementWithRating::getRating).reversed());

        // Extract the top 5 elements
        List<ElementWithRating> top5Elements = elementList.subList(0, Math.min(5, elementList.size()));

        // Print the results
        System.out.println("Top 5 elements with the highest ratings:");
        for (ElementWithRating elementWithRating : top5Elements) {

            System.out.println(elementWithRating.getElement().getText());

            WebElement picture = elementWithRating.getElement().findElement(By.xpath("./../../a[1]"));
            String imageURL = picture.getAttribute("href");

            WebElement titleElement = elementWithRating.getElement().findElement(By.xpath("./../../a[2]"));
            String title = titleElement.getAttribute("title");

            System.out.println("Title : "+ title );
            System.out.println("Image URL : "+ imageURL);
            System.out.println("");
        }

    }

    // Helper class to hold the element and its corresponding rating
    private static class ElementWithRating {
        private final int rating;
        private final WebElement element;

        public ElementWithRating(int rating, WebElement element) {
            this.rating = rating;
            this.element = element;
        }

        public int getRating() {
            return rating;
        }

        public WebElement getElement() {
            return element;
        }
    }
}
