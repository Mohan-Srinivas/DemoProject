package loginTestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BothIncorrect 
{
	@Test
	@Parameters({"username","password"})
	public void loginWithBothInCorrect(String uname,String pword)
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
