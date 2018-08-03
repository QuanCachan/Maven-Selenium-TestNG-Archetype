#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${groupId}.webdriver;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import ${groupId}.util.Browser;

public class WebDriverFactory {

    private WebDriverFactory() {
        throw new IllegalStateException("Utility class");
    }

    /* Browsers constants */
    public static final String CHROME = "chrome";
    public static final String FIREFOX = "firefox";
    public static final String OPERA = "opera";
    public static final String INTERNET_EXPLORER = "ie";
    public static final String SAFARI = "safari";
    public static final String HTML_UNIT = "htmlunit";
    public static final String IPHONE = "iphone";

    /* Platform constants */
    public static final String WINDOWS = "windows";
    public static final String ANDROID = "android";
    public static final String XP = "xp";
    public static final String VISTA = "vista";
    public static final String MAC = "mac";
    public static final String LINUX = "linux";

    /**
     * Factory method to return a RemoteWebDriver instance given the url of the
     * Grid hub and a Browser instance.
     *
     * @param gridHubUrl
     * @param browser
     * @param username
     * @param password
     * @return
     */
    public static WebDriver getInstance(String gridHubUrl, Browser browser, String username, String password) {

        // In case there is no Hub
        if (gridHubUrl == null || gridHubUrl.length() == 0) {
            return getInstanceNoHub(browser.getName(), username, password);
        } else {

            // In case with Hub
            return getInstanceWithHub(gridHubUrl, browser, username, password);
        }
    }

    /**
     * Factory method to return a WebDriver instance with Hub
     *
     * @param gridHubUrl
     * @param browser
     * @param username
     * @param password
     * @return WebDriver instance
     */
    private static WebDriver getInstanceWithHub(String gridHubUrl, Browser browser, String username, String password) {
        WebDriver webDriver = null;

        DesiredCapabilities capability = new DesiredCapabilities();
        String browserName = browser.getName();
        capability.setJavascriptEnabled(true);

        switch (browserName) {
            case CHROME:
                capability = DesiredCapabilities.chrome();
                break;

            case FIREFOX:
                capability = DesiredCapabilities.firefox();
                FirefoxProfile ffProfile = new FirefoxProfile();

                // Authenication Hack for Firefox
                if (username != null && password != null) {
                    ffProfile.setPreference("network.http.phishy-userpass-length", 255);
                    capability.setCapability(FirefoxDriver.PROFILE, ffProfile);
                }

                capability.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
                break;

            case INTERNET_EXPLORER:
                capability = DesiredCapabilities.internetExplorer();
                capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                break;

            case OPERA:
                capability = DesiredCapabilities.operaBlink();
                break;

            case SAFARI:
                capability = DesiredCapabilities.safari();
                break;

            case ANDROID:
                capability = DesiredCapabilities.android();
                break;

            case IPHONE:
                capability = DesiredCapabilities.iphone();
                break;

            default:
                capability = DesiredCapabilities.htmlUnit();
                // HTMLunit Check
                if (username != null && password != null) {
                    webDriver = AuthenticatedHtmlUnitDriver.create(username, password);
                } else {
                    webDriver = new HtmlUnitDriver(true);
                }

                return webDriver;
        }

        capability = setVersionAndPlatform(capability, browser.getVersion(), browser.getPlatform());

        // Create Remote WebDriver
        try {
            webDriver = new RemoteWebDriver(new URL(gridHubUrl), capability);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return webDriver;
    }

    /**
     * Helper method to set version and platform for a specific browser
     *
     * @param capability
     * @param version
     * @param platform
     * @return
     */
    private static DesiredCapabilities setVersionAndPlatform(DesiredCapabilities capability, String version,
            String platform) {
        switch (platform) {
            case MAC:
                capability.setPlatform(Platform.MAC);
                break;

            case LINUX:
                capability.setPlatform(Platform.LINUX);
                break;

            case XP:
                capability.setPlatform(Platform.XP);
                break;

            case VISTA:
                capability.setPlatform(Platform.VISTA);
                break;

            case WINDOWS:
                capability.setPlatform(Platform.WINDOWS);
                break;

            case ANDROID:
                capability.setPlatform(Platform.ANDROID);
                break;

            default:
                capability.setPlatform(Platform.ANY);
                break;
        }

        if (version != null) {
            capability.setVersion(version);
        }
        return capability;
    }

    /**
     * Factory method to return a WebDriver instance without Hub
     *
     * @param browser
     * @param username
     * @param password
     * @return WebDriver instance
     */
    private static WebDriver getInstanceNoHub(String browser, String username, String password) {
        WebDriver webDriver = null;

        switch (browser) {
            case CHROME:
                setChromeDriver();
                webDriver = new ChromeDriver();
                break;

            case FIREFOX:
                // TODO : setFirefoxDriver();
                FirefoxProfile ffProfile = new FirefoxProfile();

                // Authenication Hack for Firefox
                if (username != null && password != null) {
                    ffProfile.setPreference("network.http.phishy-userpass-length", 255);
                }

                FirefoxOptions ffOptions = new FirefoxOptions();
                ffOptions.setProfile(ffProfile);
                webDriver = new FirefoxDriver(ffOptions);
                break;

            case INTERNET_EXPLORER:
                // TODO : setIEDriver();
                isSupportedPlatform(browser);
                webDriver = new InternetExplorerDriver();
                break;

            case OPERA:
                // TODO : setOperaDriver();
                webDriver = new OperaDriver();
                break;

            case SAFARI:
                // TODO : setSafariDriver();
                isSupportedPlatform(browser);
                webDriver = new SafariDriver();
                break;

            case IPHONE:
                // TODO : setIpDriver();
                webDriver = new RemoteWebDriver(DesiredCapabilities.iphone());
                break;

            case ANDROID:
                // TODO : setAndroDriver();
                webDriver = new RemoteWebDriver(DesiredCapabilities.android());
                break;

            default:
                // HTMLunit Check
                if (username != null && password != null) {
                    webDriver = AuthenticatedHtmlUnitDriver.create(username, password);
                } else {
                    webDriver = new HtmlUnitDriver(true);
                }
                break;
        }

        return webDriver;
    }

    private static void isSupportedPlatform(String browser) {
        boolean isSupported = true;
        Platform current = Platform.getCurrent();
        if (INTERNET_EXPLORER.equals(browser)) {
            isSupported = Platform.WINDOWS.is(current);
        } else if (SAFARI.equals(browser)) {
            isSupported = Platform.MAC.is(current) || Platform.WINDOWS.is(current);
        }
        assert isSupported : "Platform is not supported by " + browser.toUpperCase() + " browser";

    }

    /**
     * This method is to set ChromeDriver for different OSs
     */
    private static void setChromeDriver() {
        String subPath = "${groupId}".replace(".", "/");

        String os = System.getProperty("os.name").toLowerCase().substring(0, 3);
        String chromeBinary = "src/main/resources/" + subPath + "/drivers/chrome/chromedriver" + (os.equals("win") ? ".exe" : "");
        System.setProperty("webdriver.chrome.driver", chromeBinary);

    }

}
