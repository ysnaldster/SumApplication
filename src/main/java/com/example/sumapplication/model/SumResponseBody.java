package com.example.sumapplication.model;

public class SumResponseBody {

    private int idResponse;
    private int idRequestFk;
    private String endpoint;
    private int resultSum;

    public SumResponseBody() {
    }

    public SumResponseBody(int idResponse, int idRequestFk, String endpoint, int resultSum) {
        this.idResponse = idResponse;
        this.idRequestFk = idRequestFk;
        this.endpoint = endpoint;
        this.resultSum = resultSum;
    }

    public SumResponseBody(int idRequestFk, String endpoint, int resultSum) {
        this.idRequestFk = idRequestFk;
        this.endpoint = endpoint;
        this.resultSum = resultSum;
    }

    public int getIdResponse() {
        return idResponse;
    }

    public void setId_response(int idResponse) {
        this.idResponse = idResponse;
    }

    public int getIdRequestFk() {
        return idRequestFk;
    }

    public void setIdRequestFk(int idRequestFk) {
        this.idRequestFk = idRequestFk;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public int getResultSum() {
        return resultSum;
    }

    public void setResultSum(int resultSum) {
        this.resultSum = resultSum;
    }
}
