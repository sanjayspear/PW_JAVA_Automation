package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

// Good practices: Never assert inside the page class, make sure all your tests are independent.

public class HomePageTest extends BaseTest {

    // This test checks if the home page title is correct.
    @Test
    public void homePageTitleTest() {
        // Fetch the title from the HomePage
        String actualTitle = hm.getPageTitle();
        
        // Assert that the title matches the expected value
        Assert.assertEquals(actualTitle, AppConstants.HOME_PAGE_TITLE);
    }

    // This test checks if the home page URL is correct.
    @Test
    public void homePageUrlTest() {
        // Fetch the URL from the HomePage
        String actualUrl = hm.getHomePageUrl();
        String expectedUrl = prop.getProperty("url").trim();
        // Assert that the URL matches the expected value
        Assert.assertEquals(actualUrl, expectedUrl);
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

}
