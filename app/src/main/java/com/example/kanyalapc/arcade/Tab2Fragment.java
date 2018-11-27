package com.example.kanyalapc.arcade;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

/**
 * Created by User on 2/28/2017.
 */

public class Tab2Fragment extends Fragment {
    private static final String TAG = "Tab2Fragment";
    private TextView pid,dt,date;

    String data;
    String ppid=" ",pdt=" ",pd=" ";

    String txtInfo,txtPID,txtID,editName;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.tab2_fragment,container,false);


        pid =(TextView) view.findViewById(R.id.pid);
        dt =(TextView) view.findViewById(R.id.det);
        date =(TextView) view.findViewById(R.id.date);

        editName=(""+getActivity().getIntent().getSerializableExtra("usrname") );

        checkstatus();
        return view;

    }
    public void checkstatus() {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            OkHttpClient client = new OkHttpClient();

            HttpUrl.Builder urlBuilder = HttpUrl.parse("http://"+ConfigIP.IP+"/read.php").newBuilder();
            txtPID=(editName);
            urlBuilder.addQueryParameter("Name", txtPID.toString());


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

                    getActivity().runOnUiThread(new Runnable() {
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

                                    try {
                                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                                        StrictMode.setThreadPolicy(policy);

                                        OkHttpClient client = new OkHttpClient();

                                        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://"+ConfigIP.IP+"/chkpoint.php").newBuilder();
                                        txtPID=(editName);
                                        urlBuilder.addQueryParameter("Id", txtID);


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

                                                getActivity().runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {

                                                        try {


                                                            try {
                                                                String data = response.body().string();

                                                                JSONArray jsonArray = new JSONArray(data);
                                                                JSONObject jsonObject;
                                                                for (int i=0;i<jsonArray.length();i++) {
                                                                    jsonObject = jsonArray.getJSONObject(i);
                                                                    ppid+="ID "+jsonObject.getString("pid")+"\n ";
                                                                    pdt+= " "+jsonObject.getString("details")+"\n  ";
                                                                    pd+= "Date "+jsonObject.getString("date")+"\n  ";

                                                                }
                                                                pid.setText(ppid);
                                                                dt.setText(pdt);
                                                                date.setText(pd);

                                                            } catch (JSONException e) {
                                                               // txtInfo.setText(e.getMessage());
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

                                } catch (JSONException e) {

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
    public  void  checkpoint(){

    }
}
