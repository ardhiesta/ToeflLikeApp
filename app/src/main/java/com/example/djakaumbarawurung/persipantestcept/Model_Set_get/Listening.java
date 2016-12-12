package com.example.djakaumbarawurung.persipantestcept.Model_Set_get;

import java.util.HashMap;

/**
 * Created by linuxluv on 12/10/16.
 */

public class Listening {
    String question;
    HashMap<String, String> opsi;

    public HashMap<String, String> getOpsi() {
        return opsi;
    }

    public void setOpsi(HashMap<String, String> opsi) {
        this.opsi = opsi;
    }

    String realAnswer;
    String userAnswer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getRealAnswer() {
        return realAnswer;
    }

    public void setRealAnswer(String realAnswer) {
        this.realAnswer = realAnswer;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }
}
