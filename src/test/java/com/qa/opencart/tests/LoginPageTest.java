package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest {

	@Test(priority = 1)
	public void loginPageNavigatonTest() {
		/**
		 * Page Chaining Definition: Obtaining the object of page X (LoginPage => lp) via page Y (HomePage => hm), rather than directly 
		 * from page X (LoginPage => lp), is referred to as page chaining. In our case the method 
		 * `hm.navigateToLoginPage()` resides in the home page and it returns loginPage object. So this 
		 * process is called Page Chaining.
		 */
		lp = hm.navigateToLoginPage();
		String actualTitle = lp.getLoginPageTitle();
		System.out.println("Login page actual title is: " + actualTitle);
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(lp.forgotPwdLinkExist());
	}

	@Test(priority = 3)
	public void appLoginTest() {
		boolean flag = lp.doLogin(prop.getProperty("username").trim(), prop.getProperty("pwd").trim());
		Assert.assertTrue(flag);
	}
}
