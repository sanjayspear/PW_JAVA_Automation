package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class LoginPage {

	private Page page;

	private String emailId = "#input-email";
	private String password = "//input[@id='input-password']";
	private String forgotPassword = "(//a[normalize-space()='Forgotten Password'])[1]";
	private String loginBtn = "//input[@value='Login']";
	private String logoutLink = "aside[id='column-right'] a:nth-child(1)";

	public LoginPage(Page page) {
		this.page = page;
	}

	public String getLoginPageTitle() {
		return page.title();
	}

	public boolean forgotPwdLinkExist() {
		return page.isVisible(forgotPassword);
	}

	public boolean doLogin(String userName, String userPwd) {
		System.out.println("App Creds " + userName + ":" + userPwd);
		page.fill(emailId, userName);
		page.fill(password, userPwd);
		page.click(loginBtn);

		if (page.isVisible(logoutLink)) {
			System.out.println("User logged in successfully.....");
			return true;
		} else
			return false;
	}
}
