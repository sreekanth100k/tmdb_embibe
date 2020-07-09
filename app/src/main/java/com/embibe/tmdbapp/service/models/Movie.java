package com.embibe.tmdbapp.service.models;

import java.util.ArrayList;
import java.util.List;

public class Movie {

        private double popularity;

        private int vote_count;

        private boolean video;

        private String poster_path;

        private int id;

        public double getPopularity() {
                return popularity;
        }

        public void setPopularity(double popularity) {
                this.popularity = popularity;
        }

        public int getVote_count() {
                return vote_count;
        }

        public void setVote_count(int vote_count) {
                this.vote_count = vote_count;
        }

        public boolean isVideo() {
                return video;
        }

        public void setVideo(boolean video) {
                this.video = video;
        }

        public String getPoster_path() {
                return poster_path;
        }

        public void setPoster_path(String poster_path) {
                this.poster_path = poster_path;
        }

        public boolean isAdult() {
                return adult;
        }

        public void setAdult(boolean adult) {
                this.adult = adult;
        }

        public void setBackdrop_path(String backdrop_path) {
                this.backdrop_path = backdrop_path;
        }

        public String getOriginal_language() {
                return original_language;
        }

        public void setOriginal_language(String original_language) {
                this.original_language = original_language;
        }

        public String getOriginal_title() {
                return original_title;
        }

        public void setOriginal_title(String original_title) {
                this.original_title = original_title;
        }

        public List<Integer> getGenre_ids() {
                return genre_ids;
        }

        public void setGenre_ids(List<Integer> genre_ids) {
                this.genre_ids = genre_ids;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public double getVote_average() {
                return vote_average;
        }

        public void setVote_average(double vote_average) {
                this.vote_average = vote_average;
        }

        public String getOverview() {
                return overview;
        }

        public void setOverview(String overview) {
                this.overview = overview;
        }

        public String getRelease_date() {
                return release_date;
        }

        public void setRelease_date(String release_date) {
                this.release_date = release_date;
        }

        private boolean adult;

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

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

        // Overriding equals() to compare two Complex objects
        @Override
        public boolean equals(Object o) {

                // If the object is compared with itself then return true
                if (o == this) {
                        return true;
                }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
                if (!(o instanceof Movie)) {
                        return false;
                }

                // typecast o to Complex so that we can compare data members
                Movie c = (Movie) o;

                if(c.getTitle().equals(title)){
                        if(c.getBackdrop_path().equals(backdrop_path)){
                                if(popularity == c.getPopularity()) {
                                        if (vote_count == c.getVote_count()) {
                                                if (video == c.video) {
                                                        if (poster_path.equals(c.poster_path)) {
                                                                if (original_language.equals(c.original_language)) {
                                                                        if (original_title.equals(c.original_title)) {
                                                                                if (vote_average == c.vote_average) {
                                                                                        if (overview == c.overview) {
                                                                                                if (release_date == c.release_date) {
                                                                                                        if (genre_ids.equals(c.genre_ids)) {
                                                                                                                return true;
                                                                                                        }

                                                                                                }

                                                                                        }
                                                                                }

                                                                        }

                                                                }

                                                        }
                                                }
                                        }
                                }

                        }
                }
                return false;
        }
}
