package com.nocountry.ecommerce.ports.input.rs.controller;

import com.nocountry.ecommerce.domain.model.Product;
import com.nocountry.ecommerce.domain.usecase.ProductService;
import com.nocountry.ecommerce.ports.input.rs.mapper.ProductMapper;
import com.nocountry.ecommerce.ports.input.rs.request.ProductCreateRequest;
import com.nocountry.ecommerce.ports.input.rs.request.ProductFilterRequest;
import com.nocountry.ecommerce.ports.input.rs.request.ProductUpdateRequest;
import com.nocountry.ecommerce.ports.input.rs.response.ProductDetails;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

import static com.nocountry.ecommerce.ports.input.rs.api.ApiConstants.*;

@RestController
@RequestMapping(PRODUCT_URI)
@RequiredArgsConstructor
public class ProductController {

   private final ProductService service;
   private final ProductMapper mapper;

   //====================Display product page====================//

   @ApiOperation("display a product page")
   @GetMapping(path = "/products")
   public ResponseEntity<List<ProductDetails>> getPage(
      @RequestParam(required = false, defaultValue = "") String name,
      @RequestParam(required = false, defaultValue = "") String category,
      @RequestParam(required = false, defaultValue = "") String mark)

   {
      ProductFilterRequest filter = new ProductFilterRequest(name, category, mark);
      List<Product> products = service.findBySpecification(filter);
      List<ProductDetails> productDetails = mapper.ProductListToProductDetailList(products);
      return ResponseEntity.ok(productDetails);
   }

   //====================Create====================//

   @ApiOperation("create a product")
   @PreAuthorize(ADMIN)
   @PostMapping(path = "/create")
   public ResponseEntity<Void> createProduct(@RequestBody @Valid ProductCreateRequest request) {
      long id = service.create(mapper.CreateProductToProduct(request));
      URI location = ServletUriComponentsBuilder.fromCurrentRequest()
         .path("{id}").buildAndExpand(id).toUri();
      return ResponseEntity.created(location).build();
   }

   //====================Update====================//

   @ApiOperation("update product data")
   @PreAuthorize(ADMIN)
   @PatchMapping(path = "/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void updateProduct(@PathVariable("id") @NotNull Long id,
                             @RequestBody @Valid ProductUpdateRequest request) {
      service.update(id, mapper.UpdateProductToProduct(request));
   }

   @ApiOperation("update is available product")
   @PreAuthorize(ADMIN)
   @PatchMapping(path = "/available/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void updateAvailable(@PathVariable("id") @NotNull Long id) {
      service.updateAvailable(id);
   }

   //====================Deletes====================//

   @ApiOperation("delete a product")
   @PreAuthorize(ADMIN)
   @DeleteMapping(path = "/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void deleteProduct(@PathVariable @NotBlank @Valid Long id) {
      service.delete(id);
   }
}
