package com.ioroiko.gettingstarted;

import java.util.ArrayList;
import java.util.List;

import com.ioroiko.gettingstarted.GlobalVars.Day;
import com.ioroiko.gettingstarted.GlobalVars.Holiday;

import android.R.integer;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class ArrayButtonAdapter extends ArrayAdapter {
	
	private Context _myContext;
	private Object[] _objects;
	private ArrayList<Holiday> festivities = null;
	
	View.OnClickListener dbtnClickH = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			DayButton me = (DayButton)v;
			if (me.isFestivity)
				return;
			
			boolean isValid = me.IsValid;
			if (isValid)
			{
				int day = me.day;
				int month = me.month;
				int year = me.year;
				boolean isFest = me.isFestivity;
				boolean isSelected = me.isSelected;
							
				if (isSelected)
				{
					if (me.hours!=GlobalVars.selectedHours)//Update hours if selected is different!
						selectDayButton(me);
					else //deselect here!
						deselectDayButton(me);
				}
				else
				{	//select here!
					selectDayButton(me);
				}
				
				float hours = me.hours;
				
				
				String message = String.valueOf(day)+"/"+String.valueOf(month+1)+"/"+String.valueOf(year)+
						", hours = " + String.valueOf(hours) + "\n" +
						" Festivity = " + String.valueOf(isFest) + ", Valid = " + String.valueOf(isValid);
				Toast.makeText(v.getContext(), message, Toast.LENGTH_SHORT).show();
			}
		}
	}; 
	
	private void selectDayButton(DayButton dbtn)
	{
		dbtn.hours = GlobalVars.selectedHours;
		dbtn.setTextColor(Color.GREEN);
		dbtn.isSelected=true;
	}
	
	private void deselectDayButton(DayButton dbtn)
	{
		dbtn.hours = 0;
		dbtn.setTextColor(Color.BLACK);
		dbtn.isSelected=false;
	}
	
	@Override
	public View getView (int position, View convertView, ViewGroup parent)
	{
		
		DayButton btn;
		DayButton dbtn=new DayButton(_myContext);
		Day day = (Day)_objects[position];
		//if (convertView==null)
		//{  // if it's not recycled, initialize some attributes
			if (day==null)//it's offset element: blank btn
			{
				dbtn.IsValid=false;
				return dbtn;
			}
			
			
			dbtn.day= day.Day;
			dbtn.setText(String.valueOf(day.Day));
			dbtn.month = day.Month;
			dbtn.year = day.Year;
			dbtn.isFestivity = day.IsFestivity;
			dbtn.IsValid = true;
			
			if (dbtn.isFestivity==true)
				dbtn.setTextColor(Color.RED);
			dbtn.setOnClickListener(dbtnClickH);
			
	
		//}
		return dbtn;
	}
	

	public ArrayButtonAdapter(Context context, int resource) {
		super(context, resource);
		_myContext = context;
		
		// TODO Auto-generated constructor stub
	}

	public ArrayButtonAdapter(Context context, int resource,
			int textViewResourceId) {
		super(context, resource, textViewResourceId);
		_myContext = context;
		// TODO Auto-generated constructor stub
	}

	public ArrayButtonAdapter(Context context, int resource, Object[] objects) {
		super(context, resource, objects);
		_myContext = context;
		_objects = objects;
		// TODO Auto-generated constructor stub
	}

	public ArrayButtonAdapter(Context context, int resource, List objects) {
		super(context, resource, objects);
		_myContext=context;
		_objects = objects.toArray();
		// TODO Auto-generated constructor stub
	}

	public ArrayButtonAdapter(Context context, int resource,
			int textViewResourceId, Object[] objects) {
		super(context, resource, textViewResourceId, objects);
		_myContext = context;
		// TODO Auto-generated constructor stub
	}

	public ArrayButtonAdapter(Context context, int resource,
			int textViewResourceId, List objects) {
		super(context, resource, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
	}
	
	
}
