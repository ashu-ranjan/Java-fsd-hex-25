package com.factorypattern;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("------AVAILABLE PAYMENT TYPES------");

        for (PaymentType type : PaymentType.values()){
            System.out.println(type);
        }
        System.out.println("\nEnter your payment type : ");
        String paymentTypeValue = sc.next();

        try {
            /*
            * Convert user given value to enum at first
            * then handle it, if (I can -> forward) else (catch block)
            * */

            PaymentType paymentType = PaymentType.valueOf(paymentTypeValue);

            /*
            * check whether this payment type is active or not
            * */

            if(paymentType.getValue() == 0){
                System.out.println("This is legitimate but currently in-active...");
            } else {
                /*
                * if condition not satisfied means Payment type is correct and active
                * now calling the dailyLimit() for the selected payment option
                * Note : Do not create objects of payment class !!!!IMP!!!!!
                *
                * */
                Payment payment = PaymentFactory.getInstance(paymentType);
                System.out.println("Daily Limit of " + paymentTypeValue + " is " + payment.dailyLimit());

            }
        } catch (IllegalArgumentException e) {
            System.out.println("This payment type is not active and not available right now");
        } catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            sc.close();
        }
    }
}
