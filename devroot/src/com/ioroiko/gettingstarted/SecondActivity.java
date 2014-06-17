package com.ioroiko.gettingstarted;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.ioroiko.gettingstarted.GlobalVars.Day;
import com.ioroiko.gettingstarted.GlobalVars.Holiday;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.animation.GridLayoutAnimationController;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends Activity {
	
	int curMonth=0;
	int curYear=0;
	public ArrayList<GlobalVars.Holiday> festivities = null;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
				
		curYear = GregorianCalendar.getInstance().get(GregorianCalendar.YEAR);
		curMonth = 0;
		festivities = Utils.ItalianFestivities(curYear);
		NumberPicker np = (NumberPicker) findViewById(R.id.npSelectHours);
		np.setMaxValue(8);
		np.setMinValue(1);
		np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
			
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				// TODO Auto-generated method stub
				GlobalVars.selectedHours = newVal;
			}
		});

		drawCalendar(curYear, curMonth);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);

	}
	
	public void drawCalendar(int year, int month)
	{
		if (festivities == null)
			festivities = Utils.ItalianFestivities(curYear);
		TextView tvYear = (TextView) findViewById(R.id.tvYear);
		tvYear.setText(String.valueOf(year));
		TextView tvMonth = (TextView) findViewById(R.id.tvMonth);
		tvMonth.setText(Utils.MonthNameFromInt(this, month));
		
		GridView gvHeader = (GridView) findViewById(R.id.gvDaysNames);
		String[] daysH = Utils.daysArrayHeader(this);
		ArrayAdapter<String> aaHeader = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,daysH);
		gvHeader.setAdapter(aaHeader);
		
		GridView gv = (GridView) findViewById(R.id.gvDaysofMonth);
		//String[] sDays=Utils.daysArrayWithHeader(this, curYear, curMonth);
		
		Calendar cal = new GregorianCalendar();
		int day1 = cal.get(Calendar.DAY_OF_MONTH);
		int month1 = cal.get(Calendar.MONTH);
		int year1 = cal.get(Calendar.YEAR);
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
		int firstDayOfWeek = cal.getFirstDayOfWeek();
		ArrayList<Day> daysOfMonth = Utils.dayArrayWithNoHeader(this, curYear, curMonth, festivities);
		ArrayButtonAdapter abaTest = new ArrayButtonAdapter(this, android.R.layout.simple_list_item_1, daysOfMonth);
		gv.setAdapter(abaTest);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);//Rocco

	}

	public void ivNavClick(View view)
	{
		ImageView iv = (ImageView) view;
		switch (iv.getId()) {
		case R.id.ivLeft://Back
			if (curMonth>0)
			{
				curMonth--;
				
			}
			else
			{
				if (curYear>0)
				{
					curYear--;
					curMonth = 11;
					festivities = Utils.ItalianFestivities(curYear);//re-calculate festivities for new year
				}
			}
			break;
		case R.id.ivRight://Back
			if (curMonth==11)
			{
				curMonth=0;
				curYear++;
				festivities = Utils.ItalianFestivities(curYear);//re-calculate festivities for new year
			}
			else
			{
				curMonth++;
			}
			break;
		}
		drawCalendar(curYear, curMonth);
	
	}
	
	

}
