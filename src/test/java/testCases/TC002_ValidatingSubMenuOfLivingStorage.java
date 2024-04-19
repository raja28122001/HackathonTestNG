package testCases;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import testBase.BaseClass;

public class TC002_ValidatingSubMenuOfLivingStorage extends BaseClass {

	@Test
	public void validateSubmenuUnderLivingStorage() throws InterruptedException {
		
		try {
			logger.info("******TC002_ValidatingSubMenuOfLivingStorage started*********");
			productPageObj.clickLivingOptions();
			
			logger.info("Clicked living link in product page");
			
			List<WebElement> livingOptionsList = productPageObj.getListUnderLivingOptions();
		    System.out.println("\nSubmenu under Living Option :");
			for(WebElement e: livingOptionsList) {
				System.out.println(e.getAttribute("innerText"));
			}
			logger.info("Captured the submenu under living option");
			Thread.sleep(2000);
			
			logger.info("Validating the testCase");
			if(livingOptionsList.size()==9) {
				logger.info(" ==>Test TC002_ValidatingSubMenuOfLivingStorage passed");
				Assert.assertTrue(true);
			}
			else {
				logger.info("  ==>Test TC002_ValidatingSubMenuOfLivingStorage failed");
				Assert.fail();
			}
		}
		catch(Exception e){
			logger.info("  ==>Test TC002_ValidatingSubMenuOfLivingStorage failed");
			Assert.fail();
		}
	}

}
