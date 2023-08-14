package com.katespade.automation;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.List;
import java.util.ArrayList;
import org.openqa.selenium.Keys;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.testng.Assert.assertEquals;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


public class HandBagPage {
	WebDriver driver;
	
  @Test(priority=1)
  public void LoadingHandBagPage() throws InterruptedException {
	  driver.findElement(By.xpath("(//p[@class=\"chakra-text css-10hw5hq\"])[2]")).click();
	  Thread.sleep(2000);
	  System.out.println("1"+driver.getCurrentUrl());
	  assertEquals(driver.getCurrentUrl(),"https://www.katespade.com/shop/handbags/view-all","Not Loaded HandBag Page");  
  }
  
  @Test(dependsOnMethods="LoadingHandBagPage")
  public void ApplyingFilter() throws InterruptedException {
	  driver.findElement(By.xpath("(//div[@class=\"plp-filter-card-title\"])[2]")).click();
	  Thread.sleep(2000);
	  assertEquals(driver.getCurrentUrl(),"https://www.katespade.com/shop/handbags/shoulder-bags","Not redirected to ShoulderBags");
	  Actions actions = new Actions(driver);
	  WebElement Hoverable = driver.findElement(By.xpath("//button[@class=\"chakra-button chakra-menu__menu-button Menusort css-14orpfm\"]"));
	  actions.moveToElement(Hoverable).perform();
	  driver.findElement(By.xpath("//button[@data-qa=\"d_plpsrt_select_srtby_price-low-to-high\"]")).click();
	  Thread.sleep(2000);
	  List<WebElement> products  = driver.findElements(By.xpath("//span[@class=\"chakra-text salesPrice false css-5cguw3\"]"));
	  System.out.println(products.size());
	  List<Integer> productprices = new ArrayList<>();
	  for(WebElement w1:products) {
		  productprices.add(Integer.parseInt(w1.getText().substring(1)));
	  }
	  
	  System.out.println(productprices);
  }
  
  @Test(dependsOnMethods="ApplyingFilter")
  public void ProductUnderThisRange() throws InterruptedException {
	  System.out.println(driver.getCurrentUrl());
	  driver.findElement(By.xpath("(//p[@class=\"chakra-text css-dr74y5\"])[2]")).click();
	  WebElement low_range = driver.findElement(By.xpath("//input[@data-qa=\"plpfltr_min_price_input\"]"));
	  WebElement high_range = driver.findElement(By.xpath("//input[@data-qa=\"plpfltr_max_price_input\"]"));
	  for(int i=0;i<3;i++) {
		  low_range.sendKeys(Keys.BACK_SPACE);
	  }
	  low_range.sendKeys("250");
	  for(int i=0;i<3;i++) {
		  high_range.sendKeys(Keys.BACK_SPACE);
	  }
	 high_range.sendKeys("400");
	 high_range.sendKeys(Keys.ENTER);
	 
	 List<WebElement> products = driver.findElements(By.xpath("//span[@class=\"chakra-text salesPrice false css-5cguw3\"]"));
	 List<Integer> prices = new ArrayList<>();
	 for(WebElement w1:products) {
		 prices.add(Integer.parseInt(w1.getText().substring(1)));
	 }
	 
	 System.out.println(prices);
	  
  }
  
  
  @Test(dependsOnMethods="ProductUnderThisRange")
  public void ValidatingWishlisting() throws InterruptedException {
	  System.out.println(driver.getCurrentUrl());
	  String tobewishlist = driver.findElement(By.xpath("(//p[@data-qa=\"cm_pdt_link_pt_title\"])[1]")).getText();
	  WebElement wishlisticon=driver.findElement(By.xpath("(//button[@class=\"chakra-button css-cr4ofp\"])[1]"));
	  wishlisticon.click();
	  Thread.sleep(2000);
	  WebElement wishlist=driver.findElement(By.xpath("//a[@data-qa=\"d_hdr_icon_sfl\"]"));
	  wishlist.click();
	  Thread.sleep(2000);
	  WebElement wishlisted = driver.findElement(By.xpath("//div[@class=\"product-tile__title pdp-link\" and @data-qa=\"masl_link_prodHeart\"]"));
	  String wishlistedproduct = wishlisted.getText();
	  assertEquals(wishlistedproduct,tobewishlist,"Not wishlisted");
  }
  
  @Test(dependsOnMethods="ValidatingWishlisting")
  public void AddingtoCart() throws InterruptedException {
	  System.out.println(driver.getCurrentUrl());
	  driver.findElement(By.xpath("(//button[@class=\"chakra-button quick-view-container css-5nmdpt\"])[1]")).click();
	  Thread.sleep(3000);
	  String productname = driver.findElement(By.xpath("//h1[@class=\"chakra-heading pdp-product-title css-eztj7i\"]")).getText();
	  String productprice = driver.findElement(By.xpath("//p[@class=\"chakra-text active-price css-sqy4ds\"]")).getText();
	  System.out.println("The name of Product"+productname);
	  System.out.println("The price of Product"+productprice);
	  
	  WebElement dropdown = driver.findElement(By.xpath("//div[@class=\"chakra-select__wrapper css-13z73i1\"]"));
	  
	  Select dropping = new Select(dropdown);
	  dropping.selectByVisibleText("5");
	  
	  driver.findElement(By.xpath("//button[@class=\"chakra-button add-to-cart css-5ezz59 persado-cta-EXPL-656_QB1UV9rNXn\"]")).click();
	  driver.findElement(By.xpath("//button[@class=\"chakra-button minicart-cart-btn css-cl82pt\"]")).click();
	  Thread.sleep(2000);
	  WebElement w2 = driver.findElement(By.xpath("//div[@class=\"line-item-name\"]"));
	  String AddedItems = w2.getText();
	  assertEquals(AddedItems,productname,"Not Added same product to Cart");
	  
  }
  
  @Test(dependsOnMethods="AddingtoCart")
  public void SignIn() throws InterruptedException {
	  System.out.println("Ye"+driver.getCurrentUrl());
	  
	  
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
	  driver.close();
  }

}
