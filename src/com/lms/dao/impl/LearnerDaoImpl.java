package com.lms.dao.impl;

import com.lms.dao.LearnerDao;
import com.lms.exception.InvalidIdException;
import com.lms.exception.InvalidInputException;
import com.lms.model.Learner;
import com.lms.utility.DBUtility;
import com.lms.utility.LearnerUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LearnerDaoImpl implements LearnerDao {

    private LearnerUtility learnerUtility = new LearnerUtility();

    @Override
    public List<Learner> getAll() {
        /* return learnerUtility.getSampleData(); */
        DBUtility db = new DBUtility();
        Connection conn = db.connect();
        String sql = "SELECT * FROM learner";
        List<Learner> list = new ArrayList<>();

        try {
            // prepare the statement
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Execute the statement
            ResultSet rst = pstmt.executeQuery(sql);
            while (rst.next()){ // while the records exists in DB
                // read the columns
                Learner learner = new Learner(
                        rst.getInt("id"),
                        rst.getString("name"),
                        rst.getString("email")
                );
                list.add(learner);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        db.close();
        return list;
    }

    @Override
    public Learner getById(int id) throws InvalidIdException{

        DBUtility db = new DBUtility();
        Connection con = db.connect();
        String sql = "SELECT * FROM learner WHERE id = ?";

        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet rst = pstmt.executeQuery();
            if (rst.next()) {
                Learner learner = new Learner(
                        rst.getInt("id"),
                        rst.getString("name"),
                        rst.getString("email")
                );
                db.close();
                return learner;
            } else {
                db.close();
                throw new InvalidIdException("Id not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new InvalidIdException("Invalid");
        }
//		List<Learner> list = learnerUtility.getSampleData();
//		list.stream().filter(l -> l.getId() == id).toList();
//		if(list.isEmpty()) throw new InvaildIdException("Id is not valid");
//		return list.get(0);

    }

    @Override
    public void deleteById(int id) throws InvalidIdException {
        List<Learner> list = learnerUtility.getSampleData(); //100X=[1,2,3,4]
        int size  = list.size(); //this is the initial size
        //System.out.println("In delete method... " + list);
        //filter the record as per given id
        list =  list.stream().filter(l->l.getId() != id).toList();
        int resetSize = list.size();
        if(size == resetSize)
            throw new InvalidIdException("Could not find given ID");

        //System.out.println("In delete, after filter,,, "+ list);
        LearnerUtility.setList(list);

    }

    @Override
    public Learner update(int id, Learner learner) throws InvalidIdException, InvalidInputException {
        deleteById(id);
        List<Learner> list = getAll();
        List<Learner> newList = new ArrayList<>(list); //200X
        newList.add(learner);
        LearnerUtility.setList(newList);
        return learner;
    }

    @Override
    public void insert(Learner learner) throws InvalidInputException {
        DBUtility db = new DBUtility();

        // Establish connection
        Connection conn = db.connect();

        //Generate random id
        int id = (int) (Math.random() * 100000000);

        // SQL to insert record
        String sql = "INSERT INTO learner (id, name, email) VALUES (?,?,?)";

        try {
            // Prepare sql statement
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Give values to query
            pstmt.setInt(1,id);
            pstmt.setString(2,learner.getName());
            pstmt.setString(3, learner.getEmail());
            // run the statement
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        db.close();
    }
}
