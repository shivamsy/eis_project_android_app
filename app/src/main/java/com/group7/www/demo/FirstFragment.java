package com.group7.www.demo;

/**
 * Created by prade on 31/03/18.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirstFragment extends Fragment {
    // Store instance variables
    private String title;
    private int page;

    // newInstance constructor for creating fragment with arguments
    public static FirstFragment newInstance(int page, String title) {
        FirstFragment fragmentFirst = new FirstFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        final TextView tvLabel = (TextView) view.findViewById(R.id.title);
        final TextView email = (TextView) view.findViewById(R.id.email);
        final TextView gas = (TextView) view.findViewById(R.id.gas);
        final TextView flame = (TextView) view.findViewById(R.id.flame);
        final TextView pir = (TextView) view.findViewById(R.id.pir);
        //tvLabel.setText(page + " -- " + title);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        System.out.println(uid);
        FirebaseDatabase.getInstance().getReference("users/ieMCfEpFLxcQOO4AnDeKAhP03Cw1")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        System.out.println(dataSnapshot.child("name").getValue(String.class));
                        tvLabel.setText(dataSnapshot.child("name").getValue(String.class));
                        email.setText(dataSnapshot.child("email").getValue(String.class));
                        gas.setText(dataSnapshot.child("gas_sensor").getValue(String.class));
                        flame.setText(dataSnapshot.child("flame_sensor").getValue(String.class));
                        pir.setText(dataSnapshot.child("pir_sensor").getValue(String.class));

                        if(Integer.parseInt(dataSnapshot.child("gas_sensor").getValue(String.class))>320)
                            System.out.println("Gas Leak!!!!!!!!!!!");
                        if(Integer.parseInt(dataSnapshot.child("flame_sensor").getValue(String.class))<100)
                            System.out.println("Fire!!!!!!!!!");

                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

        return view;
    }

}