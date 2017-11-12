package com.example.jonny.suggest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button searchB = (Button)findViewById(R.id.SearchButton);
        searchB.setOnClickListener(this);
        Button directions = (Button)findViewById(R.id.DirectionsB);
        directions.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.SearchButton:
                Intent searchIntent = new Intent(MainActivity.this, SuggestionActivity.class);
                startActivity(searchIntent);
            case R.id.DirectionsB:
                Intent directionIntent = new Intent(MainActivity.this, Directions.class);
                startActivity(directionIntent);
        }

    }
}
