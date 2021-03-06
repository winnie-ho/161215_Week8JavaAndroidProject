package com.example.user.runtrack;

import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.sql.Time;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * Created by user on 16/12/2016.
 */
public class Run {
    private int id;
    private String title;
    private int day;
    private int month;
    private int year;
    private float distance;
    private float hours;
    private float minutes;
    private float seconds;
    private float pace;
    private String type;
    private String comment;


    //Full Run Constructor for DB
    public Run(int id, String title, int day, int month, int year, float distance, float hours, float minutes, float seconds, String type, String comment){
        this.id = id;
        this.title = title;
        this.day = day;
        this.month = month;
        this.year = year;
        this.distance = distance;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.type = type;
        this.comment = comment;
    }

    //Full Run Constructor
    public Run(String title, int day, int month, int year, float distance, float hours, float minutes, float seconds, String type, String comment){
        this.title = title;
        this.day = day;
        this.month = month;
        this.year = year;
        this.distance = distance;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.type = type;
        this.comment = comment;
    }

    //Simple Run Constructor
    public Run(String title, float distance){
        this.title = title;
        this.distance = distance;
    }


    //Getters
    public int getId(){
        return this.id;
    }

    public String getRunTitle(){
        return this.title;
    }

    public int getDay() {
        return this.day;
    }

    public int getMonth() {
        return this.month;
    }

    public int getYear() {
        return this.year;
    }

    public String getDate(){
        return this.day + "/" + this.month + "/" + this.year;
    }

    public float getDistance(){
        return this.distance;
    }

    public float getHours(){
        return this.hours;
    }

    public float getMinutes(){
        return this.minutes;

    }
    public float getSeconds(){
        return this.seconds;
    }

    public String getTime(){

        String seconds = null;
        if (this.seconds < 10) {
            seconds = "0" + String.format("%.0f",this.seconds);
        } else if (this.seconds >= 10) {
            seconds = String.format("%.0f",this.seconds) + "";
        }

        String minutes = null;
        if (this.minutes < 10) {
            minutes = "0" + String.format("%.0f",this.minutes);
        } else if (this.minutes >= 10) {
            minutes = String.format("%.0f",this.minutes)+ "";
        }

        String hours = null;
        if (this.hours <10) {
            hours = "0" + String.format("%.0f",this.hours);
        } else if (this.hours >= 10) {
            hours = String.format("%.0f",this.hours) + "";
        }
        return (hours + ":" + minutes + ":" + seconds);

    }

    public float getPace(){
        this.pace = ((this.hours * 60)+this.minutes + (this.seconds/60))/ this.distance;
        return this.pace;
    }

    public String getType(){
        return this.type;
    }

    public String getComment(){
        return this.comment;
    }
    //Methods


}
