package com.example.a63577.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.a63577.myapplication.Entity.AndroidOrder;
import com.example.a63577.myapplication.Entity.Item;

import java.util.List;

public class order_adapter extends RecyclerView.Adapter<order_adapter.ViewHolder> {
    private List<AndroidOrder> item_list;
    private Context mcontext;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View itemView;
        ImageView card_image;
        TextView card_name;
        TextView card_price;
        TextView card_type;
        RecyclerView recycle;
        public ViewHolder(View view)
        {super(view);
            itemView=view;
            card_image=(ImageView)view.findViewById(R.id.card_image);
            card_name =(TextView) view.findViewById(R.id.card_name);
            card_price =(TextView) view.findViewById(R.id.card_price);
            card_type =(TextView) view.findViewById(R.id.card_type);
            recycle = (RecyclerView) view.findViewById(R.id.item_display_order);
        }
    }
    public order_adapter(List<AndroidOrder> iitem_list){
        System.out.println("33333333");
        item_list=iitem_list;}
    public  ViewHolder onCreateViewHolder(ViewGroup parent,int viewtype)
    {
        if(mcontext==null)
            mcontext=parent.getContext();
            final View view=LayoutInflater.from(mcontext).inflate(R.layout.card_display_collection_order,parent,false);
            final ViewHolder hholder=new ViewHolder(view);
            hholder.itemView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int position=hholder.getAdapterPosition();
                AndroidOrder item1=item_list.get(position);
                Intent intent=new Intent(mcontext,item_detail_page_Activity.class);
                intent.putExtra("item",item1);
                mcontext.startActivity(intent);
            }
        });
        hholder.itemView.setOnLongClickListener(new View.OnLongClickListener() {


            @Override
            public boolean onLongClick(View v) {

                   view.setVisibility(View.GONE);
                   return true;
            }
        });



        return  hholder;
    }
    public void onBindViewHolder(ViewHolder holder,int position)
    {
        AndroidOrder iitem=item_list.get(position);
        if(iitem.isBorrow()==true)
            holder.card_type.setText("借入");
        else
            holder.card_type.setText("借出");
        Glide.with(mcontext).load("http://"+iitem.getImageList().get(0)).into(holder.card_image);
        holder.card_name.setText(iitem.getTitle());
        holder.card_price.setText(iitem.getPrice());
    }
    public int getItemCount(){return item_list.size();}
}

