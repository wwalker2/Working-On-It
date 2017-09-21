package com.example.wes19_000.working_on_it;

import java.util.ArrayList;

/**
 * Created by wes19_000 on 9/16/2017.
 */

public class JobEntry {
    private String clientName;
    private String clientAddress;
    private String clientPhone;
    private String jobDescription;
    private String[] toolList;
    private double jobPay;
    private String startDate;
    private String endDate;

    public JobEntry(){
        setClientName("");
        setClientAddress("");
        setClientPhone("");
        setJobDescription("");
        setToolList(new String[0]);
        setJobPay(0);
        setStartDate("");
        setEndDate("");
    }

    public JobEntry(String name){
        clientName = name;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String[] getToolList() {
        return toolList;
    }

    public void setToolList(String[] toolList) {
        this.toolList = toolList;
    }

    public double getJobPay() {
        return jobPay;
    }

    public void setJobPay(double jobPay) {
        this.jobPay = jobPay;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
