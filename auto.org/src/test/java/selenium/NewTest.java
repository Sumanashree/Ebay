package selenium;

import static org.testng.Assert.assertEquals;

import java.io.File;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NewTest {

	WebDriver driver;
	
	@BeforeMethod
	
	public void setUp() {
		String loc=System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", loc+File.separator+"drivers"+File.separator+"chromedriver.exe");
  //System.setProperty("webdriver.chrome.driver", "C:/Users/deepa/Downloads/chromedriver_win32 (3)/chromedriver.exe");
  driver=new ChromeDriver();

  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
  driver.manage().window().maximize();
  driver.manage().deleteAllCookies();
  driver.get("https://www.amazon.in/");
  	System.out.println(driver.manage().window().getSize());
	Dimension d = new Dimension(1024,1366);//Screen resolution
	//Resize the current window to the given dimension
	driver.manage().window().setSize(d);
	}
	
@Test(dataProvider="SearchProvider")

public void PurchaseProduct(String username,String pwd) throws InterruptedException{
	
  driver.findElement(By.id("nav-link-accountList")).click();

  driver.findElement(By.id("ap_email")).sendKeys(username);//Enter user name
  driver.findElement(By.id("continue")).click();

  driver.findElement(By.id("ap_password")).sendKeys(pwd);//Enter password
  driver.findElement(By.id("signInSubmit")).click();

  driver.findElement(By.id("twotabsearchtextbox")).sendKeys("65-inch TV");//search
  driver.findElement(By.xpath("//input[@value='Go']")).click();
 
  driver.findElement(By.xpath("(//img[@class='s-image'])[3]")).click();//random select

  for(String winHandle : driver.getWindowHandles()){
      driver.switchTo().window(winHandle);
  }

  String txt= driver.findElement(By.id("productTitle")).getText();
  Thread.sleep(10000);
  
  	//Assert.assertTrue(True, txt.contains("65 inches"));
    Assert.assertTrue(txt.contains("65 inches"),"TestCase-FAILED");
    System.out.println("Tetscase-PASSED");
    
  /*if(txt.contains("65 inches")) {
  	System.out.println("65 inch TV is displayed");
  }
  else {
  	System.out.println("65 inch TV is not displayed");
  }*/

  driver.findElement(By.id("add-to-cart-button")).click();

  driver.findElement(By.id("hlb-ptc-btn-native"));
  
  driver.findElement(By.id("nav-cart-count")).click();
  driver.findElement(By.xpath("//input[@value='Delete']")).click();
  
  
  Actions action =new Actions(driver);
  WebElement ele=driver.findElement(By.id("nav-link-accountList"));
  action.moveToElement(ele).perform();
  
  
  Thread.sleep(5000);
  driver.findElement(By.xpath("//a[@id='nav-item-signout']")).click();
}

	@AfterMethod
  public void CloseBrowser(){
	  driver.quit();
  }
	
	@DataProvider(name="SearchProvider")
    public Object[][] getDataFromDataprovider(){
		
		Object[][] groupArray = null;
		
		groupArray = new Object[][]
    	{
            { "9710014423", "Sumana@6" }
           
        };
        return groupArray;

}
}


