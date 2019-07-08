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
    public User(User u){
        userPassword = u.getUserPassword();
        userEmail =u.getUserEmail();
        userID = u.getUserID();
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

    public boolean checkUserSignIn(String pWord, String uEmail){
        //SQLiteDatabase db = this.getReadableDatabase();
        //Cursor res = db.rawQuery("SELECT * FROM " +  TABLE_NAME + " WHERE userEmail=" + uEmail +"", null);

        //User user = new User(uEmail);

        return true;

    }
}
