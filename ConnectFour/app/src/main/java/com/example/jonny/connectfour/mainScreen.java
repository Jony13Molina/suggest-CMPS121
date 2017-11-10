package com.example.jonny.connectfour;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.support.v7.app.AppCompatActivity;
/**
 * Created by jonny on 11/4/2017.
 */


public class mainScreen extends Fragment implements View.OnClickListener
{
    Button proceedNew;
    Button proceedOld;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root = inflater.inflate(R.layout.mainscreen, container, false);

        proceedOld = (Button) root.findViewById(R.id.continueGame);
        proceedOld.setOnClickListener(this);
        proceedNew = (Button) root.findViewById(R.id.buttonNew);
        proceedNew.setOnClickListener(this);


        return root;

    }

    @Override
    public void onClick(View v) {
        //gameScreen screen = null;
        switch(v.getId())
        {
            case R.id.buttonNew:
                gameScreen screen = new gameScreen();

                android.support.v4.app.FragmentTransaction transaction =
                        getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, screen);
                transaction.addToBackStack(null);
                transaction.commit();
        }
    }
}

