package in.omerjerk.screenshotter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import in.omerjerk.libscreenshotter.ScreenshotCallback;
import in.omerjerk.libscreenshotter.Screenshotter;

public class Main2Activity extends AppCompatActivity {
    MediaProjectionManager mediaProjectionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mediaProjectionManager = (MediaProjectionManager)getSystemService(MEDIA_PROJECTION_SERVICE);
        startActivityForResult(mediaProjectionManager.createScreenCaptureIntent(), 1);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Screenshotter.getInstance()
                        .setSize(720, 1280)
                        .takeScreenshot(this, resultCode, data, new ScreenshotCallback() {
                            @Override
                            public void onScreenshot(Bitmap bitmap) {
                                Log.d("@@####", "onScreenshot called");

                                Toast.makeText(Main2Activity.this, "Screenshot Captured!", Toast.LENGTH_SHORT).show();
                            }
                        });
                this.finish();
            }
        }
    }
}
