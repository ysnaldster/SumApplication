package com.example.sumapplication.service;

import org.springframework.stereotype.Service;

@Service
public class SumService {

    public int operationSum(int numberOne, int numberTwo) {
        return numberOne + numberTwo;
    }
}
