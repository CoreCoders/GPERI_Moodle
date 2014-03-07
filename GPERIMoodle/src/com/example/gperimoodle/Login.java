package com.example.gperimoodle;

import com.example.gperimoodle.Moodle;
import com.example.gperimoodle.User;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login extends Activity
{
	
	
	Button btnLogin;
	TextView uid,pass;
	

	@Override
	public void onBackPressed()
	{
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
	    
		alertDialogBuilder.setTitle("Alert");
 
		alertDialogBuilder
				.setMessage("Exit Exit GPERI Moodle?")
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
		
	}
	
	public void tryLogin(View v)
    {
		
		
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
