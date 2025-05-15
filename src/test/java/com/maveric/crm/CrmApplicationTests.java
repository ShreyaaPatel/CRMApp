package com.maveric.crm;

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
//	@Test
//	@Order(7)
//	void testGetCustomersByAge_NotFound(){
//		List<Customer> list = customerServices.getCustomerDetailsByAge(101);
//		assertTrue(list.isEmpty());
//	}

}
