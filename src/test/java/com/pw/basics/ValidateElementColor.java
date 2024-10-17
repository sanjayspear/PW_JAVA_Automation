package com.pw.basics;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.microsoft.playwright.*;

public class ValidateElementColor {
    private Playwright playwright;
    private Browser browser;
    private Page page;

    @BeforeClass
    public void setUp() {
        // Initialize Playwright
        playwright = Playwright.create();
        // Launch the browser
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        page = context.newPage();

        // Navigate to the webpage
        page.navigate("https://ecommerce-playground.lambdatest.io/index.php?");
    }

    @Test
    public void validateColor() {
        // Locate the element using the XPath selector
        Locator element = page.locator("(//*[contains(@class, 'entry-design') and contains(@class, 'design-image') and contains(@class, 'flex-grow-0') and contains(@class, 'flex-shrink-0')])[1]");

        // Validate the CSS color of the element
        String colorValue = (String) element.evaluate("element => getComputedStyle(element).color");
        Assert.assertEquals(colorValue, "rgb(38, 38, 38)", "Color validation failed! Expected: rgb(38, 38, 38), but got: " + colorValue);
        System.out.println("Color validation passed!");
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        browser.close();
        playwright.close();
    }
}

