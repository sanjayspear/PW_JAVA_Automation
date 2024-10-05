package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;
import com.qa.opencart.factory.PlaywrightFactory;
import com.qa.opencart.pages.HomePage;

// Good practices: Never assert inside the page class, make sure all your tests are independent.

public class HomePageTest {

    // PlaywrightFactory object to initialize the browser
    PlaywrightFactory pf;
    
    // Page object to interact with browser pages
    Page page;
    
    // HomePage object to access HomePage specific methods
    HomePage hm;

    // This method runs before any test. It's used to set up the browser and initialize the HomePage object.
    @BeforeTest
    public void setUp() {
        // Initializing the PlaywrightFactory to handle browser setup
        pf = new PlaywrightFactory();
        
        // Initializing browser (chromium in this case)
        page = pf.initBrowser("chromium");
        
        // Creating HomePage object to interact with the homepage
        hm = new HomePage(page);
    }

    // This test checks if the home page title is correct.
    @Test
    public void homePageTitleTest() {
        // Fetch the title from the HomePage
        String actualTitle = hm.getPageTitle();
        
        // Assert that the title matches the expected value
        Assert.assertEquals(actualTitle, "Your Store");
    }

    // This test checks if the home page URL is correct.
    @Test
    public void homePageUrlTest() {
        // Fetch the URL from the HomePage
        String actualUrl = hm.getHomePageUrl();
        
        // Assert that the URL matches the expected value
        Assert.assertEquals(actualUrl, "https://naveenautomationlabs.com/opencart/");
    }

    // DataProvider method to supply different product names for the search test
    @DataProvider
    public Object[][] getProductData() {
        // Providing 3 sets of product names for search testing
        return new Object[][] { { "Mackbook" }, { "iMac" }, { "Samsung" } };
    }

    // This test runs 3 times with different product names from DataProvider.
    @Test(dataProvider = "getProductData")
    public void searchResultTest(String productName) {
        // Perform a search on the homepage with the product name
        String actualSearchHeader = hm.doSearch(productName);
        
        // Assert that the search result header matches the product name
        Assert.assertEquals(actualSearchHeader, "Search - " + productName);
    }

    // This method runs after all tests have completed. It closes the browser.
    @AfterTest
    public void tearDown() {
        // Closing the browser and cleaning up resources
        page.context().browser().close();
    }
}
