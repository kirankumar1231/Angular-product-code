package nl.retail.store.grocerystore.repository;

import nl.retail.store.grocerystore.constants.ProductType;
import nl.retail.store.grocerystore.entity.ProductEntity;
import nl.retail.store.grocerystore.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductsRepoTest {
    @Mock
    ProductsRepo productsRepo;

    private List<ProductEntity> products;

    @Before
    public void setUp() {
        products = new ArrayList<>();
        products.add(new ProductEntity(6, "20200101", "Apple", ProductType.FRUIT, 10, "2020-01-01"));
        products.add(new ProductEntity(7, "20210101", "Apple", ProductType.FRUIT, 11, "2021-01-01"));
        products.add(new ProductEntity(8, "20190101", "Carrot", ProductType.VEGETABLE, 10, "2019-01-01"));
        products.add(new ProductEntity(9, "20200101", "Carrot", ProductType.VEGETABLE, 11, "2020-01-01"));
        products.add(new ProductEntity(10, "20210101", "Carrot", ProductType.VEGETABLE, 12, "2021-01-01"));
    }

    @Test
    public void saveTest() {
        when(productsRepo.saveAll(products)).thenReturn(products);
        List<ProductEntity> productList = productsRepo.saveAll(products);
        assertEquals(products.size(), productList.size());
    }
}
