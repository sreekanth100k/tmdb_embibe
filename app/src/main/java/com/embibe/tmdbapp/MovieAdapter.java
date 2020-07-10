package com.embibe.tmdbapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
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
import com.embibe.tmdbapp.db.BookMarkedPhotos;
import com.embibe.tmdbapp.service.models.Movie;

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
                    .into(holder.photoIv);
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


                holder.photoIv.setImageDrawable(resource);

                return false;
            }

        }).into(holder.photoIv);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                Drawable bookMarkSelected = ContextCompat.getDrawable(mContext, R.drawable.ic_id_bookmark_selected);

                final int sdk = android.os.Build.VERSION.SDK_INT;
                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    holder.bookMarkIv.setBackgroundDrawable( bookMarkSelected );
                } else {
                    holder.bookMarkIv.setBackground( bookMarkSelected);
                }

                Movie movieObj = getItem(position);


                BookMarkedPhotos movie = new BookMarkedPhotos(String.valueOf(movieObj.getId()),movieObj.getTitle(),movieObj.getPoster_path());
                AppDb.getInMemoryDatabase(mContext).BookMarkedPhotosMappingDAO().insertResponse(movie);


            }
        };

        Drawable bookMarkUnSelected = ContextCompat.getDrawable(mContext, R.drawable.ic_id_bookmark_unselected);

        final int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            holder.bookMarkIv.setBackgroundDrawable( bookMarkUnSelected );
        } else {
            holder.bookMarkIv.setBackground( bookMarkUnSelected);
        }


        holder.bookMarkIv.setOnClickListener(onClickListener);
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
        ImageView photoIv;
        ImageView bookMarkIv;

        public MovieViewHolder(View itemView) {
            super(itemView);
            titleTv         =   itemView.findViewById(R.id.id_title_tv);
            photoIv         =   itemView.findViewById(R.id.id_photo_iv);
            bookMarkIv      =   itemView.findViewById(R.id.id_book_mark_iv);
        }
    }
}
