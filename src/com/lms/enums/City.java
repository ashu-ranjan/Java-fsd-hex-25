package com.lms.enums;

public enum City {
    MUMBAI(10), PUNE(5), CHENNAI(6);

    private int val;

    City(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    /*
    *
    * value() : iterates and gives all the cities
    * valueOf(<value>) : tell u if this is a legitimate value
    *
    * how can i convert enums to list
    * List<City> list = new ArrayList<>(Arrays.asList(cities));
    *
    * to make a list if String out of enum:
    * List<String> list = new ArrayList<>();
    * for(City city : cities){
    *   list.add(city.toString());
    * }
    *
    * */
}

