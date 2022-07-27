package com.raven.jdbctemplate.controller;

import com.raven.jdbctemplate.model.CustomerModel;
import com.raven.jdbctemplate.repository.CustomerJDBCRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
@Tag(description = "APIs related with Customers", name = "Customer")
public class CustomerController {

    private CustomerJDBCRepository customerJDBCRepository;

    @Autowired
    public CustomerController(CustomerJDBCRepository customerJDBCRepository) {
        this.customerJDBCRepository = customerJDBCRepository;
    }

    @Operation(summary = "Total record count",
            description = "Get total Customer count")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "${api.response-codes.ok.desc}",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CustomerModel.class)))}),
            @ApiResponse(responseCode = "400",
                    description = "${api.response-codes.badRequest.desc}",
                    content = {@Content(examples = {@ExampleObject(value = "")})}),
            @ApiResponse(responseCode = "404",
                    description = "${api.response-codes.notFound.desc}",
                    content = {@Content(examples = {@ExampleObject(value = "")})})})
    @RequestMapping(value = "/getRecordNumber", method = RequestMethod.GET)
    @ResponseBody
    public int getRecordNumber() {
        return customerJDBCRepository.count();
    }

    @Operation(summary = "All customer",
            description = "Get all customer details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "${api.response-codes.ok.desc}",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CustomerModel.class)))}),
            @ApiResponse(responseCode = "400",
                    description = "${api.response-codes.badRequest.desc}",
                    content = {@Content(examples = {@ExampleObject(value = "")})}),
            @ApiResponse(responseCode = "404",
                    description = "${api.response-codes.notFound.desc}",
                    content = {@Content(examples = {@ExampleObject(value = "")})})})
    @RequestMapping(value = "/getAllCustomer", method = RequestMethod.GET)
    @ResponseBody
    public List<CustomerModel> getAllCustomer() {
        return customerJDBCRepository.findAll();
    }

    @Operation(summary = "Customer details by customer number",
            description = "Customer details by customer number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "${api.response-codes.ok.desc}",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CustomerModel.class)))}),
            @ApiResponse(responseCode = "400",
                    description = "${api.response-codes.badRequest.desc}",
                    content = {@Content(examples = {@ExampleObject(value = "")})}),
            @ApiResponse(responseCode = "404",
                    description = "${api.response-codes.notFound.desc}",
                    content = {@Content(examples = {@ExampleObject(value = "")})})})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<CustomerModel> getCustomerByNumber(@PathVariable("id") int id) {
        return customerJDBCRepository.findByCustomerNumber(id);
    }

    @Operation(summary = "Save customer details",
            description = "Save customer details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "${api.response-codes.ok.desc}"),
            @ApiResponse(responseCode = "400",
                    description = "${api.response-codes.badRequest.desc}",
                    content = {@Content(examples = {@ExampleObject(value = "")})}),
            @ApiResponse(responseCode = "404",
                    description = "${api.response-codes.notFound.desc}",
                    content = {@Content(examples = {@ExampleObject(value = "")})})})
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public int save(@Valid @RequestBody CustomerModel customerModel) {
        return customerJDBCRepository.saveCustomer(customerModel);
    }

    @Operation(summary = "Update customer details",
            description = "Update customer details using customer number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "${api.response-codes.ok.desc}"),
            @ApiResponse(responseCode = "400",
                    description = "${api.response-codes.badRequest.desc}",
                    content = {@Content(examples = {@ExampleObject(value = "")})}),
            @ApiResponse(responseCode = "404",
                    description = "${api.response-codes.notFound.desc}",
                    content = {@Content(examples = {@ExampleObject(value = "")})})})
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public int update(@Valid @RequestBody CustomerModel customerModel, @PathVariable("id") int id) {
        return customerJDBCRepository.updateCustomer(customerModel, id);
    }

    @Operation(summary = "Delete a customer",
            description = "Delete a customer using customer number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "${api.response-codes.ok.desc}"),
            @ApiResponse(responseCode = "400",
                    description = "${api.response-codes.badRequest.desc}",
                    content = {@Content(examples = {@ExampleObject(value = "")})}),
            @ApiResponse(responseCode = "404",
                    description = "${api.response-codes.notFound.desc}",
                    content = {@Content(examples = {@ExampleObject(value = "")})})})
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public int delete(@PathVariable("id") int id) {
        return customerJDBCRepository.deleteCustomer(id);
    }
}
