package com.spacedebris.spacedebris;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

public class AddedLifeCustomReceiver extends BroadcastReceiver 
{
	
	@Override
	public void onReceive(Context context, Intent intent) 
	{
		 Intent intent1 = new Intent(context, MainActivity.class);
	     PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent1, 0);
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
		mBuilder.setSmallIcon(R.drawable.icon);
		mBuilder.setContentIntent(pIntent);
		mBuilder.setContentTitle("Space Debris Phantom");
		
		mBuilder.setContentText("Phantom is now refilled with lives!");
		Uri alarmSound = RingtoneManager
				.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		mBuilder.setSound(alarmSound);
		NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

		// notificationID allows you to update the
		// notification later on.
		mNotificationManager.notify(0, mBuilder.build());	
		
}

}
