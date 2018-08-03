#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${groupId}.pages;

import org.openqa.selenium.WebDriver;

/**
 * Abstract class representation of a Page in the UI.
 * 
 * @author qtran
 */
public abstract class Page {

	protected WebDriver webDriver;

	/**
	 * Constructor injecting WebDriver interface
	 * 
	 * @param webDriver
	 */
	public Page(WebDriver webDriver) {
		super();
		this.webDriver = webDriver;
	}

	public WebDriver getWebDriver() {
		return webDriver;
	}
	
	public String getWebDriverTitle() {
		return webDriver.getTitle();
	}
}
