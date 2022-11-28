package com.nocountry.ecommerce.domain.usecase;

@FunctionalInterface
public interface ActiveAvailable {
    void updateAvailable(Long id);
}
