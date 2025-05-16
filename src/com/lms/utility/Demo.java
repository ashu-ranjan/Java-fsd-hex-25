package com.lms.utility;

import com.lms.enums.City;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        /*
        * Show all allowed values
        * Iterate over the enum and display values,
        * No need to convert in to any other type
        * */
        System.out.println("------ALLOWED VALUES------");
        for(City city : City.values()){
            System.out.println(city);
        }

        /*
        * Show all allowed values --- convert them into String
        * Create a List if type String and add all enum values in to this list
        * */
        System.out.println("------ALLOWED VALUES AS STRING------");
        List<String> listString = new ArrayList<>();
        for (City city : City.values()){
            listString.add(city.toString());
        }
        listString.stream().forEach(e -> System.out.println(e));

        /*
        * Read a value and identify weather it is valid enum value
        * */
        System.out.println("------IDENTIFY ALLOWED VALUE------");
        String city = "mumbai";
        System.out.println(listString.contains(city.toUpperCase()) ? "Valid" : "Invalid");

        /*
        * Identify weather it is valid enum value without String conversion
        * */
        city = null;
        System.out.println("------IDENTIFY ALLOWED VALUE : W/O STRING");
        try {
            City urValue = City.valueOf(city);
            System.out.println("Yes ur value " + urValue + " is correct");
        } catch(IllegalArgumentException e) {
            System.out.println("No, ur value is not a legal enum value " + city);
            //e.printStackTrace();
        }
        catch(NullPointerException e) {
            System.out.println("Hey!! Looks like you r giving a null value to enum");
        }
    }
}
