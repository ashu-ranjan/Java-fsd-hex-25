package com.lms.model;

import java.time.LocalDate;

public class Enroll {

    // inject learner
    Learner learner;
    // inject course
    Course course;

    private LocalDate dateOfPublish;
    private String couponUsed;
    private String feePaid;

    public Enroll() {};

    public Enroll(Learner learner, Course course, LocalDate dateOfPublish,
                  String couponUsed, String feePaid) {
        this.learner = learner;
        this.course = course;
        this.dateOfPublish = dateOfPublish;
        this.couponUsed = couponUsed;
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

    public String getCouponUsed() {
        return couponUsed;
    }

    public void setCouponUsed(String couponUsed) {
        this.couponUsed = couponUsed;
    }

    public String getFeePaid() {
        return feePaid;
    }

    public void setFeePaid(String feePaid) {
        this.feePaid = feePaid;
    }

    @Override
    public String toString() {
        return "Enroll[" +
                "learner=" + learner +
                ", course=" + course +
                ", dateOfPublish=" + dateOfPublish +
                ", couponUsed='" + couponUsed + '\'' +
                ", feePaid='" + feePaid + '\'' +
                ']';
    }
}
