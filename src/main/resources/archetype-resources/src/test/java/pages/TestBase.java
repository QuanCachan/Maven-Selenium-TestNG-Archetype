#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${groupId}.pages;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;

import ${groupId}.util.Browser;
import ${groupId}.util.PropertyLoader;
import ${groupId}.webdriver.WebDriverFactory;

public class TestBase {

	protected static WebDriver webDriver;
	protected static String gridHubUrl;

	protected static String websiteUrl;

	protected static Browser browser;

	@BeforeClass
	public static void init() {
		websiteUrl = PropertyLoader.loadProperty("site.url");

		gridHubUrl = PropertyLoader.loadProperty("grid2.hub");

		browser = new Browser();
		browser.setName(PropertyLoader.loadProperty("browser.name"));
		browser.setVersion(PropertyLoader.loadProperty("browser.version"));
		browser.setPlatform(PropertyLoader.loadProperty("browser.platform"));

		String username = PropertyLoader.loadProperty("user.username");
		String password = PropertyLoader.loadProperty("user.password");

		webDriver = WebDriverFactory.getInstance(gridHubUrl, browser, username, password);
		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterSuite
	public static void tearDown() {
		if (webDriver != null) {
			webDriver.quit();
		}
	}
	
}
