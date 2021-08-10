package com.demo.feeder.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DAILY_TRANSACTION")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "CLIENT_TYPE", length = 4)
	private String clientType;

	@Column(name = "CLIENT_NUMBER", length = 4)
	private String clientNumber;

	@Column(name = "ACCOUNT_NUMBER", length = 4)
	private String accountNo;

	@Column(name = "ACCOUNT_SUBNUMBER", length = 4)
	private String accountSubNo;

	@Column(name = "EXCHANGE_CODE", length = 4)
	private String exchangeCode;

	@Column(name = "PRODUCT_GROUP_CODE", length = 2)
	private String productGroupCode;

	@Column(name = "SYMBOL", length = 6)
	private String symbol;

	@Column(name = "EXPIRATION_DATE", length = 8)
	private String expirationDate;

	@Column(name = "TRANSACTION_LONG", length = 10)
	private Long qtyLong;

	@Column(name = "TRANSACTION_SHORT", length = 10)
	private Long qtyShort;

	@Column(name = "TRANSACTION_REPORTED_DATE")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate reportedDate;

}
