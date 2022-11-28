package com.nocountry.ecommerce.ports.input.rs.controller;

import com.nocountry.ecommerce.domain.model.User;
import com.nocountry.ecommerce.domain.usecase.InvoiceService;
import com.nocountry.ecommerce.ports.input.rs.mapper.InvoiceMapper;
import com.nocountry.ecommerce.ports.input.rs.request.PurchaseRequest;
import com.nocountry.ecommerce.ports.input.rs.response.InvoiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.nocountry.ecommerce.ports.input.rs.api.ApiConstants.BOTH;
import static com.nocountry.ecommerce.ports.input.rs.api.ApiConstants.INVOICE_URI;

@RestController
@RequiredArgsConstructor
@RequestMapping(INVOICE_URI)
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final InvoiceMapper invoiceMapper;

    //=========================Get invoice=========================//

    @GetMapping
    @PreAuthorize(BOTH)
    public ResponseEntity<List<InvoiceResponse>> getInvoices(HttpServletRequest request) {
        Long id = (Long) request.getSession().getAttribute("id");
        List<InvoiceResponse> responses = invoiceMapper.ListInvoiceToInvoiceResponse(invoiceService.getInvoices(id));
        return ResponseEntity.ok(responses);
    }

    //=========================Buy products=========================//

    @PreAuthorize(BOTH)
    @PostMapping
    public void processPurchase(@RequestBody PurchaseRequest purchase, HttpServletRequest request) {
        Long id = (Long) request.getSession().getAttribute("id");
        invoiceService.processPurchaseRequest(purchase, id);
    }


}
