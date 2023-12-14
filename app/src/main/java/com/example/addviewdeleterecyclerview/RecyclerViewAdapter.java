package com.example.addviewdeleterecyclerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.DataViewHolder> {

    List<DataModel> dataModels;
    Context context;

    DataClick dataClick;

    interface DataClick{
        void ItemClick(DataModel model,int pos);
    }

    public void setUpInterface(DataClick dataClick){
        this.dataClick = dataClick;
    }

    public void DataUpdate(List<DataModel>dataModels){
        this.dataModels=dataModels;
        notifyDataSetChanged();
    }

    public RecyclerViewAdapter(Context context, List<DataModel> dataModels) {
        this.context = context;
        this.dataModels = dataModels;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_data, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DataModel model = dataModels.get(position);
        holder.nameTextView.setText(model.getName());
        holder.rollNoTextView.setText(model.getRollNo());;
//        holder.nameTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dataClick.ItemClick(dataModels.get(position),position);
//            }
//        });

        holder.imgVDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataModels.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder{

        TextView nameTextView;
        TextView rollNoTextView;
        ImageView imgVDelete;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.etvName);
            rollNoTextView = itemView.findViewById(R.id.etvRollNo);
        }
    }
}
