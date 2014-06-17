package com.ioroiko.gettingstarted;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.ioroiko.gettingstarted.GlobalVars.Day;
import com.ioroiko.gettingstarted.GlobalVars.Holiday;

import android.content.Context;
import android.nfc.cardemulation.OffHostApduService;

/**
 * @author roiko
 *
 */
public class Utils {

	/**
	 * @param month starts from 0. 0 = January
	 * @return Name of Month. 0 returns string "January"
	 */
	public static String MonthNameFromInt(Context context, int month)
	{
		switch (month) {
		case 0:
			return context.getString(R.string.January);
		case 1:
			return context.getString(R.string.February);				
		case 2:
			return context.getString(R.string.March);
		case 3:
			return context.getString(R.string.April);
		case 4:
			return context.getString(R.string.May);
		case 5:
			return context.getString(R.string.June);
		case 6:
			return context.getString(R.string.July);
		case 7:
			return context.getString(R.string.August);
		case 8:
			return context.getString(R.string.September);
		case 9:
			return context.getString(R.string.October);
		case 10:
			return context.getString(R.string.November);
		case 11:
			return context.getString(R.string.December);
		default:
			return "";
		}
	}
	
	
	/**
	 * @param context
	 * @param year
	 * @param month starts from 0 = January
	 * @return day starts from 0 = Sunday
	 */
	public static int FirstDayOfWeekInMonth(Context context, int year, int month)
	{
		int firstDay=-1;
		Calendar calendar = new GregorianCalendar(year,month,1);
		firstDay = calendar.get(Calendar.DAY_OF_WEEK)-1;
		return firstDay;
		
	}
	
	
	/**
	 * @param context
	 * @param year
	 * @param month starts from 0 = January
	 * @return
	 */
	public static String[] daysArrayNoHeader(Context context,int year, int month)
	{
		
		Calendar calendar = new GregorianCalendar(year, month, 1);
		int totDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		int offset = FirstDayOfWeekInMonth(context, year, month);
		String[] sDays = new String[totDays+offset];
		//Fill empty values
		for(int i = 0; i<offset; i++)
		{
			sDays[i]="";
		}
		
		for (int i = 0; i<totDays; i++)
		{
			sDays[i]=String.valueOf(i+1);
		}
		return sDays;
	}
	
	public static String[] daysArrayWithHeader(Context context,int year, int month)
	{
		
		Calendar calendar = new GregorianCalendar(year, month, 1);
		int totDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		int offset = FirstDayOfWeekInMonth(context, year, month);
		String[] sDays = new String[totDays+7+offset];
		sDays[0]=context.getString(R.string.Sun);
		sDays[1]=context.getString(R.string.Mon);
		sDays[2]=context.getString(R.string.Tue);
		sDays[3]=context.getString(R.string.Wed);
		sDays[4]=context.getString(R.string.Thu);
		sDays[5]=context.getString(R.string.Fri);
		sDays[6]=context.getString(R.string.Sat);
		//Fill empty values
		for(int i = 0; i<offset; i++)
		{
			sDays[i+7]="";
		}
		
		for (int i = 0; i<totDays; i++)
		{
			sDays[i+7+offset]=String.valueOf(i+1);
		}
		return sDays;
	}
	
	public static ArrayList<Day> dayArrayWithNoHeader(Context context, int year, int month, ArrayList<Holiday> festivities)
	{
		ArrayList<Day> days = new ArrayList<Day>();
		Calendar calendar = new GregorianCalendar(year, month,1);
		int totDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		int offset = FirstDayOfWeekInMonth(context, year, month);
		
		for (int i=0; i<offset; i++)
		{
			days.add(null);
		}
		
		for (int i = 1; i <= totDays; i++) 
		{
			boolean isFest = false;
		
			boolean checkSaturday = true;//Read from configuration
			//is saturday or sunday=
			calendar.set(Calendar.DAY_OF_MONTH, i);//i=1 è primo del mese
			int todayInWeek = calendar.get(Calendar.DAY_OF_WEEK);
			if ((todayInWeek==1)||((checkSaturday)&&(todayInWeek==7)))//Sunday(1) or saturday(7) (if check is needed)
				isFest=true;
			else
			{	//is festivity
				for (Holiday holiday : festivities) {
					if ((holiday.Month==month)&&(holiday.Day==i))
					{
					isFest=true;
					break;
					}
				}
			}

			Day myDay = new Day(year, month, i, isFest);
			days.add(myDay);
		}
		
		
		return days;
	}
	
	/**
	 * Returns the strings Su, Mo, Tu,...Sa in an arrayList
	 * @param context
	 * @param year
	 * @param month starts from 0 = January
	 * @return
	 */
	public static String[] daysArrayHeader(Context context)
	{
		String[] sDays = new String[7];
		sDays[0]=context.getString(R.string.Sun);
		sDays[1]=context.getString(R.string.Mon);
		sDays[2]=context.getString(R.string.Tue);
		sDays[3]=context.getString(R.string.Wed);
		sDays[4]=context.getString(R.string.Thu);
		sDays[5]=context.getString(R.string.Fri);
		sDays[6]=context.getString(R.string.Sat);
		
		return sDays;
		
	}
	
	
	/**
	 * @param year
	 * @return a list of Holiday with Month+Day.
	 * Month = 0 is January
	 */
	public static ArrayList<GlobalVars.Holiday> ItalianFestivities(int year)
	{
		ArrayList<Holiday> festivities = new ArrayList<GlobalVars.Holiday>();

		//January
		festivities.add(new Holiday(0, 1));
		festivities.add(new Holiday(0, 6));
		//April
		festivities.add(new Holiday(3,25));
		//May
		festivities.add(new Holiday(4, 1));
		//June
		festivities.add(new Holiday(5, 2));
		//August
		festivities.add(new Holiday(7, 15));
		//November
		festivities.add(new Holiday(10,1));
		//Dicember
		festivities.add(new Holiday(11, 25));
		festivities.add(new Holiday(11, 26));
			
		//Pasqua
		Holiday easter = Utils.CalculateEasterDay(year);
		festivities.add(easter);
		//pasquetta
		int angelDay,angelMonth=0;
		if (easter.Day==31)
		{// da 31 marzo ad 1 aprile
			angelMonth = easter.Month+1;
			angelDay = 1;
		}
		else
		{//stesso mese, giorno++
			angelMonth=easter.Month;
			angelDay = easter.Day+1;
		}

		Holiday angel = new Holiday(angelMonth,angelDay);
		festivities.add(angel);
		return festivities;
	}
	
	
	public static Holiday CalculateEasterDay(int year)
	{
		int easterMonth=-1;
		int easterDay = -1;
		
		int Y = year;
		int a = Y % 19;//resto
		int b = (int)(Y/100);
		int c = Y % 100;
		int d = (int) (b / 4);
		int e = b % 4;
		int f = (int)((b+8)/25);
		int g = (int)((b-f+1)/3);
		int h = (19*a + b - d - g + 15)%30;
		int i = (int)(c/4);
		int k = c % 4;
		int L = (32 + 2*e + 2*i - h - k) % 7;
		int m = (int)((a + 11*h + 22L) / 451);
		int month = (int)((h + L - 7*m + 114) / 31);
		int day = ((h + L - 7*m + 114) % 31) + 1;
		
		Holiday easter = new Holiday(month-1, day);
		return easter;
	}
	
}
