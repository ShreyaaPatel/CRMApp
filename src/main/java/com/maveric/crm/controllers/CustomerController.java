package com.maveric.crm.controllers;


import com.maveric.crm.exceptions.CustomerDetailsNotFoundException;
import com.maveric.crm.pojos.Customer;
import com.maveric.crm.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerServices customerServices;


    @PostMapping(value = "/v1/customer" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> acceptCustomerDetails(@RequestBody Customer customerToBeInsert){
        
        Customer insertedCustomer = customerServices.acceptCustomerDetails(customerToBeInsert);
        ResponseEntity <Customer> responseEntity = new ResponseEntity<>(insertedCustomer,HttpStatus.CREATED);
        return  responseEntity;

        //return  new ResponseEntity<>(employeeServices.acceptEmployeeDetails(employeeToBeInsert), HttpStatus.CREATED);

    }

    // PathParam
    @GetMapping( value="/v1/customer/id/{id}" )
    public ResponseEntity <Customer> getCustomerDetails(@PathVariable("id") int id ) throws CustomerDetailsNotFoundException {

        Customer customer = customerServices.getCustomerDetailsById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);

    }
    @GetMapping( value="/v1/customer/age/{age}" )
    public ResponseEntity <List<Customer>>  getCustomerDetailsByAge(@PathVariable("age") int age ) throws CustomerDetailsNotFoundException {
        List<Customer> customers = customerServices.getCustomerDetailsByAge(age);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    @GetMapping( value="/v1/customer/gender/{gender}" )
    public ResponseEntity <List<Customer>>  getCustomerDetailsByGender(@PathVariable("gender") String gender ) throws CustomerDetailsNotFoundException {
        //List<Customer> customers = customerServices.getCustomerDetailsByGender(gender);
        return new ResponseEntity<>(customerServices.getCustomerDetailsByGender(gender), HttpStatus.OK);
    }
@DeleteMapping("/v1/customer/id/{id}")
    public ResponseEntity<String> removeCustomerDetails(@PathVariable("id") int id ) throws CustomerDetailsNotFoundException {
    return new ResponseEntity<>(customerServices.removeCustomerDetails(id), HttpStatus.OK);
}

}
//



