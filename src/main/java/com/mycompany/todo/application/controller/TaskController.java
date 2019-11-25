/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.todo.application.controller;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.FileInputStream;
import java.util.Calendar;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Uomrod-PC
 */
public class TaskController {

    private Firestore database = null;
    private DateController dateController = new DateController();
    private Calendar calendar = Calendar.getInstance();
    private ArrayList<TaskResponse> Task = new ArrayList<>();
    private ArrayList<String> group = new ArrayList<>();
    
    private String mail = "gearprn@gmail.com";
    private String name = "test";
    
    
    public static void main(String[] args) {
        TaskController test = null;
        
        try {
            test = new TaskController();
        } catch (InterruptedException | ExecutionException | IOException ex) {
            Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            System.out.println(test.getProjects("gearprn@gmail.com"));
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public TaskController() throws InterruptedException, ExecutionException, FileNotFoundException, IOException {
        InputStream serviceAccount = new FileInputStream("./serviceAccount.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .build();
        FirebaseApp.initializeApp(options);
        database = FirestoreClient.getFirestore();
    }
    
    public String addProject(String email, String projrctName) throws InterruptedException, ExecutionException {
        Map<String, Object> data = new HashMap<>();
        data.put("projectName", projrctName);
        
        DocumentReference addedDocRef = database.collection(email).document("projects");
        ApiFuture<WriteResult> arrayUnion = addedDocRef.update("name",
            FieldValue.arrayUnion(projrctName));
        
        System.out.println("Update time : " + arrayUnion.get());
        return"";
    }
    
    public String addProjectTask(String email, String projrctName, String txt) throws InterruptedException, ExecutionException {
        Map<String, Object> data = new HashMap<>();
        data.put("project", projrctName);
        data.put("task", txt);
        data.put("fistTimeEdit", "false");

        ApiFuture<DocumentReference> addedDocRef = database.collection(email).add(data);
        System.out.println("Update time : " + addedDocRef.get().getId());
        return addedDocRef.get().getId();
    }
    
    public void updateProjectTask(String email,String docId, String txt) throws InterruptedException, ExecutionException {
        System.out.println("update task on docId " + docId);
        DocumentReference docRef = database.collection(email).document(docId);

        ApiFuture<WriteResult> future = docRef.update("task", txt);
        WriteResult result = future.get();
        System.out.println("Write result: " + result);
    }
    
    public ArrayList<String> getProjects(String email) throws InterruptedException, ExecutionException {
        DocumentReference docRef = database.collection(email).document("projects");
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            System.out.println("Document data: " + document.getData());
            group = (ArrayList<String>) document.get("name");
            System.out.println(group.size());
        } else {
          System.out.println("No such document!");
        }
        return group;
    }
    
    public ArrayList<TaskResponse> getProjectTasks(String email, String projectName) throws InterruptedException, ExecutionException {
        
        Task = new ArrayList<>();
        ApiFuture<QuerySnapshot> future = database.collection(email).whereEqualTo("project", projectName).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        
        if (!documents.isEmpty()) {
            for (QueryDocumentSnapshot document : documents) {
                TaskResponse task = new TaskResponse();
                task = document.toObject(TaskResponse.class);
                task.setId(document.getId());
                Task.add(task);
                System.out.println("docID: " + document.getId() + " task: " + task.getTask());
            }
        }   
        return Task;
    }

    public String addTask(String email, String txt, String date) throws IOException, FileNotFoundException, InterruptedException, ExecutionException, InterruptedException, InterruptedException {
        System.out.println("add task date on " + date);

        Map<String, Object> data = new HashMap<>();
        data.put("task", txt);
        data.put("isDone", "true");
        data.put("date", date);
        data.put("fistTimeEdit", "false");

        ApiFuture<DocumentReference> addedDocRef = database.collection(email).add(data);
        System.out.println("Update time : " + addedDocRef.get().getId());
        return addedDocRef.get().getId();
    }

    public void updateTask(String email, String docId, String txt) throws InterruptedException, ExecutionException {
        System.out.println("update task on docId " + docId);
        DocumentReference docRef = database.collection(email).document(docId);

        ApiFuture<WriteResult> future = docRef.update("task", txt);
        WriteResult result = future.get();
        System.out.println("Write result: " + result);
    }

    public ArrayList<TaskResponse> getTask(String email, String date) throws InterruptedException, ExecutionException {
        System.out.println("get task on date " + date);
        
        Task = new ArrayList<>();
        ApiFuture<QuerySnapshot> future = database.collection(email).whereEqualTo("date", date).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        
        if (!documents.isEmpty()) {
            for (QueryDocumentSnapshot document : documents) {
                TaskResponse task = new TaskResponse();
                task = document.toObject(TaskResponse.class);
                task.setId(document.getId());
                Task.add(task);
                System.out.println("docID: " + document.getId() + " date: " + task.getDate() + " task: " + task.getTask());
            }
        }   
        return Task;
    }
}
