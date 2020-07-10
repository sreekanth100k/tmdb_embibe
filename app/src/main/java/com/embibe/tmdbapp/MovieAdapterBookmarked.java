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
import androidx.core.content.ContextCompat;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.embibe.tmdbapp.db.AppDb;
import com.embibe.tmdbapp.db.BookMarkedMovie;
import com.embibe.tmdbapp.service.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapterBookmarked extends RecyclerView.Adapter<MovieAdapterBookmarked.MovieViewHolder> {
    private Context mContext;
    private ArrayList<BookMarkedMovie> mMoviesList;

    MovieAdapterBookmarked(Context mCtx, ArrayList<BookMarkedMovie> iMoviesList) {
        this.mContext       =   mCtx;
        this.mMoviesList    =   iMoviesList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater   =   LayoutInflater.from(mContext);
        View listItem                   =   layoutInflater.inflate(R.layout.list_row_item_bookmarked, parent, false);
        MovieViewHolder viewHolder      =   new MovieViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

    }


    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);

        BookMarkedMovie bookMarkedMovieObj  =   mMoviesList.get(position);


        if(bookMarkedMovieObj!=null) {

            String photoPath                    =   bookMarkedMovieObj.getPhoto_path();
            String title                        =   bookMarkedMovieObj.getTitle();

            String thumbNailUrl                 =   "https://image.tmdb.org/t/p/original/"+photoPath;

            holder.titleTv.setText(title);
            Glide.with(mContext)
                    .load(thumbNailUrl)
                    .into(holder.photoIv);

        }else {
            Toast.makeText(mContext, "Item is null", Toast.LENGTH_LONG).show();
        }
        String photoPath                    =   bookMarkedMovieObj.getPhoto_path();

        String thumbNailUrl                 =   "https://image.tmdb.org/t/p/original/"+photoPath;

        Glide.with(mContext).load(thumbNailUrl).timeout(60000).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {


                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {


                holder.photoIv.setImageDrawable(resource);

                return false;
            }

        }).into(holder.photoIv);
    }

    @Override
    public int getItemCount() {
        return mMoviesList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        TextView titleTv;
        ImageView photoIv;

        public MovieViewHolder(View itemView) {
            super(itemView);
            titleTv         =   itemView.findViewById(R.id.id_title_tv);
            photoIv         =   itemView.findViewById(R.id.id_photo_iv);
        }
    }
}
