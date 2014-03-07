package com.example.gperimoodle;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;

public class MyInfo extends Activity {
	
	
	TextView name,institute,enrollNo,department,city,eMail,contact,skypeId;
	ImageView profilePic;

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
