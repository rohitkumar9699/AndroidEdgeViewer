
package com.example.edgeviewer;

import android.graphics.SurfaceTexture;
import android.hardware.camera2.*;
import android.os.Bundle;
import android.view.TextureView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    static {
        System.loadLibrary("native-lib");
    }

    private TextureView textureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textureView = new TextureView(this);
        setContentView(textureView);

        // Camera setup code goes here
    }

    public native void processFrame(byte[] frameData, int width, int height);
}
