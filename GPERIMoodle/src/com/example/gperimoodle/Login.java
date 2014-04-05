package com.example.gperimoodle;

import com.example.gperimoodle.Moodle;
import com.example.gperimoodle.User;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class Login extends Activity
{
	
	public static final String prefName = "PrefFile";
	
	
	Button btnLogin;
	TextView uid,pass;
	CheckBox remMe;
	
	SharedPreferences sp;
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
	
		switch(item.getItemId())
		{
			case R.id.about:
				startActivity(new Intent(this, About.class));
				break;
				
			case R.id.sendFeedback:
				
				Intent emailIntent = new Intent(Intent.ACTION_SEND);
				emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"gpericollege@gmail.com"});
				emailIntent.putExtra(Intent.EXTRA_SUBJECT, "GPERI Moodle App Feedback");
				emailIntent.putExtra(Intent.EXTRA_TEXT, "Write your Likes and Dislikes about the App");				
				emailIntent.setType("message/rfc822");				
				startActivity(Intent.createChooser(emailIntent, "Choose E-mail Clent"));
				
				break;
		}
		
		
		return true;
	}
	
	

	@Override
	public void onBackPressed()
	{
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
	    
		alertDialogBuilder.setTitle("Alert");
 
		alertDialogBuilder
				.setMessage("Exit GPERI Moodle?")
				.setCancelable(false)
				.setPositiveButton("Exit",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						
					Login.this.finish();
						
					}
					
				
				  })
				  .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							
							dialog.cancel();
								
							}
							
						
						  });   
		
		AlertDialog alertDialog = alertDialogBuilder.create();
		 
		alertDialog.show();  
		
		
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		btnLogin=(Button) findViewById(R.id.btnLogin);
		uid=(TextView) findViewById(R.id.editUsername);
		pass=(TextView) findViewById(R.id.editPass);
		remMe=(CheckBox) findViewById(R.id.chkRemMe);
		remMe.setChecked(false);
		
		sp=getSharedPreferences(prefName, MODE_PRIVATE);
		String suid=sp.getString("prefUid", null);
		String spass=sp.getString("prefPass", null);
		
		if(suid!=null && spass!=null)
		{
			uid.setText(suid);
			pass.setText(spass);
			remMe.setChecked(true);
		}
		
		
	}
	
	public void tryLogin(View v)
    {
		SharedPreferences.Editor edt=sp.edit();
		if(remMe.isChecked())
		{
			edt.putString("prefPass", pass.getText().toString());
			edt.putString("prefUid", uid.getText().toString());
			edt.commit();
		}
		else
		{
			edt.putString("prefPass", null);
			edt.putString("prefUid", null);
			edt.commit();
		}
		
    	if(TextUtils.isEmpty(uid.getText()) || TextUtils.isEmpty(pass.getText()))
		{
    		
    		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
    
    		alertDialogBuilder.setTitle("Login Error");
     
    		alertDialogBuilder
    				.setMessage("Enter Username and Password!")
    				.setCancelable(false)
    				.setPositiveButton("OK",new DialogInterface.OnClickListener() {
    					public void onClick(DialogInterface dialog,int id) {
    						
    						dialog.cancel();
    						
    					}
    				  });
     
    				AlertDialog alertDialog = alertDialogBuilder.create();
     
    				alertDialog.show();    		
    		
		}
		else
		{
			
			try
			{
				
				
				
				User.setResponseXML(new Moodle(Login.this).execute("0",uid.getText().toString(),pass.getText().toString()).get());
				
				if(User.getResponseXML().contains("token"))
				{					
						User.setToken(User.getResponseXML().substring(10, 42));
						
						Intent i=new Intent(this,UserFrame.class);
						
						
					
						
						startActivity(i);
						
						
					
				}
				else
				{
					if(User.getResponseXML()=="error")
					{
						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
					    
			    		alertDialogBuilder.setTitle("Connection Error");
			     
			    		alertDialogBuilder
			    				.setMessage("Error Connecting Server!")
			    				.setCancelable(false)
			    				.setPositiveButton("OK",new DialogInterface.OnClickListener() {
			    					public void onClick(DialogInterface dialog,int id) {
			    						
			    						dialog.cancel();
			    						
			    					}
			    				  });
			     
			    				AlertDialog alertDialog = alertDialogBuilder.create();
			     
			    				alertDialog.show();
					}
					else
					{
						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
					    
			    		alertDialogBuilder.setTitle("Login Error");
			     
			    		alertDialogBuilder
			    				.setMessage("Invalid Username or Password!")
			    				.setCancelable(false)
			    				.setPositiveButton("OK",new DialogInterface.OnClickListener() {
			    					public void onClick(DialogInterface dialog,int id) {
			    						
			    						dialog.cancel();
			    						
			    					}
			    				  });
			     
			    				AlertDialog alertDialog = alertDialogBuilder.create();
			     
			    				alertDialog.show();
		    				
					}
				}				
			} 
			catch (Exception e)
			{
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
			    
	    		alertDialogBuilder.setTitle("Error");
	     
	    		alertDialogBuilder
	    				.setMessage("Error calling AsyncTask!!!")
	    				.setCancelable(false)
	    				.setPositiveButton("OK",new DialogInterface.OnClickListener() {
	    					public void onClick(DialogInterface dialog,int id) {
	    						
	    						dialog.cancel();
	    						
	    					}
	    				  });
	     
	    				AlertDialog alertDialog = alertDialogBuilder.create();
	     
	    				alertDialog.show();
				e.printStackTrace();
			}				
		}		
    }  
}
