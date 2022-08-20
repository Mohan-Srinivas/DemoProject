package loginTestCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DataDrivenUsingPOI 
{
	static List<String> userNameList=new ArrayList<String>();
	static List<String> passwordList=new ArrayList<String>();
	public void readExcelData() throws IOException
	{
		FileInputStream file=new FileInputStream("C:\\Users\\srinivasan.a.mohan\\Documents\\TestData1.xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook(file);
		XSSFSheet sheet=workbook.getSheet("Sheet1");
		Iterator<Row> rowIterator=sheet.iterator();
		while(rowIterator.hasNext())
		{
			Row rowValue=rowIterator.next();
			Iterator<Cell> columnIterator=rowValue.iterator();
			int i=2;
			while(columnIterator.hasNext())
			{
				if(i%2==0)
				{
					userNameList.add(columnIterator.next().toString());
				}
				else
				{
					passwordList.add(columnIterator.next().toString());
				}
				i++;
			}
			
		}
	}
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

	public void executeTests()
	{
		int size=userNameList.size();
		for(int i=0;i<size;i++)
		{
			loginWithDataProvider(userNameList.get(i),passwordList.get(i));
		}
	}
	
	public static void main(String[] args) throws IOException 
	{
		// TODO Auto-generated method stub
		DataDrivenUsingPOI usingPOI=new DataDrivenUsingPOI();
		usingPOI.readExcelData();
		System.out.println(userNameList);
		System.out.println(passwordList);
		usingPOI.executeTests();
	}

}
