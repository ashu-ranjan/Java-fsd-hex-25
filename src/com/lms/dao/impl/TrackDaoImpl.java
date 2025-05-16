package com.lms.dao.impl;

import com.lms.dao.TrackDao;
import com.lms.model.Track;
import com.lms.utility.DBUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TrackDaoImpl implements TrackDao {

    DBUtility db = new DBUtility();

    @Override
    public void insert(Track track) {
        Connection conn = db.connect();
        String sql = "INSERT INTO track (track_id, name) VALUES (?,?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,track.getId());
            pstmt.setString(2, track.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        db.close();
    }
}
