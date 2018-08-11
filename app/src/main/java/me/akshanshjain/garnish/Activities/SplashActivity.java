package me.akshanshjain.garnish.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import me.akshanshjain.garnish.R;

public class SplashActivity extends AppCompatActivity {

    private TextView appName;
    private Typeface QLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Setting up the activity for full-screen mode.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        //Setting the typeface on the app name text.
        QLight = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Light.ttf");
        appName = findViewById(R.id.app_name_splash);
        appName.setTypeface(QLight);

        //Adding fade in animation to the app name text from the XML.
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        appName.clearAnimation();
        appName.startAnimation(fadeIn);

        //Ending the splash screen after a delay of 3 seconds. Onto Landing page.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), LandingActivity.class));
                finish();
            }
        }, 2000);
    }
}
