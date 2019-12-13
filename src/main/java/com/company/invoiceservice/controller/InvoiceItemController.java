package com.company.invoiceservice.controller;

import com.company.invoiceservice.model.InvoiceItem;
import com.company.invoiceservice.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoiceitem")//This mapping at class level allows to set the base path for the Game API
public class InvoiceItemController {

    @Autowired
    ServiceLayer service;

    @PostMapping//Another way to set the Rest API Post mapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceItem createInvoiceItem(@RequestBody InvoiceItem invoiceItem) {
        return service.createInvoiceItem(invoiceItem);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InvoiceItem getInvoiceItem(@PathVariable int id) { return service.getInvoiceItem(id); }

    @RequestMapping(value = "/invoice/{id}",method = RequestMethod.GET)
    public List<InvoiceItem> getInvoiceItemsByInvoiceId(@PathVariable int id){
        return service.getInvoiceItemsByInvoiceId(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceItem> getAllInvoiceItems() { return service.getAllInvoiceItems(); }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInvoiceItem(@PathVariable int id, @RequestBody InvoiceItem invoiceItem) {
        service.updateInvoiceItem(invoiceItem);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoiceItem(@PathVariable int id) {
        service.deleteInvoiceItem(id);
    }
}
