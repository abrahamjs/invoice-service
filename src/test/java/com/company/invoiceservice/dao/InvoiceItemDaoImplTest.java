package com.company.invoiceservice.dao;

import com.company.invoiceservice.model.Invoice;
import com.company.invoiceservice.model.InvoiceItem;
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
public class InvoiceItemDaoImplTest {

    @Autowired
    InvoiceItemDao dao;

    @Autowired
    InvoiceDao invoiceDao;

    InvoiceItem invoiceItem;
    InvoiceItem invoiceItem1;
    Invoice invoice;
    List<InvoiceItem> invoiceItems;


    @Before
    public void setUp() {
        clearUpDataBase();
        setUpMockObjects();
    }

    private void clearUpDataBase(){
        invoiceItems = dao.getAllInvoiceItems();
        invoiceItems.stream()
                .forEach(invoiceItem2 -> dao.deleteInvoiceItem(invoiceItem2.getId()));
    }

    public void setUpMockObjects(){
        invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.of(2019,01,01));
        invoice = invoiceDao.addInvoice(invoice);

        invoiceItem =  new InvoiceItem();
        invoiceItem.setInvoiceId(invoice.getId());
        invoiceItem.setInventoryId(10);
        invoiceItem.setQuantity(5);
        invoiceItem.setUnitPrice(5.0);


        invoiceItems = Arrays.asList(invoiceItem);

    }

    @Test
    public void shouldAddGetAndDeleteInvoiceItem(){
        invoiceItem = dao.addInvoiceItem(invoiceItem);
        invoiceItem1 = dao.getInvoiceItem(invoiceItem.getId());

        assertEquals(invoiceItem,invoiceItem1);

        dao.deleteInvoiceItem(invoiceItem.getId());
        invoiceItem1 = dao.getInvoiceItem(invoiceItem.getId());
        assertNull(invoiceItem1);
    }

    @Test
    public void shouldGetInvoiceItemsByInvoiceId(){
        invoiceItem = dao.addInvoiceItem(invoiceItem);
        assertEquals(invoiceItems,dao.getInvoiceItemsByInvoiceId(invoiceItem.getInvoiceId()));
    }

    @Test
    public void shouldUpdateInvoiceItem(){
        invoiceItem = dao.addInvoiceItem(invoiceItem);
        invoiceItem.setUnitPrice(10.0);

        dao.updateInvoiceItem(invoiceItem);
        invoiceItem1 = dao.getInvoiceItem(invoiceItem.getId());

        assertEquals(invoiceItem,invoiceItem1);

    }

    @Test
    public void shouldGetAllInvoiceItems(){
        invoiceItem = dao.addInvoiceItem(invoiceItem);
        invoiceItems = dao.getAllInvoiceItems();

        assertEquals(1, invoiceItems.size());
    }
    //invoice_item_id int(11) not null auto_increment primary key,
//            invoice_id int(11) not null,
//            inventory_id int(11) not null,
//            quantity int(11) not null,
//            unit_price decimal(7,2) not null
}