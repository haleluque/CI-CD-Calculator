package com.example.Calculator.controller;

import com.example.Calculator.services.ICalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

    private final ICalculatorService calculatorService;

    @Autowired
    public CalculatorController(ICalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/sum/{a}/{b}")
    public double sum(@PathVariable double a, @PathVariable double b) {
        return calculatorService.sum(a, b);
    }

    @GetMapping("/subtract/{a}/{b}")
    public double subtract(@PathVariable double a, @PathVariable double b) {
        return calculatorService.subtract(a, b);
    }

    @GetMapping("/multiply/{a}/{b}")
    public double multiply(@PathVariable double a, @PathVariable double b) {
        return calculatorService.multiply(a, b);
    }

    @GetMapping("/divide/{a}/{b}")
    public double divide(@PathVariable double a, @PathVariable double b) {
        return calculatorService.divide(a, b);
    }
}
