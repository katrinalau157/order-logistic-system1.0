package com.kat.orderlogisticsystem1.service;

import com.kat.orderlogisticsystem1.data.OrderPageData;
import com.kat.orderlogisticsystem1.data.PlaceOrderRequestData;
import com.kat.orderlogisticsystem1.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface OrderService
{
	Order saveOrder(Order order);

	Order getOrderById(String id);

	Order placeOrder(PlaceOrderRequestData placeOrderRequest) throws Exception;

	Order takeOrder(Order id, String status);

	List<OrderPageData> findOrderPageList(int page, int size);

	Page<Order> getOrders(Pageable pageable);

	Page<OrderPageData> getOrderSummaries(Pageable pageable);
}
