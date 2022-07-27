package com.raven.jdbctemplate.repository;

import java.util.List;
import java.util.Optional;

import com.raven.jdbctemplate.model.CustomerModel;

public interface CustomerRepository {
    // gets the total record count
    int count();

    // saves a customer
    int saveCustomer(CustomerModel customerModel);

    // updates an existing customer
    int updateCustomer(CustomerModel customerModel, int id);

    // deletes ann existing customer
    int deleteCustomer(int id);

    // get all customer
    List<CustomerModel> findAll();

    // get a customer by CustomerNumber
    Optional<CustomerModel> findByCustomerNumber(int id);
}
