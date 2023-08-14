package com.katespade.automation;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertEquals;



public class ClothingPage {
	WebDriver driver;
  @Test
  public void JewelleryPage() throws InterruptedException {
	  WebElement hoverable = driver.findElement(By.xpath("(//p[@class=\"chakra-text css-10hw5hq\"])[6]"));
	  Actions actions = new Actions(driver);
	  actions.moveToElement(hoverable).perform();
	  driver.findElement(By.xpath("")).click();
	  Thread.sleep(1000);
	  
	  List<WebElement> allitems = driver.findElements(By.xpath("//div[@class=\"plp-filter-card-title\"]"));
	  for(WebElement w1:allitems) {
		  System.out.println(w1.getText());
	  }
	  
	  allitems.get(0).click();
	  Thread.sleep(2000);
	  
  }
  
  @Test
  public void ValidatingCountofProduct() throws InterruptedException {
	  System.out.println(driver.getCurrentUrl());
	  driver.findElement(By.xpath("(//div[@class=\"plp-filter-card-title\"])[2]")).click();
	  Thread.sleep(1000);
	  WebElement w1 = driver.findElement(By.xpath("//p[@class=\"chakra-text css-1615m34\"]"));
	  String[] str = w1.getText().split(" ");
	  int count = Integer.parseInt(str[0]);
	  
	  List<WebElement> allitems = driver.findElements(By.xpath("//p[@class=\"chakra-text css-onkd7s\"]"));
	  for(WebElement w2:allitems) {
		  System.out.println("The Product name is: "+w2.getText());
	  }
	  
	  assertEquals(count,allitems.size(),"Product count is not same");
	  
  }
  
  @Test
  public void ProductColorCount() {
	  System.out.println(driver.getCurrentUrl());
	  driver.findElement(By.xpath("(//p[@class=\"chakra-text css-dr74y5\"])[1]")).click();
	  driver.findElement(By.xpath("(//a[@class=\"chakra-button css-1ugfe6h\"])[7]")).click();
	  driver.findElement(By.xpath("(//a[@class=\"chakra-button css-1ugfe6h\"])[8]")).click();
	  driver.findElement(By.xpath("(//a[@class=\"chakra-button css-1ugfe6h\"])[9]")).click();
	  
	  WebElement w1 = driver.findElement(By.xpath("//p[@class=\"chakra-text css-1615m34\"]"));
	  
  }
  
  @BeforeMethod
  public void beforeMethod() {

	  System.out.println("Inside before Method");
  }

  @AfterMethod
  public void afterMethod() {

	  System.out.println("Inside after Method");
  }

  @BeforeClass
  public void beforeClass() {
	  WebDriverManager.chromedriver().setup();
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://www.katespade.com");
		System.out.println("Inside before class");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("Inside After Class");
	  //driver.close();
  }

}
