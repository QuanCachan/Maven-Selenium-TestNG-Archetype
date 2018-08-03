#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${groupId}.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Sample page
 * 
 * @author qtran
 */
public class HomePage extends Page {

	private final String H1_TAG = "h1";
	
	@FindBy(how = How.TAG_NAME, using = H1_TAG)
	@CacheLookup
	private WebElement h1Element;
	
	public HomePage(WebDriver webDriver) {
		super(webDriver);
	}

	public WebElement getH1Element() {
		return h1Element;
	}

	//You can define other elements of your Website below...

}
