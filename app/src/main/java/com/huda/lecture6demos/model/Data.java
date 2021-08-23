package com.huda.lecture6demos.model;

public class Data {
    private int icon;
    private String body;
    private String title;
    private String receiver;

    public Data() {
    }

    public Data(int icon, String body, String title, String receiver){
        this.icon = icon;
        this.body = body;
        this.title = title;
        this.receiver = receiver;
    }


    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

}
