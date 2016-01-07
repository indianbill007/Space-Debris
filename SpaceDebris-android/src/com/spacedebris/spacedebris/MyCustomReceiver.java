package com.spacedebris.spacedebris;

import org.json.JSONException;
import org.json.JSONObject;

import com.parse.ParseAnalytics;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

public class MyCustomReceiver extends BroadcastReceiver 
{
	public static boolean beatrequest=false;
	@Override
	public void onReceive(Context context, Intent intent) 
	{
		
	 	ParseAnalytics.trackAppOpenedInBackground(intent);
		  String action = intent.getAction();
			try {
				JSONObject json = new JSONObject(intent.getExtras().getString("com.parse.Data"));
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@ "+json.toString());
				if(!json.isNull("Message"))
				{
					beatrequest=true;
					json.getString("Message");
					Constant.push_msg=json.getString("Message");
					if(json.has("Type")){
					Constant.push_type=json.getString("Type");
					
					if (Constant.push_type.equals("2") && json.has("Url")) {
						Constant.URL=json.getString("Url");
					}
					else if (Constant.push_type.equals("3") && json.has("Life")) {
						Constant.extralife=json.getInt("Life");
						
					}
					}
					Intent intent1 = new Intent(context, MainActivity.class);
				    PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent1, 0);
					NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
					mBuilder.setSmallIcon(R.drawable.icon);
					mBuilder.setContentIntent(pIntent);
					mBuilder.setContentTitle("Space Debris Phantom");
					System.out.println("message"+json.getString("Message"));
					mBuilder.setContentText(json.getString("Message"));
					Uri alarmSound = RingtoneManager
							.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
					mBuilder.setSound(alarmSound);
					NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

					// notificationID allows you to update the
					// notification later on.
					mNotificationManager.notify(1, mBuilder.build());
					
				}
				
				System.out.println("");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}

}
