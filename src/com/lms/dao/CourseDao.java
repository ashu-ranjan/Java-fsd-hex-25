package com.lms.dao;

import com.lms.exception.InvalidIdException;
import com.lms.model.Course;

import java.util.List;

public interface CourseDao {
    void insert (Course course, int trackId);
    List<Course> getAll();
    List<Course> getByTrackId(int id);
    Course getById(int courseId) throws InvalidIdException;
}
