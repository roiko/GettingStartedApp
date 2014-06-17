package com.ioroiko.gettingstarted;

import java.util.List;

public class GlobalVars {

	public static class Holiday
	{
		public int Month;
		public int Day;
		public Holiday(int month, int day)
		{
			Month = month;
			Day = day;
		}
	}
	
	public static class Day
	{
		int Year,Month,Day=-1;
		boolean IsFestivity=false;
		public Day(int year,int month, int day, boolean isFestivity)
		{
			Year=year;
			Month=month;
			Day=day;
			IsFestivity=isFestivity;
		}
	}
	
	public static int selectedHours=1;

	public final static String EXTRA_NAME = "com.ioroiko.EXTRA_NAME";	
	
}
