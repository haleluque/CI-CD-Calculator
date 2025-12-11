package com.example.Calculator.controller;

import com.example.Calculator.services.ICalculatorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CalculatorController.class)
public class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICalculatorService calculatorService;

    @Test
    public void testSum() throws Exception {
        when(calculatorService.sum(2.0, 3.0)).thenReturn(5.0);

        mockMvc.perform(get("/sum/2.0/3.0"))
                .andExpect(status().isOk())
                .andExpect(content().string("5.0"));
    }

    @Test
    public void testSubtract() throws Exception {
        when(calculatorService.subtract(3.0, 2.0)).thenReturn(1.0);

        mockMvc.perform(get("/subtract/3.0/2.0"))
                .andExpect(status().isOk())
                .andExpect(content().string("1.0"));
    }

    @Test
    public void testMultiply() throws Exception {
        when(calculatorService.multiply(2.0, 3.0)).thenReturn(6.0);

        mockMvc.perform(get("/multiply/2.0/3.0"))
                .andExpect(status().isOk())
                .andExpect(content().string("6.0"));
    }

    @Test
    public void testDivide() throws Exception {
        when(calculatorService.divide(6.0, 3.0)).thenReturn(2.0);

        mockMvc.perform(get("/divide/6.0/3.0"))
                .andExpect(status().isOk())
                .andExpect(content().string("2.0"));
    }
}
