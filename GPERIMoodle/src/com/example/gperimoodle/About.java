package com.example.gperimoodle;

import com.example.gperimoodle.R.id;

import android.os.Bundle;
import android.app.Activity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.widget.TextView;

public class About extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		setTitle("About");
		
		TextView abt=(TextView) findViewById(id.lblAbt);
		abt.setMovementMethod(new ScrollingMovementMethod());
		abt.setMovementMethod(LinkMovementMethod.getInstance());
		
		
		
		
	}

}
