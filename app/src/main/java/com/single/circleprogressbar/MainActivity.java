package com.single.circleprogressbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.library.circleprogressbar.CircleProgressBarView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    int progress1;
    int progress2;
    private Timer timer1;
    private Timer timer2;
    private CircleProgressBarView progressBarView;
    private CircleProgressBarView colorFulProgressBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBarView = (CircleProgressBarView) findViewById(R.id.progress_view);
        timer1 = new Timer();
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progress1++;
                        if (progress1 > 100) {
                            progress1 = 0;
                        }
                        progressBarView.setProgress(progress1);
                    }
                });
            }
        }, 1000, 150);

        progressBarView.setListener(new CircleProgressBarView.OnProgressChangeListener() {
            @Override
            public void onChange(int progress) {
                if (progress == 100) {
                    Toast.makeText(MainActivity.this, "普通progressBar达到100%进度了", Toast.LENGTH_SHORT).show();
                }
            }
        });
        colorFulProgressBarView = (CircleProgressBarView) findViewById(R.id.progress_view_colorful);
        timer2 = new Timer();
        timer2.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progress2++;
                        if (progress2 > 100) {
                            progress2 = 0;
                        }
                        colorFulProgressBarView.setProgress(progress2);
                    }
                });
            }
        }, 1000, 200);
        colorFulProgressBarView.setListener(new CircleProgressBarView.OnProgressChangeListener() {
            @Override
            public void onChange(int progress) {
                if (progress == 100) {
                    Toast.makeText(MainActivity.this, "colorFulProgressBar达到100%进度了", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer2.cancel();
        timer1.cancel();
        if (progressBarView != null) {
            progressBarView.cancel();
        }
        if (colorFulProgressBarView != null) {
            colorFulProgressBarView.cancel();
        }
    }
}
