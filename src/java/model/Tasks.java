/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Tasks {

    private String taskID, taskName, taskDes, taskDate, taskTime, prioritize;
    private Account a;

    public Tasks() {
    }

    public Tasks(String taskID, String taskName, String taskDes, String taskDate, String taskTime, String prioritize, Account a) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.taskDes = taskDes;
        this.taskDate = taskDate;
        this.taskTime = taskTime;
        this.prioritize = prioritize;
        this.a = a;
    }

    public String getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }

    public Account getA() {
        return a;
    }

    public void setA(Account a) {
        this.a = a;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDes() {
        return taskDes;
    }

    public void setTaskDes(String taskDes) {
        this.taskDes = taskDes;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    public String getPrioritize() {
        return prioritize;
    }

    public void setPrioritize(String prioritize) {
        this.prioritize = prioritize;
    }

}
