package com.example.alan.talogintest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private Button mSendData,mSendData2;
    private Firebase mRef;
    private EditText mVaule,mKey,mVaule2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRef = new Firebase("https://team-awesome-logintest.firebaseio.com/Users");


        mSendData = (Button)findViewById(R.id.button3);
        mVaule = (EditText)findViewById(R.id.editText);
        mSendData2 = (Button)findViewById(R.id.button2);
        mKey = (EditText)findViewById(R.id.editText3);
        mVaule2 = (EditText)findViewById(R.id.editText4);


        final String email = getIntent().getStringExtra("UserEmail");

        mRef.setValue(email);

        mSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newemail = email.replace(".","");


                String value = mVaule.getText().toString();


                Firebase childRef = mRef.child(newemail);
//
//                childRef.setValue(value);
                childRef.setValue(value);
            }
        });
        mSendData2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                String value = mVaule2.getText().toString();
                String key = mKey.getText().toString();


                Firebase childRef = mRef.child(key);
//
//                childRef.setValue(value);
                childRef.setValue(value);
            }
        });

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


//                String value2 = dataSnapshot.getValue(String.class);
//                mGet.setText(value2);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
