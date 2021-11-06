package com.example.randomuser;

public class Result {
    public String gender;
    public Name name;
    public String nat;
    public Picture picture;

    public Result(String gender, Name name, Picture picture, String nat) {
        this.gender = gender;
        this.name = name;
        this.nat = nat;
        this.picture = picture;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getNat() {
        return nat;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}
