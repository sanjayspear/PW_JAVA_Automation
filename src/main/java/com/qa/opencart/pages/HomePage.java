package com.qa.opencart.pages;

//Don't just see the code, to know more about this code, scroll down and read the concept.
import com.microsoft.playwright.Page;

public class HomePage {
	// 1. String Locators or Object Repository:

	// adding Private modifier to the `Page` avoids unnecessary null pointer
	// exception (Encapsulation).
	private Page page;

	private String search = "input[name='search']";
	private String searchIcon = "div#search button";
	private String searchResultHeader = "div#content h1";
	private String loginLink = "a:text('Login')";
	private String myAccountLink = "a[title='My Account']";

	// 2. page constructor:
	public HomePage(Page page) {
		this.page = page;
	}

	// 3. page actions/methods:
	public String getPageTitle() {
		String title = page.title();
		System.out.println("Page Title: " + title);
		return title;
	}

	public String getHomePageUrl() {
		String url = page.url();
		System.out.println("Page URl: " + url);
		return url;
	}

	public String doSearch(String productName) {
		page.fill(search, productName);
		page.click(searchIcon);
		String searcHeader = page.textContent(searchResultHeader);
		System.out.println("The search header is: " + searcHeader);
		return searcHeader;
	}
	
	public LoginPage navigateToLoginPage() {
		page.click(myAccountLink);
		page.click(loginLink);
		return new LoginPage(page);
	}
}

/*
 * 1. Private Modifier: By declaring Page page as private, you encapsulate the
 * variable within the HomePage class. This ensures the page object can only be
 * accessed through the class's methods and not directly from outside the class.
 * This helps avoid unwanted modifications, which can lead to
 * NullPointerException if page is not properly initialized.
 * 
 * 2. Constructor Initialization: You correctly initialize the page object using
 * the constructor HomePage(Page page). When an instance of HomePage is created,
 * the page object is assigned, ensuring that the Playwright Page object is
 * available for methods like fill(), click(), etc.
 * 
 * 3. Avoiding NullPointerException: Since the page object is initialized in the
 * constructor and is private, it ensures that the methods accessing page won't
 * run into a NullPointerException unless it's not properly passed during object
 * creation. This makes your code safer.
 * 
 * 4. This encapsulation is the main principle of `Encapsulation`,In Java,
 * encapsulation means bundling the data (like your Page variable) and the
 * methods that operate on that data (like doSearch() or getPageTitle()) into a
 * single class. By making the Page variable private and only allowing access
 * through public methods, you're hiding the internal details of how the page is
 * managed. This prevents outside code from directly changing the Page, which
 * keeps your class safe from accidental misuse and maintains control over how
 * the data is handled.This is a core principle of object-oriented programming
 * to improve security and code organization.
 * 
 * Your approach effectively avoids issues like uninitialized variables by
 * managing the page object's lifecycle through constructor initialization.
 */

//======================================Program Explanation============================

/*
 * 
 * This code defines a HomePage class used in Playwright automation (Java) for
 * interacting with a web page.
 * 
 * Here’s a brief explanation of the key concepts:
 * 
 * 1. Encapsulation:
 * 
 * The Page object (from Playwright) is marked as private, meaning it can only
 * be accessed within this class. This prevents external access and avoids
 * potential errors like null pointer exceptions.
 * 
 * 2. Locators:
 * 
 * Strings such as search, searchIcon, and searchResultHeader are locators.
 * These are CSS selectors that identify HTML elements on the web page for
 * interactions (e.g., filling a search box or clicking a button).
 * 
 * 3. Constructor: The constructor public HomePage(Page page) initializes the
 * class by assigning the passed Page object to the page variable. This allows
 * the class to perform actions on the current web page.
 * 
 * 4. Page Methods (Actions):
 * 
 * - getPageTitle(), getHomePageUrl(), and doSearch(String productName) are
 * methods that define specific actions for interacting with the web page:
 * 
 * 1. getPageTitle(): Returns the page’s title. 2. getHomePageUrl(): Returns the
 * current URL. 3. doSearch(): Fills the search box with a product name, clicks
 * the search button, and returns the search result header text.
 * 
 * These methods structure the Page Object Model (POM) approach, making web page
 * interactions reusable and maintainable.
 */
