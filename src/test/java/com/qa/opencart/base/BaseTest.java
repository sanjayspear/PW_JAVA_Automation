package com.qa.opencart.base;

import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.microsoft.playwright.Page;
import com.qa.opencart.factory.PlaywrightFactory;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;

public class BaseTest {
	
	// PlaywrightFactory object to initialize the browser
    PlaywrightFactory pf;
    
    // Page object to interact with browser pages
    Page page;
    protected Properties prop;
    // HomePage object to access HomePage specific methods
    protected HomePage hm;
    // LoginPage object to access LoginPage specific methods
    protected LoginPage lp;

    
    
	// This method runs before any test. It's used to set up the browser and initialize the HomePage object.
    @BeforeTest
    public void setUp() {
        // Initializing the PlaywrightFactory to handle browser setup
        pf = new PlaywrightFactory();
        
        //Initialize properties
        prop = pf.init_prop();
        
        // Initializing browser (chromium in this case)
        page = pf.initBrowser(prop);
        
        // Creating HomePage object to interact with the homepage
        hm = new HomePage(page);

    }
    
 // This method runs after all tests have completed. It closes the browser.
    @AfterTest
    public void tearDown() {
        // Closing the browser and cleaning up resources
    	if (page != null) {
    	    page.context().browser().close();
    	}
    }
}
