package in.omerjerk.screenshotter;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.projection.MediaProjectionManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import in.omerjerk.libscreenshotter.ScreenshotCallback;
import in.omerjerk.libscreenshotter.Screenshotter;

import static in.omerjerk.screenshotter.ApplicationScreenClass.getScreenShotPermission;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d("@@####", "onStartCommand: ");
        captureImage();
        return START_STICKY;
    }

    private void captureImage() {
        Log.d("@@####", "captureImage");

        getScreenShotPermission();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("@@####", "onDestroy:of service ");
    }
}
