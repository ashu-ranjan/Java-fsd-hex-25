package com.lms.service;

import com.lms.dao.CourseDao;
import com.lms.dao.TrackDao;
import com.lms.dao.impl.CourseDaoImpl;
import com.lms.dao.impl.TrackDaoImpl;
import com.lms.model.Course;
import com.lms.model.Track;

import java.time.LocalDate;
import java.util.List;

public class CourseService {

    private CourseDao courseDao = new CourseDaoImpl();
    private TrackDao trackDao = new TrackDaoImpl();

    public void insertCourse(Course course, int trackId) {
        int id = (int) (Math.random() * 100000000);
        course.setId(id);
        course.setPublishDate(LocalDate.now());
        courseDao.insert(course, trackId);
    }

    public void insertTrack(Track track) {
        int id = (int) (Math.random() * 100000000);
        track.setId(id);
        trackDao.insert(track);
    }

    public List<Course> getAll() {
        return courseDao.getAll();
    }

    public List<Course> getByTrackId(int id) {
        return courseDao.getByTrackId(id);
    }
}
