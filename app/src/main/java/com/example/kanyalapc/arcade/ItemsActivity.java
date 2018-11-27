package com.example.kanyalapc.arcade;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ItemsActivity extends AppCompatActivity {
    class Teacher{
        private String name,description,imageURL,packagee,link,usepoint;
        private int id;


        public Teacher(String name, String description, String imageURL,String packagee,String link,String usepoint,int id) {
            this.name = name;
            this.description = description;
            this.imageURL = imageURL;
            this.packagee = packagee;
            this.link = link;
            this.usepoint = usepoint;
            this.id = id;
        }
        public int getID(){return id;}
        public String getName() {return name;}
        public String getDescription() { return description; }
        public String getImageURL() { return imageURL; }
        public String getPackage() { return packagee; }
        public String getLink() { return link; }
        public String getUsepoint() { return usepoint; }
    }
    /*
   Our custom adapter class
    */
    public class ListViewAdapter extends BaseAdapter {
        Context c;
        ArrayList<Teacher> teachers;

        public ListViewAdapter(Context c, ArrayList<Teacher> teachers) {
            this.c = c;
            this.teachers = teachers;
        }
        @Override
        public int getCount() {return teachers.size();}
        @Override
        public Object getItem(int i) {return teachers.get(i);}
        @Override
        public long getItemId(int i) {return i;}
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view==null)
            {
                view= LayoutInflater.from(c).inflate(R.layout.row_model,viewGroup,false);
            }

            TextView txtName = view.findViewById(R.id.nameTextView);
            TextView txtDescription = view.findViewById(R.id.descriptionTextView);
            ImageView teacherImageView = view.findViewById(R.id.teacherImageView);

            final Teacher link= (Teacher) this.getItem(i);
            final Teacher usepoint= (Teacher) this.getItem(i);


            final Teacher packagee=(Teacher) this.getItem(i);
            final Teacher teacher= (Teacher) this.getItem(i);

            txtName.setText(teacher.getName());
            txtDescription.setText(teacher.getDescription());

            if(teacher.getImageURL() != null && teacher.getImageURL().length() > 0)
            {
                Picasso.get().load(teacher.getImageURL()).placeholder(R.drawable.placeholder).into(teacherImageView);
            }else {
                Toast.makeText(c, "Empty Image URL", Toast.LENGTH_LONG).show();
                Picasso.get().load(R.drawable.placeholder).into(teacherImageView);
            }
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(c, teacher.getName(), Toast.LENGTH_SHORT).show();


                    final Intent launchIntent = getPackageManager().getLaunchIntentForPackage(packagee.getPackage());
                    if(launchIntent!=null){
                        AlertDialog.Builder alert = new AlertDialog.Builder(c);
                        alert.setTitle("Arcade");
                        alert.setMessage("Play "+ teacher.getName()+"\nUse point "+usepoint.getUsepoint());
                        alert.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(c,"Start",Toast.LENGTH_SHORT).show();
                                startActivity(launchIntent);
                            }
                        });
                        alert.setPositiveButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(c, "Cancel",Toast.LENGTH_SHORT).show();
                            }
                        });
                        alert.create().show();
                   /* Toast.makeText(getActivity(), "Start",Toast.LENGTH_SHORT).show();
                    startActivity(launchIntent);*/

                    }else {
                        AlertDialog.Builder alert = new AlertDialog.Builder(c);
                        alert.setTitle("Arcade");
                        alert.setMessage("Not found "+ teacher.getName()+" in your phone");
                        alert.setNegativeButton("Download", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(c, "Start Download",Toast.LENGTH_SHORT).show();
                                Intent ii = new Intent(Intent.ACTION_VIEW);
                                ii.setData(Uri.parse(link.getLink()));
                                startActivity(ii);
                            }
                        });
                        alert.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(c, "Cancel",Toast.LENGTH_SHORT).show();
                            }
                        });
                        alert.create().show();
                        Toast.makeText(c, "Not found",Toast.LENGTH_SHORT).show();

                    }

                }
            });

            return view;
        }
    }
    /*
    Our HTTP Client
     */
    public class DataRetriever {

        //YOU CAN USE EITHER YOUR IP ADDRESS OR  10.0.2.2 I depends on the Emulator
        //private static final String PHP_MYSQL_SITE_URL="http://10.0.2.2/php/spiritualteachers";
        //For genymotion you can use this
        //private static final String PHP_MYSQL_SITE_URL="http://10.0.3.2/php/spiritualteachers";
        //You can get your ip adrress by typing ipconfig/all in cmd
        private final String PHP_MYSQL_SITE_URL="http://"+ConfigIP.IP+"/readGameList.php";
        //INSTANCE FIELDS
        private final Context c;
        private ListViewAdapter adapter ;

        public DataRetriever(Context c) { this.c = c; }
        /*
        RETRIEVE/SELECT/REFRESH
         */
        public void retrieve(final ListView gv, final ProgressBar myProgressBar)
        {
            final ArrayList<Teacher> teachers = new ArrayList<>();

            myProgressBar.setIndeterminate(true);
            myProgressBar.setVisibility(View.VISIBLE);

            AndroidNetworking.get(PHP_MYSQL_SITE_URL)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONArray(new JSONArrayRequestListener() {
                        @Override
                        public void onResponse(JSONArray response) {
                            JSONObject jo;
                            Teacher teacher;
                            try
                            {
                                for(int i=0;i<response.length();i++)
                                {
                                    jo=response.getJSONObject(i);

                                    int id=jo.getInt("uid");
                                    String name=jo.getString("teacher_name");
                                    String description=jo.getString("teacher_description");
                                    String imageURL=jo.getString("teacher_image_url");
                                    String packagee =jo.getString("package");
                                    String usepoint =jo.getString("usepoint");
                                    String link =jo.getString("link");

                                    teacher=new Teacher(name,description,"http://"+ConfigIP.IP+""+imageURL,packagee,link,usepoint,id);
                                    teachers.add(teacher);
                                }

                                //SET TO SPINNER
                                adapter =new ListViewAdapter(c,teachers);
                                gv.setAdapter(adapter);
                                myProgressBar.setVisibility(View.GONE);

                            }catch (JSONException e)
                            {
                                myProgressBar.setVisibility(View.GONE);
                                Toast.makeText(c, "GOOD RESPONSE BUT JAVA CAN'T PARSE JSON IT RECEIEVED. "+e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                        //ERROR
                        @Override
                        public void onError(ANError anError) {
                            anError.printStackTrace();
                            myProgressBar.setVisibility(View.GONE);
                            Toast.makeText(c, "UNSUCCESSFUL :  ERROR IS : "+anError.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }
    private Button btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        ListView myGridView=findViewById(R.id.myListView);
        ProgressBar myDataLoaderProgressBar=findViewById(R.id.myDataLoaderProgressBar);

        new DataRetriever(ItemsActivity.this).retrieve(myGridView,myDataLoaderProgressBar);


        btnback = findViewById(R.id.btnback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
    }
}
