package ecommerce.selenium.testcomponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ecommerce.resources.ExtentReporterNG;
import ecommerce.selenium.pageobject.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LoginPage lg;
	private ITestContext context;

	public WebDriver driverinitialize() throws IOException {

		// properties can read global properties

		Properties prop = new Properties();
		try (FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//ecommerce//resources//GlobalData.properties")) {
			// Load properties file
			prop.load(fis);
			// input stream will b a file
			// to read browser from maven command : System.getProperty("browser")!=null
			// System.getProperty("browser")!=null?:prop.getProperty("browser");

			// take browser from gloabal data : prop.getProperty("browser");

			String browsername = System.getProperty("browser") != null ? System.getProperty("browser")
					: prop.getProperty("browser");

			if (browsername.contains("chrome")) {
				ChromeOptions options = new ChromeOptions();

				WebDriverManager.chromedriver().setup();
				if (browsername.contains("headless")) {
					options.addArguments("headless");
				}
				driver = new ChromeDriver(options);
				driver.manage().window().setSize(new Dimension(1140,900));//full screen
			} else if (browsername.equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			} else if (browsername.equalsIgnoreCase("firefox")) {
				FirefoxOptions options = new FirefoxOptions();
				options.addPreference("marionette.enabled", true);

				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver(options);
//			WebDriverManager.firefoxdriver().setup();
//			driver = new FirefoxDriver();
			} else {
				throw new IllegalArgumentException("Browser not supported: " + browsername);
			}
		} catch (IOException e) {
			System.err.println("Error loading properties file: " + e.getMessage());
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		driver.manage().window().maximize();
		return driver;
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {
		// reading the json to string

		String jsoncontent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);

		// convert string to hashmap Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsoncontent,
				new TypeReference<List<HashMap<String, String>>>() {

				});

		return data;
	}

	public String getScreenshot(String testcasename, WebDriver driver) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testcasename + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testcasename + ".png";
	}

	public static ITestContext setContext(ITestContext iTestContext, WebDriver driver) {
		iTestContext.setAttribute("driver", driver);

		return iTestContext;
	}

	@BeforeMethod(alwaysRun = true)
	public LoginPage lunch_application() throws IOException {

		driver = driverinitialize();
		lg = new LoginPage(driver);
		lg.goTo();

		return lg;
	}

	@AfterMethod(alwaysRun = true)
	public void closedriver() {
		driver.quit();
	}

}
