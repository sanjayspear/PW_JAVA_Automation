package com.pw.basics;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Alerts {

	public static void main(String[] args) throws InterruptedException {
		Playwright pw = Playwright.create();
		LaunchOptions headless = new BrowserType.LaunchOptions().setHeadless(false);
		Page page = pw.chromium().launch(headless).newPage();
		page.navigate("https://letcode.in/alert");
		String actualTitle = page.title();
		System.out.println("Title of the Application Is ==> " + actualTitle);

		page.onceDialog(dialog -> {
			System.out.println("Message from the simple alert is => " + dialog.message());
			dialog.dismiss();
		});

		page.click("#accept");

		page.onceDialog(dialog -> {
			System.out.println(dialog.defaultValue());
			dialog.accept("Sanjay");
		});

		page.locator("#prompt").click();
		System.out.println(page.locator("#myName").textContent());
		pw.close();
	}	

}
