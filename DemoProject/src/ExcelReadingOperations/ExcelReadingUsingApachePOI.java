package ExcelReadingOperations;

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

public class ExcelReadingUsingApachePOI 
{
	static List<String> userNameList=new ArrayList<String>();
	static List<String> passwordList=new ArrayList<String>();
	public void readExcelData() throws IOException
	{
		FileInputStream fis=new FileInputStream("C:\\Users\\srinivasan.a.mohan\\Documents\\TestData1.xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		XSSFSheet sheet=workbook.getSheetAt(0);
		int row=sheet.getPhysicalNumberOfRows();
		Iterator<Row> rowIterator=sheet.iterator();
		while(rowIterator.hasNext())
		{
			Row rowVal=rowIterator.next();
			Iterator<Cell> cellIterator=rowVal.iterator();
			int i=2;
			while(cellIterator.hasNext())
			{
				if(i%2==0)
				{
					userNameList.add(cellIterator.next().toString());
				}
				else
				{
					passwordList.add(cellIterator.next().toString());
				}
				i++;
			}
			
		}
	}
	
	// TN-B1-75942543
	public void login(String uname,String pwd)
	{
		WebDriver driver=new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/validateCredentials");
		WebElement userName=driver.findElement(By.id("txtUsername"));
		userName.sendKeys(uname);

		WebElement password=driver.findElement(By.id("txtPassword"));
		password.sendKeys(pwd);

		WebElement login=driver.findElement(By.id("btnLogin"));
		login.click();
		driver.quit();

	}
	public void executeTest()
	{
		for(int i=0;i<userNameList.size();i++)
		{
			login(userNameList.get(i),passwordList.get(i));
		}
	}
	public static void main(String[] args) throws IOException
	{
		ExcelReadingUsingApachePOI ex=new ExcelReadingUsingApachePOI();
		ex.readExcelData();
		System.out.println(userNameList);
		System.out.println(passwordList);
		ex.executeTest();
	}
}
