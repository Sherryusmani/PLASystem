package com.example.plasystem;

public class User {
    public String email,password,ConfirmPswd;

    public User(){

    }
    public User(String email, String password,String ConfirmPswd){
        this.email= email;
        this.password = password;
        this.ConfirmPswd= ConfirmPswd;
    }
}
