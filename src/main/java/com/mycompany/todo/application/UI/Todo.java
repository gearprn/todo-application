/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.todo.application.UI;

import com.mycompany.todo.application.controller.DateController;
import com.mycompany.todo.application.controller.TaskController;
import com.mycompany.todo.application.controller.TaskResponse;
import com.mycompany.todo.application.controller.UserController;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

/**
 *
 * @author UomrodPC
 */
public class Todo extends javax.swing.JFrame {
    private int posX = 0, posY = 0;
    private int date; // 0 == today, 1 == next1day, 2 == next3day ..... and more
    private String project; // select project
    
    private ArrayList<TaskResponse> task = new <TaskResponse>ArrayList();
    private ArrayList<String> projectName = new <TaskResponse>ArrayList();

    private DateController dateController = new DateController();
    private UserController user = new UserController();
    private TaskController taskController;

    public TaskController getTaskController() {
        return taskController;
    }

    public void setTaskController(TaskController taskController) {
        this.taskController = taskController;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public UserController getUser() {
        return user;
    }

    public void setUser(UserController user) {
        this.user = user;
    }

    public JLabel getjLabel22() {
        return jLabel22;
    }

    public void setjLabel22(JLabel jLabel22) {
        this.jLabel22 = jLabel22;
    }

    public JPanel getjPanel4() {
        return jPanel4;
    }

    public void setjPanel4(JPanel jPanel4) {
        this.jPanel4 = jPanel4;
    }

    public JPanel getjPanel7() {
        return jPanel7;
    }

    public void setjPanel7(JPanel jPanel7) {
        this.jPanel7 = jPanel7;
    }

    public JPanel getjPanel17() {
        return jPanel17;
    }

    public void setjPanel17(JPanel jPanel17) {
        this.jPanel17 = jPanel17;
    }
    
    public JLabel getjLabel9() {
        return jLabel9;
    }

    public void setjLabel9(JLabel jLabel9) {
        this.jLabel9 = jLabel9;
    }

    /**
     * Creates new form Todo
     */
    public Todo(String email) throws InterruptedException, ExecutionException, IOException {
        initComponents();
        this.setResizable(false);
        this.setSize(1000, 700);
        
        user.setEmail(email);
        jLabel7.setIcon(new ImageIcon("./assets/redLind.png"));
        jLabel10.setIcon(new ImageIcon("./assets/redLind.png"));
        jLabel24.setIcon(new ImageIcon("./assets/redLind.png"));
        jLabel10.setIcon(new ImageIcon("./assets/redLind.png"));
        jLabel8.setIcon(new ImageIcon("./assets/addButton.png"));
        
        jLabel16.setIcon(new ImageIcon("./assets/addButton.png"));
        jLabel23.setIcon(new ImageIcon("./assets/addButton.png"));

        this.taskController = new TaskController();
        
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
        
        this.date = 0;
        jLabel6.setText(dateController.getDateFormat(0));
        jLabel11.setText("        "+dateController.getDateFormat(1));
        jLabel12.setText("        "+dateController.getDateFormat(2));
        jLabel13.setText("        "+dateController.getDateFormat(3));
        jLabel15.setText("        "+dateController.getDateFormat(4));
        jLabel17.setText("        "+dateController.getDateFormat(5));
        jLabel18.setText("        "+dateController.getDateFormat(6));
        jLabel19.setText("        "+dateController.getDateFormat(7));
        
        jPanel10.setLayout(new BoxLayout(jPanel10, BoxLayout.Y_AXIS));
        jPanel12.setLayout(new BoxLayout(jPanel12, BoxLayout.Y_AXIS));
        jPanel14.setLayout(new BoxLayout(jPanel14, BoxLayout.Y_AXIS));
        jPanel17.setLayout(new BoxLayout(jPanel17, BoxLayout.Y_AXIS));
        
        jPanel10.add(Box.createVerticalGlue());
        jPanel12.add(Box.createVerticalGlue());
        jPanel14.add(Box.createVerticalGlue());
        jPanel17.add(Box.createVerticalGlue());
        
        JScrollBar scrollBar1 = new JScrollBar(JScrollBar.VERTICAL) {
            @Override
            public boolean isVisible() {
                return true;
            }
        };
        
        JScrollBar scrollBar2 = new JScrollBar(JScrollBar.VERTICAL) {
            @Override
            public boolean isVisible() {
                return true;
            }
        };
        
        JScrollBar scrollBar3 = new JScrollBar(JScrollBar.VERTICAL) {
            @Override
            public boolean isVisible() {
                return true;
            }
        };
        
        JScrollBar scrollBar4 = new JScrollBar(JScrollBar.VERTICAL) {
            @Override
            public boolean isVisible() {
                return true;
            }
        };

        jScrollPane1.setVerticalScrollBar(scrollBar1);
        jScrollPane2.setVerticalScrollBar(scrollBar2);
        jScrollPane3.setVerticalScrollBar(scrollBar3);
        jScrollPane3.setVerticalScrollBar(scrollBar4);
        
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane4.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        
        task = taskController.getTask(user.getEmail(), dateController.getDocFormat(0));
        for (int counter = 0; counter < task.size(); counter++) { 		      
            jPanel10.add(new SingleTask(task.get(counter).getTask(), this, Boolean.parseBoolean(task.get(counter).getFistTimeEdit()), task.get(counter).getId()), 0);
            jPanel10.revalidate();
            jPanel10.repaint();
        }
        
        projectName = taskController.getProjects(user.getEmail());
        for (int counter = 0; counter < projectName.size(); counter++) { 		      
            jPanel14.add(new SingleProject(this, projectName.get(counter).toString()), 0);
            jPanel14.revalidate();
            jPanel14.repaint();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel14 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel10 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel12 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel17 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(31, 31, 31));
        jPanel1.setPreferredSize(new java.awt.Dimension(1002, 705));

        jPanel2.setBackground(new java.awt.Color(40, 40, 40));
        jPanel2.setPreferredSize(new java.awt.Dimension(1000, 30));
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
        });
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(227, 227, 227));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("X");
        jLabel1.setPreferredSize(new java.awt.Dimension(30, 30));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(50, 50, 50));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(227, 227, 227));
        jLabel2.setText("   Today");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(227, 227, 227));
        jLabel3.setText("   Next 7 days");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(227, 227, 227));
        jLabel4.setText("   Project");

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(227, 227, 227));
        jLabel9.setText("Database :");

        jLabel11.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(227, 227, 227));
        jLabel11.setText("        next1day");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel11MouseEntered(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(227, 227, 227));
        jLabel12.setText("        next2day");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel12MouseEntered(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(227, 227, 227));
        jLabel13.setText("        next3day");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel13MouseEntered(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(227, 227, 227));
        jLabel15.setText("        next4day");
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel15MouseEntered(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(227, 227, 227));
        jLabel17.setText("        next5day");
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel17MouseEntered(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(227, 227, 227));
        jLabel18.setText("        next6day");
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel18MouseEntered(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(227, 227, 227));
        jLabel19.setText("        next7day");
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel19MouseEntered(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("SansSerif", 3, 11)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(227, 227, 227));
        jLabel21.setText("Create new project");
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel21MouseEntered(evt);
            }
        });

        jScrollPane3.setBorder(null);
        jScrollPane3.setMinimumSize(new java.awt.Dimension(234, 23));

        jPanel14.setBackground(new java.awt.Color(50, 50, 50));
        jPanel14.setMinimumSize(new java.awt.Dimension(232, 100));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 234, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 271, Short.MAX_VALUE)
        );

        jScrollPane3.setViewportView(jPanel14);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel21)
                        .addGap(61, 61, 61))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(31, 31, 31));
        jPanel4.setLayout(new java.awt.CardLayout());

        jPanel5.setBackground(new java.awt.Color(31, 31, 31));
        jPanel5.setMinimumSize(new java.awt.Dimension(682, 653));

        jPanel8.setBackground(new java.awt.Color(31, 31, 31));

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(227, 227, 227));
        jLabel5.setText("Today");

        jLabel6.setFont(new java.awt.Font("SansSerif", 2, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(227, 227, 227));
        jLabel6.setText("date config");

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(227, 227, 227));
        jLabel8.setText("Add Task");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel8MouseEntered(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8))
                .addGap(0, 0, 0))
        );

        jPanel9.setBackground(new java.awt.Color(31, 31, 31));
        jPanel9.setPreferredSize(new java.awt.Dimension(682, 11));

        jLabel7.setBackground(new java.awt.Color(31, 31, 31));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 11, Short.MAX_VALUE)
        );

        jScrollPane1.setBorder(null);
        jScrollPane1.setMinimumSize(new java.awt.Dimension(682, 606));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(682, 606));

        jPanel10.setBackground(new java.awt.Color(31, 31, 31));
        jPanel10.setMaximumSize(new java.awt.Dimension(682, 606));
        jPanel10.setPreferredSize(new java.awt.Dimension(682, 606));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 682, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 617, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel10);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 723, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4.add(jPanel5, "card2");

        jPanel6.setBackground(new java.awt.Color(31, 31, 31));
        jPanel6.setMinimumSize(new java.awt.Dimension(682, 653));
        jPanel6.setPreferredSize(new java.awt.Dimension(682, 653));

        jPanel13.setBackground(new java.awt.Color(31, 31, 31));

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(227, 227, 227));
        jLabel14.setText("Next 7 day");

        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(227, 227, 227));
        jLabel16.setText("Add Task");
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel16MouseEntered(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("SansSerif", 2, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(227, 227, 227));
        jLabel20.setText("date");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16)
                    .addComponent(jLabel20))
                .addGap(0, 0, 0))
        );

        jPanel11.setBackground(new java.awt.Color(31, 31, 31));

        jLabel10.setBackground(new java.awt.Color(31, 31, 31));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 11, Short.MAX_VALUE)
        );

        jScrollPane2.setBackground(new java.awt.Color(31, 31, 31));
        jScrollPane2.setBorder(null);

        jPanel12.setBackground(new java.awt.Color(31, 31, 31));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 686, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 630, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(jPanel12);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.add(jPanel6, "card2");

        jPanel7.setBackground(new java.awt.Color(31, 31, 31));
        jPanel7.setMaximumSize(new java.awt.Dimension(682, 653));
        jPanel7.setMinimumSize(new java.awt.Dimension(682, 653));
        jPanel7.setPreferredSize(new java.awt.Dimension(682, 653));

        jPanel15.setBackground(new java.awt.Color(31, 31, 31));

        jLabel22.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(227, 227, 227));
        jLabel22.setText("Project");

        jLabel23.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(227, 227, 227));
        jLabel23.setText("Add Task");
        jLabel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel23MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel23MouseEntered(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel23)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23))
                .addGap(0, 0, 0))
        );

        jPanel16.setBackground(new java.awt.Color(31, 31, 31));

        jLabel24.setBackground(new java.awt.Color(31, 31, 31));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jScrollPane4.setBackground(new java.awt.Color(31, 31, 31));
        jScrollPane4.setBorder(null);

        jPanel17.setBackground(new java.awt.Color(31, 31, 31));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 686, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 630, Short.MAX_VALUE)
        );

        jScrollPane4.setViewportView(jPanel17);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.add(jPanel7, "card2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1003, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1003, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged
        setLocation(evt.getXOnScreen() - posX, evt.getYOnScreen() - posY);
    }//GEN-LAST:event_jPanel2MouseDragged

    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
        posX = evt.getX();
        posY = evt.getY();
    }//GEN-LAST:event_jPanel2MousePressed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        jPanel4.removeAll();
        jPanel4.repaint();
        jPanel4.revalidate();
        
        jPanel10.removeAll();
        jPanel10.repaint();
        jPanel10.revalidate();
        
        jPanel4.add(jPanel5);
        jPanel4.repaint();
        jPanel4.revalidate();
        
        this.date = 0;
        this.project = "notSelectProjectYet";
        jLabel6.setText(dateController.getDateFormat(0));
        
        try {
            task = taskController.getTask(user.getEmail(), dateController.getDocFormat(0));
            System.out.println("Size: " + task.size());
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(Todo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int counter = 0; counter < task.size(); counter++) { 		      
            jPanel10.add(new SingleTask(task.get(counter).getTask(), this, Boolean.parseBoolean(task.get(counter).getFistTimeEdit()), task.get(counter).getId()), 0);
            jPanel10.revalidate();
            jPanel10.repaint();
        }
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered
        jLabel2.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jLabel2MouseEntered

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        jLabel1.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        jLabel9.setText("Database :");
        jPanel10.add(new SingleTask("Add your new task here!", 0, this), 0);
        jPanel10.revalidate();
        jPanel10.repaint();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseEntered
        jLabel8.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jLabel8MouseEntered

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        jLabel9.setText("Database :");
        jPanel12.add(new SingleTask("Add your new task here!", this.date, this), 0);
        jPanel12.revalidate();
        jPanel12.repaint();
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jLabel16MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseEntered
        jLabel16.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jLabel16MouseEntered

    private void jLabel11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseEntered
        jLabel11.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jLabel11MouseEntered

    private void jLabel12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseEntered
        jLabel12.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jLabel12MouseEntered

    private void jLabel13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseEntered
        jLabel13.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jLabel13MouseEntered

    private void jLabel15MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseEntered
        jLabel15.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jLabel15MouseEntered

    private void jLabel17MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseEntered
        jLabel17.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jLabel17MouseEntered

    private void jLabel18MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseEntered
        jLabel18.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jLabel18MouseEntered

    private void jLabel19MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseEntered
        jLabel19.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jLabel19MouseEntered

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        jPanel4.removeAll();
        jPanel4.repaint();
        jPanel4.revalidate();
        
        jPanel12.removeAll();
        jPanel12.repaint();
        jPanel12.revalidate();
        
        jPanel4.add(jPanel6);
        jPanel4.repaint();
        jPanel4.revalidate();

        this.date = 1;
        this.project = "notSelectProjectYet";
        jLabel20.setText(dateController.getDateFormat(1));
        
        try {
            task = taskController.getTask(user.getEmail(), dateController.getDocFormat(1));
            System.out.println("Size: " + task.size());
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(Todo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int counter = 0; counter < task.size(); counter++) { 		      
            jPanel12.add(new SingleTask(task.get(counter).getTask(), this, Boolean.parseBoolean(task.get(counter).getFistTimeEdit()), task.get(counter).getId()), 0);
            jPanel12.revalidate();
            jPanel12.repaint();
        }
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        jPanel4.removeAll();
        jPanel4.repaint();
        jPanel4.revalidate();
        
        jPanel12.removeAll();
        jPanel12.repaint();
        jPanel12.revalidate();
        
        jPanel4.add(jPanel6);
        jPanel4.repaint();
        jPanel4.revalidate();

        this.date = 2;
        this.project = "notSelectProjectYet";
        jLabel20.setText(dateController.getDateFormat(2));
        
        try {
            task = taskController.getTask(user.getEmail(), dateController.getDocFormat(2));
            System.out.println("Size: " + task.size());
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(Todo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int counter = 0; counter < task.size(); counter++) { 		      
            jPanel12.add(new SingleTask(task.get(counter).getTask(), this, Boolean.parseBoolean(task.get(counter).getFistTimeEdit()), task.get(counter).getId()), 0);
            jPanel12.revalidate();
            jPanel12.repaint();
        }
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        jPanel4.removeAll();
        jPanel4.repaint();
        jPanel4.revalidate();
        
        jPanel12.removeAll();
        jPanel12.repaint();
        jPanel12.revalidate();
        
        jPanel4.add(jPanel6);
        jPanel4.repaint();
        jPanel4.revalidate();

        this.date = 3;
        this.project = "notSelectProjectYet";
        jLabel20.setText(dateController.getDateFormat(3));
        
        try {
            task = taskController.getTask(user.getEmail(), dateController.getDocFormat(3));
            System.out.println("Size: " + task.size());
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(Todo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int counter = 0; counter < task.size(); counter++) { 		      
            jPanel12.add(new SingleTask(task.get(counter).getTask(), this, Boolean.parseBoolean(task.get(counter).getFistTimeEdit()), task.get(counter).getId()), 0);
            jPanel12.revalidate();
            jPanel12.repaint();
        }
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        jPanel4.removeAll();
        jPanel4.repaint();
        jPanel4.revalidate();
        
        jPanel12.removeAll();
        jPanel12.repaint();
        jPanel12.revalidate();
        
        jPanel4.add(jPanel6);
        jPanel4.repaint();
        jPanel4.revalidate();

        this.date = 4;
        this.project = "notSelectProjectYet";
        jLabel20.setText(dateController.getDateFormat(4));
        
        try {
            task = taskController.getTask(user.getEmail(), dateController.getDocFormat(4));
            System.out.println("Size: " + task.size());
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(Todo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int counter = 0; counter < task.size(); counter++) { 		      
            jPanel12.add(new SingleTask(task.get(counter).getTask(), this, Boolean.parseBoolean(task.get(counter).getFistTimeEdit()), task.get(counter).getId()), 0);
            jPanel12.revalidate();
            jPanel12.repaint();
        }
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        jPanel4.removeAll();
        jPanel4.repaint();
        jPanel4.revalidate();
        
        jPanel12.removeAll();
        jPanel12.repaint();
        jPanel12.revalidate();
        
        jPanel4.add(jPanel6);
        jPanel4.repaint();
        jPanel4.revalidate();

        this.date = 5;
        this.project = "notSelectProjectYet";
        jLabel20.setText(dateController.getDateFormat(5));
        
        try {
            task = taskController.getTask(user.getEmail(), dateController.getDocFormat(5));
            System.out.println("Size: " + task.size());
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(Todo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int counter = 0; counter < task.size(); counter++) { 		      
            jPanel12.add(new SingleTask(task.get(counter).getTask(), this, Boolean.parseBoolean(task.get(counter).getFistTimeEdit()), task.get(counter).getId()), 0);
            jPanel12.revalidate();
            jPanel12.repaint();
        }
    }//GEN-LAST:event_jLabel17MouseClicked

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        jPanel4.removeAll();
        jPanel4.repaint();
        jPanel4.revalidate();
        
        jPanel12.removeAll();
        jPanel12.repaint();
        jPanel12.revalidate();
        
        jPanel4.add(jPanel6);
        jPanel4.repaint();
        jPanel4.revalidate();

        this.date = 6;
        this.project = "notSelectProjectYet";
        jLabel20.setText(dateController.getDateFormat(6));
        
        try {
            task = taskController.getTask(user.getEmail(), dateController.getDocFormat(6));
            System.out.println("Size: " + task.size());
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(Todo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int counter = 0; counter < task.size(); counter++) { 		      
            jPanel12.add(new SingleTask(task.get(counter).getTask(), this, Boolean.parseBoolean(task.get(counter).getFistTimeEdit()), task.get(counter).getId()), 0);
            jPanel12.revalidate();
            jPanel12.repaint();
        }
    }//GEN-LAST:event_jLabel18MouseClicked

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        jPanel4.removeAll();
        jPanel4.repaint();
        jPanel4.revalidate();
        
        jPanel12.removeAll();
        jPanel12.repaint();
        jPanel12.revalidate();
        
        jPanel4.add(jPanel6);
        jPanel4.repaint();
        jPanel4.revalidate();

        this.date = 7;
        this.project = "notSelectProjectYet";
        jLabel20.setText(dateController.getDateFormat(7));
        
        try {
            task = taskController.getTask(user.getEmail(), dateController.getDocFormat(7));
            System.out.println("Size: " + task.size());
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(Todo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int counter = 0; counter < task.size(); counter++) { 		      
            jPanel12.add(new SingleTask(task.get(counter).getTask(), this, Boolean.parseBoolean(task.get(counter).getFistTimeEdit()), task.get(counter).getId()), 0);
            jPanel12.revalidate();
            jPanel12.repaint();
        }
    }//GEN-LAST:event_jLabel19MouseClicked

    private void jLabel21MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseEntered
        jLabel21.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jLabel21MouseEntered

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        jPanel14.add(new SingleProject(this), 0);
        jPanel14.revalidate();
        jPanel14.repaint();
    }//GEN-LAST:event_jLabel21MouseClicked

    private void jLabel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MouseClicked
        jLabel9.setText("Database :");
        jPanel17.add(new SingleTask("Add your new task here!", this.date, this), 0);
        jPanel17.revalidate();
        jPanel17.repaint();
    }//GEN-LAST:event_jLabel23MouseClicked

    private void jLabel23MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MouseEntered
        jLabel23.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jLabel23MouseEntered

    /**
     * @param args the command line arguments
     */
    public static void main(String email) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Todo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Todo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Todo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Todo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Todo(email).setVisible(true);
                } catch (InterruptedException | ExecutionException | IOException ex) {
                    Logger.getLogger(Todo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration//GEN-END:variables

}
