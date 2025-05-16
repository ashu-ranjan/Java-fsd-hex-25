package com.lms.utility;

import com.lms.model.Learner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LearnerUtility {
    private static List<Learner> list;
    {
        list = new ArrayList<>();
    }

    public List<Learner> getSampleData(){
        populateList();
        return list;
    }
    public void populateList(){
        if (!list.isEmpty()){
            return;
        }
        Learner l1 = new Learner();
        //l1.setId((int) (Math.random() * 10000000));
        l1.setId(1);
        l1.setName("John Doe");
        l1.setEmail("john@gmail.com");

        Learner l2 = new Learner();
        //l2.setId((int) (Math.random() * 10000000));
        l2.setId(2);
        l2.setName("Jane Doe");
        l2.setEmail("jane@gmail.com");

        Learner l3 = new Learner();
        //l3.setId((int) (Math.random() * 10000000));
        l3.setId(3);
        l3.setName("Steve Benett");
        l3.setEmail("steve@gmail.com");

        Learner l4 = new Learner();
        //l4.setId((int) (Math.random() * 10000000));
        l4.setId(4);
        l4.setName("MS Dhoni");
        l4.setEmail("ms@gmail.com");

        list = Arrays.asList(l1,l2,l3,l4);

        list = new ArrayList<>();
        list.add(l1);
        list.add(l2);
        list.add(l3);
        list.add(l4);
    }

    public static void setList(List<Learner> list){
        LearnerUtility.list = list;
    }
}
