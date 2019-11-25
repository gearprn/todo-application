/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.todo.application.UI;

import com.mycompany.todo.application.controller.TaskResponse;
import java.awt.Color;
import java.awt.Cursor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class SingleProject extends javax.swing.JPanel {
    private final Todo todoapp;
    private final Color myDeepBlack = new Color(50, 50, 50);
    private final Color myLightBlack = new Color(60, 60, 60);
    
    private ArrayList<TaskResponse> task = new <TaskResponse>ArrayList();
    
    public SingleProject(Todo mainUI) {
        initComponents();
        this.todoapp = mainUI;
    }
    
    public SingleProject(Todo mainUI, String name) {
        initComponents();
        this.todoapp = mainUI;
        jTextField1.setText(name);
        jTextField1.setEditable(false);
        jTextField1.setBackground(myDeepBlack);
        jLabel1.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(50, 50, 50));
        setMaximumSize(new java.awt.Dimension(234, 34));
        setMinimumSize(new java.awt.Dimension(214, 34));
        setPreferredSize(new java.awt.Dimension(214, 34));

        jTextField1.setBackground(new java.awt.Color(60, 60, 60));
        jTextField1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(227, 227, 227));
        jTextField1.setText("Project name");
        jTextField1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 7, 1, 1));
        jTextField1.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextField1.setMaximumSize(new java.awt.Dimension(234, 21));
        jTextField1.setMinimumSize(new java.awt.Dimension(0, 0));
        jTextField1.setPreferredSize(new java.awt.Dimension(234, 21));
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField1MouseEntered(evt);
            }
        });

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
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel1))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        jTextField1.setEditable(false);
        jTextField1.setBackground(myDeepBlack);
        jLabel1.setText("");
        try {
            todoapp.getTaskController().addProject(todoapp.getUser().getEmail(), jTextField1.getText());
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(SingleProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        jLabel1.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseClicked
        todoapp.setProject(jTextField1.getText());
        todoapp.getjLabel22().setText(jTextField1.getText());
        
        todoapp.getjPanel4().removeAll();
        todoapp.getjPanel4().repaint();
        todoapp.getjPanel4().revalidate();
        
        todoapp.getjPanel17().removeAll();
        todoapp.getjPanel17().repaint();
        todoapp.getjPanel17().revalidate();
        
        todoapp.getjPanel4().add(todoapp.getjPanel7());
        todoapp.getjPanel4().repaint();
        todoapp.getjPanel4().revalidate();
        
        try {
            // get project task down here
            task = todoapp.getTaskController().getProjectTasks(todoapp.getUser().getEmail(), jTextField1.getText());
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(SingleProject.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int counter = 0; counter < task.size(); counter++) { 		      
            todoapp.getjPanel17().add(new SingleTask(task.get(counter).getTask(), todoapp, Boolean.parseBoolean(task.get(counter).getFistTimeEdit()), task.get(counter).getId()), 0);
            todoapp.getjPanel17().revalidate();
            todoapp.getjPanel17().repaint();
        }
    }//GEN-LAST:event_jTextField1MouseClicked

    private void jTextField1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseEntered
        jTextField1.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jTextField1MouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
