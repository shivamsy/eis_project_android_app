package com.group7.www.demo;

import android.content.Context;
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

    private String title;
    private int page;
    private Context myContext;
    private int prev;

    public static FirstFragment newInstance(int page, String title) {
        FirstFragment fragmentFirst = new FirstFragment();
        Bundle args = new Bundle();

        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myContext = this.getActivity();
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
        prev = -1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        final TextView tvLabel = (TextView) view.findViewById(R.id.title);
        final TextView email = (TextView) view.findViewById(R.id.email);
        final TextView gas = (TextView) view.findViewById(R.id.gas);
        final TextView flame = (TextView) view.findViewById(R.id.flame);
        final TextView pir = (TextView) view.findViewById(R.id.pir);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //String uid = user.getUid();
        //System.out.println(uid);

        FirebaseDatabase.getInstance().getReference("users/"+user.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        System.out.println(dataSnapshot.child("name").getValue(String.class));
                        tvLabel.setText(dataSnapshot.child("name").getValue(String.class));
                        email.setText(dataSnapshot.child("email").getValue(String.class));
                        gas.setText(dataSnapshot.child("gas_sensor").getValue(String.class));
                        flame.setText(dataSnapshot.child("flame_sensor").getValue(String.class));
                        pir.setText(dataSnapshot.child("pir_sensor").getValue(String.class));

                        if(Integer.parseInt(dataSnapshot.child("gas_sensor").getValue(String.class)) > 320) {

                            System.out.println("Gas Leak!!!!!!!!!!!");
                            Notification notification = new Notification(myContext);
                            notification.notify1("gas_leak");
                        }
                        if(Integer.parseInt(dataSnapshot.child("flame_sensor").getValue(String.class))<100) {

                            System.out.println("Fire!!!!!!!!!");
                            Notification notification = new Notification(myContext);
                            notification.notify1("fire");
                        }
                        if(Integer.parseInt(dataSnapshot.child("pir_sensor").getValue(String.class))==1) {

                            System.out.println("theft!!!!!!!!!");
                            Notification notification = new Notification(myContext);
                            notification.notify1("theft");
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
        return view;
    }
}