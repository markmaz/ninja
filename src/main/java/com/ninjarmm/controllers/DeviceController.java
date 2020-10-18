package com.ninjarmm.controllers;

import com.ninjarmm.model.DeviceModel;
import com.ninjarmm.model.PagedResults;
import com.ninjarmm.model.UserModel;
import com.ninjarmm.services.IDeviceService;
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
@RequestMapping("/rmm-services-server-app/v1/customers/{customerId}/devices")
@Api(value = "Device Services", tags = "Device services")
public class DeviceController extends BaseController{
    private final IDeviceService deviceService;

    public DeviceController(final IDeviceService deviceService){
        this.deviceService = deviceService;
    }

    @GetMapping
    @ApiOperation(value = "Get all devices for a given customer", authorizations = @Authorization(value = "Bearer"))
    public ResponseEntity<PagedResults<DeviceModel>> getAllDevicesForCustomer(@ApiParam("The 0 based page number to be returned") @RequestParam(value = "page", required = false, defaultValue = "0") final int page,
                                                                              @ApiParam("The number of items to return on each page") @RequestParam(value = "size", required = false, defaultValue = "10") final int size,
                                                                              @ApiParam(required = true, value = "System id of the customer for whom the devices belong") @PathVariable("customerId") final String customerId){
        if(!Util.isUUID(customerId))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        var devices = deviceService.getManyByCustomer(UUID.fromString(customerId), page, size);
        return ResponseEntity.ok(devices);
    }

    @PostMapping
    @ApiOperation(value = "Add a device to a given customer", authorizations = @Authorization(value = "Bearer"))
    public ResponseEntity<DeviceModel> addDeviceToCustomer(@ApiParam(required = true, value = "System id of the customer for whom the devices belong") @PathVariable("customerId") final String customerId,
                                                           @RequestBody final DeviceModel device, @AuthenticationPrincipal final String username){
        var result = deviceService.add(device, UUID.fromString(customerId), new UserModel().username(username));
        return ResponseEntity.ok(result);
    }

    @PutMapping
    @ApiOperation(value = "Add a device to a given customer", authorizations = @Authorization(value = "Bearer"))
    public ResponseEntity<DeviceModel> updateDevice(@ApiParam(required = true, value = "System id of the customer for whom the devices belong") @PathVariable("customerId") final String customerId,
                                                           @RequestBody final DeviceModel device, @AuthenticationPrincipal final String username){
        var result = deviceService.update(device, UUID.fromString(customerId), new UserModel().username(username));
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{deviceId}")
    @ApiOperation(value = "Remove a device from a given customer", authorizations = @Authorization(value = "Bearer"))
    public ResponseEntity<?> removeDeviceFromCustomer(@ApiParam(required = true, value = "System id of the customer for whom the devices belong") @PathVariable("customerId") final String customerId,
                                                           @PathVariable final String deviceId, @AuthenticationPrincipal final String username){
        if(!Util.isUUID(deviceId))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        deviceService.delete(UUID.fromString(deviceId));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{deviceId}")
    @ApiOperation(value = "Get a specific device for a given customer", authorizations = @Authorization(value = "Bearer"))
    public ResponseEntity<DeviceModel> getDevice(@ApiParam(required = true, value = "System id of the customer for whom the device belong") @PathVariable("customerId") final String customerId,
                                                 @ApiParam(required = true, value = "System id of the device") @PathVariable("deviceId") final String deviceId){
        if(!Util.isUUID(customerId) || !Util.isUUID(deviceId))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        var device = deviceService.getOne(UUID.fromString(deviceId), UUID.fromString(customerId));
        return ResponseEntity.ok(device);
    }
}
