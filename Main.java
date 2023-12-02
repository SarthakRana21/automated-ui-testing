package org.example;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = initializeDriver();
        fillnames(driver);
        singleselect(driver);
        multiselect(driver);
        alertbox(driver);
        alert(driver);
        brokenLinks(driver);
        driver.manage().window().minimize();
        driver.manage().window().maximize();
        driver.quit();
    }

    private static WebDriver initializeDriver() {
        System.setProperty("webdriver.chrome.driver", "/Users/sarthakrana/Desktop/IDE/google/auto/src/main/resources/drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://only-testing-blog.blogspot.com/2014/01/new-testing.html?");
        return driver;
    }

    private static void fillnames(WebDriver driver) {
        if (driver.findElement(By.name("fname")).isEnabled()) {
            driver.findElement(By.name("fname")).sendKeys("Testing");
            System.out.println("First name field is enabled");
        } else {
            System.out.println("First name field is disabled");
        }
        if (driver.findElement(By.name("lname")).isEnabled()) {
            driver.findElement(By.name("lname")).sendKeys("autmation");
            System.out.println("Last name field is enabled");
        } else {
            System.out.println("Last name field is disabled");
        }
        driver.findElement(By.xpath("//*[@id=\"post-body-3647323225296998740\"]/div[1]/form[1]/input[3]")).click();
        driver.findElement(By.xpath("//*[@id=\"post-body-3647323225296998740\"]/div[1]/form[1]/input[4]")).click();
        driver.findElement(By.xpath("//*[@id=\"post-body-3647323225296998740\"]/div[1]/form[1]/input[6]")).click();
    }
    private static void singleselect(WebDriver driver){
        WebElement element = driver.findElement(By.xpath("//*[@id=\"post-body-3647323225296998740\"]/div[1]/select"));
        Select select = new Select(element);
        select.selectByVisibleText("Audi");

        List<WebElement> list = select.getOptions();
        System.out.println("\nList of all Cars: \n"+element.getText());

        WebElement sel = select.getFirstSelectedOption();
        System.out.println("\nSelected Car: "+sel.getText());

    }
    private static void multiselect(WebDriver driver){
        WebElement element = driver.findElement(By.name("FromLB"));
        Select select = new Select(element);
        select.selectByIndex(0);
        select.selectByIndex(2);
        select.selectByIndex(3);

        List<WebElement> option = select.getOptions();
        System.out.println("\nAll options- \n"+element.getText());

        List<WebElement> opt = select.getAllSelectedOptions();
        System.out.println("\nAll selected options-");
        for(WebElement e: opt){
            System.out.println(e.getText());
        }
        driver.findElement(By.xpath("//*[@id=\"post-body-3647323225296998740\"]/div[1]/form[2]/table/tbody/tr/td[2]/input[1]")).click();
    }
    private static void alertbox(WebDriver driver) throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"post-body-3647323225296998740\"]/div[1]/input")).click();
        String get = driver.switchTo().alert().getText();
        System.out.println("\n"+get);
        driver.switchTo().alert().accept();

        driver.findElement(By.xpath("(//button[text()='Show Me Confirmation'])")).click();
        String get1 = driver.switchTo().alert().getText();
        System.out.println(get1);
        driver.switchTo().alert().dismiss();
        String get2 = driver.findElement(By.id("demo")).getText();
        System.out.println(get2);

        driver.findElement(By.xpath("(//button[text()='Show Me Prompt'])")).click();
        driver.switchTo().alert().sendKeys("Hello testing");
        String get3 = driver.switchTo().alert().getText();
        System.out.println(get3);
        driver.switchTo().alert().accept();
    }
    private static void alert(WebDriver driver){
        driver.findElement(By.id("submitButton")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        System.out.println("\nAlert message: "+alert.getText());
        driver.switchTo().alert().accept();
    }
    private static void brokenLinks(WebDriver driver) {
        HttpURLConnection huc = null;

        List<WebElement> allHyperlinksList = driver.findElements(By.tagName("a"));

        for (WebElement hyperLink : allHyperlinksList) {
            String url = hyperLink.getAttribute("href");

            if (url != null) {
                try {
                    URL actualUrl = new URL(url);
                    huc = (HttpURLConnection) actualUrl.openConnection();
                    huc.setRequestMethod("HEAD");
                    huc.connect();

                    int respCode = huc.getResponseCode();

                    if (respCode != 200) {
                        System.out.println("\n");
                        System.err.println(hyperLink.getText() + " | " + url + " is a BROKEN link");
                    } else {
                        System.out.println("\n");
                        System.out.println(hyperLink.getText() + " | " + url + " is a VALID link");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.err.println("\nHref attribute is null for the link with text: " + hyperLink.getText());
            }
        }
    }
}
