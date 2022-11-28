package com.nocountry.ecommerce.domain.usecase.impl;

import com.nocountry.ecommerce.domain.model.Invoice;
import com.nocountry.ecommerce.domain.model.Product;
import com.nocountry.ecommerce.domain.model.PurchaseSummary;
import com.nocountry.ecommerce.domain.model.User;
import com.nocountry.ecommerce.domain.repository.InvoiceRepository;
import com.nocountry.ecommerce.domain.repository.PurchaseSummaryRepository;
import com.nocountry.ecommerce.domain.usecase.InvoiceService;
import com.nocountry.ecommerce.domain.usecase.ProductService;
import com.nocountry.ecommerce.domain.usecase.UserService;
import com.nocountry.ecommerce.ports.input.rs.request.ProductRequestSimple;
import com.nocountry.ecommerce.ports.input.rs.request.PurchaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final UserService userService;
    private final ProductService productService;
    private final InvoiceRepository invoiceRepository;
    private final PurchaseSummaryRepository purchaseRepository;

    //====================Get Invoice====================//


    @Override
    @Transactional(readOnly = true)
    public List<Invoice> getInvoices(Long id) {
        return invoiceRepository.findByUserId(id);
    }

    //====================Purchase====================//

    @Override
    @Transactional
    public void processPurchaseRequest(PurchaseRequest request, Long id) {

        User user = userService.getByIdIfExist(id);
        List<PurchaseSummary> listProducts = new ArrayList<>();
        float totalPrice = 0;

        for (ProductRequestSimple p : request.getListProducts()) {
            {
                Product productDB = productService.getByIdIfExist(p.getId());

                discountProduct(p, productDB);
                totalPrice += (p.getAmount() * productDB.getPrice());
                disableProduct(productDB);

                productService.save(productDB);
                listProducts.add(createPurchaseSummary(p.getAmount(), productDB));
            }
        }

        createAndSaveInvoice(user, listProducts, totalPrice);
    }

    //====================Purchase Utils====================//


    private void createAndSaveInvoice(User user, List<PurchaseSummary> listProducts, float totalPrice) {

        Invoice invoice = new Invoice();
        invoice.setUser(user);
        invoice.setProductList(listProducts);
        invoice.setCreationDate(LocalDateTime.now());
        invoice.setTotalPrice(totalPrice);

        invoiceRepository.save(invoice);

    }

    private PurchaseSummary createPurchaseSummary(Integer amount, Product product) {

        PurchaseSummary purchaseSummary = new PurchaseSummary();
        purchaseSummary.setAmount(amount);
        purchaseSummary.setProduct(product);

        return purchaseRepository.save(purchaseSummary);
    }


    private void disableProduct(Product productDB) {
        if (productDB.getStock() == 0) productDB.setIsAvailable(false);
    }

    private void discountProduct(ProductRequestSimple p, Product productDB) {
        if (p.getAmount() > productDB.getStock() || !productDB.getIsAvailable()) throw new RuntimeException("no stock");
        productDB.setStock(productDB.getStock() - p.getAmount());
    }

}
