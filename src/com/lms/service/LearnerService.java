package com.lms.service;

import com.lms.dao.LearnerDao;
import com.lms.dao.impl.LearnerDaoImpl;
import com.lms.exception.InvalidIdException;
import com.lms.exception.InvalidInputException;
import com.lms.model.Learner;

import java.util.List;

public class LearnerService {
    LearnerDao dao = new LearnerDaoImpl(); //Polymorphic object.

    public List<Learner> getAllLearners() {
        return dao.getAll();
    }

    public void deleteById(int id) {
        dao.deleteById(id);
    }

    public Learner getById(int id) {
        return dao.getById(id);
    }

    public void update(Learner learner, String name, String email) throws InvalidInputException, InvalidIdException {
        if(name == null || name.equals("null"))
            throw new InvalidInputException("Invalid name value given");
        if(email == null || email.equals("null"))
            throw new InvalidInputException("Invalid email value given");

        learner.setName(name);
        learner.setEmail(email);

        dao.update(learner.getId(), learner);
    }

    public void insert(Learner learner) {
        if (learner.getName() == null || learner.getName().equals("null"))
            throw new InvalidInputException("Invalid Name !");
        if (learner.getEmail() == null || learner.getEmail().equals("null"))
            throw new InvalidInputException("Invalid Email !");
        dao.insert(learner);

    }
}
