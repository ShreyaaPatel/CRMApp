package com.maveric.crm.services;

import com.maveric.crm.exceptions.CustomerDetailsNotFoundException;
import com.maveric.crm.pojos.Customer;
import com.maveric.crm.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service("customerService")
public class CustomerServicesImpl implements CustomerServices {


    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer acceptCustomerDetails(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void updateCustomerDetails(Customer customer) throws CustomerDetailsNotFoundException {
     this.getCustomerDetails();
     customerRepository.save(customer);
    }

    @Override
    public String removeCustomerDetails(int id) throws CustomerDetailsNotFoundException {
        this.getCustomerDetailsById(id);
        customerRepository.deleteById(id);
        return "Successfully deleted";

    }

    @Override
    public List<Customer> getCustomerDetails() throws CustomerDetailsNotFoundException {
        List<Customer> customers = customerRepository.findAll();
        if(customers.isEmpty()) throw new CustomerDetailsNotFoundException("Customer details not found");
        return customers;

    }

    @Override
    public Customer getCustomerDetailsById(int id) throws CustomerDetailsNotFoundException {
        Optional<Customer> customer=   customerRepository.findById(id);
        if( customer.isEmpty()) throw  new CustomerDetailsNotFoundException("Customer details not found for Id:  "+id);
        return  customer.get();
    }

    @Override
    public List<Customer> getCustomerDetailsByAge(int age) throws CustomerDetailsNotFoundException {
        List<Customer> customers = customerRepository.findByAge(age);
        if(customers.isEmpty()) throw new CustomerDetailsNotFoundException("Customer details not found for age:" +" "+ age);
        return customers;
    }

    @Override
    public List<Customer> getCustomerDetailsByGender(String gender) throws CustomerDetailsNotFoundException {
        List<Customer> customers = customerRepository.findByGender(gender);
        if(customers.isEmpty()) throw new CustomerDetailsNotFoundException("Customer details not found by gender"+" "+gender);
        return customers;
    }

    @Override
    public List<Customer> getCustomerDetailsByLastName(String lastName) throws CustomerDetailsNotFoundException {
        List<Customer> customers = customerRepository.findBylastName(lastName);
        if(customers.isEmpty()) throw new CustomerDetailsNotFoundException("Customer details not found by LastName"+" "+lastName);
        return customers;
    }
    @Override
    public List<Customer> getCustomerDetailsByFirstName(String firstName) throws CustomerDetailsNotFoundException {
        List<Customer> customers =   customerRepository.findByFirstName(firstName);
        if( customers.isEmpty()) throw  new CustomerDetailsNotFoundException("Customer details not found for firstName :  "+firstName);
        return customers;
    }

    @Override
    public List<Customer> getCustomerByEmail(String emailId) throws CustomerDetailsNotFoundException{
        List<Customer> customers =   customerRepository.findByEmail(emailId);
        if( customers.isEmpty()) throw  new CustomerDetailsNotFoundException("Customer details not found for email :  "+emailId);
        return customers;
    }
}
