package com.test.dubbo.service.api;

import com.alibaba.dubbo.config.*;
import com.test.dubbo.service.OrderService;
import com.test.dubbo.service.UserService;
import com.test.dubbo.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class Consumer {

    public static UserService userService;
    public static void main(String[] args) {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("consumer");


        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol("zookeeper");
        registryConfig.setAddress("49.232.165.185:2181");

        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setCheck(false);
        consumerConfig.setTimeout(5000);

        ReferenceConfig<UserService> userServiceReferenceConfig = new ReferenceConfig<>();
        userServiceReferenceConfig.setApplication(applicationConfig);
        userServiceReferenceConfig.setRegistry(registryConfig);
        userServiceReferenceConfig.setInterface(UserService.class);
        userServiceReferenceConfig.setVersion("1.0.0");

         userService = userServiceReferenceConfig.get();

        OrderService orderService= new OrderServiceImpl();
        orderService.initOrder("1");
        System.out.println("调用成功....");
    }
}
