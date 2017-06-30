package info.android.rowing.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Timer;
import java.util.TimerTask;

import info.android.rowing.R;

import static android.R.attr.angle;

/**
 * Created by hp on 29/06/2017.
 */

public class maincoach extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessageDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coach_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mFirebaseDatabase= FirebaseDatabase.getInstance(); //getinstance(),Gets the default FirebaseDatabase instance
        mMessageDatabaseReference=mFirebaseDatabase.getReference().child("Message");
        // final FirebaseDatabase database = FirebaseDatabase.getInstance();

        ///send data every second 1000ms////////////////
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {



                //RowerData mRowerData=new RowerData( name,strRate, nCurrentSpeed,angle,Dist,time) ;


                submitPost();

            }
        }, 0, 7000); // 7 sec
    }// end on create

    private void submitPost() {

        mMessageDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // if(dataSnapshot==null) {


                for (DataSnapshot asmaa : dataSnapshot.getChildren()) {
                    //   String string1 = String.valueOf(dataSnapshot.getValue());
                    // TextView kh1 = (TextView) findViewById(R.id.as1);
                    //kh1.setText(("str.rat " + string1));
                    //DataSnapshot  asmaa =dataSnapshot;
                    String string1 = String.valueOf(asmaa.child("strokeRate").getValue());
                    TextView kh1 = (TextView) findViewById(R.id.as1);
                    kh1.setText((  string1));

                    String string2 = String.valueOf(asmaa.child("name").getValue());
                    TextView kh2 = (TextView) findViewById(R.id.Rname);
                    kh2.setText(( string2));

                    String string3 = String.valueOf(asmaa.child("angle").getValue());
                    TextView kh3 = (TextView) findViewById(R.id.Rangle);
                    kh3.setText(( string3));

                    String string4 = String.valueOf(asmaa.child("speed").getValue());
                    TextView kh4 = (TextView) findViewById(R.id.Rspeed);
                    kh4.setText(( string4));

                    String string5 = String.valueOf(asmaa.child("distance").getValue());
                    TextView kh5 = (TextView) findViewById(R.id.Rdist);
                    kh5.setText(( string5));

                    String string6 = String.valueOf(asmaa.child("time").getValue());
                    TextView kh6 = (TextView) findViewById(R.id.Rtime);
                    kh6.setText(( string6));


                }
                //  }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }


}