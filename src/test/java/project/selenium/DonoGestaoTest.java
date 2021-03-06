package project.selenium;


import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

import org.openqa.selenium.*;

import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.interactions.Actions;


public class DonoGestaoTest {
  private WebDriver driver;
  JavascriptExecutor js;
   /* SÓ CONEXÕES LOCAIS
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
  public void donogestao() {
    driver.get("http://localhost:3000/");
    driver.manage().window().setSize(new Dimension(1853, 895));
    driver.findElement(By.cssSelector("input:nth-child(3)")).click();
    driver.findElement(By.cssSelector("input:nth-child(3)")).sendKeys("dono@gmail.com");
    driver.findElement(By.cssSelector("input:nth-child(4)")).sendKeys("dono");
    driver.findElement(By.cssSelector("button")).click();
    driver.findElement(By.linkText("Gestão")).click();
    {
      WebElement element = driver.findElement(By.linkText("Gestão"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.cssSelector(".navbar:nth-child(2) li:nth-child(3) > a"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.tagName("body"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element, 0, 0).perform();
    }
    assertThat(driver.findElement(By.cssSelector("td")).getText(), is("Sem cabeleireiros"));
    driver.findElement(By.name("name")).click();
    driver.findElement(By.name("name")).sendKeys("salao_teste");
    driver.findElement(By.name("postalCode")).sendKeys("123");
    driver.findElement(By.name("city")).sendKeys("Aveiro");
    driver.findElement(By.name("country")).sendKeys("Portugal");
    {
      WebElement element = driver.findElement(By.name("status"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).clickAndHold().perform();
    }
    {
      WebElement element = driver.findElement(By.name("status"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.name("status"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).release().perform();
    }
    driver.findElement(By.name("status")).click();
    {
      WebElement element = driver.findElement(By.name("type"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).clickAndHold().perform();
    }
    {
      WebElement element = driver.findElement(By.name("type"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.name("type"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).release().perform();
    }
    driver.findElement(By.name("type")).click();
    driver.findElement(By.name("contact")).click();
    driver.findElement(By.name("contact")).sendKeys("1213");
    driver.findElement(By.cssSelector("form > input:nth-child(16)")).click();
    driver.findElement(By.cssSelector("form > input:nth-child(16)")).sendKeys("123");
    driver.findElement(By.name("address")).click();
    {
      WebElement element = driver.findElement(By.name("cortes"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    driver.findElement(By.name("address")).sendKeys("123");
    driver.findElement(By.name("cortes")).click();
    {
      WebElement element = driver.findElement(By.tagName("body"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element, 0, 0).perform();
    }
    driver.findElement(By.cssSelector(".MuiFormGroup-root > input:nth-child(2)")).click();
    driver.findElement(By.cssSelector(".MuiFormGroup-root > input:nth-child(2)")).sendKeys("09:00:00,12:30:00");
    driver.findElement(By.cssSelector("button")).click();
    assertThat(driver.switchTo().alert().getText(), is("Salão Adicionado!"));
    String MainWindow=driver.getWindowHandle();
    Alert alert = driver.switchTo().alert();
    alert.accept();
    driver.switchTo().window(MainWindow);
    driver.findElement(By.cssSelector(".navbar:nth-child(2) li:nth-child(3) > a")).click();
    driver.findElement(By.cssSelector(".navbar:nth-child(2) li:nth-child(1) > a")).click();
    driver.findElement(By.linkText("Gestão")).click();
    assertThat(driver.findElement(By.cssSelector("td:nth-child(1)")).getText(), is("salao_teste"));
    driver.findElement(By.cssSelector("button:nth-child(1)")).click();
    driver.findElement(By.name("name")).click();
    driver.findElement(By.name("name")).click();
    driver.findElement(By.name("name")).sendKeys("_edit");
    driver.findElement(By.cssSelector("button:nth-child(21)")).click();
    {
      WebElement element = driver.findElement(By.cssSelector("button:nth-child(21)"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    driver.manage().deleteAllCookies();
    driver.navigate().refresh();
    assertThat(driver.findElement(By.cssSelector("td:nth-child(1)")).getText(), is("salao_teste_edit"));
    driver.findElement(By.cssSelector(".navbar:nth-child(2) li:nth-child(1) > a")).click();
    driver.findElement(By.cssSelector(".btn-primary:nth-child(4)")).click();
    assertThat(driver.findElement(By.cssSelector(".cabeleireiro-info")).getText(), is("Salao_teste_edit"));
  }


   */
}

