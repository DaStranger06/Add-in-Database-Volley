package com.siamsoft.customeradd;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetCustomer extends AppCompatActivity {



    //  private static final String JSON_URL = "http://192.168.56.1/android/crudmysql/volleycrud/getAllProduct.php";


    //private static final String JSON_URL = "http://10.0.2.2/android/CMS/getAllCustomer.php";



    private static final String JSON_URL = "http://10.0.2.2/LMEM_01/getAllCustomer.php";

    // private static final String JSON_URL = "http://192.168.1.5/android/CMS/getAllCustomer.php";



    //listview object
    ListView listView;

    //the hero list where we will store all the hero objects after parsing json
    List<Model> customerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_customer);



        //initializing listview and hero list
        listView = (ListView) findViewById(R.id.viewCustomer);
        customerList = new ArrayList<>();

        //this method will fetch and parse the data
        loadListdata();



    }//slb

    private void loadListdata() {
        //getting the progressbar
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbar);

        //making the progressbar visible
        progressBar.setVisibility(View.VISIBLE);

        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion
                        progressBar.setVisibility(View.INVISIBLE);


                        try {

                            /*//--------way-1
                              //way 1 --object wise    // echo json_encode(array('result'=>$result));



                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);

                            //we have the array named hero inside the object
                            //so here we are getting that json array


                            // JSONArray productArray = obj.getJSONArray("heroes");

                            JSONArray productArray = obj.getJSONArray("result");


                            //now looping through all the elements of the json array
                            for (int i = 0; i < productArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject productObject = productArray.getJSONObject(i);

                                     Customer proo2 = new Customer(productObject.getInt("id"),productObject.getString("cname"), productObject.getString("cmobile"));


                                //adding the proo2 to herolist
                                customerList.add(proo2);
                            }

                            //------way--1
*/

                            //-----------way--2   //way 2 --array wise
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject customerObject = array.getJSONObject(i);

                                //adding the product to product list
                                Model proo2 = new Model(customerObject.getInt("cid"),customerObject.getString("cname"), customerObject.getString("cmobile"));

                                //adding the proo2 to herolist
                                customerList.add(proo2);
                            }


                            //-------------way--2  //way 2 --array wise




                            //creating custom adapter object
                            Adapter adapter = new Adapter(customerList, getApplicationContext());

                            //adding the adapter to listview
                            listView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }


    //--------------------------------------











}