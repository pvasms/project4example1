/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.entity;

import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "customer")
public class Customer  {

    public Customer() {
        super();
    }

    public Customer(Integer taxId, String name, String adresse, String city, String state, String zip, String phone, List<CustomerOrder> CustomerOrders) {
      
        this.taxId = taxId;
        this.name = name;
        this.adresse = adresse;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.CustomerOrders = CustomerOrders;
    }

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "tax_id", nullable = false)
    private Integer taxId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "adresse", nullable = false)
    private String adresse;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "zip", nullable = false)
    private String zip;

    @Column(name = "phone", nullable = false)
    private String phone;

    @OneToMany
    private List<CustomerOrder> CustomerOrders;

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getTaxId() {
        return taxId;
    }

    public void setTaxId(Integer taxId) {
        this.taxId = taxId;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public List<CustomerOrder> getCustomerOrders() {
        return CustomerOrders;
    }

    public void setCustomerOrders(List<CustomerOrder> CustomerOrders) {
        this.CustomerOrders = CustomerOrders;
    }

    
}
