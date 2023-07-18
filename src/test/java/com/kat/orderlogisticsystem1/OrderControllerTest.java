package com.kat.orderlogisticsystem1;

import com.kat.orderlogisticsystem1.controller.OrderController;
import com.kat.orderlogisticsystem1.data.PlaceOrderRequestData;
import com.kat.orderlogisticsystem1.data.PlaceOrderResponseData;
import com.kat.orderlogisticsystem1.data.TakeOrderData;
import com.kat.orderlogisticsystem1.entity.Order;
import com.kat.orderlogisticsystem1.exception.GlobalErrorExceptionHandler;
import com.kat.orderlogisticsystem1.exception.GlobalErrorMessage;
import com.kat.orderlogisticsystem1.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.RequestEntity.patch;


@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest
{
	@Mock
	private OrderService orderService;

	@InjectMocks
	private OrderController orderController;

	@Test
	public void testPlaceOrder_success() throws Exception
	{
		//create request data
		PlaceOrderRequestData placeOrderRequestData = new PlaceOrderRequestData();
		placeOrderRequestData.setOrigin(new Object[] { 1.0, 1.0 });
		placeOrderRequestData.setDestination(new Object[] { 1.0, 1.0 });
		//create order entity
		Order order = new Order();
		order.setId("uuid");
		order.setTotalDistance(100);
		order.setStatus("UNASSIGNED");
		//create response data
		PlaceOrderResponseData placeOrderResponseData = PlaceOrderResponseData
				.builder()
				.id(order.getId())
				.distance(order.getTotalDistance())
				.status(order.getStatus())
				.build();
		//mock service
		when(orderService.placeOrder(any(PlaceOrderRequestData.class))).thenReturn(order);
		when(orderService.saveOrder(any(Order.class))).thenReturn(order);
		ResponseEntity<PlaceOrderResponseData> response = orderController.placeOrder(placeOrderRequestData);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(placeOrderResponseData.getStatus(), response.getBody().getStatus());
	}

	@Test
	public void testTakeOrder_success() throws Exception
	{
		TakeOrderData takeOrderData = new TakeOrderData();
		takeOrderData.setStatus("TAKEN");
		Order order = new Order();
		order.setId("uuid");
		order.setStatus("TAKEN");
		when(orderService.getOrderById(any(String.class))).thenReturn(order);
		ResponseEntity<TakeOrderData> response = orderController.takeOrder(takeOrderData, "uuid");
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("SUCCESS", response.getBody().getStatus());
	}
}