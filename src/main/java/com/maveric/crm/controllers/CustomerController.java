package com.maveric.crm.controllers;


import com.maveric.crm.exceptions.CustomerDetailsNotFoundException;
import com.maveric.crm.pojos.Customer;
import com.maveric.crm.services.CustomerServices;
import jakarta.validation.Valid;
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

//Create Customer
    @PostMapping(value = "/v1/customer" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> acceptCustomerDetails(@Valid @RequestBody Customer customerToBeInsert){
        Customer insertedCustomer = customerServices.acceptCustomerDetails(customerToBeInsert);
        ResponseEntity <Customer> responseEntity = new ResponseEntity<>(insertedCustomer,HttpStatus.CREATED);
        return  responseEntity;

        //return  new ResponseEntity<>(employeeServices.acceptEmployeeDetails(employeeToBeInsert), HttpStatus.CREATED);
    }

    //Get All Customers
    @GetMapping(value = "/v1/customer/all")
    public ResponseEntity <List<Customer>> getCustomerDetails() throws CustomerDetailsNotFoundException {
        List<Customer> allCustomers = customerServices.getCustomerDetails();    return new ResponseEntity<>(allCustomers, HttpStatus.OK);}

    //Get Customer by id
    @GetMapping( value="/v1/customer/id/{id}" )
    public ResponseEntity <Customer> getCustomerDetails(@PathVariable("id") int id ) throws CustomerDetailsNotFoundException {
        Customer customer = customerServices.getCustomerDetailsById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    //Get Customers by age
    @GetMapping( value="/v1/customer/age/{age}" )
    public ResponseEntity <List<Customer>>  getCustomerDetailsByAge(@PathVariable("age") int age ) throws CustomerDetailsNotFoundException {
        List<Customer> customers = customerServices.getCustomerDetailsByAge(age);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    //Get Customers by gender
    @GetMapping( value="/v1/customer/gender/{gender}" )
    public ResponseEntity <List<Customer>>  getCustomerDetailsByGender(@PathVariable("gender") String gender ) throws CustomerDetailsNotFoundException {
        //List<Customer> customers = customerServices.getCustomerDetailsByGender(gender);
        return new ResponseEntity<>(customerServices.getCustomerDetailsByGender(gender), HttpStatus.OK);
    }

    //Get Customer by firstname
    @GetMapping("/v1/customer/firstName/{firstName}")
    public ResponseEntity <List<Customer>> getCustomerDetailsByFirstName(@PathVariable String firstName) throws CustomerDetailsNotFoundException {
        List<Customer> customersToBeFound = customerServices.getCustomerDetailsByFirstName(firstName);
        return new ResponseEntity<>(customersToBeFound, HttpStatus.OK);
    }

    //Delete customer by id
    @DeleteMapping("/v1/customer/id/{id}")
    public ResponseEntity<String> removeCustomerDetails(@PathVariable("id") int id ) throws CustomerDetailsNotFoundException {
    return new ResponseEntity<>(customerServices.removeCustomerDetails(id), HttpStatus.OK);
}
    //Update customer by id
    @PutMapping("/v1/customer/update/{customer}")
    public ResponseEntity<String> updateCustomerDetails(@Valid @RequestBody Customer customer) throws CustomerDetailsNotFoundException {
        customerServices.updateCustomerDetails(customer);
        return new ResponseEntity<>("Successfully Updated", HttpStatus.OK);
    }

    @RequestMapping(value = "/v1/customer/all", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerServices.getCustomerDetails();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}
//



