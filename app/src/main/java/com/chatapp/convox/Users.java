package com.chatapp.convox;

public class Users {
    String profilepic,userName,userid,email,pass,lastMess,status;

    public Users(String profilepic, String userName, String userid, String email, String pass, String status) {
        this.profilepic = profilepic;
        this.userName = userName;
        this.userid = userid;
        this.email = email;
        this.pass = pass;
        this.status = status;
    }


    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getLastMess() {
        return lastMess;
    }

    public void setLastMess(String lastMess) {
        this.lastMess = lastMess;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
