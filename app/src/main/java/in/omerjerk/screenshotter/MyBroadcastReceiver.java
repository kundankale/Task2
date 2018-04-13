package in.omerjerk.screenshotter;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by kundankale on 13/4/18.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("@@####", "onReceive: ");

        Intent intentservice = new Intent(context,MyService.class);
        context.startService(intentservice);

    }
}