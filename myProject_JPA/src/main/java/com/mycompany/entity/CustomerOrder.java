/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author abdelkafi_s
 */

@Entity
@Table(name = "customer_order")
public class CustomerOrder {

    public CustomerOrder() {
        super();
    }

    public CustomerOrder(Long orderId, Customer customer, Date datePlaced, Date datePromised, String terms, String status) {
        this.orderId = orderId;
        this.customer = customer;
        this.datePlaced = datePlaced;
        this.datePromised = datePromised;
        this.terms = terms;
        this.status = status;
    }
    
    @Id
    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @Column(name = "date_placed", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datePlaced;

    @Column(name = "date_promised", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datePromised;

    @Column(name = "terms", nullable = false)
    private String terms;

    @Column(name = "status", nullable = false)
    private String status;

   
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getDatePlaced() {
        return datePlaced;
    }

    public void setDatePlaced(Date datePlaced) {
        this.datePlaced = datePlaced;
    }

    public Date getDatePromised() {
        return datePromised;
    }

    public void setDatePromised(Date datePromised) {
        this.datePromised = datePromised;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }
    
    
}
