package com.embibe.tmdbapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.embibe.tmdbapp.service.models.Movie;

import java.util.ArrayList;

public class MovieAdapter extends PagedListAdapter<Movie, MovieAdapter.MovieViewHolder> {
    private Context mContext;

    MovieAdapter(Context mCtx) {
        super(DIFF_CALLBACK);
        this.mContext = mCtx;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater   =   LayoutInflater.from(mContext);
        View listItem                   =   layoutInflater.inflate(R.layout.list_row_item, parent, false);
        MovieViewHolder viewHolder      =   new MovieViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        Movie movieObj = getItem(position);

        if(movieObj!=null){
            holder.titleTv.setText(movieObj.getTitle());

            String thumbNailUrl = "https://image.tmdb.org/t/p/original/" +movieObj.getPoster_path();

            Glide.with(mContext)
                    .load(thumbNailUrl)
                    .into(holder.imageView);
        }else{
            Toast.makeText(mContext, "Item is null", Toast.LENGTH_LONG).show();
        }
        String thumbNailUrl = "https://image.tmdb.org/t/p/original/" +movieObj.getPoster_path();

        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(mContext);
        circularProgressDrawable.setStrokeWidth(5f);
        circularProgressDrawable.setCenterRadius(30f);
        circularProgressDrawable.start();

        Glide.with(mContext).load(thumbNailUrl).timeout(60000).listener(new RequestListener<Drawable>() {
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
        return super.getItemCount();
    }

    private static DiffUtil.ItemCallback<Movie> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Movie>() {
                @Override
                public boolean areItemsTheSame(Movie oldItem, Movie newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(Movie oldItem, Movie newItem) {
                    return newItem.equals(oldItem);
                }
            };

    class MovieViewHolder extends RecyclerView.ViewHolder {

        TextView titleTv;
        ImageView imageView;

        public MovieViewHolder(View itemView) {
            super(itemView);
            titleTv         =   itemView.findViewById(R.id.id_title_tv);
            imageView       =   itemView.findViewById(R.id.id_iv);
        }
    }
}
