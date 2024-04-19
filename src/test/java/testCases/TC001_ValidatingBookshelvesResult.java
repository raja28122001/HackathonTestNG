package testCases;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.ProductsPage;
import testBase.BaseClass;

public class TC001_ValidatingBookshelvesResult extends BaseClass {

	@Test
	public void validateBookshelveResult() throws InterruptedException {
		
		try {
			homeObj = new HomePage(driver);
			logger.info("******TC001_ValidatingBookshelvesResult Started*******");
			homeObj.searchItem("BookShelves");
			logger.info("Searched for bookshelves");
			
			productPageObj = new ProductsPage(driver);
			productPageObj.closePopup();
			logger.info("Closed the ad popup");
			
			productPageObj.clickPrice();
			productPageObj.setPrice();
			logger.info("Clicked price option and setting price");
			
			productPageObj.clickCategory();
			productPageObj.selectBookshelves();
			logger.info("Clicked category option and selecting the bookshelves option ");
			
			productPageObj.selectOutOfStock();
			logger.info("Selected exclude out of stock");
			
			productPageObj.clickSortBy();
			productPageObj.selectHighToLow();
			Thread.sleep(2000);
			logger.info("Clicked sortby option and selected High to Low");
			
			List<String> productDetail = productPageObj.getProductNameAndPrice();
			System.out.println("Product And Price Details : ");
			for(String s : productDetail) {
		        System.out.println(s);
			}
			logger.info("Captured the Product names and prices");
			
			logger.info("Valiating the testCase");
			if(productDetail.size()==3) {
				logger.info(" ==>Test TC001_ValidateBookshelves Passed");
				Assert.assertTrue(true);
			}
			else {
				logger.error("  ==>Test TC001_ValidateBookshelves Failed");
				Assert.fail();
			}
			
		}
		catch(Exception e) {
			logger.error("  ==>Test TC001_ValidateBookshelves Failed");
			Assert.fail();
		}	
	}
		
}
