/*
 * Name: Althea Chen
 * AndrewID: yuuanch
 */

package com.hw3.CommonPackage;

// Class: Person
// Purpose: Models the person receiving a Citation

public class Person {
    private String firstName, lastName, address, phoneNumber;

    public Person(String firstName, String lastName, String address, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Person() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return  firstName + " " + lastName + " " + address + " " + phoneNumber;
    }

    public String toCSV(){
        return firstName + "," + lastName + "," + address + "," + phoneNumber;
    }
    

}
