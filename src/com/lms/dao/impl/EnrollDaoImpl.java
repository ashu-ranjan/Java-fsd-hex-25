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
            pstmt.setInt(1,enroll.getLearner().getId());
            pstmt.setInt(2,enroll.getCourse().getId());
            pstmt.setString(3, enroll.getDateOfPublish().toString());
            pstmt.setString(4, enroll.getCouponUsed());
            pstmt.setString(5, enroll.getFeePaid());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        db.close();
    }
}
