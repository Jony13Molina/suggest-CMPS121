/*Jonathan Molina
jomolina@ucsc.edu
program is not complete ran out of time
 */

package com.example.jonny.connectfour;


import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //check activity uses the version with the
        //fragment container
        if(findViewById(R.id.fragment_container)!= null)
        {
            //if we are restoring from a previous state
            //then do nothing and return, else we will
            //end up with overlapping fragments
            if(savedInstanceState!= null)
            {
                return;

            }

            //make a new fragment to place it in our activity
            mainScreen mainfrag = new mainScreen();
            //if this activity was started with special instructions from
            //intent, then pass intents extras as argument to the fragment
            mainfrag.setArguments(getIntent().getExtras());
            //add the fragment to the fragment layout
            //FragmentManager mainscreenManager = getFragmentManager();
            getSupportFragmentManager().beginTransaction().
                    add(R.id.fragment_container, mainfrag).commit();
        }


    }

}
