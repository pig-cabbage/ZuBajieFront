package com.example.a63577.myapplication;



import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class collection_adapter extends RecyclerView.Adapter<collection_adapter.ViewHolder> {
    private List<Item> item_list;
    private Context mcontext;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View itemView;
        ImageView card_image;
        TextView card_name;
        TextView card_price;
        TextView card_type;
        public ViewHolder(View view)
        {super(view);
            itemView=view;
            card_image=(ImageView)view.findViewById(R.id.card_image);
            card_name =(TextView) view.findViewById(R.id.card_name);
            card_price =(TextView) view.findViewById(R.id.card_price);
            card_type =(TextView) view.findViewById(R.id.card_type);
        }
    }
    public collection_adapter(List<Item> iitem_list){item_list=iitem_list;}
    public  ViewHolder onCreateViewHolder(ViewGroup parent,int viewtype)
    {
        if(mcontext==null)
            mcontext=parent.getContext();
        View view=LayoutInflater.from(mcontext).inflate(R.layout.card_display_collection_order,parent,false);
        final ViewHolder hholder=new ViewHolder(view);
        hholder.itemView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int position=hholder.getAdapterPosition();
                Item item1=item_list.get(position);
                Intent intent=new Intent(mcontext,item_detail_page_Activity.class);
                intent.putExtra("item",item1);
                mcontext.startActivity(intent);
            }
        });
        return  hholder;
    }
    public void onBindViewHolder(ViewHolder holder,int position)
    {
        Item iitem=item_list.get(position);
        if(iitem.type==0)
            holder.card_type.setText("借入");
        else
            holder.card_type.setText("借出");
        Glide.with(mcontext).load(iitem.Imageid.get(0)).into(holder.card_image);
        holder.card_name.setText(iitem.title);
        holder.card_price.setText(iitem.price);
    }
    public int getItemCount(){return item_list.size();}
}
