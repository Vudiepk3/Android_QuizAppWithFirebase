package com.example.android_quizappwithfirebase.model;

public class UniversityModel {
    private String title;
    private int logo;

    public UniversityModel(String title, int logo) {
        this.title = title;
        this.logo = logo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }
    public String toString() {
        return "UniversityData{" +
                "title='" + title + '\'' +
                ", logo=" + logo +
                '}';
    }
}
