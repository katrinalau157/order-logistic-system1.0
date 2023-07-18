package com.kat.orderlogisticsystem1.controller;

import com.kat.orderlogisticsystem1.data.OrderPageData;
import com.kat.orderlogisticsystem1.data.PlaceOrderRequestData;
import com.kat.orderlogisticsystem1.data.PlaceOrderResponseData;
import com.kat.orderlogisticsystem1.data.TakeOrderData;
import com.kat.orderlogisticsystem1.entity.Order;
import com.kat.orderlogisticsystem1.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
@Validated
@RequestMapping("/orders")
public class OrderController
{

	@Autowired
	private OrderService orderService;

	/**
	 * validate the latitude and longitude value of coordinates must be correctly validated.
	 * unique Order id in response.
	 * Distance in response is integer in meters
	 *
	 * @param placeOrderRequest
	 * @return
	 */
	@PostMapping
	public ResponseEntity<PlaceOrderResponseData> placeOrder(@Valid @RequestBody PlaceOrderRequestData placeOrderRequest)
			throws Exception
	{
		Order order = orderService.placeOrder(placeOrderRequest);
		PlaceOrderResponseData placeOrderResponse = PlaceOrderResponseData.builder().id(order.getId())
				.distance(order.getTotalDistance())
				.status(order.getStatus()).build();
		return ResponseEntity.ok(placeOrderResponse);
	}



	/**
	 * mindful of race condition.
	 * When there are concurrent requests to take a same order, we expect only one can take the order while the other will fail.
	 *
	 * @return
	 */
	@PatchMapping("/{id}")
	public ResponseEntity<TakeOrderData> takeOrder(@Valid @RequestBody TakeOrderData request, @PathVariable String id)
			throws Exception
	{
		Order order = orderService.getOrderById(id);
		if (order == null)
		{
			throw new Exception("Order not found");
		}
		if (order.isTaken())
		{
			throw new Exception("Order already taken");
		}
		orderService.takeOrder(order, request.getStatus());
		return ResponseEntity.ok(TakeOrderData.builder().status("SUCCESS").build());
	}

	/**
	 * Page number starts with 1
	 * If page or limit is not a valid integer, return error response
	 * If there is no result, return an empty array json in response body
	 *
	 * @param page
	 * @return
	 */
	@GetMapping
	public ResponseEntity<?> getOrderPageList(@RequestParam @Min(1) Integer page, @RequestParam @Min(1) Integer limit)
	{
		List<OrderPageData> orderPageList = orderService.findOrderPageList(page - 1, limit);
		return ResponseEntity.ok(orderPageList);
	}
}
