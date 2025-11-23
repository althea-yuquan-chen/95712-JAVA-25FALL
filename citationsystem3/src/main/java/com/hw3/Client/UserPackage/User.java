package com.hw3.Client.UserPackage;

public abstract class User {
    private int id;
    private String userName;
    
    public User() {
    }

    @Override
    public String toString() {
        return "User id: " + id + ", userName: " + userName;
    }
    public User(int id, String userName) {
        this.id = id;
        this.userName = userName;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

}
