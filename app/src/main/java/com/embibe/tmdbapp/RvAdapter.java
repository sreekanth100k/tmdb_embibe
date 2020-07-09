package com.embibe.tmdbapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.embibe.tmdbapp.service.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {

    private ArrayList<Movie> moviesArrayList;
    private Context mContext;

    // RecyclerView recyclerView;
    public RvAdapter(ArrayList<Movie> moviesListData, Context iContext) {
        this.moviesArrayList = moviesListData;
        this.mContext        = iContext;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater   =   LayoutInflater.from(parent.getContext());
        View listItem                   =   layoutInflater.inflate(R.layout.list_row_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = moviesArrayList.get(position);
        holder.textView.setText(movie.getTitle());
        String url = "https://image.tmdb.org/t/p/original/" +movie.getBackdrop_path();

        Glide.with(mContext).load(url).timeout(60000).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {


                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {


                holder.imageView.setImageDrawable(resource);

                return false;
            }

        }).into(holder.imageView);



    }


    @Override
    public int getItemCount() {
        return moviesArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView  = (ImageView) itemView.findViewById(R.id.id_iv);
            this.textView   = (TextView) itemView.findViewById(R.id.id_title_tv);
        }
    }
}
