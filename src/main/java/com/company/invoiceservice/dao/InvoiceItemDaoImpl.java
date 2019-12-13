package com.company.invoiceservice.dao;

import com.company.invoiceservice.model.InvoiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InvoiceItemDaoImpl implements InvoiceItemDao {

    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_INVOICE_ITEM_SQL =
            "insert into invoice_item (invoice_id, inventory_id, quantity, unit_price) values (?, ?, ?, ?)";

    private static final String SELECT_INVOICE_ITEM_SQL =
            "select * from invoice_item where invoice_item_id = ?";

    private static final String SELECT_INVOICE_ITEMS_BY_INVOICE_ID_SQL =
            "select * from invoice_item where invoice_id = ?";

    private static final String SELECT_ALL_INVOICE_ITEMS_SQL =
            "select * from invoice_item";

    private static final String UPDATE_INVOICE_ITEM_SQL =
            "update invoice_item set invoice_id = ?, inventory_id = ?,quantity = ?, unit_price = ? where invoice_item_id = ?";

    private static final String DELETE_INVOICE_ITEM =
            "delete from invoice_item where invoice_item_id = ?";

    @Autowired
    public InvoiceItemDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public InvoiceItem addInvoiceItem(InvoiceItem invoiceItem) {
        jdbcTemplate.update(INSERT_INVOICE_ITEM_SQL,
                invoiceItem.getInvoiceId(),
                invoiceItem.getInventoryId(),
                invoiceItem.getQuantity(),
                invoiceItem.getUnitPrice());

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        invoiceItem.setId(id);

        return invoiceItem;
    }

    @Override
    public InvoiceItem getInvoiceItem(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_INVOICE_ITEM_SQL, this::mapRowToInvoice, id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<InvoiceItem> getAllInvoiceItems() {
        return jdbcTemplate.query(SELECT_ALL_INVOICE_ITEMS_SQL,this::mapRowToInvoice);
    }

    @Override
    public List<InvoiceItem> getInvoiceItemsByInvoiceId(int id) {
        return jdbcTemplate.query(SELECT_INVOICE_ITEMS_BY_INVOICE_ID_SQL,this::mapRowToInvoice,id);
    }


    @Override
    public void updateInvoiceItem(InvoiceItem invoiceItem) {
        jdbcTemplate.update(UPDATE_INVOICE_ITEM_SQL,
                invoiceItem.getInvoiceId(),
                invoiceItem.getInventoryId(),
                invoiceItem.getQuantity(),
                invoiceItem.getUnitPrice(),
                invoiceItem.getId());
    }

    @Override
    public void deleteInvoiceItem(int id) { jdbcTemplate.update(DELETE_INVOICE_ITEM, id); }

    private InvoiceItem mapRowToInvoice(ResultSet rs, int rowNum) throws SQLException{
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setId(rs.getInt("invoice_item_id"));
        invoiceItem.setInvoiceId(rs.getInt("invoice_id"));
        invoiceItem.setInventoryId(rs.getInt("inventory_id"));
        invoiceItem.setQuantity(rs.getInt("quantity"));
        invoiceItem.setUnitPrice(rs.getDouble("unit_price"));

        return invoiceItem;
    }

    //invoice_item_id int(11) not null auto_increment primary key,
//            invoice_id int(11) not null,
//            inventory_id int(11) not null,
//            quantity int(11) not null,
//            unit_price decimal(7,2) not null
}
