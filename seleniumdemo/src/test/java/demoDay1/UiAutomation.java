package demoDay1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class UiAutomation {

	@Test
	public void test() throws InterruptedException {
		ChromeDriver driver=  new ChromeDriver();		
		driver.manage().window().maximize();
		driver.get("https://www.ebay.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8000));
		
		driver.findElement(By.id("gh-ac")).sendKeys("Book");		
		driver.findElement(By.id("gh-search-btn")).click();
		driver.findElement(By.xpath("//div[@class='s-item__title']//span[@role='heading']//span")).click();
		
		Set<String> windowsId=driver.getWindowHandles();
		List<String> windowList=new ArrayList(windowsId);
		String parent=windowList.get(0);
		String child=windowList.get(1);
		driver.switchTo().window(child);
		
		driver.findElement(By.id("atcBtn_btn_1")).click(); 
		
		try {
		String badge=driver.findElement(By.xpath("//span[@class='badge']")).getText();
		int itemCount=Integer.parseInt(badge);
		
		if(itemCount>0) {
			System.out.println("Cart is updated and total number of item(s) present in cart is : " +itemCount);
		} else {
			System.out.println("Cart is not Updated. Adding Failed");
		}
		} catch(Exception e) {
			e.printStackTrace();
		}
	    finally {
	    	driver.quit();
	    }
	}

}
