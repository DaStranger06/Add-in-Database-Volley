package com.siamsoft.customeradd;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;



public class RecylerviewAdapter_2 extends RecyclerView.Adapter<RecylerviewAdapter_2.CustomerViewHolder> {


    private Context mCtx;
    private List<Customer> customerList;

    public RecylerviewAdapter_2(Context mCtx, List<Customer> customerList) {

        this.mCtx = mCtx;
        this.customerList = customerList;
    }
    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.rv_items_2, null);
        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {

        //Getting the pro for the specified position
        Customer pro = customerList.get(position);
        holder.textid.setText(String.valueOf(pro.getC_id()));
        holder.textViewName.setText(pro.getC_fullname());
        holder.textViewMobile.setText(pro.getC_mobile());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mCtx,Updelta.class);

                int id =pro.getC_id();
                String C_auto_id = String.valueOf(id);
                String Name = pro.getC_fullname();
                String Mobile = pro.getC_mobile();

                intent.putExtra("key_id",C_auto_id);
                intent.putExtra("key_name",Name);
                intent.putExtra("key_mobile",Mobile);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mCtx.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }


    class CustomerViewHolder extends RecyclerView.ViewHolder {


        //getting text views
        TextView textid;
        TextView textViewName;
        TextView textViewMobile;


        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);


            //getting text views
            textid = itemView.findViewById(R.id.textViewid);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewMobile = itemView.findViewById(R.id.textViewMobile);


            itemView.setTag(itemView);

        }
    }



}


