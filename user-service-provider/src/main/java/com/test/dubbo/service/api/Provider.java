package com.test.dubbo.service.api;

import com.alibaba.dubbo.config.*;
import com.test.dubbo.service.UserService;
import com.test.dubbo.service.impl.UserServiceImpl;
import org.apache.zookeeper.server.ServerConfig;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        /**
         * 创建服务实现类
         */
        UserService userService = new UserServiceImpl();
        /**
         * 创建应用配置
         */
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("provider");

        //注册中心配置
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol("zookeeper");
        registryConfig.setAddress("49.232.165.185:2181");

        ProviderConfig providerConfig = new ProviderConfig();
        providerConfig.setTimeout(5000);
        //协议
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(20882);

        //暴露服务
        ServiceConfig<UserService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setApplication(applicationConfig);
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setProtocol(protocolConfig);
        serviceConfig.setInterface(UserService.class);
        serviceConfig.setRef(userService);
        serviceConfig.setVersion("1.0.0");

        serviceConfig.export();
        System.in.read();
    }


}
