package com.kat.orderlogisticsystem1.repository;

import com.kat.orderlogisticsystem1.data.OrderPageData;
import com.kat.orderlogisticsystem1.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<Order, String>
{
	@Query("select new com.kat.orderlogisticsystem1.data.OrderPageData(o.id, o.status, o.totalDistance) from Order o")
	Page<OrderPageData> findAllOrderSummaries(Pageable pageable);

}
