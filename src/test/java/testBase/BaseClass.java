package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import pageObjects.GiftCardPage;
import pageObjects.HomePage;
import pageObjects.ProductsPage;

public class BaseClass {
	
	public static WebDriver driver;
	public static Logger logger;
	public static HomePage homeObj;
	public static ProductsPage productPageObj;
	public static GiftCardPage giftCardPageObj;
	public static Properties property;
	public static FileInputStream file;
	
	
	@BeforeTest
	@Parameters({"browser","os"})
	public void setup(String browserName,String os) throws MalformedURLException{
			
		  try {
				property = new Properties();
				file = new FileInputStream("C:\\Users\\2318630\\eclipse-workspace\\HackathonTestNG\\src\\test\\resources\\config.properties");
				property.load(file);
			}
			catch(Exception e) {
				System.out.println("file not found!");
			}
		
		
	      if(property.getProperty("execution_env").equalsIgnoreCase("remote")){
				DesiredCapabilities capabilities = new DesiredCapabilities();
				//os
				if (os.equalsIgnoreCase("windows")) {
				    capabilities.setPlatform(Platform.WIN11);
				} else if (os.equalsIgnoreCase("mac")) {
				    capabilities.setPlatform(Platform.MAC);
				} else {
				    System.out.println("No matching OS..");
				      }
				//browser
				switch (browserName.toLowerCase()) {
				    case "chrome":
				        capabilities.setBrowserName("chrome");
				        break;
				    case "edge":
				        capabilities.setBrowserName("MicrosoftEdge");
				        break;
				    default:
				        System.out.println("No matching browser");
				     }
		        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);	
			}
	      
	      else if(property.getProperty("execution_env").equalsIgnoreCase("local")){
				switch(browserName.toLowerCase()) 
					{
					case "chrome":
				        driver=new ChromeDriver();
				        break;
				    case "edge":
				    	driver=new EdgeDriver();
				        break;
				    default:
				        System.out.println("No matching browser");
				        driver=null;
					}
	      }
		
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get(property.getProperty("appURL"));
			driver.manage().window().maximize();
			
			logger = LogManager.getLogger(this.getClass());
			
			logger.info("-----------Driver setup done with "+browserName+" browser------------------");
	}
	
	@AfterTest
	public void close() {
		driver.quit();
	}
	
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}
	
}


