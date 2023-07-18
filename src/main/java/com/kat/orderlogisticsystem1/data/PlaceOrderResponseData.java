package com.kat.orderlogisticsystem1.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderResponseData
{
	String id;
	Integer distance;
	String status;
}
