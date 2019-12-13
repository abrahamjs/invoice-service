package com.company.invoiceservice.controller;

import com.company.invoiceservice.model.Invoice;
import com.company.invoiceservice.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    ServiceLayer service;

    @PostMapping//Another way to set the Rest API Post mapping
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice createInvoice(@RequestBody Invoice invoice) {
        return service.createInvoice(invoice);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Invoice getInvoice(@PathVariable int id) {
        return service.getInvoice(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getAllInvoices() { return service.getAllInvoices(); }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInvoice(@PathVariable int id, @RequestBody Invoice invoice) { service.updateInvoice(invoice); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable int id) {
        service.deleteInvoice(id);
    }

}
