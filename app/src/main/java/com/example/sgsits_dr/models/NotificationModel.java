package com.example.sgsits_dr.models;

public class NotificationModel {
    private String title;
    private String des;
    private String summary;
    private String domain;
    private String startdate;
    private String enddate;

    public NotificationModel(String title, String des, String summary, String domain, String startdate, String enddate) {
        this.title = title;
        this.des = des;
        this.summary = summary;
        this.domain = domain;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }
}
