package com.example.linlinhan.linlinhan_comp304_assignment3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class DrawingActivity extends AppCompatActivity {

    ImageView reusableImageView;

    //
    int startx = 0;
    int starty = 0;
    int endx=0;
    int endy=0;
    //


    String[] thickness;
    int selectedThickness;
    Paint paint;
    Bitmap bitmap;
    Canvas canvas;
    EditText edtX;
    EditText edtY;
    ImageView btnUp;
    ImageView btnDown;
    ImageView btnLeft;
    ImageView btnRight;
    RadioButton rbnRed;
    RadioButton rbnGreen;
    RadioButton rbnBlue;

    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);

        edtX= (EditText)findViewById(R.id.xValue);
        edtY=(EditText)findViewById(R.id.yValue);
        btnUp= (ImageView) findViewById(R.id.imgUp);
        btnDown= (ImageView) findViewById(R.id.imgDown);
        btnLeft=(ImageView) findViewById(R.id.imgLeft);
        btnRight=(ImageView) findViewById(R.id.imgRight);

        //---RadioButton (get color)---
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.rdbGpColor);
        paint = new Paint();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                rbnRed = (RadioButton) findViewById(R.id.rbnRed);
                rbnGreen = (RadioButton) findViewById(R.id.rbnGreen);
                rbnBlue = (RadioButton) findViewById(R.id.rbnBlue);
                if (rbnRed.isChecked()) {
                    paint.setColor(Color.RED);
                }
                else if (rbnGreen.isChecked())
                {
                    paint.setColor(Color.GREEN);

                }

                else if (rbnBlue.isChecked())
                {
                    paint.setColor(Color.BLUE);
                }
            }
        });

        //---get thickness---
        thickness=getResources().getStringArray(R.array.thickness);
        Spinner spinner = (Spinner) findViewById(R.id.spnThick);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.thickness, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int index=parent.getSelectedItemPosition();
                selectedThickness=Integer.valueOf(thickness[index]);

                paint.setStrokeWidth(selectedThickness);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //creating a bitmap as content view for the image
        bitmap = Bitmap.createBitmap((int) getWindowManager()
                .getDefaultDisplay().getWidth(), (int) getWindowManager()
                .getDefaultDisplay().getHeight(), Bitmap.Config.ARGB_8888);

        canvas = new Canvas(bitmap);
        reusableImageView = (ImageView)findViewById(R.id.ImageViewForDrawing);
        reusableImageView.setImageBitmap(bitmap);
        reusableImageView.setVisibility(View.VISIBLE);

        // arrow key - up
        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reusableImageView.setFocusable(true);
                reusableImageView.requestFocus();
                endy=endy-10;
                drawLine( canvas);
                reusableImageView.invalidate();
            }
        });

        // arrow key - down
        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reusableImageView.setFocusable(true);
                reusableImageView.requestFocus();
                endy=endy+10;
                drawLine( canvas);
                reusableImageView.invalidate();
            }
        });

        // arrow key - left
        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reusableImageView.setFocusable(true);
                reusableImageView.requestFocus();
                endx=endx-10;
                drawLine( canvas);
                reusableImageView.invalidate();
            }
        });

        // arrow key - right
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reusableImageView.setFocusable(true);
                reusableImageView.requestFocus();
                endx=endx+10;
                drawLine( canvas);
                reusableImageView.invalidate();
            }
        });


    }// end of onCreate

    public void drawLine(Canvas canvas)
    {
        edtX.setText(String.valueOf(endx), TextView.BufferType.EDITABLE);
        edtY.setText(String.valueOf(endy), TextView.BufferType.EDITABLE);
        canvas.drawLine(startx, starty, endx, endy, paint);
        startx=endx;
        starty=endy;

    }

    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        switch(keyCode)
        {
            case KeyEvent.KEYCODE_DPAD_UP:
                reusableImageView.setFocusable(true);
                reusableImageView.requestFocus();
                endy=endy-10;
                drawLine( canvas);
                reusableImageView.invalidate();

                return true;

            case KeyEvent.KEYCODE_DPAD_DOWN:
                reusableImageView.setFocusable(true);
                reusableImageView.requestFocus();
                endy=endy+10;
                drawLine( canvas);
                reusableImageView.invalidate();

                return true;


            case KeyEvent.KEYCODE_DPAD_RIGHT:
                reusableImageView.setFocusable(true);
                reusableImageView.requestFocus();
                endx=endx+10;
                drawLine( canvas);
                reusableImageView.invalidate();

                return true;

            case KeyEvent.KEYCODE_DPAD_LEFT:
                reusableImageView.setFocusable(true);
                reusableImageView.requestFocus();
                endx=endx-10;
                drawLine( canvas);
                reusableImageView.invalidate();

                return true;

        }
        return false;
    }

    public void clearAll(View view) {
        init();
    }

    public  void init(){

        bitmap = Bitmap.createBitmap((int) getWindowManager()
                .getDefaultDisplay().getWidth(), (int) getWindowManager()
                .getDefaultDisplay().getHeight(), Bitmap.Config.ARGB_8888);

        canvas = new Canvas(bitmap);
        reusableImageView = (ImageView)findViewById(R.id.ImageViewForDrawing);
        reusableImageView.setImageBitmap(bitmap);
        reusableImageView.setVisibility(View.VISIBLE);
        edtX.setText("");
        edtY.setText("");

        RadioGroup rdbGpColor=(RadioGroup) findViewById(R.id.rdbGpColor);
        rdbGpColor.clearCheck();
        paint.setColor(Color.BLACK);

        Spinner spinner = (Spinner) findViewById(R.id.spnThick);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.thickness, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        startx = 0;
        starty = 0;
        endx=0;
        endy=0;
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
