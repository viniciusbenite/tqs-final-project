package project.selenium;// Generated by Selenium IDE

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class ClienteMarcarTest {
  private WebDriver driver;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void clienteMarcar() {
    /*
    driver.get("http://localhost:3000/");
    driver.manage().window().setSize(new Dimension(911, 893));
    driver.findElement(By.cssSelector("input:nth-child(3)")).click();
    driver.findElement(By.cssSelector("input:nth-child(3)")).sendKeys("cliente@gmail.com");
    driver.findElement(By.cssSelector("input:nth-child(4)")).sendKeys("cliente");
    driver.findElement(By.cssSelector("button")).click();
    driver.findElement(By.cssSelector(".btn-primary:nth-child(4)")).click();
    {
      WebElement element = driver.findElement(By.cssSelector("img"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    driver.findElement(By.cssSelector(".btn-primary")).click();
    js.executeScript("window.scrollTo(0,616)");
    {
      WebElement element = driver.findElement(By.cssSelector(".calendar"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).clickAndHold().perform();
    }
    driver.findElement(By.cssSelector("label > div")).click();
    driver.findElement(By.cssSelector(".react-datepicker__navigation")).click();
    {
      WebElement element = driver.findElement(By.cssSelector(".react-datepicker__navigation"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.tagName("body"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element, 0, 0).perform();
    }
    driver.findElement(By.cssSelector(".react-datepicker__navigation--next")).click();
    {
      WebElement element = driver.findElement(By.cssSelector(".react-datepicker__navigation--next"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.tagName("body"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element, 0, 0).perform();
    }
    driver.findElement(By.cssSelector(".react-datepicker__day--keyboard-selected")).click();
    driver.findElement(By.cssSelector(".service_option")).click();
    driver.findElement(By.cssSelector(".service_option")).click();
    {
      WebElement element = driver.findElement(By.cssSelector(".service_option"));
      Actions builder = new Actions(driver);
      builder.doubleClick(element).perform();
    }
    driver.findElement(By.cssSelector(".reservar > input")).click();
    driver.close();
     */
  }
}