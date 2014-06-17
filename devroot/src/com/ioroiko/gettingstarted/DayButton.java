package com.ioroiko.gettingstarted;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

public class DayButton extends Button {
	
	
	public boolean isSelected;
	public float hours;
	public boolean IsValid = false;//Not used
	public int month=-1;
	public int day=-1;
	public int year = -1;
	public boolean isFestivity=false;//Sunday and festivities
	
	public DayButton(Context context) {
		super(context);
		this.setBackgroundDrawable(null);
		// TODO Auto-generated constructor stub
	}

	public DayButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setBackgroundDrawable(null);
		// TODO Auto-generated constructor stub
	}

	public DayButton(Context context, AttributeSet attrs, int defStyle) {
		//super(context, attrs, defStyle);
		super(context, attrs, R.style.DayButton);
		this.setBackgroundDrawable(null);
		// TODO Auto-generated constructor stub
	}

}
