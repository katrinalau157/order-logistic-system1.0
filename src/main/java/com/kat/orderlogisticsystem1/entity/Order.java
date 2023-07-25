package com.kat.orderlogisticsystem1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ORDERS")
@Builder
public class Order
{
	@Id
	@GeneratedValue
	@UuidGenerator(style = UuidGenerator.Style.TIME)
	@Column(name = "ORDER_ID")
	private String id;

	@Column(name = "TOTAL_DISTANCE")
	private Integer totalDistance;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "TAKEN")
	private boolean taken;

	@CreationTimestamp
	@Column(name = "CREATION_DATE")
	private LocalDateTime creationDate;

	@UpdateTimestamp
	@Column(name = "LAST_MODIFIED_DATE")
	private LocalDateTime lastModifiedDate;

	@Version
	@Column(name = "VERSION")
	private Long version;

}
