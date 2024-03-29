/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.todo.application.UI;

import com.mycompany.todo.application.controller.DateController;
import java.awt.Color;
import java.awt.Cursor;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class SingleTask extends javax.swing.JPanel {
    private final Todo todoapp;
    private final DateController dateController = new DateController();
    private final Color myDeepBlack = new Color(31, 31, 31);
    private final Color myLightBlack = new Color(50, 50, 50);
    private String docId;
    private int date; // today == 0, next day == 1
    private boolean isFirstEdit = true;
    private boolean isTaskDone = false;
    /**
     * Creates new form SingleTask
     */
    
    public SingleTask(String task, int numDate, Todo mainUI) {
        initComponents();
        this.date = numDate;
        this.isFirstEdit = true;
        this.todoapp = mainUI;
        this.todoapp.getjLabel9().setText("Database: ");
        
        jTextField1.setText(task);
        jLabel2.setIcon(new ImageIcon("./assets/line.png"));
    }

    public SingleTask(String task, Todo mainUI, boolean firstTimeEdit, String id) {
        initComponents();
        this.isFirstEdit = firstTimeEdit;
        this.todoapp = mainUI;
        this.todoapp.getjLabel9().setText("Database: ");
        
        if (!firstTimeEdit) {
            this.docId = id;
            this.isTaskDone = false;
        }
        
        jTextField1.setText(task);
        jTextField1.setEditable(false);
        jTextField1.setBackground(myDeepBlack);
        jLabel2.setIcon(new ImageIcon("./assets/line.png"));
        jLabel1.setText("Edit");        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(31, 31, 31));
        setMaximumSize(new java.awt.Dimension(660, 72));
        setPreferredSize(new java.awt.Dimension(660, 80));

        jPanel1.setBackground(new java.awt.Color(31, 31, 31));

        jTextField1.setBackground(new java.awt.Color(50, 50, 50));
        jTextField1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(227, 227, 227));
        jTextField1.setText("Add your new task here!");
        jTextField1.setBorder(javax.swing.BorderFactory.createEmptyBorder(7, 7, 7, 7));
        jTextField1.setCaretColor(new java.awt.Color(227, 227, 227));

        jLabel3.setForeground(new java.awt.Color(227, 227, 227));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3))
        );

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(227, 227, 227));
        jLabel1.setText("Save");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addGap(17, 17, 17)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(6, 6, 6)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        if (jLabel1.getText().equals("Save")) {
            jTextField1.setEditable(false);
            jTextField1.setBackground(myDeepBlack);
            jLabel1.setText("Edit");

            if (this.isFirstEdit) {
                // add new task
                try {
                    
                    if (todoapp.getProject().equals("notSelectProjectYet")) {
                        this.docId = todoapp.getTaskController().addTask(todoapp.getUser().getEmail(), jTextField1.getText(), dateController.getDocFormat(this.date));
                    } else {
                        this.docId = todoapp.getTaskController().addProjectTask(todoapp.getUser().getEmail(), todoapp.getProject(), jTextField1.getText());
                    }
                    
                    
                } catch (IOException | InterruptedException | ExecutionException ex) {
                    Logger.getLogger(SingleTask.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.isFirstEdit = false;
                
            } else {
                // update task
                try {
                    if (todoapp.getProject().equals("notSelectProjectYet")) {
                        todoapp.getTaskController().updateTask(todoapp.getUser().getEmail(), this.docId, jTextField1.getText());
                    } else {
                        todoapp.getTaskController().updateProjectTask(todoapp.getUser().getEmail(), this.docId, jTextField1.getText());
                    }
                } catch (InterruptedException | ExecutionException ex) {
                    Logger.getLogger(SingleTask.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            todoapp.getjLabel9().setText("Database: save");
        } else {
            jTextField1.setEditable(true);
            jTextField1.setBackground(myLightBlack);
            jLabel1.setText("Save");
            todoapp.getjLabel9().setText("Database:");
        }
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        jLabel1.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jLabel1MouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
