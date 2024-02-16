package com.example.volleyapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private Context mcontext;
    private ArrayList<ExampleItem> mexamplelist;
private onitemclicklistner mlistner;
public interface  onitemclicklistner {
    void onitemclick(int posistion);
}
public  void  setonitemclicklistner(onitemclicklistner listner){
    mlistner=listner;
}
    public ExampleAdapter(Context context, ArrayList examplelist){
        mcontext=context;
        mexamplelist=examplelist;

    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mcontext).inflate(R.layout.example_item,parent,false);

        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
ExampleItem currentitem=mexamplelist.get(position);
String imageurl=currentitem.getImageurl();
String creatorname=currentitem.getcreator();
String likecount=currentitem.getlikes();

holder.mtextviewcreator.setText(creatorname);
holder.mtextviewlkes.setText("likes"+likecount);
        Picasso.get().load(imageurl).fit().centerInside().into(holder.mimageview);
    }

    @Override
    public int getItemCount() {
        return mexamplelist.size();
    }

    public  class ExampleViewHolder extends RecyclerView.ViewHolder{
        public ImageView mimageview;
        public TextView mtextviewcreator;
        public TextView mtextviewlkes;
public  ExampleViewHolder(View itemView){
    super(itemView);
    mimageview=itemView.findViewById(R.id.image_view);
    mtextviewcreator=itemView.findViewById(R.id.creatertext_view);
    mtextviewlkes=itemView.findViewById(R.id.liketext_view);
    itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mlistner!= null){
                int position=getAdapterPosition();
                if (position!=RecyclerView.NO_POSITION){
                    mlistner.onitemclick(position);
                }
            }
        }
    });
}
    }

}
