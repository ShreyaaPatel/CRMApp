package com.maveric.crm.services;

import com.maveric.crm.exceptions.CustomerDetailsNotFoundException;
import com.maveric.crm.pojos.Customer;

import java.util.List;

public interface CustomerServices {
    Customer acceptCustomerDetails(Customer customer);
    void updateCustomerDetails(Customer customer)throws CustomerDetailsNotFoundException;
    String removeCustomerDetails(int id)throws CustomerDetailsNotFoundException;

    Customer getCustomerDetailsById(int id)throws CustomerDetailsNotFoundException;

    List<Customer> getCustomerDetails() throws CustomerDetailsNotFoundException;
    List<Customer> getCustomerDetailsByAge(int age) throws CustomerDetailsNotFoundException;
    List<Customer> getCustomerDetailsByGender(String  gender)throws  CustomerDetailsNotFoundException;
    List<Customer> getCustomerDetailsBylastName(String lastName)throws CustomerDetailsNotFoundException;
    List<Customer> getCustomerDetailsByFirstName(String firstName) throws CustomerDetailsNotFoundException;

}
