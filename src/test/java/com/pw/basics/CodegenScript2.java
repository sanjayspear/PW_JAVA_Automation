package com.pw.basics;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;

public class CodegenScript2 {
  public static void main(String[] args) throws InterruptedException {
    try (Playwright playwright = Playwright.create()) {
      Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
        .setHeadless(false));
      BrowserContext context = browser.newContext();
      Page page = context.newPage();
      page.navigate("https://letcode.in/");
      page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Log in")).click();
      Thread.sleep(1000);
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter registered email")).click();
      Thread.sleep(1000);
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter registered email")).fill("test001@test.com");
      Thread.sleep(1000);
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter registered email")).press("Tab");
      Thread.sleep(1000);
      page.getByPlaceholder("Enter password").fill("123456");
      Thread.sleep(1000);
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("LOGIN")).click();
      Thread.sleep(1000);
      page.getByLabel("Close", new Page.GetByLabelOptions().setExact(true)).click();
      Thread.sleep(1000);
      page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Work-Space")).click();
      Thread.sleep(1000);
      page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Drop-Down")).click();
      Thread.sleep(1000);
      page.locator("#fruits").selectOption("3");
      page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Sign out")).click();
      Thread.sleep(1000);
      playwright.close();
    }
  }
}
