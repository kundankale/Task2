package in.omerjerk.screenshotter;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import in.omerjerk.libscreenshotter.ScreenshotCallback;
import in.omerjerk.libscreenshotter.Screenshotter;

/**
 * Created by kundankale on 12/4/18.
 */

public class ApplicationScreenClass extends Application {

    private static Intent screenshotPermission = null;


    static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

    }

    protected static void getScreenShotPermission() {
        try {

                /*if(null != mediaProjection) {
                    mediaProjection.stop();
                    mediaProjection = null;
                }
                mediaProjection = mediaProjectionManager.getMediaProjection(Activity.RESULT_OK, (Intent) screenshotPermission.clone());
*/

            Log.d("@@####", "getScreenShotPermission: ");
            Screenshotter.getInstance()
                    .setSize(720, 1280)
                    .takeScreenshot(mContext, Activity.RESULT_OK, (Intent) screenshotPermission.clone(), new ScreenshotCallback() {
                        @Override
                        public void onScreenshot(Bitmap bitmap) {

                            saveImage(bitmap);
                            Toast.makeText(mContext, "Screenshot Captured!", Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (final RuntimeException ignored) {
            openScreenshotPermissionRequester();
        }
    }

    protected static void openScreenshotPermissionRequester(){
        final Intent intent = new Intent(mContext, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }



    protected static void setScreenshotPermission(final Intent permissionIntent) {
        screenshotPermission = permissionIntent;
    }

    private static void saveImage(Bitmap data) {
        File createFolder = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/ScreenShotTask");
        if(!createFolder.exists())
            createFolder.mkdir();
        File saveImage = new File(createFolder,"screenshot"+"_"+System.currentTimeMillis()+".jpg");

        try {
            OutputStream outputStream = new FileOutputStream(saveImage);
            data.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
            outputStream.flush();
            outputStream.close();

            Log.d("@@####", "saveImage: ");

            Intent intent = new Intent(mContext,MyService.class);
            mContext.stopService(intent);

            int timeInSec = 5000;

            Intent intent1 = new Intent(mContext, MyBroadcastReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    mContext, 234, intent1, 0);
            AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(mContext.ALARM_SERVICE);
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+timeInSec, pendingIntent);



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
