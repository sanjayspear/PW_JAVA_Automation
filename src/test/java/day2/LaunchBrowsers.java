package day2;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class LaunchBrowsers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(
					new BrowserType
					.LaunchOptions()
					.setHeadless(false)
					//.setChannel("msedge") //If you enable then your script runs in the local chrome browser
											//else it will run default chromium browser.
				);
		Page page = browser.newPage();
		//page.navigate("https://letcode.in/");
		page.navigate("https://www.google.com");
		System.out.println(page.title());
		System.out.println(page.url());
		page.close();
		browser.close();
		playwright.close();
	}

}
