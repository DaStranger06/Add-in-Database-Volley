package com.siamsoft.customeradd;


import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ViewproductRV_2 extends AppCompatActivity {



  private static final String URL_student_all_details = "http://10.0.2.2/LMEM_01/getAllCustomer.php";

   // private static final String URL_student_all_details = "http://169.254.106.123/android/CMS_LMEM/getAllCustomer.php";


   //169.254. 106.123


   //a list to store all the products
    List<Customer> customerList;

    //the recyclerview
    RecyclerView recyclerView;


    RecylerviewAdapter_2 viewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewinrecyclerview);
        recyclerView = (RecyclerView)findViewById(R.id.rv_customer_view);

        adddata ();



    }//slb





    public void adddata ()

    {
        //initializing the productlist
        customerList = new ArrayList<>();



        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_student_all_details,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject studetailsall = array.getJSONObject(i);


                                //adding the customer to customer list  all tiem
                                customerList.add(new Customer(
                                        studetailsall.getInt("cid"),
                                        studetailsall.getString("cname"),
                                        studetailsall.getString("cmobile")


                                ));



                            }

                            //creating adapter object and setting it to recyclerview

                             viewAdapter = new RecylerviewAdapter_2(getApplicationContext(), customerList);

                            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            recyclerView.setAdapter(viewAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);

    }


}
