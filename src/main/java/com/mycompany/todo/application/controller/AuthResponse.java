/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.todo.application.controller;

/**
 *
 * @author UomrodPC
 */
public class AuthResponse {
    private String idToken;
    private String email;
    private String refreshToken;
    private String vexpiresIn;
    private String localId;
    private AuthErrorResponse error = null;
    private boolean registered;
    
    /**
     * @return the idToken
     */
    public String getIdToken() {
        return idToken;
    }

    /**
     * @param idToken the idToken to set
     */
    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the refreshToken
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * @param refreshToken the refreshToken to set
     */
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    /**
     * @return the vexpiresIn
     */
    public String getVexpiresIn() {
        return vexpiresIn;
    }

    /**
     * @param vexpiresIn the vexpiresIn to set
     */
    public void setVexpiresIn(String vexpiresIn) {
        this.vexpiresIn = vexpiresIn;
    }

    /**
     * @return the localId
     */
    public String getLocalId() {
        return localId;
    }

    /**
     * @param localId the localId to set
     */
    public void setLocalId(String localId) {
        this.localId = localId;
    }

    /**
     * @return the registered
     */
    public boolean isRegistered() {
        return registered;
    }

    /**
     * @param registered the registered to set
     */
    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    /**
     * @return the error
     */
    public AuthErrorResponse getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(AuthErrorResponse error) {
        this.error = error;
    }

}
