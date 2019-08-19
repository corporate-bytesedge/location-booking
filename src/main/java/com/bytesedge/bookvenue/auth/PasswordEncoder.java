package com.bytesedge.bookvenue.auth;

import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder extends BCryptPasswordEncoder {
	public PasswordEncoder() {
		super();
	}

	public String encodePassword(String arg0) throws DataAccessException {
		return super.encode(arg0);
	}

	public static void main(String[] args) {
		try {
			System.out.println("encrypting...");
			PasswordEncoder encoder = new PasswordEncoder();
			System.out.println(encoder.encodePassword("1!Qdesigner"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
