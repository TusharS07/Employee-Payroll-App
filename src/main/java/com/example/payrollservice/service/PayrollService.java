package com.example.payrollservice.service;

import com.example.payrollservice.dto.RegisterDTO;
import com.example.payrollservice.exception.EmpException;
import com.example.payrollservice.model.PayrollModel;
import com.example.payrollservice.repositery.PayrollRepositery;
import com.example.payrollservice.utility.EmailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayrollService  implements IpayrollService {

    @Autowired
    PayrollRepositery payrollRepositery;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    EmailService emailService;


    @Override
    public List<PayrollModel> getAllEmployeeData(int id) {
        if (payrollRepositery.findById(id).get().getRole().equals("Admin")) {
            return payrollRepositery.findAll();
        }
        throw new EmpException("Only Admin Can access This API");
    }


    @Override
    public String registerEmp(RegisterDTO registerDTO) {
        if (payrollRepositery.findByEmail(registerDTO.getEmail()) == null) {
            PayrollModel payrollModel = modelMapper.map(registerDTO, PayrollModel.class);
            payrollRepositery.save(payrollModel);
            emailService.sendMail(registerDTO.getEmail(), "Employee Registered Successfull");
            return "Employee Registered Successfull";
        }
        throw new EmpException("Emplyoee Already Registered");
    }

    @Override
    public PayrollModel getEmpData(int id) {
        if (payrollRepositery.findById(id).isPresent()) {
            return payrollRepositery.findById(id).get();
        }
        throw new EmpException("Employee Not Found "+"\nInvalid Id ");
    }

    @Override
    public String deleteEmpData(int id) {
        if (payrollRepositery.findById(id).isPresent()) {
            payrollRepositery.deleteById(id);
            return "Employee Data Deleted successful";
        }
        throw new EmpException("Employee Not Found "+"\nInvalid Id ");
    }

    @Override
    public PayrollModel updateEmpData(int id, RegisterDTO registerDTO) {
        if (payrollRepositery.findById(id).isPresent()) {
            PayrollModel model = payrollRepositery.findById(id).get();
            PayrollModel update = modelMapper.map(registerDTO, PayrollModel.class);
            update.setId(id);
            if (payrollRepositery.findByEmail(registerDTO.getEmail()) == null) {
                if (update.getName() == null) {
                    update.setName(model.getName());
                }
                if (update.getEmail() == null) {
                    update.setEmail(model.getEmail());
                }
                if (update.getDepartment() == null) {
                    update.setDepartment(model.getDepartment());
                }
                if (update.getGender() == null) {
                    update.setGender(model.getGender());
                }
                if (update.getSalary() == 0) {
                    update.setSalary(model.getSalary());
                }
                if (update.getStartDate() == null) {
                    update.setStartDate(model.getStartDate());
                }
                if (update.getNotes() == null) {
                    update.setNotes(model.getNotes());
                }
                if (update.getRole() == null) {
                    update.setRole(model.getRole());
                }
                return payrollRepositery.save(update);
            }
            throw new EmpException("This email_id is already exist"+"\nplease try with another email id");
        }
        throw new EmpException("Employee Not Found "+"\nInvalid Id ");
    }

}
