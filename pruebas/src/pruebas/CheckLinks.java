package pruebas;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class CheckLinks {
	
	WebDriver driver;
	
	CheckingLinks pagina;
	
  
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.chrome.driver", "D:\\Eclipse\\eclipse\\chromedriver_win32\\chromedriver.exe");
	  driver = new ChromeDriver();
	  pagina = new CheckingLinks(driver);
	  driver.get("https://demo.oneapp.cl/admin/admin");
	  driver.findElement(By.xpath("//*[@id=\"AdministradorUsuario\"]")).sendKeys("admin");
	  driver.findElement(By.xpath("//*[@id=\"AdministradorClave\"]")).sendKeys("andain5546");
	  driver.findElement(By.xpath("//*[@id=\"AdministradorAdminLoginForm\"]/div[4]/div/button")).click();
	  driver.findElement(By.xpath("//*[@id=\"menu\"]/ul/li[6]/a")).click();
  }
  
  @Test
  public void check() {
	  
	  assertTrue(pagina.checkingLinkPage(), "There are broken Links");
  }
  
  @AfterClass
  public void afterClass() {
	  
	  driver.close();
  }


}
