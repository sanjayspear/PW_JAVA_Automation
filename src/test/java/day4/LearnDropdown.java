package day4;

import java.util.List;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.SelectOption;

public class LearnDropdown {
	
	//Youtube Tutoial URL: https://youtu.be/Rd_wm2gNzbo?list=PL699Xf-_ilW7qlOrCGqwsWkgNkHQTqaBb

	public static void main(String[] args) throws InterruptedException {
		Playwright pw = Playwright.create();
		LaunchOptions headless = new BrowserType.LaunchOptions().setHeadless(false);
		Page page = pw.chromium().launch(headless).newPage();
		page.navigate("https://letcode.in/dropdowns");
		// Approach 01
		// page.selectOption("#fruits", "3");

		// Approach 2 (value)
		Locator dropdown = page.locator("#fruits");
		// dropdown.selectOption("1");

		// Approach 3 (Label)
		// dropdown.selectOption(new SelectOption().setLabel("Pine Apple"));

		// Approach 4 (Index)
		dropdown.selectOption(new SelectOption().setIndex(3));

		String successMSG = page.locator(".subtitle").textContent();
		System.out.println(successMSG);
		Thread.sleep(1000);

		// To select multiple options from the drowdown
		Locator heros = page.locator("#superheros");
		heros.selectOption(new String[] { "am", "aq", "ta", "bt", "rb" });
		List<String> options = heros.allInnerTexts();
//		for(String i : options) {
//			System.out.println(i);
//		}
		options.forEach(i -> System.out.println(i));
		Thread.sleep(3000);

		pw.close();

	}

}
