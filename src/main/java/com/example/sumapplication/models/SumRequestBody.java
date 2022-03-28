package com.example.sumapplication.models;

public class SumRequestBody {

    private int id_request;
    private int numberOne;
    private int numberTwo;

    public SumRequestBody() {}

    public SumRequestBody(int id_request, int numberOne, int numberTwo) {
        this.id_request = id_request;
        this.numberOne = numberOne;
        this.numberTwo = numberTwo;
    }

    public int getId_request() {
        return id_request;
    }

    public void setId_request(int id_request) {
        this.id_request = id_request;
    }

    public int getNumberOne() {
        return numberOne;
    }

    public void setNumberOne(int numberOne) {
        this.numberOne = numberOne;
    }

    public int getNumberTwo() {
        return numberTwo;
    }

    public void setNumberTwo(int numberTwo) {
        this.numberTwo = numberTwo;
    }
}
