/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.todo.application.controller;

/**
 *
 * @author Uomrod-PC
 */
public class TaskResponse {
    private String id;
    private String task;
    private String isDone;
    private String date;
    private String fistTimeEdit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFistTimeEdit() {
        return fistTimeEdit;
    }

    public void setFistTimeEdit(String fistTimeEdit) {
        this.fistTimeEdit = fistTimeEdit;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getIsDone() {
        return isDone;
    }

    public void setIsDone(String isDone) {
        this.isDone = isDone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
