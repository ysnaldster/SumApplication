package com.example.sumapplication.models;

public class SumRequestBody {

    private int idRequest;
    private int numberOne;
    private int numberTwo;

    public SumRequestBody() {}

    public SumRequestBody(int idRequest, int numberOne, int numberTwo) {
        this.idRequest = idRequest;
        this.numberOne = numberOne;
        this.numberTwo = numberTwo;
    }

    public int getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(int idRequest) {
        this.idRequest = idRequest;
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
