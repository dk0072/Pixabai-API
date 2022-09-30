package com.nogravity.pixabaytest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    List<Model> list;
    SharedPreferences myPrefs;

    ArrayList<String> likedList = new ArrayList<>();

    public Adapter(Context context, List<Model> list) {
        this.context = context;
        this.list = list;
    }





    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {


        Model model = list.get(position);
        holder.setImageView(model.getImgURL());
        holder.setLikes(model.getLikes());
        holder.setDownload(model.getDownload());

        myPrefs = context.getSharedPreferences("likedData",Context.MODE_PRIVATE);

            Gson gson = new Gson();
            String json = myPrefs.getString("liked",null);
            Type type = new TypeToken<ArrayList<String>>(){}.getType();
            likedList = gson.fromJson(json,type);

            if(likedList!=null && position < likedList.size()){

                Toast.makeText(context, likedList.size()+"", Toast.LENGTH_SHORT).show();


                if(likedList.get(position).equals("checked"))
                    holder.like_unlike.setImageDrawable(context.getResources().getDrawable(R.drawable.liked_icon));
                else
                    holder.like_unlike.setImageDrawable(context.getResources().getDrawable(R.drawable.unliked_icon));
        }else{
                likedList = new ArrayList<>();
            }






        holder.like_unlike.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "Liked", Toast.LENGTH_SHORT).show();
                holder.like_unlike.setImageDrawable(context.getResources().getDrawable(R.drawable.liked_icon));

                likedList.add("liked");

                SharedPreferences.Editor editor = myPrefs.edit();

                Gson gson = new Gson();
                String json = gson.toJson(likedList);
                editor.putString("liked",json);
                editor.apply();

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView likes,downloads;
        View view;
        ImageView like_unlike;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            like_unlike = itemView.findViewById(R.id.loved);

        }
        public void setImageView(String url){
            imageView = view.findViewById(R.id.imageview);
            Glide.with(context).load(url).into(imageView);
        }
        public void setLikes(int like){
            likes = view.findViewById(R.id.like);
            likes.setText("Likes : " + like);
        }

        public void setDownload(int download){
            downloads = view.findViewById(R.id.download);
            downloads.setText("Downloads : "+download);
        }
    }
}
