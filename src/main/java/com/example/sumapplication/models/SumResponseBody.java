package com.example.sumapplication.models;

public class SumResponseBody {
    private int id_response;
    private int id_request_fk;
    private String endpoint;
    private int result_sum;

    public SumResponseBody() {}

    public SumResponseBody(int id_response, int id_request_fk, String endpoint, int result_sum) {
        this.id_response = id_response;
        this.id_request_fk = id_request_fk;
        this.endpoint = endpoint;
        this.result_sum = result_sum;
    }

    public int getId_response() {
        return id_response;
    }

    public void setId_response(int id_response) {
        this.id_response = id_response;
    }

    public int getId_request_fk() {
        return id_request_fk;
    }

    public void setId_request_fk(int id_request_fk) {
        this.id_request_fk = id_request_fk;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public int getResult_sum() {
        return result_sum;
    }

    public void setResult_sum(int result_sum) {
        this.result_sum = result_sum;
    }
}
