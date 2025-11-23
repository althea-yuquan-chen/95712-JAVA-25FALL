package com.hw3.Client.UserPackage;

public class UserFactory {
    public static User CreateUser(int id, String userType, String name, String other){
        if (userType.equalsIgnoreCase("Administrator")){
            return new Administrator(id, name, other);
        } else if (userType.equalsIgnoreCase("Officer")){
            return new Officer(id, name, other);
        } else {
            return new CourtOfficial(id, name, other);
        }
    }

}
