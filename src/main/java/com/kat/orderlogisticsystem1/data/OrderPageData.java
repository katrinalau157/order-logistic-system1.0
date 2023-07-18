package com.kat.orderlogisticsystem1.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderPageData
{
    private String id;
    private String status;
    private Integer distance;
}