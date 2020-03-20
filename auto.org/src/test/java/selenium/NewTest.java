package selenium;

import java.io.File;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
public class NewTest extends Basicmethods{

	
	public static HSSFWorkbook workbook;
	public static HSSFSheet worksheet;
    public static DataFormatter formatter= new DataFormatter();
    public static String file_location = System.getProperty("user.dir")+File.separator+"Data"+File.separator+"DataSheet.xlsx";
    static String SheetName= "Sheet1";
    public  String Res;
	
   
	
	@Test(dataProvider="SearchProvider")

	public void Amazonproduct(String USERNAME,String PASSWORD,String NAME,String PHONENUM,String PINCODE,String ADDRESSLINE1,String ADDRESSLINE2,String CITY,String STATE) throws InterruptedException{

		driver.findElement(By.id("nav-link-accountList")).click();

		driver.findElement(By.id("ap_email")).sendKeys(USERNAME);//Enter user name
		driver.findElement(By.id("continue")).click();

		driver.findElement(By.id("ap_password")).sendKeys(PASSWORD);//Enter password
		driver.findElement(By.id("signInSubmit")).click();
		
	
	
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("65-inch TV");//search for product
		driver.findElement(By.xpath("//input[@value='Go']")).click();

		driver.findElement(By.xpath("(//img[@class='s-image'])[2]")).click();//select the product

		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}
		
	
		
		String txt= driver.findElement(By.id("productTitle")).getText();
		Thread.sleep(10000);


		Assert.assertTrue(txt.contains("65"),"Product displayed-FAILED");
		System.out.println("Product displayed-PASSED");
	
	
		//To add product to cart and enter address details for product delivery
		driver.findElement(By.id("add-to-cart-button")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[@id='hlb-ptc-btn-native']")).click();
		driver.findElement(By.id("enterAddressFullName")).sendKeys(NAME);
		driver.findElement(By.id("enterAddressPhoneNumber")).sendKeys(PHONENUM);
		driver.findElement(By.id("enterAddressPostalCode")).sendKeys(PINCODE);
		driver.findElement(By.id("enterAddressAddressLine1")).sendKeys(ADDRESSLINE1);
		driver.findElement(By.id("enterAddressAddressLine2")).sendKeys(ADDRESSLINE2);
		driver.findElement(By.id("enterAddressCity")).sendKeys(CITY);
		driver.findElement(By.id("enterAddressStateOrRegion")).sendKeys(STATE);
		driver.findElement(By.xpath("//input[contains(@class,'submit-button')]")).isDisplayed();
		System.out.println("Address page is displayed");
	


		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
		driver.manage().window().maximize();
		Thread.sleep(5000);

		driver.findElement(By.id("nav-cart-count")).click();
		driver.findElement(By.xpath("//input[@value='Delete']")).click();


		Actions action =new Actions(driver);
		WebElement ele=driver.findElement(By.id("nav-link-accountList"));
		action.moveToElement(ele).perform();
	

		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[@id='nav-item-signout']")).click();
	
	}
		
	@DataProvider(name="SearchProvider")
	public Object[][] getDataFromDataprovider(){

		Object[][] groupArray = null;

		groupArray = new Object[][]
				{
			{ "9710014423", "Sumana@6" ,"Sumana","9786755656","560097","10","Rajajinagar","Bangalore","Karnataka"}

				};
				return groupArray;

	}

}


