package com.lms.service;

import com.lms.dao.CourseDao;
import com.lms.dao.EnrollDao;
import com.lms.dao.LearnerDao;
import com.lms.dao.impl.LearnerDaoImpl;
import com.lms.dao.impl.CourseDaoImpl;
import com.lms.dao.impl.EnrollDaoImpl;
import com.lms.enums.Coupon;
import com.lms.model.Course;
import com.lms.model.Enroll;
import com.lms.model.Learner;

import java.time.LocalDate;
import java.util.Scanner;

public class EnrollService {

    private LearnerDao learnerDao = new LearnerDaoImpl();
    private CourseDao courseDao = new CourseDaoImpl();
    private EnrollDao enrollDao = new EnrollDaoImpl();


    public void enroll(int learnerId, int courseId, Scanner sc) {
        Enroll enroll = new Enroll();
        /*
        * Step 1: Validate learners ID
        * return learners object
        * */
        Learner learner = learnerDao.getById(learnerId);
        enroll.setLearner(learner);

        /*
         * Step 2: Validate course ID
         * return course object
         * */

        Course course = courseDao.getById(courseId);
        enroll.setCourse(course);

        /*
         * Step 3: Ask if any coupon code is available
         * if yes , adjust the fee accordingly
         * */

        System.out.println("Do you have a coupon? (Y/N)");
        String ans = sc.next();
        if (ans.equals("Y")){
            System.out.println("Enter the code : ");
            String couponCode = sc.next().toUpperCase();
            /* Check : if the coupon code is valid*/
            Coupon coupon = Coupon.valueOf(couponCode);
            // if it does not work i get IllegalArgumentException
            double discount = (double)coupon.getDiscount();
            System.out.println("Discount = " + discount);
            double discountedFee = course.getFee() - (course.getFee() * (discount / 100));
            System.out.println("After Discount, Fee is " + discountedFee);
            enroll.setCoupon(coupon);
            enroll.setFeePaid(String.valueOf(discountedFee));
            System.out.println("Successfully enrolled...");
        }
        else{
            System.out.println("No coupon applied....");
            enroll.setFeePaid(String.valueOf(course.getFee()));
        }
        enroll.setDateOfPublish(LocalDate.now());

        /*
         * Save 4: Enroll Object in DB via enrollDao
         * */

        enrollDao.insert(enroll);
    }
}
