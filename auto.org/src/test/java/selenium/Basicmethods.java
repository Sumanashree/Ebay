package selenium;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Basicmethods {
	WebDriver driver;
	@BeforeMethod

	public void setUp() {
		String loc=System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", loc+File.separator+"drivers"+File.separator+"chromedriver.exe");

		driver=new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://www.amazon.in/");
		System.out.println(driver.manage().window().getSize());
		Dimension d = new Dimension(1024,1366);//Screen resolution

		driver.manage().window().setSize(d);//Resize the current window to the given dimension
	}
	
	

	@AfterMethod
	public void CloseBrowser(){
		driver.quit();
	}

}
