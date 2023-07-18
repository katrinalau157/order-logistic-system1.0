package com.kat.orderlogisticsystem1.service.impl;

import com.kat.orderlogisticsystem1.data.OrderPageData;
import com.kat.orderlogisticsystem1.data.PlaceOrderRequestData;
import com.kat.orderlogisticsystem1.entity.Order;
import com.kat.orderlogisticsystem1.repository.OrderRepository;
import com.kat.orderlogisticsystem1.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class OrderServiceImpl implements OrderService
{
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private DistanceServiceImpl distanceService;

	@Override
	public Order saveOrder(Order order)
	{
		return orderRepository.save(order);
	}

	@Override
	public Order getOrderById(String id)
	{
		return orderRepository.findById(id).orElse(null);
	}

	@Override
	public Page<Order> getOrders(Pageable pageable)
	{
		return orderRepository.findAll(pageable);
	}

	@Override
	public Order placeOrder(PlaceOrderRequestData placeOrderRequest) throws Exception
	{
		//validate request
		Object[] origin = placeOrderRequest.getOrigin();
		Object[] destination = placeOrderRequest.getDestination();

		double startLatitude = isValidLatitudeOrLongitude(String.valueOf(origin[0]), true);
		double startLongitude = isValidLatitudeOrLongitude(String.valueOf(origin[1]), false);
		double endLatitude = isValidLatitudeOrLongitude(String.valueOf(destination[0]), true);
		double endLongitude = isValidLatitudeOrLongitude(String.valueOf(destination[1]), false);

		int totalDistance = distanceService.calculateDistance(startLatitude, startLongitude, endLatitude, endLongitude);
		Order order = saveOrder(Order.builder().totalDistance(totalDistance).status("UNASSIGNED").taken(false).build());
		return order;
	}

	@Override
	public Order takeOrder(Order order, String status)
	{
		order.setStatus(status);
		order.setTaken(true);
		return saveOrder(order);
	}

	@Override
	public Page<OrderPageData> getOrderSummaries(Pageable pageable)
	{
		return orderRepository.findAllOrderSummaries(pageable);
	}

	@Override
	public List<OrderPageData> findOrderPageList(int page, int size)
	{
		Pageable pageable = PageRequest.of(page, size);
		Page<OrderPageData> orderPage = getOrderSummaries(pageable);
		List<OrderPageData> orders = orderPage.getContent();
		return orders;
	}

	public double isValidLatitudeOrLongitude(String str, boolean isLatitude) throws Exception
	{
		try
		{
			if (str == null)
			{
				throw new Exception("Latitude/Longitude is null");
			}
			double value = Double.parseDouble(str);
			if (isLatitude)
			{
				if (!(value >= -90 && value <= 90))
				{
					throw new Exception("Latitude not valid");
				}
			}
			else
			{
				if (!(value >= -180 && value <= 180))
				{
					throw new Exception("Longitude not valid");
				}
			}
			return value;
		}
		catch (NumberFormatException e)
		{
			throw new Exception("Latitude/Longitude not valid");
		}
	}

}
