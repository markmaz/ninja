package com.ninjarmm.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public enum DeviceType {

    @JsonProperty("Windows Workstation") WINDOWS_WORKSTATION("Windows Workstation"),
    @JsonProperty("Windows Server") WINDOWS_SERVER("Windows Server"),
    @JsonProperty("MAC") MAC("Mac");

    private static final Map<String, DeviceType> MAP;
    private final String value;

    static {
        var identifierTypeMap = Arrays.stream(values()).collect(toMap(val -> val.value, e -> e));
        MAP = Collections.unmodifiableMap(identifierTypeMap);
    }

    DeviceType(String value){
        this.value = value;
    }

    //Use to get the enum from it's name.
    public static DeviceType of(final String name){
        if(MAP.get(name) == null){
            throw new IllegalArgumentException(name + "is not a valid device type");
        }else{
            return MAP.get(name);
        }
    }

    public final String value(){
        return this.value;
    }
}
