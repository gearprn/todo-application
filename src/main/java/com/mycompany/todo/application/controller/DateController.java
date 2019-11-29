/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.todo.application.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateController {
    private String dateFormat;
    private String docFormat;
    
    public String getDateFormat(int num) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, num);
        Date date = c.getTime();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE d MMM", Locale.US);
        return dateFormat.format(date);
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getDocFormat(int num) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, num);
        Date date = c.getTime();
        SimpleDateFormat documentFormat = new SimpleDateFormat("dMMMYY", Locale.US);
        return documentFormat.format(date);
    }

    public void setDocFormat(String docFormat) {
        this.docFormat = docFormat;
    }
    
    public int setDate(int today) {
        return today;
    }
}
