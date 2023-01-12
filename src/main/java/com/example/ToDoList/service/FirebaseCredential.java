package com.example.ToDoList.service;

public class FirebaseCredential {

    String type;
    String project_id;
    String private_key_id;
    String private_key;
    String client_email;
    String client_id;
    String auth_uri;
    String token_uri;
    String auth_provider_x509_cert_uri;
    String client_x509_cert_uri;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getPrivate_key_id() {
        return private_key_id;
    }

    public void setPrivate_key_id(String private_key_id) {
        this.private_key_id = private_key_id;
    }

    public String getPrivate_key() {
        return private_key;
    }

    public void setPrivate_key(String private_key) {
        this.private_key = private_key;
    }

    public String getClient_email() {
        return client_email;
    }

    public void setClient_email(String client_email) {
        this.client_email = client_email;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getAuth_uri() {
        return auth_uri;
    }

    public void setAuth_uri(String auth_uri) {
        this.auth_uri = auth_uri;
    }

    public String getToken_uri() {
        return token_uri;
    }

    public void setToken_uri(String token_uri) {
        this.token_uri = token_uri;
    }

    public String getAuth_provider_x509_cert_uri() {
        return auth_provider_x509_cert_uri;
    }

    public void setAuth_provider_x509_cert_uri(String auth_provider_x509_cert_uri) {
        this.auth_provider_x509_cert_uri = auth_provider_x509_cert_uri;
    }

    public String getClient_x509_cert_uri() {
        return client_x509_cert_uri;
    }

    public void setClient_x509_cert_uri(String client_x509_cert_uri) {
        this.client_x509_cert_uri = client_x509_cert_uri;
    }
}
