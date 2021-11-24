package com.example.courseorganiser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class CourseDetails extends AppCompatActivity {
    private String courseName=null;
    Button btn_deleteCourse;
    ListView lv_customers;
    Button btn_addParticipant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        btn_deleteCourse= (Button) findViewById(R.id.btn_deleteCourse);
        lv_customers =(ListView) findViewById(R.id.lv_customers);
        btn_addParticipant= (Button) findViewById(R.id.btn_addParticipant);


        Intent intent= getIntent();
        Bundle bundle = intent.getExtras();

        MyDB db= new MyDB(CourseDetails.this);
        showParticipantsOnLV(db.getParticipantNames(courseName));
        db.close();

        if(bundle!=null)
            courseName =(String) bundle.get("name");

        btn_deleteCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    MyDB db = new MyDB(CourseDetails.this);
                    db.deleteOne(courseName);
                    db.close();

                    Intent intent = new Intent(CourseDetails.this, MainActivity.class);
                    startActivity(intent);
                }
                catch (Exception e){
                    Toast.makeText(CourseDetails.this,"ERROR",Toast.LENGTH_SHORT).show();
                }

            }
        });

        btn_addParticipant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDB db = new MyDB(CourseDetails.this);

            }
        });
    }
    public void showParticipantsOnLV(List<String> names){
        ArrayAdapter namesArrayAdapter=new ArrayAdapter<String>(CourseDetails.this, android.R.layout.simple_dropdown_item_1line,names);
        lv_customers.setAdapter(namesArrayAdapter);

    }

}