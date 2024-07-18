package com.example.android_quizappwithfirebase.model;

import java.util.ArrayList;
import java.util.List;

public class QuizModel {
    private String id; // Quiz ID
    private String title; // Quiz title
    private String subtitle; // Quiz subtitle
    private String time; // Quiz duration as a string
    private List<QuestionModel> questionList; // List of questions in the quiz

    // Constructor to create a new QuizModel object
    public QuizModel(String id, String title, String subtitle, String time, List<QuestionModel> questionList) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.time = time;
        this.questionList = questionList;
    }

    // Default constructor
    public QuizModel() {
        this("", "", "", "", new ArrayList<>());
    }

    // Getter and setter for id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getter and setter for title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter and setter for subtitle
    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    // Getter and setter for time
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    // Getter and setter for questionList
    public List<QuestionModel> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<QuestionModel> questionList) {
        this.questionList = questionList;
    }
}
