package com.company.invoiceservice.service;

import com.company.invoiceservice.dao.InvoiceDao;
import com.company.invoiceservice.dao.InvoiceItemDao;
import com.company.invoiceservice.model.Invoice;
import com.company.invoiceservice.model.InvoiceItem;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;

public class ServiceLayerTest {

    InvoiceDao invoiceDao;
    InvoiceItemDao invoiceItemDao;

    ServiceLayer service;

    Invoice invoice;
    Invoice invoice1;
    List<Invoice> invoices;

    InvoiceItem item;
    InvoiceItem item1;
    List<InvoiceItem> items;
    List<InvoiceItem> items1;

    @Before
    public void setUp() {
        setUpInvoiceDaoMock();
        setUpInvoiceItemDaoMock();

        service = new ServiceLayer(invoiceDao,invoiceItemDao);
    }

    public void setUpInvoiceDaoMock(){
        invoiceDao = mock(InvoiceDao.class);

        invoice = new Invoice();
        invoice.setId(1);
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.of(2019,01,01));

        invoice1 = new Invoice();
        invoice1.setCustomerId(1);
        invoice1.setPurchaseDate(LocalDate.of(2019,01,01));

        invoices = Arrays.asList(invoice);
        doReturn(invoice).when(invoiceDao).addInvoice(invoice1);
        doReturn(invoice).when(invoiceDao).getInvoice(1);
        doReturn(invoices).when(invoiceDao).getAllInvoices();
    }

    public void setUpInvoiceItemDaoMock(){
        invoiceItemDao = mock(InvoiceItemDao.class);

        item = new InvoiceItem();
        item.setId(1);
        item.setInvoiceId(1);
        item.setInventoryId(10);
        item.setQuantity(5);
        item.setUnitPrice(5.0);

        item1 = new InvoiceItem();
        item1.setInvoiceId(1);
        item1.setInventoryId(10);
        item1.setQuantity(5);
        item1.setUnitPrice(5.0);

        items = Arrays.asList(item);

        doReturn(item).when(invoiceItemDao).addInvoiceItem(item1);
        doReturn(item).when(invoiceItemDao).getInvoiceItem(1);
        doReturn(items).when(invoiceItemDao).getInvoiceItemsByInvoiceId(1);
        doReturn(items).when(invoiceItemDao).getAllInvoiceItems();

    }

    @Test
    public void shouldAddAndGetInvoiceById(){
      invoice = service.createInvoice(invoice1);
      invoice1 = service.getInvoice(invoice.getId());

      assertEquals(invoice,invoice1);
    }

    @Test
    public void shouldGetAllInvoices(){
        invoices = service.getAllInvoices();

        assertEquals(1,invoices.size());
    }

    @Test
    public void shouldAddAndGetInvoiceItemById(){
        item = service.createInvoiceItem(item1);
        item1 = service.getInvoiceItem(item.getId());

        assertEquals(item,item1);
    }

    @Test
    public void shouldGetAllInvoiceItems(){
        items = service.getAllInvoiceItems();

        assertEquals(1,items.size());
    }

    @Test
    public void shouldGetInvoiceItemsByInvoiceId(){
        items = service.getInvoiceItemsByInvoiceId(invoice.getId());

        assertEquals(1,items.size());
    }

}