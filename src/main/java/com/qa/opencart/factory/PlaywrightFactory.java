package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {

	/**
	 * This code introduces the use of the ThreadLocal class to ensure that each
	 * thread gets its own instance of the Playwright, Browser, BrowserContext, and
	 * Page objects.
	 * 
	 * In a multi-threaded environment (e.g., when running parallel tests),
	 * ThreadLocal allows each thread to work with its own copy of these objects.
	 * This prevents resource conflicts between threads, ensuring that actions
	 * performed in one thread do not interfere with actions in another.
	 * 
	 * ThreadLocal effectively isolates thread-specific data, maintaining thread
	 * safety without requiring explicit synchronization.
	 */

	// ThreadLocal ensures that each thread has its own instance of Playwright,
	// Browser, BrowserContext, and Page
	private static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();
	private static ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
	private static ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal<>();
	private static ThreadLocal<Page> tlPage = new ThreadLocal<>();

	private Properties prop;

	/**
	 * This method returns the Playwright instance for the current thread.
	 * 
	 * @return Playwright instance for the current thread.
	 */
	public static Playwright getPlaywright() {
		return tlPlaywright.get();
	}

	/**
	 * This method returns the Browser instance for the current thread.
	 * 
	 * @return Browser instance for the current thread.
	 */
	public static Browser getBrowser() {
		return tlBrowser.get();
	}

	/**
	 * This method returns the BrowserContext instance for the current thread.
	 * 
	 * @return BrowserContext instance for the current thread.
	 */
	public static BrowserContext getBrowserContext() {
		return tlBrowserContext.get();
	}

	/**
	 * This method returns the Page instance for the current thread.
	 * 
	 * @return Page instance for the current thread.
	 */
	public static Page getPage() {
		return tlPage.get();
	}

	/**
	 * This method initializes the browser (Chromium, Firefox, Safari, etc.) based
	 * on the browser name from the properties file. It also sets up the Browser,
	 * BrowserContext, and Page for each thread using ThreadLocal.
	 * 
	 * @param prop Properties object containing the browser and URL information.
	 * @return Page The new Page instance after browser initialization and
	 *         navigation to the URL.
	 */
	public Page initBrowser(Properties prop) {
		String browserName = prop.getProperty("browser").trim();
		System.out.println("Browser Name is: " + browserName);

		tlPlaywright.set(Playwright.create());

		switch (browserName.toLowerCase()) {
		case "chromium":
			tlBrowser.set(tlPlaywright.get().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;
		case "firefox":
			tlBrowser.set(tlPlaywright.get().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;
		case "safari":
			tlBrowser.set(tlPlaywright.get().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;
		case "chrome":
			tlBrowser.set(tlPlaywright.get().chromium()
					.launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false)));
			break;
		default:
			System.out.println("Please pass the right browser name.....");
			throw new IllegalArgumentException("Invalid browser name provided: " + browserName);
		}

		// Initialize BrowserContext and Page for the current thread
		tlBrowserContext.set(getBrowser().newContext());
		tlPage.set(getBrowserContext().newPage());

		// Navigate to the URL specified in the properties file
		String url = prop.getProperty("url").trim();
		if (url != null && !url.isEmpty()) {
			getPage().navigate(url);
		} else {
			throw new IllegalArgumentException("URL is not specified in the properties file.");
		}

		return getPage();
	}

	/**
	 * This method is used to initialize the Properties object by loading the
	 * configuration properties from the config file.
	 * 
	 * @return Properties object containing the configuration settings.
	 */
	public Properties init_prop() {
		prop = new Properties();
		try (FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties")) {
			prop.load(ip);
		} catch (FileNotFoundException e) {
			System.err.println("Config file not found: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Error loading config properties: " + e.getMessage());
		}
		return prop;
	}

	/**
	 * This method is used to close and clean up resources for each thread, ensuring
	 * that the browser is closed after use.
	 */
	public static void closeBrowser() {
		if (getPage() != null) {
			getPage().close();
		}
		if (getBrowserContext() != null) {
			getBrowserContext().close();
		}
		if (getBrowser() != null) {
			getBrowser().close();
		}
		if (getPlaywright() != null) {
			getPlaywright().close();
		}
	}
}
