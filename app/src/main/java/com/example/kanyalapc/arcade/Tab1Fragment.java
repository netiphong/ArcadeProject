package com.example.kanyalapc.arcade;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

/**
 * Created by User on 2/28/2017.
 */

public class Tab1Fragment extends Fragment {
    private static final String TAG = "Tab1Fragment";
    String txtInfo,txtPID,txtID,editName;
    CardView mycard,mycard2,mycard3,mycard4 ;
    Intent i,i3 ;
    LinearLayout ll;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_fragment,container,false);
        editName=(""+getActivity().getIntent().getSerializableExtra("usrname") );

        ll = (LinearLayout) view.findViewById(R.id.ll);
        mycard = (CardView) view.findViewById(R.id.bt1);
        mycard2 = (CardView) view.findViewById(R.id.bt2);
        mycard3 = (CardView) view.findViewById(R.id.bt3);
        mycard4 = (CardView) view.findViewById(R.id.bt4);
        i = new Intent(getActivity(),ItemsActivity.class);
        i3 = new Intent(getActivity(),LoginActivity.class);
        mycard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });
        mycard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ReaderActivity.class);

                intent.putExtra("usrname",(editName));
                startActivity(intent);
            }
        });
        mycard3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),GeneraterActivity.class);

                intent.putExtra("usrname",(editName));
                startActivity(intent);
            }
        });
        mycard4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setTitle("Logout");
                alert.setMessage("Are you sure?");
                alert.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(), "Logout",Toast.LENGTH_SHORT).show();
                        startActivity(i3);
                    }
                });
                alert.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(), "Cancel",Toast.LENGTH_SHORT).show();

                    }
                });
                alert.create().show();


            }
        });

        return view;
    }

}

