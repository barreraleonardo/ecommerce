package com.nocountry.ecommerce.ports.input.rs.mapper;

import com.nocountry.ecommerce.domain.model.Product;
import com.nocountry.ecommerce.ports.input.rs.request.ProductCreateRequest;
import com.nocountry.ecommerce.ports.input.rs.request.ProductUpdateRequest;
import com.nocountry.ecommerce.ports.input.rs.response.ProductDetails;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "mark", target = "mark.id")
    @Mapping(source = "category", target = "category.id")
    Product CreateProductToProduct(ProductCreateRequest request);

    Product UpdateProductToProduct(ProductUpdateRequest request);

    ProductDetails ProductToProductDetails(Product product);

    List<ProductDetails> ProductListToProductDetailList(List<Product> list);

    default Page<ProductDetails> pageProductToPageProductDetails(Page<Product> page) {
        return page.map(this::ProductToProductDetails);
    }

}
