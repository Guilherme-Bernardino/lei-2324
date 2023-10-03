package com.pa.refatoring.extractclass;

public class Calculator {

    //move field
    private int total;

    public Calculator() {
        total = 1;
    }

    //move methods
    public void reset() {
        total = 1;
    }

    public void multiplyBy(Integer operand) {
        total = total * operand;
    }

    public int getTotal() {
        return total;
    }
}
