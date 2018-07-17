package me.akshanshjain.garnish;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Animatable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    private TextView appName;
    private Typeface greatVibes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Setting up the activity for full-screen mode.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        //Setting the typeface on the app name text.
        greatVibes = Typeface.createFromAsset(getAssets(), "fonts/GreatVibes-Regular.ttf");
        appName = findViewById(R.id.app_name_splash);
        appName.setTypeface(greatVibes);

        //Adding fade in animation to the app name text from the XML.
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        appName.clearAnimation();
        appName.startAnimation(fadeIn);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), LandingActivity.class));
                finish();
            }
        }, 3000);
    }
}
