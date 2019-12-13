package com.company.invoiceservice.dao;


import com.company.invoiceservice.model.Invoice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceDaoImplTest {

    @Autowired
    InvoiceDao dao;


    Invoice invoice;
    Invoice invoice1;
    List<Invoice> invoices;

    @Before
    public void setUp() {
        clearUpDatabase();
        setUpMockObjects();

    }

    private void clearUpDatabase(){
        invoices = dao.getAllInvoices();
        invoices.stream()
                .forEach(invoice2 -> dao.deleteInvoice(invoice2.getId()));
    }

    private void setUpMockObjects(){
        invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.of(2019,01,01));

        invoices = Arrays.asList(invoice);

    }

    @Test
    public void shouldAddGetAndDeleteInvoice(){
        invoice = dao.addInvoice(invoice);

        invoice1 = dao.getInvoice(invoice.getId());

        assertEquals(invoice, invoice1);

        dao.deleteInvoice(invoice.getId());
        invoice1 = dao.getInvoice(invoice.getId());

        assertNull(invoice1);

    }

    @Test
    public void shouldUpdateInvoice(){
        invoice = dao.addInvoice(invoice);
        invoice.setPurchaseDate(LocalDate.of(2019,12,15));
        dao.updateInvoice(invoice);

        invoice1 = dao.getInvoice(invoice.getId());

        assertEquals(invoice, invoice1);

    }

    @Test
    public void shouldGetAllInvoices(){
        dao.addInvoice(invoice);
        invoices = dao.getAllInvoices();

        assertEquals(1,invoices.size());
    }
}
