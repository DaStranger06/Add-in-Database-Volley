package com.siamsoft.customeradd;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Updelta extends AppCompatActivity {

    TextView txt;
    EditText ed_name, ed_mobile;
    Button bt_update, bt_del;

    private  static String TAG = Updelta.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updelta);


        txt = findViewById(R.id.id);
        ed_name = findViewById(R.id.name);
        ed_mobile= findViewById(R.id.mobile);

        bt_update =findViewById(R.id.button);
        bt_del = findViewById(R.id.button2);


        bt_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDelete();
            }
        });

        bt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmeditdata();
            }
        });


        Intent intent =getIntent();

        String id = intent.getStringExtra("key_id");
        String name = intent.getStringExtra("key_name");
        String mobile = intent.getStringExtra("key_mobile");


        txt.setText(id);
        ed_name.setText(name);
        ed_mobile.setText(mobile);

    }

    private void confirmUpdate() {
        //pd.setMessage("Update Data");
        // pd.setCancelable(false);
        //pd.show();

        //String updateDataURL = "http://192.168.56.1/android/andoid_image_crud_wcap/recyclerview_dynamic/recyclerview_dynamic/updateProduct.php";
        String updateDataURL = "http://10.0.2.2/LMEM_01/Updated.php";




        StringRequest updateReq = new StringRequest(Request.Method.POST, updateDataURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // player_name.setText("");
                        // PRICE.setText("");
                        Toast.makeText(getApplicationContext(), "Data UPDATE Successfully", Toast.LENGTH_SHORT).show();
                        //pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            //Toast.makeText(InsertData.this, "pesan : "+   res.getString("message") , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //startActivity( new Intent(InsertData.this,MainActivity.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();

                txt = findViewById(R.id.id);
                ed_name = findViewById(R.id.name);
                ed_mobile= findViewById(R.id.mobile);


                map.put("c_id",txt.getText().toString());
                map.put("c_fullname",ed_name.getText().toString());
                map.put("c_mobile",ed_mobile.getText().toString());



                //map.put("p_price",PRICE.getText().toString());
                return map;
            }
        };

        AppSingleton1.getInstance(this).addToRequestQueue(updateReq,TAG);
    }


    //----------CONFIRM EDIT---------------


    private void confirmeditdata(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to update this Data?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        confirmUpdate(); //edit method call
                        startActivity(new Intent(Updelta.this,ViewproductRV_2.class));
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    //------
    //EDIT DATA


/* /  private void showpDialog() {
        if (!pDialog.isShowing()) pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing()) pDialog.dismiss();
    }*/





    private void confirmDelete() {

        //delete section
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Are you sure you want to delete this employee?");

            alertDialogBuilder.setPositiveButton("Yes",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            delete_data();
                            startActivity(new Intent(Updelta.this, Updelta.class));
                        }
                    });

            alertDialogBuilder.setNegativeButton("No",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {

                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

        //--------------------------


        public void delete_data() {


            String myurl = "http://10.0.2.2/LMEM_01/Delete.php";




            //  String myurl = "http://192.168.56.1/android/crudmysql/volleycrud/deleteProduct.php?";
            //if everything is fine
            StringRequest StringRequest = new StringRequest(Request.Method.POST, myurl,new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    txt.setText("");
                    ed_name.setText("");
                    ed_mobile.setText("");

                    Toast.makeText(getApplicationContext(), "Data DELETE Successfully", Toast.LENGTH_SHORT).show();
                }

            },new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<>();
                    map.put("c_id",txt.getText().toString());
                    return map;
                }
            };
            AppSingleton1.getInstance(this).addToRequestQueue(StringRequest,TAG);
        }

        //delete section
    }
