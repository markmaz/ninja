package com.ninjarmm.controllers;

import com.ninjarmm.model.CustomerModel;
import com.ninjarmm.model.PagedResults;
import com.ninjarmm.model.ServiceModel;
import com.ninjarmm.model.UserModel;
import com.ninjarmm.services.ICustomerServiceService;
import com.ninjarmm.utils.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/rmm-services-server-app/v1")
@Api(value = "Customer Service", tags = "Customer to Service relationship")
public class CustomerServiceController extends BaseController{
    private final ICustomerServiceService service;

    public CustomerServiceController(final ICustomerServiceService service){
        this.service = service;
    }

    @GetMapping("/customers/{customerId}/services")
    @ApiOperation(value = "Get all services for customer", authorizations = @Authorization(value = "Bearer"))
    public ResponseEntity<PagedResults<ServiceModel>> getAllServices(@ApiParam(required = true, value = "System id of the customer to get") @PathVariable("customerId") final String customerId,
                                                                     @ApiParam("The 0 based page number to be returned") @RequestParam(value = "page", required = false, defaultValue = "0") final int page,
                                                                     @ApiParam("The number of items to return on each page") @RequestParam(value = "size", required = false, defaultValue = "10") final int size){
        if(!Util.isUUID(customerId))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        var services = service.getAllServicesForCustomer(UUID.fromString(customerId), page, size);
        return ResponseEntity.ok(services);
    }

    @GetMapping("/services/{serviceId}/customers")
    @ApiOperation(value = "Get all customers for a service", authorizations = @Authorization(value = "Bearer"))
    public ResponseEntity<PagedResults<CustomerModel>> getAllCustomers(@ApiParam(required = true, value = "System id of the customer to get") @PathVariable("serviceId") final String serviceId,
                                                                       @ApiParam("The 0 based page number to be returned") @RequestParam(value = "page", required = false, defaultValue = "0") final int page,
                                                                       @ApiParam("The number of items to return on each page") @RequestParam(value = "size", required = false, defaultValue = "10") final int size){
        if(!Util.isUUID(serviceId))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        var customers = service.getAllCustomersForService(UUID.fromString(serviceId), page, size);
        return ResponseEntity.ok(customers);
    }


    @PutMapping("/customers/{customerId}/services/{serviceId}")
    @ApiOperation(value = "Add a service to a customer", authorizations = @Authorization(value = "Bearer"))
    public ResponseEntity<PagedResults<ServiceModel>> addServiceToCustomer(@ApiParam(required = true, value = "System id of the customer") @PathVariable("customerId") final String customerId,
                                                                     @ApiParam(required = true, value = "System id of the system to relate") @PathVariable("serviceId") final String serviceId,
                                                                     @AuthenticationPrincipal final String username){
        if(!Util.isUUID(customerId) || !Util.isUUID(serviceId))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        service.addServiceToCustomer(UUID.fromString(customerId), UUID.fromString(serviceId), new UserModel().username(username));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/customers/{customerId}/services/{serviceId}")
    @ApiOperation(value = "Remove a service to a customer", authorizations = @Authorization(value = "Bearer"))
    public ResponseEntity<PagedResults<ServiceModel>> removeServiceFromCustomer(@ApiParam(required = true, value = "System id of the customer") @PathVariable("customerId") final String customerId,
                                                                     @ApiParam(required = true, value = "System id of the system to relate") @PathVariable("serviceId") final String serviceId,
                                                                     @AuthenticationPrincipal final String username){
        if(!Util.isUUID(customerId) || !Util.isUUID(serviceId))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        service.removeServiceFromCustomer(UUID.fromString(customerId), UUID.fromString(serviceId));
        return ResponseEntity.ok().build();
    }
}
