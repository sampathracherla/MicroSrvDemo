package com.msdemo.utils;

import java.security.SecureRandom;
import java.util.Random;

public class MSDemoUtils {

	private static final Random random = new SecureRandom();
	private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%^&";
	
	public static String generatePassword(int length) {
		StringBuilder password = new StringBuilder(length);
		for(int i = 0; i < length; i++) {
			password.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
		}
		return new String(password);
	}
	
}
