package com.example.gperimoodle;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class InstantMessaging extends Activity implements AdapterView.OnItemSelectedListener
{
	
	Spinner lstFriends;
	
	String[] id;
	
	EditText msg;
	
	String toUserId;
	
	
	
	@Override
	public void onBackPressed()
	{
		if(!TextUtils.isEmpty(msg.getText()))
		{
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
	    
		alertDialogBuilder.setTitle("Alert!");
 
		alertDialogBuilder
				.setMessage("Are you sure you want to go Back?")
				.setCancelable(false)
				.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {						
					
						
					InstantMessaging.this.finish();
						
					}
					
				
				  })
				  .setNegativeButton("No", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							
							dialog.cancel();
								
							}
							
						
						  });
 
				AlertDialog alertDialog = alertDialogBuilder.create();
 
				alertDialog.show();   
	}
		else
		{
			InstantMessaging.this.finish();
		}
		
		
	}
	
	
	
	
	public void trySendMsg(View v)
	{
		
		
		if(!TextUtils.isEmpty(msg.getText()))
		{
			
			try
			{
				User.setResponseXML(new Moodle(InstantMessaging.this).execute("5",User.getToken(),toUserId,msg.getText().toString()).get());
				
				if(ParseXML.getValue("errormessage", User.getResponseXML())=="N/A")
				{
					Toast.makeText(this, "Message Sent!!!", Toast.LENGTH_SHORT).show();
				}
				else
				{
					Toast.makeText(this, "Error!!!", Toast.LENGTH_SHORT).show();
				}
				
			}
			catch(Exception e)
			{
				msg.setText(User.getResponseXML());
			}
		}
		else
		{
			Toast.makeText(this, "Please Enter Message", Toast.LENGTH_SHORT).show();
		}
		
	}
	
	
	
	public void setFriends()
	{
		
		id=Friend.getId();
		
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,Friend.getName());
		lstFriends.setAdapter(adapter);
		
	}
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_instant_messaging);
		
		lstFriends=(Spinner) findViewById(R.id.spnFriends);
		lstFriends.setOnItemSelectedListener(this);
		msg=(EditText) findViewById(R.id.txtMsg);
		
		try
		{
			
			setFriends();
			
		}
		catch(Exception e)
		{
			
		}		
		
	}
	
	

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int index, long arg3)
	{
		
		toUserId=id[index];
		Toast.makeText(this, toUserId, Toast.LENGTH_SHORT).show();
		
	}



	@Override
	public void onNothingSelected(AdapterView<?> arg0)
	{
		
	}

}
