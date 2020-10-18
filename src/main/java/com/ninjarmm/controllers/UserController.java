package com.ninjarmm.controllers;

import com.ninjarmm.model.UserModel;
import com.ninjarmm.services.IResource;
import com.ninjarmm.utils.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/rmm-services-server-app/v1/users")
@Api(value = "User Service", tags = "User services, sign up, sign in")
public class UserController {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final IResource<UserModel> userService;


    public UserController(final BCryptPasswordEncoder bCryptPasswordEncoder, final  IResource<UserModel> userService){
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    @ApiOperation(value = "Create a new User", authorizations = @Authorization(value = "Bearer"))
    public ResponseEntity<Object> signUp(@RequestBody final UserModel userModel){
        userModel.setPassword(bCryptPasswordEncoder.encode(userModel.getPassword()));
        var result = userService.add(userModel, userModel);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    @ApiOperation(value = "Get All users", authorizations = @Authorization(value = "Bearer"))
    public ResponseEntity<Object> getAllUsers(@ApiParam("The 0 based page number to be returned") @RequestParam(value = "page", required = false, defaultValue = "0") final int page,
                                              @ApiParam("The number of items to return on each page") @RequestParam(value = "size", required = false, defaultValue = "10") final int size){
        var name = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var result = userService.getMany(page, size);
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<Object> updateUser(@RequestBody final UserModel userModel, @ApiParam(required = true, value = "System id of the user to get") @PathVariable("userId") final String id){
        var username = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!Util.isUUID(id))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        var result = userService.update(userModel.id(UUID.fromString(id)), new UserModel().username(username.toString()));
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@ApiParam(required = true, value = "System id of the user to get") @PathVariable("userId") final String id){
        if(!Util.isUUID(id))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        userService.delete(UUID.fromString(id));
        return ResponseEntity.ok().build();
    }
}
