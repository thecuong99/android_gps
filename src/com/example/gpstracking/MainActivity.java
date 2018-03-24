package com.example.gpstracking;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button btnShowLocation;
	
    // GPSTracker class
    GPSTracker gps;
    //final Button btnSendLocation = (Button) findViewById(R.id.sendLocation);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final Button btnSendLocation = (Button) findViewById(R.id.sendLocation);
		btnShowLocation = (Button) findViewById(R.id.btnShowLocation);
		final TextView textviewA = (TextView) findViewById(R.id.textView);
		// show location button click event
        btnShowLocation.setOnClickListener(new View.OnClickListener() {
             
            @Override
            public void onClick(View arg0) {
            	btnShowLocation.setText("Refesh GPS.");
                // create class object
                gps = new GPSTracker(MainActivity.this);
 
                // check if GPS enabled     
                if(gps.canGetLocation()){
                	
                	//layout.setVisibility(View.VISIBLE);
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    if (latitude > 0) 
                    	btnSendLocation.setVisibility(View.VISIBLE);
                    // \n is for new line
                    //Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                    textviewA.setText("My gps now (Latitude,Longtitude)___" + latitude + ", " + longitude);
                }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                	}
                 
            	}
        	}    )     ;
		}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onSend(View view) {
		TextView messageView = (TextView) findViewById(R.id.textView);
		String strTextView = messageView.getText().toString();
		strTextView = "Help me." + strTextView;
/**		Intent intentA = new Intent(Intent.ACTION_SEND);
		intentA.setType("text/plain");
		intentA.putExtra(Intent.EXTRA_TEXT, strTextView);
		//startActivity(intentA);
		String chooserTitle = getString(R.string.chooser);
		Intent chosenIntent = Intent.createChooser(intentA,chooserTitle);
		startActivity(chosenIntent);  **/
		String strTextView2;
		TextView messageView2 = (TextView) findViewById(R.id.textView2);
		strTextView2 = messageView2.getText().toString();
		
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(strTextView2, null, strTextView, null, null);
		//sms.sendTextMessage("+84966604999", null, strTextView, null, null);
		Toast.makeText(getApplicationContext(), "Your Location sent.", Toast.LENGTH_LONG).show();
		
		finish();
	}
	
	public void onExit(View v) {
		finish();
		//System.exit(0);
		
	}

}
