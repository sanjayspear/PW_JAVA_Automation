package com.pw.basics;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;

public class CodegenScript {
	public static void main(String[] args) throws InterruptedException {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();
			page.navigate("https://letcode.in/");
			page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Log in")).click();
			page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter registered email")).click();
			page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter registered email"))
					.fill("test001@test.com");
			page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter registered email"))
					.press("Tab");
			page.getByPlaceholder("Enter password").fill("123456");
			page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("LOGIN")).click();
			Thread.sleep(3000);
		}
	}
}
