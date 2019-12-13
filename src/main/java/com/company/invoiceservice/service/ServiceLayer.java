package com.company.invoiceservice.service;

import com.company.invoiceservice.dao.InvoiceDao;
import com.company.invoiceservice.dao.InvoiceItemDao;
import com.company.invoiceservice.model.Invoice;
import com.company.invoiceservice.model.InvoiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceLayer {

    InvoiceDao invoiceDao;
    InvoiceItemDao invoiceItemDao;

    @Autowired
    public ServiceLayer(InvoiceDao invoiceDao, InvoiceItemDao invoiceItemDao) {
        this.invoiceDao = invoiceDao;
        this.invoiceItemDao = invoiceItemDao;
    }

    public Invoice createInvoice(Invoice invoice) {return invoiceDao.addInvoice(invoice); }

    public Invoice getInvoice(int id) {return invoiceDao.getInvoice(id);}


    public List<Invoice> getAllInvoices() { return invoiceDao.getAllInvoices(); }

    public void updateInvoice(Invoice invoice) { invoiceDao.updateInvoice(invoice); }

    public void deleteInvoice(int id) {
        invoiceDao.deleteInvoice(id);
    }

    public InvoiceItem createInvoiceItem(InvoiceItem invoiceItem) { return invoiceItemDao.addInvoiceItem(invoiceItem); }

    public InvoiceItem getInvoiceItem(int id) {return invoiceItemDao.getInvoiceItem(id); }

    public List<InvoiceItem> getInvoiceItemsByInvoiceId(int id){ return invoiceItemDao.getInvoiceItemsByInvoiceId(id); }

    public List<InvoiceItem> getAllInvoiceItems() {return invoiceItemDao.getAllInvoiceItems(); }

    public void updateInvoiceItem(InvoiceItem invoiceItem) {invoiceItemDao.updateInvoiceItem(invoiceItem); }

    public void deleteInvoiceItem(int id) {
        invoiceItemDao.deleteInvoiceItem(id);
    }

}
