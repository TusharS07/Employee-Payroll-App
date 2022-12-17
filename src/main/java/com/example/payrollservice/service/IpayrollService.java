package com.example.payrollservice.service;

import com.example.payrollservice.dto.RegisterDTO;
import com.example.payrollservice.model.PayrollModel;

import java.util.List;

public interface IpayrollService {

    String registerEmp(RegisterDTO registerDTO);

    PayrollModel getEmpData(int id);
    String deleteEmpData(int id);

    PayrollModel updateEmpData(int id, RegisterDTO registerDTO);

    List<PayrollModel> getAllEmployeeData(int id);
}
