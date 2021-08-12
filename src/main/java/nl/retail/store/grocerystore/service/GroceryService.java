package nl.retail.store.grocerystore.service;

import nl.retail.store.grocerystore.entity.ProductEntity;
import nl.retail.store.grocerystore.exception.ProductNotFoundException;
import nl.retail.store.grocerystore.mapper.Mapper;
import nl.retail.store.grocerystore.model.Product;
import nl.retail.store.grocerystore.repository.ProductsRepo;
import nl.retail.store.grocerystore.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GroceryService {

    private ProductsRepo productsRepo;

    @Autowired
    public GroceryService (ProductsRepo productsRepo) {
        this.productsRepo = productsRepo;
    }

    public List<ProductEntity> save(List<ProductEntity> products) {
        return productsRepo.saveAll(products);
    }

    public List<Product> getProducts(int page, int size) {
        List<Product> products = new ArrayList<>();
        Pageable pageable = PageRequest.of(page, size);
        productsRepo.findAll(pageable).getContent()
                .stream().forEach(productEntity ->
                    products.add(Mapper.toProduct(productEntity)
                ));
        return products;
    }

    public List<Product> getProductsByName(String name) {
        List<Product> products = new ArrayList<>();
        productsRepo.findAllByName(name)
                .stream().forEach(productEntity ->
                products.add(Mapper.toProduct(productEntity)
                ));;
        if (products.isEmpty())
            throw new ProductNotFoundException("Product not found: " + name);
        return products;
    }

    public List<Product> getUniqueProductsWithMaxPrice() {
        List<Product> products = new ArrayList<>();
        Map<String, Optional<ProductEntity>> eachProductWithMaxPrice = productsRepo.findAll().stream()
                .collect(Collectors.groupingBy(ProductEntity::getName, Collectors.maxBy(Comparator.comparingDouble(ProductEntity::getPrice))));
        eachProductWithMaxPrice.forEach((s, productEntity) -> products.add(Mapper.toProduct(productEntity.get())));
        return products;
    }
}
