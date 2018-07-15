package com.example.linlinhan.linlinhan_comp304_assignment3;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {
    String[] activities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView lstView = getListView();

        //handle the item click event
        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent=null;
                String selectedSetting = (String)lstView.getItemAtPosition(position);
                //
                switch (position)
                {

                    case 0:
                        intent = new Intent(MainActivity.this, DrawingActivity.class);
                        break;

                    case 1:
                        intent = new Intent(MainActivity.this, FrameAnimation.class);
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this, TweenActivity.class);
                        break;

                }

                startActivity(intent);
            }
        });

        /*lstView.setChoiceMode(ListView.CHOICE_MODE_NONE);
        lstView.setTextFilterEnabled(true);
        //populate the array activities
        activities = getResources().getStringArray(R.array.activities);
        //set the adapter to array activities
        //binds the array with the list view
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, activities));*/
    }

    //list item event handler
    /*public void onListItemClick(
            ListView parent, View v, int position, long id)
    {
        Intent i=null;
        switch (position)
        {
            case 0:
                i = new Intent(this, DrawingActivity.class);
                break;

            case 1:
                i = new Intent(this, FrameAnimation.class);
                break;
            case 2:
                i = new Intent(this, TweenActivity.class);
                break;

        }
        startActivity(i);

    }*/
}
