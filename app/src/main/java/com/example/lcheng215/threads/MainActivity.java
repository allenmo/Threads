package com.example.lcheng215.threads;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import java.util.logging.ConsoleHandler;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    ProgressBar bar;
    Handler handler = new Handler(){
        @Override
        public  void  handleMessage(Message msg){
            bar.incrementProgressBy(5);
        }
    };
    boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar=(ProgressBar)findViewById(R.id.progressBar);
    }

    public void onStart(){
        super.onStart();
        bar.setProgress(0);

        Thread background = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    for(int i=0; i<20 && isRunning; i++){
                        Thread.sleep(1000);
                        handler.sendMessage(handler.obtainMessage());
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        });
        isRunning = true;
        background.start();
    }

    public void onStop(){
        super.onStop();
        isRunning = false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
