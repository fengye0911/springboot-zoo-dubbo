package com.test.dubbo.service.impl;

import com.example.dubbo.bean.UserAddress;
import com.example.dubbo.service.OrderService;
import com.example.dubbo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 让服务消费者去注册中心订阅服务提供者的服务地址
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	UserService userService;
	@Override
	public List<UserAddress> initOrder(String userId) {
		System.out.println("用户id："+userId);
		//查询用户的收货地址
		List<UserAddress> addressList = userService.getUserAddressList(userId);
		for (UserAddress userAddress : addressList) {
			System.out.println(userAddress.getUserAddress());
		}
		return addressList;
	}
}