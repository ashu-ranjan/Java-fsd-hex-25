package com.lms.dao.impl;

import com.lms.dao.CourseDao;
import com.lms.dao.TrackDao;
import com.lms.exception.InvalidIdException;
import com.lms.model.Course;
import com.lms.model.Track;
import com.lms.utility.DBUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDao {

    DBUtility db = new DBUtility();

    @Override
    public void insert(Course course, int trackId) {
        Connection conn = db.connect();
        String sql = "INSERT INTO course VALUES (?,?,?,?,?,?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, course.getId());
            pstmt.setString(2, course.getTitle());
            pstmt.setDouble(3, course.getFee());
            pstmt.setDouble(4, course.getDiscount());
            pstmt.setString(5, course.getPublishDate().toString());
            pstmt.setInt(6, trackId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        db.close();
    }

    @Override
    public List<Course> getAll() {
        Connection conn = db.connect();
        String sql = "SELECT * FROM course c JOIN track t ON c.track_track_id = t.track_id";
        List<Course> list = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rst = pstmt.executeQuery();
            while(rst.next()){
                Course course = new Course();
                course.setId(rst.getInt("id"));
                course.setTitle(rst.getString("title"));
                course.setFee(rst.getDouble("fee"));

                Track track = new Track();
                track.setName(rst.getString("name"));

                // attach track to course
                course.setTrack(track);
                list.add(course);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        db.close();
        return list;
    }

    @Override
    public List<Course> getByTrackId(int id) {
        Connection conn = db.connect();
        List<Course> list = new ArrayList<>();
        String sql = "SELECT c.*, t.* FROM course c JOIN track t ON c.track_track_id = t.track_id WHERE t.track_id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rst = pstmt.executeQuery();
            while(rst.next()){
                Course course = new Course();
                course.setId(rst.getInt("id"));
                course.setTitle(rst.getString("title"));
                course.setFee(rst.getDouble("fee"));
                course.setDiscount(rst.getDouble("discount"));

                Track track = new Track();
                track.setId(rst.getInt("track_id"));
                track.setName(rst.getString("name"));

                course.setTrack(track);

                list.add(course);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        db.close();
        return list;
    }

    @Override
    public Course getById(int courseId) throws InvalidIdException {
        Connection conn = db.connect();
        String sql = "SELECT * FROM course WHERE id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, courseId);

            ResultSet rst = pstmt.executeQuery();
            if (rst.next()){
                Course course = new Course();
                course.setId(rst.getInt("id"));
                course.setTitle(rst.getString("title"));
                course.setFee(rst.getDouble("fee"));
                course.setDiscount(rst.getDouble("discount"));
                return course;

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        db.close();
        throw new InvalidIdException("Course ID given is Invalid!");
    }

}
