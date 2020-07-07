package com.embibe.tmdbapp.service.models;

public class Date {

    private String maximum;

    private String minimum;

    public void setMaximum(String maximum){
        this.maximum = maximum;
    }
    public String getMaximum(){
        return this.maximum;
    }
    public void setMinimum(String minimum){
        this.minimum = minimum;
    }
    public String getMinimum(){
        return this.minimum;
    }
}
