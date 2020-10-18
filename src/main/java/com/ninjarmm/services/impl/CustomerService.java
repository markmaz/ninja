package com.ninjarmm.services.impl;

import com.ninjarmm.dao.ICustomerServiceDataService;
import com.ninjarmm.dao.IDataResource;
import com.ninjarmm.dao.IDeviceDataService;
import com.ninjarmm.dao.jooq.tables.pojos.Customer;
import com.ninjarmm.model.*;
import com.ninjarmm.services.ICustomerService;
import com.ninjarmm.services.IMapperService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CustomerService implements ICustomerService {
    private final IDataResource<Customer> dataService;
    private final ICustomerServiceDataService serviceDataService;
    private final IDeviceDataService deviceDataService;
    private final IMapperService mapperService;

    public CustomerService(final IDataResource<Customer> dataService, final IMapperService mapperService, final ICustomerServiceDataService serviceDataService, final IDeviceDataService deviceDataService){
        this.dataService = dataService;
        this.mapperService = mapperService;
        this.serviceDataService = serviceDataService;
        this.deviceDataService = deviceDataService;
    }

    @Override
    public CustomerModel getOne(UUID id) {
        var results = this.dataService.selectOne(id);
        return mapperService.toModel(results);

    }

    @Override
    public PagedResults<CustomerModel> getMany(int page, int size) {
        var results = dataService.selectMany(page, size);
        return mapperService.toModel(results, page, size, CustomerModel.class);
    }

    @Override
    public void delete(UUID id) {
        dataService.delete(id);
    }

    @Override
    public CustomerModel update(CustomerModel resource, UserModel userModel) {
        if(resource.getId() == null) {
            return add(resource, userModel);
        }else {
            var results = dataService.update(mapperService.toDao(resource), mapperService.toDao(userModel));
            return mapperService.toModel(results);
        }
    }

    @Override
    public CustomerModel add(CustomerModel resource, UserModel userModel) {
        var results = dataService.insert(mapperService.toDao(resource), mapperService.toDao(userModel));
        return mapperService.toModel(results);
    }

    @Override
    public Bill generateBill(UUID customerId) {
        var services = mapperService.toModel(serviceDataService.selectManyByCustomer(customerId), ServiceModel.class);
        var devices = mapperService.toModel(deviceDataService.selectManyByParentId(customerId), DeviceModel.class);
        Bill bill = new Bill();
        AtomicInteger macCount = new AtomicInteger();
        AtomicInteger windows = new AtomicInteger();
        BigDecimal deviceCost = new BigDecimal(0);

        for(DeviceModel device : devices){
            switch (device.getDeviceType()){
                case MAC:
                    macCount.getAndIncrement();
                    break;
                case WINDOWS_SERVER:
                    windows.getAndIncrement();
                    break;
                case WINDOWS_WORKSTATION:
                    windows.getAndIncrement();
                    break;
            }

            deviceCost = deviceCost.add(device.getDeviceCost());
        }

        bill.getLineItems().add(new LineItem().withServiceName("Device Cost").withMacCount(macCount.get()).withWindowsCount(windows.get()).withDeviceCount(devices.size()).withLineTotal(deviceCost));

        for(ServiceModel service : services){
            BigDecimal macCost = service.getMacPricing().multiply(new BigDecimal(macCount.get()));
            BigDecimal windowsCost = service.getWindowsPricing().multiply(new BigDecimal(windows.get()));
            BigDecimal total = macCost.add(windowsCost);

            bill.getLineItems().add(new LineItem().withServiceName(service.getServiceName()).withDeviceCount(devices.size()).withMacCount(macCount.get()).withWindowsCount(windows.get()).withLineTotal(total));
        }

        bill.setDate(OffsetDateTime.now());
        bill.calculateTotal();
        return bill;
    }
}
