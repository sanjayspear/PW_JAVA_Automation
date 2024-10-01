package day3;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class LearnInputs {

	@SuppressWarnings({ "deprecation" })
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Playwright pw = Playwright.create();
		Browser browser = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		Page page = browser.newPage();
		page.navigate("https://letcode.in/edit");
		page.locator("#fullName").fill("Sanjay Singania");
		Locator appendText = page.locator("#join");
		appendText.press("End");
		appendText.type(" man");
		appendText.press("Tab");
		page.fill("#getMe", "I'm not sure buddy...!");
		String ph = page.locator("id=getMe").getAttribute("placeholder");
		System.out.println(ph);
		page.locator("//input[@id='clearMe']").clear();
		Thread.sleep(1000);
		pw.close();
	}
}
