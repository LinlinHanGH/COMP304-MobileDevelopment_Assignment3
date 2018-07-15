package com.example.linlinhan.linlinhan_comp304_assignment3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

public class TweenActivity extends AppCompatActivity {

    ImageView imgViewMoon;
    ImageView imgViewEarth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween);

        ImageView earth = (ImageView)findViewById(R.id.earth);
        earth.setVisibility(View.INVISIBLE);
        // Handle Start Button
        final Button onButton = (Button) findViewById(R.id.ButtonStart);
        onButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                performAnimation(R.anim.translate_position);
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

    private void performAnimation(int animationResourceID)
    {
        // We will animate the imageview
        imgViewMoon = (ImageView)findViewById(R.id.ImageView_Show);
        imgViewMoon.setImageResource(R.drawable.moon);
        imgViewMoon.setVisibility(View.VISIBLE);

        imgViewEarth = (ImageView)findViewById(R.id.earth);
        imgViewEarth.setImageResource(R.drawable.earth);
        imgViewEarth.setVisibility(View.VISIBLE);

        // Load the appropriate animation
        Animation anMoon=  AnimationUtils.loadAnimation(this, R.anim.translate_position);
        Animation anEarth=AnimationUtils.loadAnimation(this, R.anim.rotate);

        // keep the same speed
        LinearInterpolator lir = new LinearInterpolator();
        anMoon.setInterpolator(lir);
        anEarth.setInterpolator(lir);

        // Start the animation
        imgViewMoon.startAnimation(anMoon);
        imgViewEarth.startAnimation(anEarth);
    }



    private void stopAnimation(){
        imgViewMoon.clearAnimation();
        imgViewEarth.clearAnimation();
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
