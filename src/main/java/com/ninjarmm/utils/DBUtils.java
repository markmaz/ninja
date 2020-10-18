package com.ninjarmm.utils;

import org.jooq.Field;

import static org.jooq.impl.DSL.count;

public class DBUtils {
    public static Field getCountOverField(){
        return count().over().as("TotalRecords");
    };
}
