package testCases;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.GiftCardPage;
import testBase.BaseClass;
import utilities.DataProviders;
import utilities.ExcelUtility;

public class TC003_ValidatingGiftCardsSection extends BaseClass {
	
	public boolean firstExecution = true;
	public int currentRow = 1;
	public void navigateToGiftCardPage() {
		productPageObj.clickGiftCard();
	}
	
	
	@Test(dataProvider="FormInputData",dataProviderClass=DataProviders.class)
	public void validateGiftCardsSection(String ocassion, String  amount,String recName,String recEmail,String recMobile,String yName,String yEmail,String yMobile,String address,String pincode,String expResult, String result) throws InterruptedException, IOException
	{
		
		ExcelUtility excelObj = new ExcelUtility("C:\\Users\\2318630\\eclipse-workspace\\HackathonProject\\testData\\HackathonData.xlsx");
	
      try {
    	    logger.info("******TC003__ValidatingGiftCardsSection started*********");
			if(firstExecution==true) {
				navigateToGiftCardPage();
			}
			firstExecution=false;
			logger.info("Navigated to GiftCard section");
			
			giftCardPageObj = new GiftCardPage(driver);
			giftCardPageObj.selectOccasion(ocassion);
			logger.info("Selected Ocassion :"+ocassion);
			
			giftCardPageObj.resetAmount();
			giftCardPageObj.selectAmount(amount);
			logger.info("Selected amount :"+amount);
			
			giftCardPageObj.clickNext();
			logger.info("Clicked next button");
			
			giftCardPageObj.resetAllInputs();
			giftCardPageObj.setRecieverName(recName);
			giftCardPageObj.setRecieverEmail(recEmail);
			giftCardPageObj.setRecieverMobile(recMobile);
			giftCardPageObj.setYourName(yName);
			giftCardPageObj.setYourEmail(yEmail);
			giftCardPageObj.setYourMobile(yMobile);
			giftCardPageObj.setAddress(address);
			giftCardPageObj.setPincode(pincode);
			logger.info("provided all details in the form section");
			
			Thread.sleep(2000);
			giftCardPageObj.clickConfirm();
			logger.info("Clicked confirm button");
			Thread.sleep(2000);
				
			logger.info("Validating the testCase");
			if(expResult.equalsIgnoreCase("valid")) {
				
				if(giftCardPageObj.isTargetPageDisplayed()) {
					giftCardPageObj.scrollToTargetPage();
					excelObj.setCellData("Sheet1", currentRow,11, "Passed");
					currentRow++;
					logger.info("  ==>Test TC003_ValidatingGiftCardsSection Passed");
					Assert.assertTrue(true);
				}
				else {
					excelObj.setCellData("Sheet1", currentRow,11, "Failed");
					currentRow++;
					logger.error("  ==>Test TC003_ValidatingGiftCardsSection Failed");
					Assert.fail();
				}		
			}
			
			else if(expResult.equalsIgnoreCase("invalid")) {
				if(giftCardPageObj.isTargetPageDisplayed()) {
					excelObj.setCellData("Sheet1", currentRow,11, "Failed");
					currentRow++;
					logger.error("  ==>Test TC003_ValidatingGiftCardsSection Failed");
					Assert.fail();
				}
				else {
					giftCardPageObj.scrollToTargetPage();
					excelObj.setCellData("Sheet1", currentRow,11, "Passed");
					currentRow++;
					logger.info("  ==>Test TC003_ValidatingGiftCardsSection Passed");
					Assert.assertTrue(true);
				}
			}
        }
	    catch(Exception e) {
	    	logger.error("  ==>Test TC003_ValidatingGiftCardsSection Failed");
	    	Assert.fail();
	    }
	}
}
