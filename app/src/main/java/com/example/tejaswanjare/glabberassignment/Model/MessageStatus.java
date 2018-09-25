package com.example.tejaswanjare.glabberassignment.Model;

public class MessageStatus {
    private  int userID;//userId mapping status for which user
    private int status; //1 for message deliver 2 for message read
    private long time; // time when message was read or delivered
    private String userName;

    public MessageStatus(int userID,int status, long time , String userName){
        this.userID = userID;
        this.status = status;
        this.time = time;
        this.userName =userName;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }



}
