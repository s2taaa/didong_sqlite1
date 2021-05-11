package com.example.sqlite;

public class user {
    private int id;
    private String name;

    public user( String name) {

        this.name = name;
    }

    public user() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
