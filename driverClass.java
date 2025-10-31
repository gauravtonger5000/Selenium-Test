package ProcurementAllPagesUpdatedTemp;

import java.io.File;
import java.util.Map;
import java.util.Optional;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v132.network.Network; // Use the correct version for Network and ConnectionType
import org.openqa.selenium.devtools.v132.network.model.ConnectionType;
public class driverClass
{
	WebDriver driver;
	public driverClass(WebDriver driver) {
		this.driver = driver;
	}
	public static WebDriver browserSel() throws EncryptedDocumentException, InterruptedException {
		String browserName = System.getProperty("browser", "Web Chrome");
		WebDriver driver = null;
		switch (browserName) {
		case "Web Chrome": {
			String userHome = System.getProperty("user.home");
			String downloadsFolderPath = userHome + File.separator + "Downloads";
			String chromeDriverPath = downloadsFolderPath + File.separator + "chromedriver.exe";
			File chromeDriverFile = new File(chromeDriverPath);
			if (!chromeDriverFile.exists()) {
				throw new RuntimeException("ChromeDriver not found in Downloads folder: " + chromeDriverPath);
			}
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-gpu", "--window-size=1920,1080", "--disable-notifications");
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			
//			Use this to slow down the browser like. 3G. No need to change in the network. 
			DevTools devTools = ((ChromeDriver) driver).getDevTools();
			devTools.createSession();
	        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

//	        it set to 1 mbps upload speed and 750 kbps download speed
//	        devTools.send(Network.emulateNetworkConditions(false, 100, 750 * 1024 / 8, 250 * 1024 / 8, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));
//	        100 ms latency
//	        750 Kbps download
//	        250 Kbps upload
//	        for 1 mbps add *1024
		}
			break;
		case "Mobile Chrome": {
			String userHome = System.getProperty("user.home");
			String downloadsFolderPath = userHome + File.separator + "Downloads";
			String chromeDriverPath = downloadsFolderPath + File.separator + "chromedriver.exe";
			File chromeDriverFile = new File(chromeDriverPath);
			if (!chromeDriverFile.exists()) {
				throw new RuntimeException("ChromeDriver not found in Downloads folder: " + chromeDriverPath);
			}
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("mobileEmulation", Map.of("deviceName", "iPhone X"));
			options.addArguments("--disable-gpu", "--disable-notifications");
			driver = new ChromeDriver(options);
		}
			break;
		case "Web Firefox": {
			String userHome = System.getProperty("user.home");
			String downloadsFolderPath = userHome + File.separator + "Downloads";
			String geckoDriverPath = downloadsFolderPath + File.separator + "geckodriver.exe";
			System.setProperty("webdriver.gecko.driver", geckoDriverPath);
			File firefoxDriverFile = new File(geckoDriverPath);
			if (!firefoxDriverFile.exists()) {
				throw new RuntimeException("Firefox Driver not found in Downloads folder: " + geckoDriverPath);
			}
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--disable-gpu", "--window-size=1920,1080", "--disable-notifications");
			driver = new FirefoxDriver(options);
			driver.manage().window().maximize();
		}
			break;
		case "Mobile Firefox": {
			String userHome = System.getProperty("user.home");
			String downloadsFolderPath = userHome + File.separator + "Downloads";
			String geckoDriverPath = downloadsFolderPath + File.separator + "geckodriver.exe";
			System.setProperty("webdriver.gecko.driver", geckoDriverPath);
			File firefoxDriverFile = new File(geckoDriverPath);
			if (!firefoxDriverFile.exists()) {
				throw new RuntimeException("Firefox Driver not found in Downloads folder: " + geckoDriverPath);
			}
			FirefoxOptions options = new FirefoxOptions();
			options.addPreference("general.useragent.override","Mozilla/5.0 (iPhone; CPU iPhone OS 13_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1");

			// Disable GPU and notifications
			options.addArguments("--disable-gpu", "--disable-notifications");

			// Create Firefox driver with options
			driver = new FirefoxDriver(options);

			// Set the window size to simulate iPhone X dimensions (375x812)
			driver.manage().window().setSize(new Dimension(375, 812));
		}
			break;
		case "Web Edge": {
			String userHome = System.getProperty("user.home");
			String downloadsFolderPath = userHome + File.separator + "Downloads";
			String edgeDriverPath = downloadsFolderPath + File.separator + "msedgedriver.exe";
			File edgeDriverFile = new File(edgeDriverPath);
			if (!edgeDriverFile.exists()) {
				throw new RuntimeException("EdgeDriver not found in Downloads folder: " + edgeDriverPath);
			}
			System.setProperty("webdriver.edge.driver", edgeDriverPath);
			EdgeOptions option2 = new EdgeOptions();
			option2.addArguments("--disable-gpu", "--window-size=1920,1080", "--disable-notifications");
			driver = new EdgeDriver(option2);
			driver.manage().window().maximize();
		}
			break;
		case "Mobile Edge": {
			String userHome = System.getProperty("user.home");
			String downloadsFolderPath = userHome + File.separator + "Downloads";
			String edgeDriverPath = downloadsFolderPath + File.separator + "msedgedriver.exe";
			File edgeDriverFile = new File(edgeDriverPath);
			if (!edgeDriverFile.exists()) {
				throw new RuntimeException("EdgeDriver not found in Downloads folder: " + edgeDriverPath);
			}
			System.setProperty("webdriver.edge.driver", edgeDriverPath);
			EdgeOptions options = new EdgeOptions();
			options.addArguments("--disable-gpu", "--disable-notifications");
			options.addArguments("user-agent=Mozilla/5.0 (iPhone; CPU iPhone OS 13_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Edg/91.0.864.59 Safari/537.36");
			driver = new EdgeDriver(options);
			driver.manage().window().setSize(new Dimension(375, 812)); // iPhone X resolution
		}
			break;
		default:
			throw new IllegalStateException("Unexpected browser: " + browserName);
		}
		return driver;
	}
}