package com.siamsoft.customeradd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class RVadapter extends RecyclerView.Adapter<RVadapter.MYViewHolder> {

    //the Model list that will be displayed
    private List<Model> Modellist;
    //the context object
    private Context mCtx;

    public RVadapter(List<Model> modellist, Context mCtx) {
        Modellist = modellist;
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public MYViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.activity_model,null,false);
        return  new MYViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MYViewHolder holder, int position) {

        holder.txtid.setText(String.valueOf(Modellist.get(position).getC_id()));
        holder.txtname.setText(Modellist.get(position).getC_name());
        holder.txtmobile.setText(Modellist.get(position).getC_mobile());

    }

    @Override
    public int getItemCount() {
        return Modellist.size();
    }

    public class MYViewHolder extends RecyclerView.ViewHolder {


        private TextView txtid;
        private TextView txtname;
        private TextView txtmobile;


        public MYViewHolder(@NonNull View itemView) {
            super(itemView);

            txtid = itemView.findViewById(R.id.idg);
            txtname = itemView.findViewById(R.id.nameg);
            txtmobile = itemView.findViewById(R.id.mobileg);

        }
    }

}