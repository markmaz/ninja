package com.ninjarmm.controllers;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
@ActiveProfiles("CustomerController")
class CustomerControllerTest {
    @Configuration
    @Profile("CustomerController")
    @ComponentScan(basePackageClasses = CustomerController.class, excludeFilters =
            {
                    @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = BaseController.class),
                    @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = CustomerServiceController.class),
                    @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = DeviceController.class),
                    @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ServiceController.class),
                    @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = UserController.class)
            });
    public static class SpringConfig
    {}
}