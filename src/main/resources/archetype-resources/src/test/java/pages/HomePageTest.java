#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${groupId}.pages;

import org.junit.Assert;
import org.junit.Test;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import ${groupId}.pages.HomePage;

public class HomePageTest extends TestBase {

	HomePage homepage;

	@Test
	public void testH1Existing() throws InterruptedException {

		//when the website URL is definded in src/main/resources, you can modify it by adding a subPath
		String path="/decouvrir-squash-ta/contenu-statique/outils-et-fonctionnalites/squash-ta-test-automation" ;
		
		webDriver.get(websiteUrl + path);
		homepage = PageFactory.initElements(webDriver, HomePage.class);
	
		WebElement h1Text = homepage.getH1Element();
		Thread.sleep(3000);
		Assert.assertEquals("Not the right H1", "Squash TA (Test Automation)", h1Text.getText());;
	}


}
