package com.qa.opencart.utils;

import java.util.Arrays;
import java.util.List;

public class Constants {

	public static final String LOGIN_PAGE_TILTE = "Account Login";
	public static final String LOGIN_PAGE_URL_FRACTION = "route=account/login";
	public static final String ACCOUNT_PAGE_TILTE = "My Account";
	public static final String ACCOUNT_PAGE_URL_FRACTION = "route=account/account";
	public static final String ACCOUNTS_PAGE_HEADER = "Your Store";
	public static final String LOGOUT_MESSAGE = "You have been logged off your account. It is now safe to leave the computer.";
	public static final String FORGOT_PASSWORD_PAGE_TILTE = "Forgot Your Password?";
	public static final String FORGOT_PASSWORD_PAGE_URL_FRACTION = "route=account/forgotten";
	public static final String FORGOT_PASSWORD_MESSAGE = "Enter the e-mail address associated with your account. Click submit to have a password reset link e-mailed to you.";
	public static final String PASSWORD_RESET_MESSAGE = "An email with a confirmation link has been sent your email address.";
	public static final String REGISTRATION_SUCCESS_MESSAGE = "Your Account Has Been Created!";

	
	public static final List<String> ACCOUNTS_PAGE_SECTION_HEADER_LIST = Arrays.asList("My Account",
																					"My Orders",
																					"My Affiliate Account",
																					"Newsletter");

	public static final int DEFAULT_TIMEOUT = 5;
	public static final int DEFAULT_ELEMENT_WAIT_TIMEOUT = 10;
	
	// Test data excel sheet name
	public static final String REGISTER_SHEET_NAME = "RegsSheet";
}
