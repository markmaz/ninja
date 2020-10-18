package com.ninjarmm.controllers;

import com.ninjarmm.model.Bill;
import com.ninjarmm.model.CustomerModel;
import com.ninjarmm.model.PagedResults;
import com.ninjarmm.model.UserModel;
import com.ninjarmm.services.ICustomerService;
import com.ninjarmm.utils.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rmm-services-server-app/v1/customers")
@Api(value = "Customer Service", tags = "Customer services")
public class CustomerController extends BaseController{
    private final ICustomerService customerService;

    public CustomerController(final ICustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping(path = "/{customerId}")
    @ApiOperation(value = "Get a specific customer's information", authorizations = @Authorization(value = "Bearer"))
    public ResponseEntity<CustomerModel> getCustomerById(@ApiParam(required = true, value = "System id of the customer to get") @PathVariable("customerId") final String id){
        if(!Util.isUUID(id))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        var customer = this.customerService.getOne(UUID.fromString(id));
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    @ApiOperation(value = "Create a new Customer", authorizations = @Authorization(value = "Bearer"))
    public ResponseEntity<CustomerModel> createCustomer(@RequestBody final CustomerModel customerModel, @AuthenticationPrincipal final String username){
        var result = this.customerService.add(customerModel, new UserModel().username(username));
        return ResponseEntity.ok(result);
    }

    @PutMapping
    @ApiOperation(value = "Update a given customer record", authorizations = @Authorization(value = "Bearer"))
    public ResponseEntity<CustomerModel> updateCustomer(@RequestBody final CustomerModel customerModel, @AuthenticationPrincipal final String username){

        var result = this.customerService.update(customerModel, new UserModel().username(username));
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(path="/{customerId}")
    @ApiOperation(value = "Delete a given customer record", authorizations = @Authorization(value = "Bearer"))
    public ResponseEntity<?> deleteCustomer(@ApiParam(required = true, value = "System id of the customer to get") @PathVariable("customerId") final String id){
        if(!Util.isUUID(id))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        this.customerService.delete(UUID.fromString(id));
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @ApiOperation(value = "Get all customers", authorizations = @Authorization(value = "Bearer"))
    public ResponseEntity<PagedResults<CustomerModel>> getAllCustomers(@ApiParam("The 0 based page number to be returned") @RequestParam(value = "page", required = false, defaultValue = "0") final int page,
                                                                      @ApiParam("The number of items to return on each page") @RequestParam(value = "size", required = false, defaultValue = "10") final int size,
                                                                      @ApiParam("List of columns with optional directions to sort by ") @RequestParam(value = "sort", required = false, defaultValue = "") final List<String> sort){
        var customers = this.customerService.getMany(page, size);
        return ResponseEntity.ok(customers);
    }

    @GetMapping(path = "/{customerId}/bill")
    @ApiOperation(value = "Get the customer's bill", authorizations = @Authorization(value = "Bearer"))
    public ResponseEntity<Bill> getCustomerBill(@ApiParam(required = true, value = "System id of the customer to get") @PathVariable("customerId") final String id){
        if(!Util.isUUID(id))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        var bill = this.customerService.generateBill(UUID.fromString(id));
        return ResponseEntity.ok(bill);
    }


}
