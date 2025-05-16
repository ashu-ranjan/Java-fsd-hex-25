package com.factorypattern;

public enum PaymentType {
    UPI(1),
    NEFT(1),
    IMPS(0),
    RTGS(0);

    PaymentType(int value) {
        this.value = value;
    }

    private int value;

    public int getValue() {
        return value;
    }
}
