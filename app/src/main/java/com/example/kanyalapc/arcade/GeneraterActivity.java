package com.example.kanyalapc.arcade;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

public class GeneraterActivity extends AppCompatActivity {
    private TextView txtInfo;
    EditText text;
    Button gen_btn;
    String editName,txtPID,txtID,txtPoint;
    ImageView image;
    String text2Qr;
    int pointt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generater);
        text = (EditText) findViewById(R.id.text);
        gen_btn = (Button) findViewById(R.id.gen_btn);
        image = (ImageView) findViewById(R.id.image);
        editName=(""+getIntent().getSerializableExtra("usrname"));
        txtInfo = (TextView)findViewById(R.id.txtInfo);
        checkstatus();

        gen_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text2Qr = text.getText().toString().trim();
                insertpoint("-"+text2Qr,Integer.parseInt(text2Qr),false);


            }
        });
    }



    public void checkstatus() {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            OkHttpClient client = new OkHttpClient();

            HttpUrl.Builder urlBuilder = HttpUrl.parse("http://"+ConfigIP.IP+"/read.php").newBuilder();
            txtPID=(editName);
            urlBuilder.addQueryParameter("Name", txtPID);


            String url = urlBuilder.build().toString();

            okhttp3.Request request = new okhttp3.Request.Builder()
                    .url(url)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, final okhttp3.Response response) throws IOException {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                //txtInfo.setText(response.body().string());

                                try {
                                    String data = response.body().string();

                                    JSONArray jsonArray = new JSONArray(data);
                                    JSONObject jsonObject;

                                    jsonObject = jsonArray.getJSONObject(0);
                                    txtID=(jsonObject.getString("id"));
                                    txtPoint=(jsonObject.getString("point"));


                                } catch (JSONException e) {
                                    txtInfo.setText(e.getMessage());
                                }


                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }

                ;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertpoint(String details ,int point,boolean a){
        boolean g=false;
        pointt= Integer.parseInt(txtPoint);
        if(a==true){

            pointt+=point;
            g=true;

        }
        else {
            if(point> pointt){
                android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(GeneraterActivity.this);
                alert.setTitle("Generater");
                alert.setMessage("You have "+pointt+" point");
                alert.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(GeneraterActivity.this, "Cancel",Toast.LENGTH_SHORT).show();

                    }
                });
                alert.create().show();

            }else {
                g=true;
                pointt -= point;
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try{
                    BitMatrix bitMatrix = multiFormatWriter.encode(text2Qr, BarcodeFormat.QR_CODE,200,200);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    image.setImageBitmap(bitmap);
                }
                catch (WriterException e){
                    e.printStackTrace();
                }
            }
        }
        try {
            if(g==true){
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                OkHttpClient client = new OkHttpClient();

                HttpUrl.Builder urlBuilder = HttpUrl.parse("http://"+ConfigIP.IP+"/addpoint.php").newBuilder();
                urlBuilder.addQueryParameter("Id", txtID);
                urlBuilder.addQueryParameter("Details", details);
                urlBuilder.addQueryParameter("Point", ""+pointt);
                urlBuilder.addQueryParameter("Name", editName);
                String url = urlBuilder.build().toString();

                okhttp3.Request request = new okhttp3.Request.Builder()
                        .url(url)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, final okhttp3.Response response) throws IOException {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                try {
                                    txtInfo.setText(response.body().string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                    }

                    ;
                });

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        g=false;
    }
}
