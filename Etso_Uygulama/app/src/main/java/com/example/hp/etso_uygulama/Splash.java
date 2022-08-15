
package com.example.hp.etso_uygulama;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Splash extends AppCompatActivity {

    int sayici = 0;
    private Handler handler = new Handler();
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_ekran);

        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.circular);
        final ProgressBar mProgress = (ProgressBar) findViewById(R.id.circularProgressbar);
        mProgress.setProgress(0);   // Ana Progress
        mProgress.setSecondaryProgress(100); // İkinci Progress
        mProgress.setMax(100); // Maksimum Progress
        mProgress.setProgressDrawable(drawable);

        tv = (TextView) findViewById(R.id.tv);

        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (sayici < 100) {
                    sayici += 1;

                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub


                            mProgress.setProgress(sayici);
                            tv.setText(sayici + "%");

                        }
                    });
                    try {

                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

                Intent intent = new Intent(Splash.this, Main3Activity.class);
                startActivity(intent);

            }


        }).start();
    }


    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }

}




