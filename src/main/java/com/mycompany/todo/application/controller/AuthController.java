/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.todo.application.controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class AuthController {
    AuthResponse userInfo; 
    public static String signUpNewUser(String email, String password) throws IOException {

        HttpPost post = new HttpPost("https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=AIzaSyDLv0RpndQmLMLw7nX3qfbgLzE1W-tFsyU");
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("email", email));
        urlParameters.add(new BasicNameValuePair("password", password));
        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        try (CloseableHttpClient httpClient = HttpClients.createDefault(); CloseableHttpResponse response = httpClient.execute(post)) {
            Gson gson = new Gson();
            AuthResponse userInfo = new AuthResponse(); 
            userInfo = gson.fromJson(EntityUtils.toString(response.getEntity()), AuthResponse.class);

            if (userInfo.getEmail() == null) {
                return userInfo.getError().getMessage();
            } else {
                return userInfo.getEmail();
            }
        }
    }

    public static String login(String email, String password) throws IOException {

        HttpPost post = new HttpPost("https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=AIzaSyDLv0RpndQmLMLw7nX3qfbgLzE1W-tFsyU");
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("email", email));
        urlParameters.add(new BasicNameValuePair("password", password));
        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        try (CloseableHttpClient httpClient = HttpClients.createDefault(); CloseableHttpResponse response = httpClient.execute(post)) {

            Gson gson = new Gson();
            AuthResponse userInfo = new AuthResponse(); 
            userInfo = gson.fromJson(EntityUtils.toString(response.getEntity()), AuthResponse.class);

            if (userInfo.getEmail() == null) {
                return userInfo.getError().getMessage();
            } else {
                return userInfo.getEmail();
            }
        }
    }
}
