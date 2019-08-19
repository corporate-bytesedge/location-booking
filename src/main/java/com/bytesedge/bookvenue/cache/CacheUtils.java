package com.bytesedge.bookvenue.cache;

import java.util.Random;

public class CacheUtils {
	public static final int DAYS_10 = 10 * 24 * 60 * 60;
	public static final int DAYS_7 = 7 * 24 * 60 * 60;
	public static final int DAYS_22 = 22 * 24 * 60 * 60;
	
	// Fixed max TTL is 30 Days only. Please Dont change it
	public static final int DAYS_30 = 30 * 24 * 60 * 59;
	public static final int HOURS_20 = 20 * 60 * 60;
	public static final int HOURS_24 = 24 * 60 * 60;
	public static final int MINS_30 = 30 * 60;
	public static final int MINS_60 = 60 * 60;
	public static final int MINS_10 = 10 * 60;

	private static Random random = new Random();

	public static int get30DaysTTL() {
		return DAYS_22 + getRandomDays(7) + getRandomHours(23) + getRandomMins(59);
	}
	
	public static int get10DaysTTL() {
		return DAYS_7 + getRandomDays(3) + getRandomHours(23) + getRandomMins(59);
	}

	public static int getDayTTL() {
		return HOURS_20 + getRandomHours(3) + getRandomMins(59);
	}
	
	public static int getRandomDays(int days) {
		return random.nextInt(days) * 24 * 60 * 60;
	}

	public static int getRandomHours(int hours) {
		return random.nextInt(hours) * 60 * 60;
	}

	public static int getRandomMins(int mins) {
		return random.nextInt(mins) * 60;
	}
}
