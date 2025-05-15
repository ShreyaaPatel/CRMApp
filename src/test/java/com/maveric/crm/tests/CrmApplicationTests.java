package com.maveric.crm.tests;

import com.maveric.crm.exceptions.CustomerDetailsNotFoundException;
import com.maveric.crm.pojos.Customer;
import com.maveric.crm.services.CustomerServices;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = com.maveric.crm.CrmApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class CrmApplicationTests {
	@Autowired
	CustomerServices customerServices;

	@Test
	@Order(1)
	void testAcceptCustomers() {
		assertNotNull(customerServices.acceptCustomerDetails(new Customer(22, "Female", "S@gmail.com", "Patel", "Shreya")));
	}
	@Test
	@Order(2)
	void testGetCustomerById_Positive() throws CustomerDetailsNotFoundException {
		assertEquals("Shreya",customerServices.getCustomerDetailsById(1).getFirstName());
	}
	@Test
	@Order(3)
	void testGetCustomerBylastName_Positive() throws CustomerDetailsNotFoundException {
		assertFalse(customerServices.getCustomerDetailsBylastName("Patel").isEmpty());
	}
	@Test
	@Order(4)
	void testAcceptMultipleCustomers() {
		Customer c1 = new Customer(30, "Male", "Amrit@example.com", "Verma", "Amrit");
		Customer c2 = new Customer(27, "Male", "Aman@example.com", "Srivastava", "Aman");

		assertNotNull(customerServices.acceptCustomerDetails(c1));
		assertNotNull(customerServices.acceptCustomerDetails(c2));
	}
	@Test
	@Order(5)
	void testGetCustomersByAge_Positive() throws CustomerDetailsNotFoundException{
		assertFalse(customerServices.getCustomerDetailsByAge(22).isEmpty());
	}
	@Test
	@Order(6)
	void testGetCustomersByGender_Positive() {
		List<Customer> females = customerServices.getCustomerDetailsByGender("Female");
		assertFalse(females.isEmpty());
	}
@Test
@Order(7)
void testGetCustomersByGenderCaseInsensitive() {
	List<Customer> females = customerServices.getCustomerDetailsByGender("fEmAle");
	assertFalse(females.isEmpty());
}

	@Test
	@Order(8)
	void testDuplicateEmailAllowedOrNot() {
		Customer c1 = customerServices.acceptCustomerDetails(new Customer(28, "Male", "Ajay@example.com", "sharma", "Ajay"));
		Customer c2 = customerServices.acceptCustomerDetails(new Customer(23, "Male", "Ajay@example.com", "Pandey", "Ajay"));
		assertNotEquals(c1.getId(), c2.getId()); // only passes if duplicate emails are allowed
	}
	@Test
	@Order(9)
	void testCheckFirstNameAfterSaving() {
		Customer customer = new Customer(28, "Female", "neha@example.com", "Sharma", "Neha");
		Customer savedCustomer = customerServices.acceptCustomerDetails(customer);
		assertEquals("Neha", savedCustomer.getFirstName());
	}
	@Test
	@Order(10)
	void testVerifyEmailAfterSavingCustomer() {
		Customer customer = new Customer(30, "Male", "rohan@example.com", "Singh", "Rohan");
		Customer savedCustomer = customerServices.acceptCustomerDetails(customer);
		assertEquals("rohan@example.com", savedCustomer.getEmailId());
	}
}
