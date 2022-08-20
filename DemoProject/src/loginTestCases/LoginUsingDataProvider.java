package loginTestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginUsingDataProvider 
{
	
	String[][] data={
						{"Admin","admin123"},
						{"Admin","admin"},
						{"Admin1","admin123"},
						{"admin","admin"},
					};
	
	@DataProvider(name="LoginData",indices = {2})
	public String[][] loginData()
	{
		return data;
	}
	
	@Test(dataProvider = "LoginData")
	public void loginWithDataProvider(String uname,String pword)
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\srinivasan.a.mohan\\Downloads\\SeleniumDrivers\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/validateCredentials");

		WebElement userName=driver.findElement(By.id("txtUsername"));
		userName.sendKeys(uname);

		WebElement password=driver.findElement(By.id("txtPassword"));
		password.sendKeys(pword);

		WebElement login=driver.findElement(By.id("btnLogin"));
		login.click();

		driver.quit();
	}


}
