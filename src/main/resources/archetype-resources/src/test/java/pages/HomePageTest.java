#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${groupId}.pages;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import ${groupId}.pages.HomePage;

public class HomePageTest extends TestBase {

	HomePage homepage;

	@Parameters({ "path" })
	@Test
	public void testH1Existing(String path) throws InterruptedException {

		webDriver.get(websiteUrl + path);
		homepage = PageFactory.initElements(webDriver, HomePage.class);
	
		WebElement h1Text = homepage.getH1Element();
		Thread.sleep(3000);
		Assert.assertEquals(h1Text.getText(), "Squash TA (Test Automation)", "Not the right H1");;
	}


}
