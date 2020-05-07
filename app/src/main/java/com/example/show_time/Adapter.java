package com.example.show_time;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.Adapter_viewholder> {

    private Context context;
    private ArrayList<Access_data> list;
    private OnItemClickListner itemClickListner;


    //create this interface for click functionality
    public interface OnItemClickListner {
        public void onItemclick(int position,View view);
    }

    //this method is used just to pass the object of Onitemclicklisterner to the local variable
    public void setOnClickListner(OnItemClickListner itemClickListner) {

        this.itemClickListner = itemClickListner;

    }

    public Adapter(Context context, ArrayList<Access_data> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public Adapter_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.data_layout, parent, false);


        return new Adapter_viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_viewholder holder, int position) {

        Access_data i = list.get(position);
        String movie_imageUrl = i.getMovie_image_url();
        String movie_name = i.getMovie_name();


        holder.movie_name.setText(movie_name);
        Picasso.get().load(movie_imageUrl).fit().centerInside().into(holder.movie_image);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class Adapter_viewholder extends RecyclerView.ViewHolder {
        ImageView movie_image;
        TextView movie_name;


        public Adapter_viewholder(@NonNull View itemView) {
            super(itemView);
            movie_image = itemView.findViewById(R.id.movie_image);
            movie_name = itemView.findViewById(R.id.movie_name);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemClickListner != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            itemClickListner.onItemclick(position,view);
                        }


                    }
                }
            });


        }
    }


}
