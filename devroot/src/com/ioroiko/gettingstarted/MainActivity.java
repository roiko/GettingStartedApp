package com.ioroiko.gettingstarted;

import android.os.Bundle;
import android.app.Activity;
import android.content.ClipData.Item;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);//Rocco

	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId()) {
		case R.id.action_myItem:
			Toast.makeText(this, "Pressed \"myItem\" in action bar!", Toast.LENGTH_SHORT).show();
			break;
		case R.id.action_settings:
			Toast.makeText(this, "Pressed \"settings\" in action bar!", Toast.LENGTH_SHORT).show();
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void btn1Pressed(View view)
	{
		Intent intent = new Intent(this, SecondActivity.class);
		EditText et = (EditText) findViewById(R.id.etName);
		String name = et.getText().toString();
		intent.putExtra(GlobalVars.EXTRA_NAME, name);
		startActivity(intent);
	}

}
