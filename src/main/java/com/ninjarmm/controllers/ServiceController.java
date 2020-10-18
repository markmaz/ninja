package com.ninjarmm.controllers;

import com.ninjarmm.model.PagedResults;
import com.ninjarmm.model.ServiceModel;
import com.ninjarmm.model.UserModel;
import com.ninjarmm.services.IService;
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
@RequestMapping("/rmm-services-server-app/v1/services")
@Api(value = "Company Services", tags = "Company  services")
public class ServiceController extends BaseController {
    private final IService service;

    public ServiceController(final IService service){
        this.service = service;
    }

    @PostMapping
    @ApiOperation(value = "Add a service", authorizations = @Authorization(value = "Bearer"))
    public ResponseEntity<ServiceModel> addService(@RequestBody final ServiceModel serviceModel, @AuthenticationPrincipal final String username){
        var result = service.add(serviceModel, new UserModel().username(username));
        return ResponseEntity.ok(result);
    }

    @PutMapping
    @ApiOperation(value = "Update a Service", authorizations = @Authorization(value = "Bearer"))
    public ResponseEntity<ServiceModel> updateDevice(@ApiParam(required = true, value = "System id of the customer for whom the devices belong")
                                                    @RequestBody final ServiceModel serviceModel, @AuthenticationPrincipal final String username){
        var result = service.update(serviceModel, new UserModel().username(username));
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{serviceId}")
    @ApiOperation(value = "Delete a service", authorizations = @Authorization(value = "Bearer"))
    public ResponseEntity<?> removeDeviceFromCustomer(@ApiParam(required = true, value = "System id of the service that is being deleted.") @PathVariable("serviceId") final String serviceId,
                                                      @AuthenticationPrincipal final String username){
        if(!Util.isUUID(serviceId))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        service.delete(UUID.fromString(serviceId));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{serviceId}")
    @ApiOperation(value = "Get a specific servicer", authorizations = @Authorization(value = "Bearer"))
    public ResponseEntity<ServiceModel> getDevice(@ApiParam(required = true, value = "System id of the service") @PathVariable("serviceId") final String serviceId){
        if(!Util.isUUID(serviceId))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        var result = service.getOne(UUID.fromString(serviceId));
        return ResponseEntity.ok(result);
    }

    @GetMapping
    @ApiOperation(value = "Get all devices for a given customer", authorizations = @Authorization(value = "Bearer"))
    public ResponseEntity<PagedResults<ServiceModel>> getAllDevicesForCustomer(@ApiParam("The 0 based page number to be returned") @RequestParam(value = "page", required = false, defaultValue = "0") final int page,
                                                                              @ApiParam("The number of items to return on each page") @RequestParam(value = "size", required = false, defaultValue = "10") final int size){
        var services = service.getMany(page, size);
        return ResponseEntity.ok(services);
    }
}
