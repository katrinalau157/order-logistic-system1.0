package com.kat.orderlogisticsystem1.data;

import com.kat.orderlogisticsystem1.validator.ValidStringArray;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderRequestData
{
	@NotEmpty
	@Size(min = 2, max = 2)
	@ValidStringArray
	Object[] origin;

	@NotEmpty
	@Size(min = 2, max = 2)
	@ValidStringArray
	Object[] destination;
}
