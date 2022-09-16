package com.egg.EggNewsintel.domain;

import java.util.Date;

public class UserResponse {

    private int userId;
    private String userName;
    private Date created_at;
    private String role;

    private Double monthly_salary;

    public Double getMonthly_salary() {
        return monthly_salary;
    }

    public void setMonthly_salary(Double monthly_salary) {
        this.monthly_salary = monthly_salary;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
