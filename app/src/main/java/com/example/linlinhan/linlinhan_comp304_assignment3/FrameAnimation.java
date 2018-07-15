package com.example.linlinhan.linlinhan_comp304_assignment3;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class FrameAnimation extends AppCompatActivity {
    AnimationDrawable mframeAnimation = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_animation);

        // Handle Start Button
        final Button onButton = (Button) findViewById(R.id.ButtonStart);
        onButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startAnimation();
            }
        });

        // Handle Stop Button
        final Button offButton = (Button) findViewById(R.id.ButtonStop);
        offButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                stopAnimation();
            }
        });
    }// end of onCreate

    private void startAnimation()
    {

        ImageView img = (ImageView)findViewById(R.id.ImageView_Heart);

        BitmapDrawable frame1 = (BitmapDrawable)getResources().getDrawable(R.drawable.ic_heart_0);
        BitmapDrawable frame2 = (BitmapDrawable)getResources().getDrawable(R.drawable.ic_heart_25);
        BitmapDrawable frame3 = (BitmapDrawable)getResources().getDrawable(R.drawable.ic_heart_50);
        BitmapDrawable frame4 = (BitmapDrawable)getResources().getDrawable(R.drawable.ic_heart_75);
        BitmapDrawable frame5 = (BitmapDrawable)getResources().getDrawable(R.drawable.ic_heart_100);

        // Get the background, which has been compiled to an AnimationDrawable object.
        int reasonableDuration = 500;
        mframeAnimation = new AnimationDrawable();
        mframeAnimation.setOneShot(false);	// loop continuously
        mframeAnimation.addFrame(frame1, reasonableDuration);
        mframeAnimation.addFrame(frame2, reasonableDuration);
        mframeAnimation.addFrame(frame3, reasonableDuration);
        mframeAnimation.addFrame(frame4, reasonableDuration);
        mframeAnimation.addFrame(frame5, reasonableDuration);

        img.setBackgroundDrawable(mframeAnimation);

        mframeAnimation.setVisible(true,true);
        mframeAnimation.start();
    } // end of startAnimation

    private void stopAnimation()
    {
        mframeAnimation.stop();
        mframeAnimation.setVisible(false,false);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent=new Intent(this,MainActivity.class);;

        //pass value to ThirdActivity according to the selected option on SecondActivity
        switch (item.getItemId()){
            case R.id.main:
                intent=new Intent(this,MainActivity.class);
                break;
            case R.id.drawing:
                intent=new Intent(this,DrawingActivity.class);
                break;
            case R.id.framed_animation:
                intent=new Intent(this,FrameAnimation.class);
                break;
            case R.id.tweened_animation:
                intent=new Intent(this,TweenActivity.class);
                break;
        }
        startActivity(intent);
        return true;
    }
}
