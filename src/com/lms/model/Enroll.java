package com.lms.model;

import com.lms.enums.Coupon;

import java.time.LocalDate;

public class Enroll {

    // inject learner
    Learner learner;
    // inject course
    Course course;

    private LocalDate dateOfPublish;
    private Coupon coupon;
    private String feePaid;

    public Enroll() {};

    public Enroll(Learner learner, Course course, LocalDate dateOfPublish, Coupon coupon, String feePaid) {
        this.learner = learner;
        this.course = course;
        this.dateOfPublish = dateOfPublish;
        this.coupon = coupon;
        this.feePaid = feePaid;
    }

    public Learner getLearner() {
        return learner;
    }

    public void setLearner(Learner learner) {
        this.learner = learner;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDate getDateOfPublish() {
        return dateOfPublish;
    }

    public void setDateOfPublish(LocalDate dateOfPublish) {
        this.dateOfPublish = dateOfPublish;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public String getFeePaid() {
        return feePaid;
    }

    public void setFeePaid(String feePaid) {
        this.feePaid = feePaid;
    }

    @Override
    public String toString() {
        return "Enroll [" +
                "learner=" + learner +
                ", course=" + course +
                ", dateOfPublish=" + dateOfPublish +
                ", coupon=" + coupon +
                ", feePaid='" + feePaid + '\'' +
                ']';
    }
}
