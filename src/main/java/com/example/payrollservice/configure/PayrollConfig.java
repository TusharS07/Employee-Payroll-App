package com.example.payrollservice.configure;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PayrollConfig {

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
