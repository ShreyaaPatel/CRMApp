package com.maveric.crm.tests;

import com.maveric.crm.exceptions.CustomerDetailsNotFoundException;
import com.maveric.crm.pojos.Customer;
import com.maveric.crm.repositories.CustomerRepository;
import com.maveric.crm.services.CustomerServices;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.doNothing;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = com.maveric.crm.CrmApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class CRMApplicationTests {
    @MockitoBean
    CustomerRepository customerRepository;

    @Autowired
    CustomerServices customerServices;

    @BeforeEach
    public void setUpTestEnv() {
        Customer customer1 = new Customer(1, "shreya", "Patel", "s@gmail.com", "Female", 22);
        Customer customer2 = new Customer(2, "aman", "srivastava", "a@gmail.com", "male", 23);
        Customer customer3 = new Customer(3, "Vinay", "patel", "v@gmail.com", "male", 25);
        Customer customer4 = new Customer(4, "Amrit", "Verma", "amrit@gmail.com", "male", 23);
        Customer customer5 = new Customer(5, "Amrit", "sharma", "am@gmail.com", "male", 33);


        //save-1
        Mockito.when(customerRepository.save(new Customer("shreya", "Patel", "s@gmail.com", "Female", 22))).thenReturn(customer1);
        Mockito.when(customerRepository.save(new Customer("aman", "srivastava", "a@gmail.com", "male", 23))).thenReturn(customer2);
        Mockito.when(customerRepository.save(new Customer("Vinay", "patel", "v@gmail.com", "male", 25))).thenReturn(customer3);
        Mockito.when(customerRepository.save(new Customer("Amrit", "Verma", "amrit@gmail.com", "male", 23))).thenReturn(customer4);
        Mockito.when(customerRepository.save(new Customer("Amrit", "sharma", "am@gmail.com", "male", 33))).thenReturn(customer5);


        // findById-2
        Mockito.when(customerRepository.findById(1)).thenReturn(Optional.of(customer1));
        Mockito.when(customerRepository.findById(2)).thenReturn(Optional.of(customer2));
        Mockito.when(customerRepository.findById(3)).thenReturn(Optional.of(customer3));
        Mockito.when(customerRepository.findById(10000)).thenReturn(Optional.ofNullable(null));


        //findByEmailId-3
        Mockito.when(customerRepository.findByEmailId("s@gmail.com")).thenReturn(customer1);
        Mockito.when(customerRepository.findByEmailId("a@gmail.com")).thenReturn(customer2);
        Mockito.when(customerRepository.findByEmailId("v@gmail.com")).thenReturn(customer3);
        Mockito.when(customerRepository.findByEmailId("amrit@gmail.com")).thenReturn(customer4);
        Mockito.when(customerRepository.findByEmailId("aditi@gmail.com")).thenReturn(null);


        //findAll-4
        List<Customer> customers = List.of(customer1, customer2, customer3,customer4,customer5);
        Mockito.when(customerRepository.findAll()).thenReturn(customers);


        //findByFirstName-5
        List<Customer> employeeWithFirstNameAmrit = new ArrayList<>();
        employeeWithFirstNameAmrit.add(customer4);
        employeeWithFirstNameAmrit.add(customer5);
        Mockito.when(customerRepository.findByFirstName("Amrit")).thenReturn(employeeWithFirstNameAmrit);
        Mockito.when(customerRepository.findByFirstName("Aditi")).thenReturn(new ArrayList<>());


        //findByLastName-6
        List<Customer> employeeWithLastNamePatel = new ArrayList<>();
        employeeWithLastNamePatel.add(customer1);
        employeeWithLastNamePatel.add(customer3);
        Mockito.when(customerRepository.findBylastName("Patel")).thenReturn(employeeWithLastNamePatel);
        Mockito.when(customerRepository.findBylastName("Pandey")).thenReturn(new ArrayList<>());



        //deleteById-7
        doNothing().when(customerRepository).deleteById(1);
        doNothing().when(customerRepository).deleteById(999);



        // findByAge-8
        Mockito.when(customerRepository.findByAge(23)).thenReturn(List.of(customer2, customer4));
        Mockito.when(customerRepository.findByAge(30)).thenReturn(List.of(customer5));



        // findByGender-9
        Mockito.when(customerRepository.findByGender("male")).thenReturn(List.of(customer2, customer3, customer4, customer5));
        Mockito.when(customerRepository.findByGender("female")).thenReturn(List.of(customer1));

    }

    @Test
    @Order(1)
    public void testGetCustomerDetailsForValidId() throws CustomerDetailsNotFoundException {
        Customer expectedCustomer = new Customer(1, "shreya", "Patel", "s@gmail.com", "Female", 22);
        Customer actualCustomer = customerServices.getCustomerDetailsById(1);
        Assertions.assertNotNull(actualCustomer);
        assertEquals(expectedCustomer, actualCustomer);
        Mockito.verify(customerRepository, Mockito.atLeastOnce()).findById(Mockito.anyInt());
    }
    @Test
    @Order(2)
    public void testGetCustomerDetailsForInvalidId() {
        Assertions.assertThrows(CustomerDetailsNotFoundException.class, () -> {customerServices.getCustomerDetailsById(10000);});
        Mockito.verify(customerRepository, Mockito.atLeastOnce()).findById(Mockito.anyInt());
    }

    @Test
    @Order(3)
    public void FirstName_Positive() {
        List<Customer> customers = customerServices.getCustomerDetailsByFirstName("Amrit");
        List<Customer> customerWithFirstNameAmrit = new ArrayList<>();
        customerWithFirstNameAmrit.add( new Customer(4, "Amrit", "Verma", "amrit@gmail.com", "male", 23));
        customerWithFirstNameAmrit.add(new Customer(5, "Amrit", "sharma", "am@gmail.com", "male", 33));
        assertFalse(customers.isEmpty());
        assertEquals(customerWithFirstNameAmrit, customers);
        Mockito.verify(customerRepository, Mockito.atLeastOnce()).findByFirstName(Mockito.anyString());
    }
    @Test
    @Order(4)
    void invalidFirstName_Negative(){
        Assertions.assertThrows(CustomerDetailsNotFoundException.class, ()-> customerServices.getCustomerDetailsByFirstName("hello"));
        Mockito.verify(customerRepository, Mockito.atLeastOnce()).findByFirstName("hello");
    }

    @Test
    @Order(5)
    public void LastName_Positive() {
        List<Customer> customers = customerServices.getCustomerDetailsByLastName("Patel");
        assertFalse(customers.isEmpty());
        assertEquals("Patel", customers.get(0).getLastName());
        Mockito.verify(customerRepository, Mockito.atLeastOnce()).findBylastName(Mockito.anyString());
    }
    @Test
    @Order(6)
    void invalidLastName_Negative(){
        Assertions.assertThrows(CustomerDetailsNotFoundException.class,()->customerServices.getCustomerDetailsByLastName("Pandey"));
        Mockito.verify(customerRepository, Mockito.atLeastOnce()).findBylastName("Pandey");
    }
    @Test
    @Order(7)
    public void testSaveCustomer() {
        Customer newCustomer = new Customer(6, "Aditi", "Singh", "aditi@gmail.com", "Female", 27);
        Mockito.when(customerRepository.save(newCustomer)).thenReturn(newCustomer);
        Customer saved = customerServices.acceptCustomerDetails(newCustomer);
        Assertions.assertNotNull(saved);
        assertEquals("Aditi", saved.getFirstName());
        Mockito.verify(customerRepository, Mockito.atLeastOnce()).save(Mockito.any(Customer.class));
    }

    @Test
    @Order(8)
    void removeCustomerDetailsById_Positive() throws CustomerDetailsNotFoundException {
        customerServices.removeCustomerDetails(2);
        Mockito.verify(customerRepository, Mockito.atLeastOnce()).deleteById(2);
    }
    @Test
    @Order(9)
    void removeCustomerDetailsById_Negative() {
        Assertions.assertThrows(CustomerDetailsNotFoundException.class, ()-> customerServices.removeCustomerDetails(1000));
        Mockito.verify(customerRepository, Mockito.atLeastOnce()).findById(1000);
    }
    @Test
    @Order(10)
    void testGetCustomersByGender_Positive() throws CustomerDetailsNotFoundException{
        customerServices.getCustomerDetailsByGender("Female");
        Mockito.verify(customerRepository, Mockito.atLeastOnce()).findByGender("Female");
    }
    @Test
    @Order(11)
    void testGetCustomersByGender_Negative() {
        Assertions.assertThrows(CustomerDetailsNotFoundException.class, ()-> customerServices.removeCustomerDetails(1000));
        Mockito.verify(customerRepository, Mockito.atLeastOnce()).findByGender("female");
    }
    @Test
    @Order(12)
    void testGetCustomersByGenderCaseInsensitive() {
        Assertions.assertThrows(CustomerDetailsNotFoundException.class,()->customerServices.getCustomerDetailsByGender("feMale"));
        Mockito.verify(customerRepository, Mockito.atLeastOnce()).findByGender("feMale");
    }

    @Test
    @Order(13)
    void testFindByAge() {
        List<Customer> customers = customerServices.getCustomerDetailsByAge(23);
        assertFalse(customers.isEmpty());
        assertEquals(23, customers.size());
        Mockito.verify(customerRepository,Mockito.atLeastOnce()).findByAge(23);
    }
    @Test
    @Order(14)
    void testFindByAgeNotFound() {
        Assertions.assertThrows(CustomerDetailsNotFoundException.class, ()-> customerServices.getCustomerDetailsByAge(26));
        Mockito.verify(customerRepository, Mockito.atLeastOnce()).findByAge(26);
    }

}




