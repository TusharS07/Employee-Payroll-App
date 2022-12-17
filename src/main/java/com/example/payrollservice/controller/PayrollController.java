package com.example.payrollservice.controller;


import com.example.payrollservice.Response;
import com.example.payrollservice.dto.RegisterDTO;
import com.example.payrollservice.model.PayrollModel;
import com.example.payrollservice.service.IpayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Emplyoee_Payroll")
public class PayrollController {

    @Autowired
    IpayrollService ipayrollService;

    @PostMapping("/Register_Employee")
    public ResponseEntity<Response> registerEmp(@RequestBody RegisterDTO registerDTO) {
        ipayrollService.registerEmp(registerDTO);
        Response response = new Response(registerDTO, "Employee Registered Successfull");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/Find_Employee_Data")
    public ResponseEntity<Response> getEmpData(@RequestParam int id) {
        PayrollModel emplyoeeData = ipayrollService.getEmpData(id);
        Response response = new Response(emplyoeeData, "Emplyoee Data");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/Delete_Employee_Data")
    public ResponseEntity<Response> deleteEmpData(@RequestParam int id) {
        ipayrollService.deleteEmpData(id);
        Response response = new Response("Deleted Employee Data for id: " + id, "Deleted Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/Update_Employee_Data")
    public ResponseEntity<Response> updateEmpData(@RequestParam int id, @RequestBody RegisterDTO registerDTO) {
        PayrollModel update = ipayrollService.updateEmpData(id, registerDTO);
        Response response = new Response(update, "Employee Data Updated Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/Show_All_Emp_Data")
    public ResponseEntity<Response> showAllEmpData(@RequestParam int id){
        List<PayrollModel> payrollModelList = ipayrollService.getAllEmployeeData(id);
        Response response = new Response(payrollModelList, "All Employee Data" );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}