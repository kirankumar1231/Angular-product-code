package nl.retail.store.grocerystore.service;

import nl.retail.store.grocerystore.constants.ProductType;
import nl.retail.store.grocerystore.entity.ProductEntity;
import nl.retail.store.grocerystore.exception.ProductNotFoundException;
import nl.retail.store.grocerystore.model.Product;
import nl.retail.store.grocerystore.repository.ProductsRepo;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GroceryServiceTest {
    @InjectMocks
    GroceryService groceryService;
    @Mock
    ProductsRepo productsRepo;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    //private List<Product> products;
    private List<ProductEntity> productEntities;

    @Before
    public void setUp() {
        productEntities = new ArrayList<>();
        productEntities.add(new ProductEntity(1, "20200101", "Apple", ProductType.FRUIT, 10, "2020-01-01"));
        productEntities.add(new ProductEntity(2, "20210101", "Apple", ProductType.FRUIT, 11, "2021-01-01"));
        productEntities.add(new ProductEntity(3, "20190101", "Carrot", ProductType.VEGETABLE, 10, "2019-01-01"));
        productEntities.add(new ProductEntity(4, "20200101", "Carrot", ProductType.VEGETABLE, 11, "2020-01-01"));
        productEntities.add(new ProductEntity(5, "20210101", "Carrot", ProductType.VEGETABLE, 12, "2021-01-01"));
    }

    @Test
    public void saveTest() {
        when(productsRepo.saveAll(productEntities)).thenReturn(productEntities);
        List<ProductEntity> productList = groceryService.save(productEntities);
        assertEquals(productEntities.size(), productList.size());
    }

    @Test
    public void getProductsTest() {
        int page = 1, size = 20;
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductEntity> productPage = new PageImpl(productEntities);

        when(productsRepo.findAll(pageable)).thenReturn(productPage);

        List<Product> productList = groceryService.getProducts(page, size);
        assertEquals(productEntities.size(), productList.size());
    }

    @Test
    public void getProductsByNameTest_OK() {
        List<ProductEntity> apples = new ArrayList<>();
        apples.add(productEntities.get(0));
        apples.add(productEntities.get(1));
        when(productsRepo.findAllByName("Apple")).thenReturn(apples);

        List<Product> appleList = groceryService.getProductsByName("Apple");
        assertEquals(apples.size(), appleList.size());
        assertEquals(apples.get(1).getName(), appleList.get(1).getName());
        assertEquals(apples.get(1).getPrice(), appleList.get(1).getPrice(), 0);
    }

    @Test
    public void getProductsByNameTest_NotFound() {
        when(productsRepo.findAllByName("Mango")).thenReturn(new ArrayList<>());
        exception.expect(ProductNotFoundException.class);
        groceryService.getProductsByName("Mango");
    }

    @Test
    public void getUniqueProductsWithMaxPriceTest_OK() {
        when(productsRepo.findAll()).thenReturn(productEntities);
        List<Product> uniqueProducts = groceryService.getUniqueProductsWithMaxPrice();
        assertEquals(uniqueProducts.size(), 2);
        assertEquals(uniqueProducts.get(1).getPrice(), 11, 0);
        assertEquals(uniqueProducts.get(0).getPrice(), 12, 0);
    }

    @Test
    public void getUniqueProductsWithMaxPriceTest_WithNoProduct() {
        when(productsRepo.findAll()).thenReturn(new ArrayList<>());
        List<Product> uniqueProducts = groceryService.getUniqueProductsWithMaxPrice();
        assertEquals(uniqueProducts.size(), 0);
    }
}
