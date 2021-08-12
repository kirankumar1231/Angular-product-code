package nl.retail.store.grocerystore.resource;

import nl.retail.store.grocerystore.constants.ProductType;
import nl.retail.store.grocerystore.model.Product;
import nl.retail.store.grocerystore.service.GroceryService;
import nl.retail.store.grocerystore.util.DateUtil;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class GroceryProductResourceTest {
    @InjectMocks
    GroceryProductResource groceryProductResource;
    @Mock
    GroceryService groceryService;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private List<Product> uniqueProducts;
    private List<Product> carrots;

    @Before
    public void setUp() {
        uniqueProducts = new ArrayList<>();
        carrots = new ArrayList<>();
        uniqueProducts.add(new Product(2, "20210101", "Apple", ProductType.FRUIT, 11, DateUtil.toLocalDate("2021-01-01")));
        uniqueProducts.add(new Product(5, "20210101", "Carrot", ProductType.VEGETABLE, 12, DateUtil.toLocalDate("2021-01-01")));

        carrots.add(new Product(3, "20190101", "Carrot", ProductType.VEGETABLE, 10, DateUtil.toLocalDate("2019-01-01")));
        carrots.add(new Product(4, "20200101", "Carrot", ProductType.VEGETABLE, 11, DateUtil.toLocalDate("2020-01-01")));
        carrots.add(new Product(5, "20210101", "Carrot", ProductType.VEGETABLE, 12, DateUtil.toLocalDate("2021-01-01")));
    }

    @Test
    public void getProductsOverviewTest_overview() {
        when(groceryService.getUniqueProductsWithMaxPrice()).thenReturn(uniqueProducts);
        ResponseEntity<List<Product>> responseEntity = ResponseEntity.status(HttpStatus.OK)
                .body(uniqueProducts);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(uniqueProducts.size(), responseEntity.getBody().size());
    }

    @Test
    public void getProductsOverviewTest_trend() {
        when(groceryService.getProductsByName("Carrot")).thenReturn(carrots);
        ResponseEntity<List<Product>> responseEntity = ResponseEntity.status(HttpStatus.OK)
                .body(carrots);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(carrots.size(), responseEntity.getBody().size());
    }
}
