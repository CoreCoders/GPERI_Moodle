package com.example.gperimoodle;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class MyInfo extends Activity {
	
	
	TextView name,institute,enrollNo,department,city,eMail,contact,skypeId;
	ImageView profilePic;
	
	
	
	
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
	protected void onCreate(Bundle savedInstanceState)
	{
		
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_info);
		
		setTitle("My Info");
		
		profilePic=(ImageView) findViewById(R.id.imgPicture);
		
		name=(TextView) findViewById(R.id.lblNm);
		institute=(TextView) findViewById(R.id.lblInst);
		enrollNo=(TextView) findViewById(R.id.lblEnrollNo);
		department=(TextView) findViewById(R.id.lblDep);
		city=(TextView) findViewById(R.id.lblCty);
		eMail=(TextView) findViewById(R.id.lbleMail);
		contact=(TextView) findViewById(R.id.lblContact);
		skypeId=(TextView) findViewById(R.id.lblSkypeId);
		
		loadUserDetails();
		
	}
	
	public void loadUserDetails()
	{
		
		name.append(" "+User.getFullName());
		institute.append(" "+User.getInstitute());
		enrollNo.append(" "+User.getEnrollNo());
		department.append(" "+User.getDepartment());
		city.append(" "+User.getCity());
		eMail.append(" "+User.geteMail());
		contact.append(" "+User.getContact());
		skypeId.append(" "+User.getSkypeId());
		
		
		
		try
		{
			profilePic.setImageBitmap(new GetImage().execute(User.getProfilePic()).get());
		}
		catch(Exception e)
		{
			
		}
		
		
	}

}
