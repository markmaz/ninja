package com.ninjarmm.services.impl;

import com.ninjarmm.dao.jooq.tables.pojos.Customer;
import com.ninjarmm.dao.jooq.tables.pojos.Device;
import com.ninjarmm.dao.jooq.tables.pojos.Service;
import com.ninjarmm.dao.jooq.tables.pojos.User;
import com.ninjarmm.exceptions.UnprocessableEntityException;
import com.ninjarmm.model.*;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class MappingBuilder {
    public static ModelMapper Build() {
        var mapper = new ModelMapper();

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        buildUser(mapper);
        buildCustomer(mapper);
        buildeDevice(mapper);
        buildService(mapper);
        return mapper;
    }

    static final
    Converter<String, DeviceType> StringToDeviceType = ctx ->
    {
        if (ctx.getSource() == null)
        {
            return null;
        }
        else if (ctx.getSource().equalsIgnoreCase("MAC"))
        {
            return DeviceType.MAC;
        }
        else if (ctx.getSource().equalsIgnoreCase("Windows Server"))
        {
            return DeviceType.WINDOWS_SERVER;
        }
        else if (ctx.getSource().equalsIgnoreCase("Windows Workstation"))
        {
            return DeviceType.WINDOWS_SERVER;
        }

        throw new UnprocessableEntityException("Invalid device type");
    };

    private static void buildUser(ModelMapper mapper){
        mapper.typeMap(User.class, UserModel.class).addMappings(m ->
        {
            m.map(User::getId, UserModel::setId);
            m.map(User::getPassword, UserModel::setPassword);
            m.map(User::getUsername, UserModel::setUsername);
            m.map(User::getLastmodifiedby, UserModel::setLastModifiedBy);
            m.map(User::getLastmodifieddatetime, UserModel::setLastModifiedDateTime);
        });

        mapper.typeMap(UserModel.class, User.class).addMappings(m ->
        {
            m.map(UserModel::getId, User::setId);
            m.map(UserModel::getPassword, User::setPassword);
            m.map(UserModel::getUsername, User::setUsername);
            m.map(UserModel::getLastModifiedBy, User::setLastmodifiedby);
            m.map(UserModel::getLastModifiedDateTime, User::setLastmodifieddatetime);
        });
    }

    private static void buildCustomer(ModelMapper mapper){
        mapper.typeMap(Customer.class, CustomerModel.class).addMappings(m ->{
            m.map(Customer::getId, CustomerModel::setId);
            m.map(Customer::getName, CustomerModel::setCustomerName);
            m.map(Customer::getLastmodifiedby, CustomerModel::setLastModifiedBy);
            m.map(Customer::getLastmodifieddatetime, CustomerModel::setLastModifiedDateTime);
        });

        mapper.typeMap(CustomerModel.class, Customer.class).addMappings(m ->{
            m.map(CustomerModel::getId, Customer::setId);
            m.map(CustomerModel::getCustomerName, Customer::setName);
            m.map(CustomerModel::getLastModifiedBy, Customer::setLastmodifiedby);
            m.map(CustomerModel::getLastModifiedDateTime, Customer::setLastmodifieddatetime);
        });
    }

    private static void buildeDevice(ModelMapper mapper){
        mapper.typeMap(Device.class, DeviceModel.class).addMappings(m ->{
            m.map(Device::getId, DeviceModel::setId);
            m.map(Device::getSystemname, DeviceModel::setSystemName);
            m.map(Device::getDevicetype, DeviceModel::setDeviceType);
            m.map(Device::getLastmodifiedby, DeviceModel::setLastModifiedBy);
            m.map(Device::getLastmodifieddatetime, DeviceModel::setLastModifiedDateTime);
            m.map(Device::getDevicecost, DeviceModel::setDeviceCost);
        });

        mapper.typeMap(DeviceModel.class, Device.class).addMappings(m ->{
            m.map(DeviceModel::getId, Device::setId);
            m.map(DeviceModel::getDeviceType, Device::setDevicetype);
            m.map(DeviceModel::getSystemName, Device::setSystemname);
            m.map(DeviceModel::getLastModifiedBy, Device::setLastmodifiedby);
            m.map(DeviceModel::getLastModifiedDateTime, Device::setLastmodifieddatetime);
            m.map(DeviceModel::getDeviceCost, Device::setDevicecost);
        });
    }

    private static void buildService(ModelMapper mapper){
        mapper.typeMap(Service.class, ServiceModel.class).addMappings(m ->{
            m.map(Service::getId, ServiceModel::setId);
            m.map(Service::getServicename, ServiceModel::setServiceName);
            m.map(Service::getPriceformac, ServiceModel::setMacPricing);
            m.map(Service::getPriceforwindows, ServiceModel::setWindowsPricing);
            m.map(Service::getLastmodifiedby, ServiceModel::setLastModifiedBy);
            m.map(Service::getLastmodifieddatetime, ServiceModel::setLastModifiedDateTime);
            m.map(Service::getComments, ServiceModel::setComment);
        });

        mapper.typeMap(ServiceModel.class, Service.class).addMappings(m ->{
            m.map(ServiceModel::getId, Service::setId);
            m.map(ServiceModel::getServiceName, Service::setServicename);
            m.map(ServiceModel::getMacPricing, Service::setPriceformac);
            m.map(ServiceModel::getWindowsPricing, Service::setPriceforwindows);
            m.map(ServiceModel::getLastModifiedBy, Service::setLastmodifiedby);
            m.map(ServiceModel::getLastModifiedDateTime, Service::setLastmodifieddatetime);
            m.map(ServiceModel::getComment, Service::setComments);
        });
    }
}
