package com.company.invoiceservice.dao;

import com.company.invoiceservice.model.InvoiceItem;

import java.util.List;

public interface InvoiceItemDao {

    public InvoiceItem addInvoiceItem(InvoiceItem invoiceItem);

    public InvoiceItem getInvoiceItem(int id);

    public List<InvoiceItem> getAllInvoiceItems();

    public List<InvoiceItem> getInvoiceItemsByInvoiceId(int id);

    public void updateInvoiceItem(InvoiceItem invoiceItem);

    public void deleteInvoiceItem(int id);
}

