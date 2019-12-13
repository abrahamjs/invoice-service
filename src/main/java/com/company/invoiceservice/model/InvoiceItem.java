package com.company.invoiceservice.model;

import java.util.Objects;

public class InvoiceItem {
    private int id;
    private int invoiceId;
    private int inventoryId;
    private int quantity;
    private double unitPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceItem that = (InvoiceItem) o;
        return id == that.id &&
                invoiceId == that.invoiceId &&
                inventoryId == that.inventoryId &&
                quantity == that.quantity &&
                Double.compare(that.unitPrice, unitPrice) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, invoiceId, inventoryId, quantity, unitPrice);
    }
}
//	invoice_item_id int(11) not null auto_increment primary key,
//            invoice_id int(11) not null,
//            inventory_id int(11) not null,
//            quantity int(11) not null,
//            unit_price decimal(7,2) not null