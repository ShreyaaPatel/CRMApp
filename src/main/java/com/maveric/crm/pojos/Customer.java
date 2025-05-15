package com.maveric.crm.pojos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

import java.util.Objects;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    @NotNull(message = "First name is required")
    @Size(min = 1, message = "First name can not be empty!")
    @NotNull(message = "Last name is required")
    @Size(min = 1, message = "Last name can not be empty!")
    private String firstName, lastName;

    @NotNull(message = "Gender must be Male or Female")
    private String gender;

    @Email(message = "Email should be valid")
    @NotNull(message = "Email is required")
    private String emailId;

    @Min(value=18 ,message = "Age should be above or equal to 18")
    @Max(value=100 ,message = "Age should be below or equal to 100")
    private int age;

    public Customer() {
    }

    public Customer(int age, String gender, String emailId, String lastName, String firstName) {
        this.age = age;
        this.gender = gender;
        this.emailId = emailId;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public Customer(int id, String firstName, String lastName, String emailId, String gender, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.gender = gender;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && age == customer.age && Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName) && Objects.equals(emailId, customer.emailId) && Objects.equals(gender, customer.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, emailId, gender, age);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                '}';
    }
}




