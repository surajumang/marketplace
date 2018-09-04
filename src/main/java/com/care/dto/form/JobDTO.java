package com.care.dto.form;

import com.care.validation.FormBean;
import com.care.validation.FormValidator;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class JobDTO extends FormBean {
    private int id;
    private String title;
    private double hourlyPay;
    // should be greater than current StringDate and time.
    private java.util.Date startDate;
    // should be greater than or equal to current StringDate and time.
    private java.util.Date endDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getHourlyPay() {
        return hourlyPay;
    }

    public void setHourlyPay(double hourlyPay) {
        this.hourlyPay = hourlyPay;
    }

    public java.util.Date getStartDate() {
        return startDate;
    }

    public void setStartDate(java.util.Date startDate) {
        this.startDate = startDate;
    }

    public java.util.Date getEndDate() {
        return endDate;
    }

    public void setEndDate(java.util.Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "JobForm{" +
                "title='" + title + '\'' +
                ", hourlyPay='" + hourlyPay + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }

    @Override
    public void validateCustom(Map<String, String> errors) {
        /*
        Check if start date is greater than the end Date.
         */
        try {
            FormValidator.validate(this, errors);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if (startDate.after(endDate)){
            errors.put("StartDate", "Start date must be less than end Date");
        }
    }

}
