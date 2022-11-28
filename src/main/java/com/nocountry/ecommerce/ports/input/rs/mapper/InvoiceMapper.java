package com.nocountry.ecommerce.ports.input.rs.mapper;

import com.nocountry.ecommerce.domain.model.Invoice;
import com.nocountry.ecommerce.domain.model.PurchaseSummary;
import com.nocountry.ecommerce.ports.input.rs.response.InvoiceResponse;
import com.nocountry.ecommerce.ports.input.rs.response.SummaryResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {

   SummaryResponse PurchaseSummaryToSummaryResponse(PurchaseSummary summary);

   InvoiceResponse InvoiceToInvoiceResponse(Invoice invoice);

   List<InvoiceResponse> ListInvoiceToInvoiceResponse(List<Invoice> list);
}
