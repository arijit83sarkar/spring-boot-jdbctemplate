package com.raven.jdbctemplate.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.raven.jdbctemplate.model.CustomerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerJDBCRepository implements CustomerRepository {

    @Qualifier("jdbcTemplate")
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        return jdbcTemplate
                .queryForObject("select count(*) from customers", Integer.class);
    }

    @Override
    public int saveCustomer(CustomerModel customerModel) {
        String sql = "INSERT INTO customers(customerName, contactLastName, contactFirstName, phone, addressLine1, city, country) " +
                "VALUES(?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, customerModel.getCustomerName(),
                customerModel.getCustomerName().split(" ")[1],
                customerModel.getCustomerName().split(" ")[0],
                customerModel.getPhone(),
                customerModel.getAddress1(),
                customerModel.getCity(),
                customerModel.getCountry());
    }

    @Override
    public int updateCustomer(CustomerModel customerModel, int id) {
        String sql = "UPDATE customers " +
                "SET customerName= ?, contactLastName= ?, contactFirstName= ?, phone=?, addressLine1=?, " +
                "city= ?, country= ? WHERE customerNumber= ?;";
        return jdbcTemplate.update(sql, customerModel.getCustomerName(),
                customerModel.getCustomerName().split(" ")[1],
                customerModel.getCustomerName().split(" ")[0],
                customerModel.getPhone(),
                customerModel.getAddress1(),
                customerModel.getCity(),
                customerModel.getCountry(),
                id);
    }

    @Override
    public int deleteCustomer(int id) {
        String sql = "DELETE FROM customers WHERE customerNumber = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<CustomerModel> findAll() {
        String sql = "select customerNumber, customerName, phone, addressLine1, city, country from customers";
        return jdbcTemplate.query(sql, new CustomerRowMapper());
    }

    @Override
    public Optional<CustomerModel> findByCustomerNumber(int id) {
        String sql = "select customerNumber, customerName, phone, addressLine1, city, country from customers where customerNumber = ?";
        return jdbcTemplate.query(sql, new CustomerRowMapper(), id).stream().findFirst();
    }

    private class CustomerRowMapper implements RowMapper<CustomerModel> {

        @Override
        public CustomerModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new CustomerModel(rs.getInt("customerNumber"),
                    rs.getString("customerName"),
                    rs.getString("phone"),
                    rs.getString("addressLine1"),
                    rs.getString("city"),
                    rs.getString("country"));
        }
    }
}
