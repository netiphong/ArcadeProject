package com.example.kanyalapc.arcade;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

public class AdminMainActivity extends AppCompatActivity {
    String txtInfo,txtPID,txtID,editName;
    CardView mycard,mycard2,mycard3,mycard4 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        mycard = (CardView) findViewById(R.id.bt1);
        mycard2 = (CardView) findViewById(R.id.bt2);
        mycard4 = (CardView) findViewById(R.id.bt4);
        editName= ""+getIntent().getSerializableExtra("usrname");
        mycard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminMainActivity.this, AdminActivity.class);

                intent.putExtra("usrname",(editName));
                startActivity(intent);
            }
        });
        mycard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMainActivity.this, AdminListActivity.class);

                intent.putExtra("usrname",(editName));
                startActivity(intent);
            }
        });

        mycard4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                AlertDialog.Builder alert = new AlertDialog.Builder(AdminMainActivity.this);
                alert.setTitle("Logout");
                alert.setMessage("Are you sure?");
                alert.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(AdminMainActivity.this, "Logout",Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(AdminMainActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });
                alert.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(AdminMainActivity.this, "Cancel",Toast.LENGTH_SHORT).show();

                    }
                });
                alert.create().show();


            }
        });

    }
}
