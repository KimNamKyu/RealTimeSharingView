package com.example.cp517.project;

/**
 * Created by da224 on 2017-03-11.
 */

public class Friend{

    public String email;

    public String photo;

    public String key;

    public Friend() {
        // Default constructor required for calls to DataSnapshot.getValue(Comment.class)
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
