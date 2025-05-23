package com.lms.controller;

import com.lms.exception.InvalidIdException;
import com.lms.exception.InvalidInputException;
import com.lms.model.Course;
import com.lms.model.Learner;
import com.lms.model.Track;
import com.lms.service.CourseService;
import com.lms.service.EnrollService;
import com.lms.service.LearnerService;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LearnerService learnerService = new LearnerService();
        Learner learner = new Learner();
        Course course = new Course();
        Track track = new Track();
        CourseService courseService = new CourseService();
        EnrollService enrollService = new EnrollService();

        while(true) {
            System.out.println("********************MAIN LMS MENU****************");
            System.out.println("1. Add Learner");
            System.out.println("2. View All Learner");
            System.out.println("3. Delete Learner");
            System.out.println("4. Edit Learner");
            System.out.println("5. get Learner Info by ID");
            System.out.println("6. Add Track");
            System.out.println("7. Add Course");
            System.out.println("8. Get All Courses");
            System.out.println("9. Get Courses by Track");
            System.out.println("10. Enroll Learner to Course");
            System.out.println("0. To Exit");
            System.out.println("********************-------------****************");
            int input  = sc.nextInt();
            if(input == 0) {
                System.out.println("Exiting... Thank you");
                break; //while loop breaks
            }

            switch(input) {
                case 1:
                    System.out.println("Enter Name : ");
                    sc.nextLine();
                    learner.setName(sc.nextLine());
                    System.out.println("Enter Email : ");
                    learner.setEmail(sc.nextLine());

                    try {
                        learnerService.insert(learner);
                        System.out.println("Record inserted in DB");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 2:
                    List<Learner> list = learnerService.getAllLearners();
                    list.stream().forEach(l -> System.out.println(l));
                    break;

                case 3:
                    System.out.println("Enter Learner ID: ");
                    try {
                        learnerService.deleteById(sc.nextInt());
                        System.out.println("learner deleted..");
                    } catch (InvalidIdException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Enter Learner ID: ");

                    try {
                        learner = learnerService.getById(sc.nextInt());
                        System.out.println("Existing Record");
                        System.out.println(learner);
                        System.out.println("Enter Name");
                        sc.nextLine(); //hold the console
                        String name = sc.nextLine();
                        System.out.println("Enter email");
                        String email = sc.nextLine();
                        learnerService.update(learner, name, email);
                        System.out.println("Learner record updated..");
                    } catch (InvalidIdException | InvalidInputException e) {
                        System.out.println(e.getMessage());
                    }

                    System.out.println("Record Update...");
                    break;
                case 5:
                    System.out.println("Enter Learner ID: ");
                    try {
                        learner = learnerService.getById(sc.nextInt());
                        System.out.println(learner);
                    } catch (InvalidIdException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    System.out.println("Enter track name");
                    sc.nextLine();
                    track.setName(sc.nextLine());
                    courseService.insertTrack(track);
                    System.out.println("Track added in DB");
                    break;
                case 7:
                    System.out.println("Enter title");
                    sc.nextLine();
                    course.setTitle(sc.nextLine());
                    System.out.println("Enter fee");
                    course.setFee(sc.nextDouble());
                    System.out.println("Enter discount");
                    course.setDiscount(sc.nextDouble());
                    System.out.println("Enter Track Id");
                    int trackId = sc.nextInt();
                    courseService.insertCourse(course,trackId);
                    System.out.println("Course added in DB");
                    break;
                case 8:
                    courseService.getAll()
                                    .stream()
                                    .forEach(c -> {
                                        System.out.println(c.getId() + "\t"
                                                + c.getTitle() + "\t"
                                                + c.getFee() + "\t"
                                                + c.getTrack().getName());
                                    });
                    break;
                case 9:
                    System.out.println("Enter Track Id :");
                    courseService.getByTrackId(sc.nextInt())
                            .stream()
                            .forEach(c -> {
                                System.out.println(c.getId() + "\t"
                                        + c.getTitle() + "\t"
                                        + c.getFee() + "\t"
                                        + c.getTrack().getName());
                            });

                    break;
                case 10:
                    System.out.println("Enter Learner ID: ");
                    int learnerId = sc.nextInt();
                    System.out.println("Enter Course ID: ");
                    int courseId = sc.nextInt();

                    try {
                        enrollService.enroll(learnerId, courseId, sc);
                    } catch (InvalidIdException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                default:
                    System.out.println("Invalid Input!!!");
            }
        }

        sc.close();
    }
}
