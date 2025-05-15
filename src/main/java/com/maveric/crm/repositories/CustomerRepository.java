package com.maveric.crm.repositories;

import com.maveric.crm.pojos.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findByAge(int age);
    List<Customer> findByGender(String gender);
    List<Customer> findBylastName(String lastName);
    Customer findByEmailId(String emailId);
    List<Customer> findByFirstName(String firstName);
}
