package com.egg.EggNewsintel.domain;

import java.util.List;

public class Journalist extends UserResponse{
    private Double monthly_salary;
    private List<New> news;


    public Double getMonthly_salary() {
        return monthly_salary;
    }

    public void setMonthly_salary(double monthly_salary) {
        this.monthly_salary = monthly_salary;
    }

    public List<New> getNews() {
        return news;
    }

    public void setNews(List<New> news) {
        this.news = news;
    }
}
