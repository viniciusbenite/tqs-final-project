package project.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
public class CriarClienteTest {
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
    public void criarcliente() {
        driver.get("https://reacttqs.herokuapp.com/");
        driver.manage().window().setSize(new Dimension(1853, 895));
        driver.findElement(By.linkText("Criar conta como Cliente")).click();
        driver.findElement(By.cssSelector("input:nth-child(1)")).click();
        driver.findElement(By.cssSelector("input:nth-child(1)")).sendKeys("cliente");
        driver.findElement(By.cssSelector("input:nth-child(2)")).sendKeys("cliente@gmail.com");
        driver.findElement(By.cssSelector("input:nth-child(3)")).sendKeys("cliente");
        driver.findElement(By.cssSelector("button")).click();
        {
            WebElement element = driver.findElement(By.cssSelector("button"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        driver.findElement(By.cssSelector("input:nth-child(3)")).click();
        driver.findElement(By.cssSelector("input:nth-child(3)")).click();
        driver.findElement(By.cssSelector("input:nth-child(3)")).sendKeys("cliente@gmail.com");
        driver.findElement(By.cssSelector("input:nth-child(4)")).sendKeys("dono");
        driver.findElement(By.cssSelector("button")).click();
        {
            WebElement element = driver.findElement(By.cssSelector("button"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        assertThat(driver.findElement(By.cssSelector("p")).getText(), is("Houve um problema com o login, verifique suas credenciais...."));
        driver.findElement(By.cssSelector("input:nth-child(5)")).click();
        driver.findElement(By.cssSelector("input:nth-child(5)")).click();
        driver.findElement(By.cssSelector("input:nth-child(5)")).click();
        {
            WebElement element = driver.findElement(By.cssSelector("input:nth-child(5)"));
            Actions builder = new Actions(driver);
            builder.doubleClick(element).perform();
        }
        driver.findElement(By.cssSelector("input:nth-child(5)")).sendKeys("cliente");
        driver.findElement(By.cssSelector("button")).click();
        assertThat(driver.findElement(By.cssSelector("h1")).getText(), is("Bem-vindo"));
        assertThat(driver.findElement(By.linkText("As minhas reservas")).getText(), is("As minhas reservas"));
    }
}