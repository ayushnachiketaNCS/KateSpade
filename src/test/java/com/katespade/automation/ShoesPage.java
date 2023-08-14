package com.katespade.automation;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ShoesPage {
	WebDriver driver;
  @Test
  public void SignUpFunctionality() throws InterruptedException {
	  driver.findElement(By.xpath("(//div[@class=\"css-70qvj9\"])[3]")).click();
	  Thread.sleep(2000);
	  WebElement FirstName = driver.findElement(By.xpath("//input[@class=\"form-control isvalid-fname required validChar\"]"));
	  FirstName.click();
	  FirstName.sendKeys("Ayush");
	  
	  WebElement LastName = driver.findElement(By.xpath("//input[@class=\"form-control isvalid-lname required validChar\"]"));
	  LastName.click();
	  LastName.sendKeys("Nachiketa");
	  
//	  WebElement EmailAddress = driver.findElement(By.xpath("//label[@for=\"registration-form-email\"]"));
//	  EmailAddress.click();
//	  EmailAddress.click();
//	  EmailAddress.sendKeys("abc@gmail.com");
	  
//	  WebElement Password = driver.findElement(By.xpath("//label[@for=\"registration-form-password\"]"));
//	  Password.click();
//	  Password.sendKeys("Ayush@123");
	  
	  Thread.sleep(5000);
	  WebElement checkbox1 = driver.findElement(By.xpath("//div[@class=\"recaptcha-checkbox-border\"]"));
	  if(!checkbox1.isSelected()) {
		  checkbox1.click();
	  }
	  
	  driver.findElement(By.xpath("//button[@class=\"btn btn-primary btn-block g-recaptcha js-verify-account-modal-open\"]")).click();
	  
	  
	  	  
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
