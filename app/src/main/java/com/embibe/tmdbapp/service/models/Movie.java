package com.embibe.tmdbapp.service.models;

import java.util.ArrayList;
import java.util.List;

public class Movie {

        private double popularity;

        private int vote_count;

        private boolean video;

        private String poster_path;

        private int id;

        private boolean adult;

        private String backdrop_path;

        private String original_language;

        private String original_title;

        private List<Integer> genre_ids;

        private String title;

        private double vote_average;

        private String overview;

        private String release_date;

        public String getTitle() {
                return title;
        }


        public String getBackdrop_path() {
                return backdrop_path;
        }
}
