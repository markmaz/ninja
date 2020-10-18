package com.ninjarmm.utils;

import java.util.UUID;

public class Util {
    public static boolean isUUID(String uuid){
        try{
            UUID.fromString(uuid);
            return true;
        }catch (Exception e){
            return  false;
        }
    }
}
