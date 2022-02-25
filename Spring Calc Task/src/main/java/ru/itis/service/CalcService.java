package ru.itis.service;

import org.springframework.stereotype.Service;

@Service
public class CalcService {
    public Integer getSum(Integer a, Integer b){
        Integer sum = a + b;
        return sum;
    }
}
