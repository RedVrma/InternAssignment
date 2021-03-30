package com.puneetverma.internassignment;

import android.icu.text.CaseMap;

import java.util.ArrayList;

public class model {

    String Title;
    ArrayList<String> listImageUri;

    public model() {
    }

    public model(String title, ArrayList<String> listImageUri) {
        Title = title;
        this.listImageUri = listImageUri;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public ArrayList<String> getListImageUri() {
        return listImageUri;
    }

    public void setListImageUri(ArrayList<String> listImageUri) {
        this.listImageUri = listImageUri;
    }

    @Override
    public String toString() {
        return "model{" +
                "Title='" + Title + '\'' +
                ", listImageUri=" + listImageUri +
                '}';
    }
}
