package nl.retail.store.grocerystore.resource;

import lombok.extern.slf4j.Slf4j;
import nl.retail.store.grocerystore.model.Product;
import nl.retail.store.grocerystore.service.GroceryService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/grocery")
public class GroceryProductResource {

    private GroceryService groceryService;

    @Autowired
    public GroceryProductResource (GroceryService groceryService) {
        this.groceryService = groceryService;
    }

    @GetMapping(value = "/products", produces = "application/json")
    public ResponseEntity<List<Product>> getProducts(@RequestParam(value = "name", required = false) String name,
                                                     @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
                                                     @RequestParam(value = "pageSize", defaultValue = "20", required = false) int pageSize) {

        log.debug("incoming request on endpoint \"/products\" with name {} pageNo {} pageSize {}", name, pageNo, pageSize);
        List<Product> products;
        if (Strings.isBlank(name))
            products = groceryService.getProducts(pageNo, pageSize);
        else
            products = groceryService.getProductsByName(name);

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(value = "/products/overview", produces = "application/json")
    public ResponseEntity<List<Product>> getProductsOverview(@RequestParam(value = "name", required = false) String name) {
        log.debug("incoming request on endpoint \"/products/overview\" with name {}", name);
        List<Product> products;
        if (Strings.isBlank(name))
            products =  groceryService.getUniqueProductsWithMaxPrice();
        else
            products =  groceryService.getProductsByName(name);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}
