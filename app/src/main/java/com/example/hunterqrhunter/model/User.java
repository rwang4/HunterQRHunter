package com.example.hunterqrhunter.model;

import com.example.hunterqrhunter.HashQR;
import com.google.firebase.firestore.Exclude;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {
    String uid;
    String firstName;
    String lastName;
    int born;
    int hash;
    ArrayList<Integer> scanned;
    //    Array<HashQR> qrArray;


    public User(String uid,String fn, String ln, int born, int hash,ArrayList<Integer> scanned) {
        this.uid = uid;
        this.firstName =  fn;
        this.lastName = ln;
        this.born = born;
        this.hash = hash;
        this.scanned = scanned;
    }

    public User(String uid) {
        this.uid = uid;
    }


    public String getFirstName() {
        return this.firstName;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<String,Object>();
        result.put("uid", this.uid);
        result.put("firstName", this.firstName);
        result.put("lastName", this.lastName);
        result.put("born", this.born);
        result.put("hash", this.hash);
        result.put("scanned", this.scanned);

        return result;
    }

    public String getUid() {
        return uid;
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

    public int getBorn() {
        return born;
    }

    public void setBorn(int born) {
        this.born = born;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }
}
