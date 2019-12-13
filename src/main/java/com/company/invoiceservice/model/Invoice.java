package com.company.invoiceservice.model;

import java.time.LocalDate;
import java.util.Objects;

public class Invoice {
	private int id;
	private int customerId;
	private LocalDate purchaseDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int cutomerId) {
		this.customerId = cutomerId;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Invoice invoice = (Invoice) o;
		return id == invoice.id &&
				customerId == invoice.customerId &&
				Objects.equals(purchaseDate, invoice.purchaseDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, customerId, purchaseDate);
	}
}
