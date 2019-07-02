package com.example.drgreenthumb;


public class User {
    private String userPassword;
    private String userEmail;
    private int userID;

    public User(String usrPass, String usrEmail,int  usrID){
        userPassword  = usrPass;
        userEmail = usrEmail;
        userID = usrID;
    }

    public User(String newPass, String newEmail) {
        userPassword = newPass;
        userEmail = newEmail;
    }

    public String getUserEmail(){
        return userEmail;
    }
    public String getUserPassword(){
        return userPassword;
    }
    public int getUserID(){
        return userID;
    }
}
