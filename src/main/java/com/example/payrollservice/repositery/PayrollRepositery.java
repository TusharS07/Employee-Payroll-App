package com.example.payrollservice.repositery;

import com.example.payrollservice.model.PayrollModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayrollRepositery extends JpaRepository<PayrollModel, Integer> {

    PayrollModel findByEmail(String email);
}
