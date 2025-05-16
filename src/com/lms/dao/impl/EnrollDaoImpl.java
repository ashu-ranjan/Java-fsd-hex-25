package com.lms.dao.impl;

import com.lms.dao.EnrollDao;
import com.lms.model.Enroll;
import com.lms.utility.DBUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EnrollDaoImpl implements EnrollDao {

    DBUtility db = new DBUtility();

    @Override
    public void insert(Enroll enroll) {
        Connection conn = db.connect();
        String sql = "INSERT INTO enroll VALUES (?,?,?,?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, enroll.getDateOfPublish().toString());
            pstmt.setString(2, String.valueOf(enroll.getCoupon()));
            pstmt.setString(3, enroll.getFeePaid());
            pstmt.setInt(4,enroll.getCourse().getId());
            pstmt.setInt(5,enroll.getLearner().getId());



            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        db.close();
    }
}

