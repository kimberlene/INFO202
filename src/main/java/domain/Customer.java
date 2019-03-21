/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import net.sf.oval.constraint.Email;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

/**
 *
 * @author shaki694
 */
public class Customer implements Comparable<Customer> {

    private String personId;
    @NotNull(message = "Username must be provided.")
    @NotBlank(message = "Username must be provided.")
    @Length(min = 2, message = "Username must contain at least two characters.")
    private String username;
    @NotNull(message = "First Name must be provided.")
    @NotBlank(message = "First Name must be provided.")
    @Length(min = 2, message = "First Name must contain at least two characters.")
    private String firstname;
    @NotNull(message = "Surname must be provided.")
    @NotBlank(message = "Surname must be provided.")
    @Length(min = 2, message = "Surname must contain at least two characters.")
    private String surname;
    @NotNull(message = "Password must be provided.")
    @NotBlank(message = "Password must be provided.")
    @Length(min = 5, message = "Password must contain at least five characters.")
    private String password;
    @NotNull(message = "Email Address must be provided.")
    @NotBlank(message = "Email Address must be provided.")
    @Email(message = "Please enter a valid Email address.")
    private String emailAddress;
    @NotNull(message = "Address must be provided.")
    @NotBlank(message = "Address must be provided.")
    @Length(min = 5, message = "Address must contain at least five characters.")
    private String address;
    @NotNull(message = "Credit Card Details must be provided.")
    @NotBlank(message = "Credit Card details must be provided.")
    @Length(min = 16, message = "Please Enter valid credit card details")
    private String creditCardDetails;

    public Customer(String personId, String username, String firstname, String surname, String password, String emailAddress, String address, String creditCardDetails) {
        this.personId = personId;
        this.username = username;
        this.firstname = firstname;
        this.surname = surname;
        this.password = password;
        this.emailAddress = emailAddress;
        this.address = address;
        this.creditCardDetails = creditCardDetails;
    }

    public Customer() {

    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreditCardDetails() {
        return creditCardDetails;
    }

    public void setCreditCardDetails(String creditCardDetails) {
        this.creditCardDetails = creditCardDetails;
    }

    @Override
    public String toString() {
        return "Customer{" + "personId=" + personId + ", username=" + username + ", firstname=" + firstname + ", surname=" + surname + ", password=" + password + ", emailAddress=" + emailAddress + ", address=" + address + ", creditCardDetails=" + creditCardDetails + '}';
    }

    @Override
    public int compareTo(Customer t) {
        String id = this.getPersonId();
        String id2 = t.getPersonId();
        return id.compareTo(id2);
    }

}
