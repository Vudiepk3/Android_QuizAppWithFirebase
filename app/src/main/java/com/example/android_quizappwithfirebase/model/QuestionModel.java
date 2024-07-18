package com.example.android_quizappwithfirebase.model;

import java.util.ArrayList;
import java.util.List;

public class QuestionModel {
    private String question;
    private List<String> options;
    private String correct;

    // Parameterized constructor
    public QuestionModel(String question, List<String> options, String correct) {
        this.question = question;
        this.options = options;
        this.correct = correct;
    }

    // Default constructor
    public QuestionModel() {
        this("", new ArrayList<>(), "");
    }

    // Getter and setter for the question
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    // Getter and setter for the list of options
    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    // Getter and setter for the correct answer
    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }
}
